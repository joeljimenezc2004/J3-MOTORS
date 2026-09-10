import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navbar.component.html',
  styles: [`
    .navbar {
      background-color: rgba(15, 17, 21, 0.95);
      backdrop-filter: blur(10px);
      border-bottom: 2px solid #ff5500;
    }
    .nav-link {
      font-weight: 600;
      letter-spacing: 0.5px;
      transition: color 0.2s;
    }
    .nav-link:hover {
      color: #ff5500 !important;
    }
    .logo-badge {
      background: #ff5500;
      color: #000;
      font-weight: 800;
      padding: 2px 8px;
      border-radius: 4px;
    }
  `]
})
export class NavbarComponent {}
