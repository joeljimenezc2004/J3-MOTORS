import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-hero',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './hero.component.html',
  styles: [`
    .hero-section {
      min-height: 85vh;
      background: linear-gradient(135deg, rgba(15, 17, 21, 0.93) 0%, rgba(24, 27, 34, 0.85) 100%),
                  url('https://images.unsplash.com/photo-1558981806-ec527fa84c39?auto=format&fit=crop&w=1920&q=80') center/cover no-repeat;
      display: flex;
      align-items: center;
      position: relative;
    }
    .hero-badge {
      display: inline-block;
      padding: 6px 16px;
      border: 1px solid #ff5500;
      border-radius: 50px;
      font-size: 0.85rem;
      letter-spacing: 1px;
      color: #ff5500;
      background: rgba(255, 85, 0, 0.1);
    }
    .highlight-number {
      font-size: 2.2rem;
      font-weight: 800;
      color: #ff5500;
      line-height: 1;
    }
  `]
})
export class HeroComponent {}
