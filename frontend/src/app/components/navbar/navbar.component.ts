import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './navbar.component.html'
  // 👈 Borramos la línea de styleUrls para que no busque ningún archivo externo
})
export class NavbarComponent implements OnInit {

  isLogged = false;
  nombreUsuario = '';
  esAdmin = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    // Escucha en tiempo real si el usuario inicia o cierra sesión
    this.authService.isLoggedIn().subscribe(status => {
      this.isLogged = status;
      if (status) {
        this.nombreUsuario = this.authService.getNombreUsuario() || '';
        this.esAdmin = this.authService.hasRole('ROLE_ADMIN'); // Verifica si tiene rol de administrador
      }
    });
  }

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']); // Te manda al login al salir
  }
}
