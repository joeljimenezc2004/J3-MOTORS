import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { J3MotorsService } from '../../services/j3motors.service';
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

  nuevaCita: Cita = {
    nombreCliente: '',
    telefono: '',
    email: '',
    modeloMoto: '',
    placa: '',
    tipoServicio: '',
    fechaCita: '',
    horaCita: '',
    observaciones: ''
  };

  enviando = false;
  mensajeExito = '';
  mensajeError = '';

  constructor(private j3Service: J3MotorsService) {}

  ngOnInit(): void {
    this.cargarServicios();
    this.cargarCitas();
  }

  cargarServicios(): void {
    this.j3Service.getServicios().subscribe({
      next: (data) => {
        this.serviciosDisponibles = data;
        if (data.length > 0) {
          this.nuevaCita.tipoServicio = data[0].nombre;
        }
      }
    });
  }

  cargarCitas(): void {
    this.j3Service.getCitas().subscribe({
      next: (data) => {
        this.citasRegistradas = data;
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
        this.mensajeExito = `¡Cita agendada con éxito! Código de turno #${citaCreada.id || 'J3-OK'}. Te contactaremos a ${citaCreada.telefono}.`;
        this.citasRegistradas.unshift(citaCreada);
        // Reset form
        this.nuevaCita = {
          nombreCliente: '',
          telefono: '',
          email: '',
          modeloMoto: '',
          placa: '',
          tipoServicio: this.serviciosDisponibles.length > 0 ? this.serviciosDisponibles[0].nombre : '',
          fechaCita: '',
          horaCita: '',
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
