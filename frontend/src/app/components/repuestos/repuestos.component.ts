import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { J3MotorsService } from '../../services/j3motors.service';
import { Repuesto } from '../../models/models';

@Component({
  selector: 'app-repuestos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './repuestos.component.html',
  styles: [`
    .repuesto-img {
      height: 200px;
      object-fit: cover;
      border-radius: 6px 6px 0 0;
      filter: brightness(0.9);
      transition: filter 0.3s;
    }
    .card:hover .repuesto-img {
      filter: brightness(1.05);
    }
    .badge-stock {
      position: absolute;
      top: 12px;
      right: 12px;
    }
  `]
})
export class RepuestosComponent implements OnInit {
  repuestos: Repuesto[] = [];
  cargando = true;

  constructor(private j3Service: J3MotorsService) {}

  ngOnInit(): void {
    this.j3Service.getRepuestos().subscribe({
      next: (data) => {
        this.repuestos = data;
        this.cargando = false;
      },
      error: () => {
        this.cargando = false;
      }
    });
  }
}
