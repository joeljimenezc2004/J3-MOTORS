import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { Servicio, Cita, Repuesto } from '../models/models';

@Injectable({
  providedIn: 'root'
})
export class J3MotorsService {
  private apiUrl = 'http://localhost:8080/api';

  // Datos mock de contingencia en caso de que el backend no esté iniciado
  private mockServicios: Servicio[] = [
    {
      id: 1,
      nombre: 'Mantenimiento General Preventivo',
      descripcion: 'Revisión completa de 25 puntos: ajuste de tornillería, lubricación de guayas, calibración de bujía y revisión de niveles.',
      precioAproximado: 35.0,
      duracionEstimada: '2 - 3 Horas',
      icono: 'bi-wrench-adjustable'
    },
    {
      id: 2,
      nombre: 'Sincronización y Diagnóstico Computarizado',
      descripcion: 'Escaneo electrónico del sistema EFI / Inyección electrónica, calibración de TPS y limpieza de inyector por ultrasonido.',
      precioAproximado: 45.0,
      duracionEstimada: '1.5 Horas',
      icono: 'bi-cpu'
    },
    {
      id: 3,
      nombre: 'Sistema de Frenos y Suspensión',
      descripcion: 'Cambio de líquido de frenos DOT4/DOT5.1, purgado, cambio de pastillas y mantenimiento de suspensión delantera.',
      precioAproximado: 28.0,
      duracionEstimada: '2 Horas',
      icono: 'bi-shield-check'
    },
    {
      id: 4,
      nombre: 'Reparación de Motor y Transmisión',
      descripcion: 'Ajuste de válvulas, cambio de discos de embrague/clutch, rectificación de cilindro y torquímetro según manual de taller.',
      precioAproximado: 120.0,
      duracionEstimada: '1 - 2 Días',
      icono: 'bi-gear-wide-connected'
    }
  ];

  private mockRepuestos: Repuesto[] = [
    {
      id: 1,
      nombre: 'Aceite Motul 7100 4T 10W-40 100% Sintético',
      categoria: 'Lubricantes',
      marca: 'Motul',
      precio: 19.5,
      stock: 24,
      imagenUrl: 'https://images.unsplash.com/photo-1596765851458-1f19f180702d?auto=format&fit=crop&w=400&q=80',
      compatibilidad: 'Universal 4 Tiempos (Alto Rendimiento)'
    },
    {
      id: 2,
      nombre: 'Pastillas de Freno Brembo Serie Oro',
      categoria: 'Frenos',
      marca: 'Brembo',
      precio: 48.0,
      stock: 15,
      imagenUrl: 'https://images.unsplash.com/photo-1619642751034-765dfdf7c58e?auto=format&fit=crop&w=400&q=80',
      compatibilidad: 'Yamaha MT-03/07, KTM 390'
    },
    {
      id: 3,
      nombre: 'Kit de Arrastre DID Cadena Dorada X-Ring',
      categoria: 'Transmisión',
      marca: 'DID',
      precio: 89.9,
      stock: 8,
      imagenUrl: 'https://images.unsplash.com/photo-1558981403-c5f9899a28bc?auto=format&fit=crop&w=400&q=80',
      compatibilidad: 'KTM Duke 200/250/390, Pulsar NS200'
    },
    {
      id: 4,
      nombre: 'Bujía Iridium IX NGK CR9EIX',
      categoria: 'Encendido',
      marca: 'NGK',
      precio: 14.0,
      stock: 32,
      imagenUrl: 'https://images.unsplash.com/photo-1486006920555-c77dce18193b?auto=format&fit=crop&w=400&q=80',
      compatibilidad: 'Universal Alto Rendimiento'
    }
  ];

  constructor(private http: HttpClient) {}

  getServicios(): Observable<Servicio[]> {
    return this.http.get<Servicio[]>(`${this.apiUrl}/servicios`).pipe(
      catchError(error => {
        console.warn('No se pudo conectar al Backend Spring Boot para /servicios. Usando datos locales de contingencia.', error);
        return of(this.mockServicios);
      })
    );
  }

  getRepuestos(): Observable<Repuesto[]> {
    return this.http.get<Repuesto[]>(`${this.apiUrl}/repuestos`).pipe(
      catchError(error => {
        console.warn('No se pudo conectar al Backend Spring Boot para /repuestos. Usando datos locales de contingencia.', error);
        return of(this.mockRepuestos);
      })
    );
  }

  getCitas(): Observable<Cita[]> {
    return this.http.get<Cita[]>(`${this.apiUrl}/citas`).pipe(
      catchError(error => {
        console.warn('No se pudo conectar al Backend Spring Boot para /citas.', error);
        return of([]);
      })
    );
  }

  registrarCita(cita: Cita): Observable<Cita> {
    return this.http.post<Cita>(`${this.apiUrl}/citas`, cita).pipe(
      catchError(error => {
        console.warn('Backend Spring Boot no disponible. Simulando registro exitoso de cita.', error);
        const citaConId = { ...cita, id: Math.floor(Math.random() * 1000) + 10, estado: 'CONFIRMADA' };
        return of(citaConId);
      })
    );
  }
}
