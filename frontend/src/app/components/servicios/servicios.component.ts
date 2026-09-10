import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { J3MotorsService } from '../../services/j3motors.service';
import { Servicio } from '../../models/models';

@Component({
  selector: 'app-servicios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './servicios.component.html',
  styles: [`
    .service-icon-box {
      width: 60px;
      height: 60px;
      border-radius: 12px;
      background: rgba(255, 85, 0, 0.15);
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 1.8rem;
      color: #ff5500;
      margin-bottom: 1.25rem;
      transition: all 0.3s ease;
    }
    .card:hover .service-icon-box {
      background: #ff5500;
      color: #ffffff;
      transform: rotate(5deg);
    }
  `]
})
export class ServiciosComponent implements OnInit {
  servicios: Servicio[] = [];
  cargando = true;

  constructor(private j3Service: J3MotorsService) {}

  ngOnInit(): void {
    this.j3Service.getServicios().subscribe({
      next: (data) => {
        this.servicios = data;
        this.cargando = false;
      },
      error: () => {
        this.cargando = false;
      }
    });
  }
}
