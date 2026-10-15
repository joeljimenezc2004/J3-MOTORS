package com.j3motors.service;

import com.j3motors.dto.RegisterRequest;
import com.j3motors.model.Rol;
import com.j3motors.model.Usuario;
import com.j3motors.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarCliente(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(request.getUsername());
        nuevoUsuario.setPassword(request.getPassword()); // Contraseña temporal sin encriptar
        nuevoUsuario.setNombreCompleto(request.getNombreCompleto());
        nuevoUsuario.setEmail(request.getEmail());

        Set<Rol> rolesUsuario = new HashSet<>();

        // Si desde la web no se envían roles específicos, se le asigna ROLE_CLIENTE automáticamente
        if (request.getRoles() == null || request.getRoles().isEmpty()) {
            rolesUsuario.add(Rol.ROLE_CLIENTE);
        } else {
            for (String roleStr : request.getRoles()) {
                try {
                    rolesUsuario.add(Rol.valueOf(roleStr.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("El rol proporcionado no existe en el sistema: " + roleStr);
                }
            }
        }

        nuevoUsuario.setRoles(rolesUsuario);
        return usuarioRepository.save(nuevoUsuario);
    }

    public Optional<Usuario> validarLogin(String username, String password) {
        return usuarioRepository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password) && u.isActivo());
    }
}
