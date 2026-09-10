export interface Servicio {
  id?: number;
  nombre: string;
  descripcion: string;
  precioAproximado: number;
  duracionEstimada: string;
  icono: string;
}

export interface Cita {
  id?: number;
  nombreCliente: string;
  telefono: string;
  email: string;
  modeloMoto: string;
  placa: string;
  tipoServicio: string;
  fechaCita: string;
  horaCita: string;
  observaciones: string;
  estado?: string;
  fechaCreacion?: string;
}

export interface Repuesto {
  id?: number;
  nombre: string;
  categoria: string;
  marca: string;
  precio: number;
  stock: number;
  imagenUrl: string;
  compatibilidad: string;
}
