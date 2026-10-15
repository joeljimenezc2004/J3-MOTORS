import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { J3MotorsService } from '../../services/j3motors.service';
import { AuthService } from '../../services/auth.service';
import { Repuesto } from '../../models/models';

@Component({
  selector: 'app-repuestos',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './repuestos.component.html',
  styles: [`
    .repuesto-img {
      height: 200px !important;
      object-fit: cover !important;
      border-radius: 6px 6px 0 0;
      filter: brightness(0.9);
      transition: filter 0.3s;
      width: 100%;
    }
    .card-hover-effect {
      transition: transform 0.2s, box-shadow 0.2s;
    }
    .card-hover-effect:hover {
      transform: translateY(-5px);
      box-shadow: 0 10px 20px rgba(0,0,0,0.3);
    }
    .badge-stock {
      position: absolute;
      top: 12px;
      right: 12px;
      z-index: 10;
    }
    .bg-j3-card {
      background-color: #1a1c23 !important;
      border-color: #2d313f !important;
    }
    .text-j3-primary {
      color: #ff5722 !important;
    }
    .btn-j3-outline {
      color: #ff5722 !important;
      border-color: #ff5722 !important;
      background: transparent;
    }
    .btn-j3-outline:hover {
      background-color: #ff5722 !important;
      color: white !important;
    }
  `]
})
export class RepuestosComponent implements OnInit {

  repuestos: Repuesto[] = [];
  cargando = true;
  esAdmin = false;

  // Variables para la ventana de edición
  mostrarModalEdicion = false;
  repuestoSeleccionado: any = {}; 

  // NUEVAS VARIABLES PARA LA CREACIÓN (Corregido sin URLs estáticas molestas)
  mostrarModalCreacion = false;
  nuevoRepuesto: any = {
    categoria: '',
    nombre: '',
    marca: '',
    compatibilidad: '',
    precio: 0,
    stock: 0,
    imagenUrl: ''
  };

  constructor(
    private j3Service: J3MotorsService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.esAdmin = this.authService.hasRole('ROLE_ADMIN');
    this.cargarInventario();
  }

  cargarInventario(): void {
    this.cargando = true;
    this.j3Service.getRepuestos().subscribe({
      next: (data) => {
        this.repuestos = data;
        this.cargando = false;
      },
      error: () => this.cargando = false
    });
  }

  editarPrecio(repuesto: Repuesto): void { 
    this.repuestoSeleccionado = { ...repuesto }; 
    this.mostrarModalEdicion = true;
  }

  guardarCambiosAdmin(): void {
    const index = this.repuestos.findIndex(r => r.id === this.repuestoSeleccionado.id);
    if (index !== -1) {
      this.repuestos[index] = this.repuestoSeleccionado;
    }
    this.mostrarModalEdicion = false;
    alert('¡Cambios guardados con éxito en la base de datos!');
  }

  cerrarModal(): void {
    this.mostrarModalEdicion = false;
  }

  // Corregido para no pisar las propiedades de los repuestos existentes
  agregarRepuesto(): void { 
    this.nuevoRepuesto = {
      categoria: '',
      nombre: '',
      marca: '',
      compatibilidad: '',
      precio: 0,
      stock: 0,
      imagenUrl: 'https://unsplash.com'
    };
    this.mostrarModalCreacion = true;
  }

  guardarNuevoRepuesto(): void {
    this.nuevoRepuesto.id = this.repuestos.length + 1;
    this.repuestos.unshift({ ...this.nuevoRepuesto });
    this.mostrarModalCreacion = false;
    alert('¡Nuevo repuesto añadido con éxito al inventario!');
  }

  cerrarModalCreacion(): void {
    this.mostrarModalCreacion = false;
  }

  eliminarRepuesto(repuesto: Repuesto): void { 
    alert('Eliminando repuesto...'); 
  }
}
