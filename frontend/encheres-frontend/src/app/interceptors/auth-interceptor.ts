import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpErrorResponse} from '@angular/common/http';
import {AuthService} from '../services/auth.service';
import {catchError} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {Router} from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.auth.getToken();
    if (token) {
      req = req.clone({setHeaders: {Authorization: `Bearer ${token}`}});
    }
    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {

        // On ne traite pas le login ici
        if (req.url.endsWith('api/auth/login')) {
          return throwError(() => err);
        }

        // Ressources protégées
        if (err.status === 401 || err.status === 403) {
          // JWT absent ou expiré
          // redirection vers login
          this.router.navigate(['/login']);
        }

        return throwError(() => err);
      })
    );
  }
}

