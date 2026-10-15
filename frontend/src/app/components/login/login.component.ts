import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData = { username: '', password: '' };
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.loginData).subscribe({
      next: () => {
        this.router.navigate(['/']); // Te redirige al inicio al entrar correctamente
      },
      error: (err) => {
        this.errorMessage = 'Nombre de usuario o contraseña incorrectos.';
        console.error(err);
      }
    });
  }
}
