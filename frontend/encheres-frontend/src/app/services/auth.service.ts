import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Observable, throwError} from 'rxjs';
import {ApiConfigService} from './api-config.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'access_token';

  constructor(
    private http: HttpClient,
    private apiConfig: ApiConfigService
  ) {}

  login(username: string, password: string): Observable<{ token: string }> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.authEndpoints.login}`;
    return this.http.post<{ token: string }>(url, { username, password })
      .pipe(
        tap(res => localStorage.setItem(this.tokenKey, res.token)),
        catchError(error => throwError(() => error))
      );
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
