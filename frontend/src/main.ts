import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes, withInMemoryScrolling } from '@angular/router'; // 👈 1. IMPORTANTE: Importamos con la sintaxis correcta
import { provideHttpClient } from '@angular/common/http';
import { AppComponent } from './app/app.component';
import { LoginComponent } from './app/components/login/login.component';
import { RegisterComponent } from './app/components/register/register.component';
import { InicioComponent } from './app/components/inicio/inicio.component';
import { AdminDashboardComponent } from './app/components/admin-dashboard/admin-dashboard.component';
import { CitasComponent } from './app/components/citas/citas.component';
import { RepuestosComponent } from './app/components/repuestos/repuestos.component';

const routes: Routes = [
  { path: 'inicio', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: AdminDashboardComponent },
  { path: 'citas', component: CitasComponent },       
  { path: 'repuestos', component: RepuestosComponent }, 
  { path: '', redirectTo: '/inicio', pathMatch: 'full' }
];

bootstrapApplication(AppComponent, {
  providers: [
    // 👇 2. MODIFICADO: Esta es la forma estándar e infalible para configurar el scroll en tu Angular
    provideRouter(
      routes, 
      withInMemoryScrolling({
        anchorScrolling: 'enabled',
        scrollPositionRestoration: 'enabled'
      })
    ), 
    provideHttpClient()
  ]
}).catch(err => console.error(err));
