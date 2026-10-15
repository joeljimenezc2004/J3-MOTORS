import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { J3MotorsService } from '../../services/j3motors.service';
import { AuthService } from '../../services/auth.service'; 
import { Cita, Servicio } from '../../models/models';

@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './citas.component.html',
  styles: [`
    .form-control, .form-select {
      background-color: #12141a;
      border: 1px solid #2d3342;
      color: #fff;
      padding: 12px 16px;
    }
    .form-control:focus, .form-select:focus {
      background-color: #161922;
      border-color: #ff5500;
      box-shadow: 0 0 0 0.25rem rgba(255, 85, 0, 0.25);
      color: #fff;
    }
    .form-label {
      font-size: 0.88rem;
      font-weight: 600;
      color: #c4c9d4;
    }
    .appointment-card {
      background: #181b22;
      border: 1px solid #2c3240;
      border-radius: 12px;
    }
  `]
})
export class CitasComponent implements OnInit {
  serviciosDisponibles: Servicio[] = [];
  citasRegistradas: Cita[] = [];
  esAdmin = false; 

  nuevaCita: Cita = {
    nombreCliente: '',
    telefono: '',
    email: '',
    modeloMoto: '',
    placa: '',
    tipoServicio: 'Otro Servicio / Diagnóstico General', // Valor por defecto seguro
    fechaCita: '',
    horaCita: '08:00 AM',
    observaciones: ''
  };

  enviando = false;
  mensajeExito = '';
  mensajeError = '';

  constructor(
    private j3Service: J3MotorsService,
    private authService: AuthService 
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.hasRole('ROLE_ADMIN');
    this.cargarServicios();
    this.cargarCitas();
  }

  cargarServicios(): void {
    this.j3Service.getServicios().subscribe({
      next: (data) => {
        this.serviciosDisponibles = data;
        // 👇 CORREGIDO: Leemos el nombre del primer elemento del array de forma segura
        if (data && data.length > 0) {
          this.nuevaCita.tipoServicio = data[0].nombre; 
        }
      },
      error: (err) => console.error("Error al cargar servicios:", err)
    });
  }

  cargarCitas(): void {
    this.j3Service.getCitas().subscribe({
      next: (data) => {
        // 👇 Sincroniza los datos reales del backend con la lista del HTML
        this.citasRegistradas = data; 
      },
      error: (err) => {
        console.error("Error al conectar con las citas de Spring Boot:", err);
      }
    });
  }

  agendarCita(): void {
    if (!this.nuevaCita.nombreCliente || !this.nuevaCita.telefono || !this.nuevaCita.modeloMoto || !this.nuevaCita.fechaCita) {
      this.mensajeError = 'Por favor completa todos los campos requeridos (*).';
      return;
    }

    this.enviando = true;
    this.mensajeError = '';
    this.mensajeExito = '';

    this.j3Service.registrarCita(this.nuevaCita).subscribe({
      next: (citaCreada) => {
        this.enviando = false;
        this.mensajeExito = `¡Cita agendada con éxito! Código de turno #${citaCreada.id || 'J3-OK'}.`;
        
        // Añadimos la nueva cita al inicio de la lista para ver el cambio inmediato
        this.citasRegistradas.unshift(citaCreada);
        
        // Reset del formulario
        this.nuevaCita = {
          nombreCliente: '',
          telefono: '',
          email: '',
          modeloMoto: '',
          placa: '',
          tipoServicio: this.serviciosDisponibles.length > 0 ? this.serviciosDisponibles[0].nombre : 'Otro Servicio / Diagnóstico General',
          fechaCita: '',
          horaCita: '08:00 AM',
          observaciones: ''
        };
      },
      error: () => {
        this.enviando = false;
        this.mensajeError = 'Ocurrió un error al agendar la cita. Inténtalo de nuevo.';
      }
    });
  }
}
