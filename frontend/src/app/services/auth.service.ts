import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private loggedIn = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) { }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  login(loginData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginData).pipe(
      tap(res => {
        // Guardamos los datos exactos que devuelve tu AuthResponse en el Backend
        localStorage.setItem('token', res.token);
        localStorage.setItem('username', res.username);
        localStorage.setItem('nombreCompleto', res.nombreCompleto);
        localStorage.setItem('roles', JSON.stringify(res.roles));
        this.loggedIn.next(true);
      })
    );
  }

  register(registerData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, registerData);
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  hasRole(role: string): boolean {
    const rolesRaw = localStorage.getItem('roles');
    if (!rolesRaw) return false;
    const roles: string[] = JSON.parse(rolesRaw);
    return roles.includes(role);
  }

  getNombreUsuario(): string | null {
    return localStorage.getItem('nombreCompleto');
  }

  logout(): void {
    localStorage.clear();
    this.loggedIn.next(false);
  }
}
