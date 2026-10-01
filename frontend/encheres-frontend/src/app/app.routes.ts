import {Routes} from '@angular/router';
import {AuthGuard} from './guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  {path: 'login', loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)},
  // { path: 'login', component: LoginComponent },
  {path: 'home', loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)},
  // { path: 'home', component: LoginComponent },
  {path: 'encheres', loadComponent: () => import('./components/encheres/encheres.component').then(m => m.EncheresComponent), canActivate:[AuthGuard] },
  // { path: 'encheres', component: EncheresComponent, canActivate:[AuthGuard] },

  {path: 'detailEnchere/:idEnchere', loadComponent: () => import('./components/detail-enchere/detail-enchere.component').then(m => m.DetailEnchereComponent), canActivate:[AuthGuard] }
  //{path: 'detailEnchere/:idEnchere', component: DetailEnchereComponent, canActivate:[AuthGuard] },
];
