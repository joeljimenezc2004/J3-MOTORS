import { Component } from '@angular/core';
import { HeroComponent } from '../hero/hero.component';
import { ServiciosComponent } from '../servicios/servicios.component';
import { RepuestosComponent } from '../repuestos/repuestos.component';
import { CitasComponent } from '../citas/citas.component';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [HeroComponent, ServiciosComponent, RepuestosComponent, CitasComponent],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent {}
