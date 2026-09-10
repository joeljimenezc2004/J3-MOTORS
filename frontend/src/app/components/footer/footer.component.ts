import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './footer.component.html',
  styles: [`
    footer {
      background-color: #0b0c0e;
      border-top: 1px solid #222631;
    }
  `]
})
export class FooterComponent {
  currentYear = new Date().getFullYear();
}
