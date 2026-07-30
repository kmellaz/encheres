import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly baseUrl: string = 'http://localhost:8080/api/auth';
  private tokenKey = 'access_token';

  constructor(private http: HttpClient) {}

  login(endPoint: string, username: string, password: string):Observable<{ token: string }> {
    return this.http.post<{ token: string }>(this.baseUrl + endPoint, { username, password })
      .pipe(
        tap(res => localStorage.setItem(this.tokenKey, res.token)),
        catchError(error => {
          console.error('Erreur de connexion', error);
          return throwError(() => error);
        })
      );
  }

  logout() {
    localStorage.removeItem(this.tokenKey);

  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
    }

  isAuthenticated(): boolean {
    return !!this.getToken();
    }
}
