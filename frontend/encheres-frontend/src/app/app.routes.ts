import {Routes} from '@angular/router';
import {EncheresComponent} from './components/encheres/encheres.component';
import {DetailEnchereComponent} from './components/detail-enchere/detail-enchere.component';
import {LoginComponent} from './components/login/login.component';
import {AuthGuard} from './guards/auth-guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: LoginComponent },
  { path: 'encheres', component: EncheresComponent, canActivate:[AuthGuard] },
  {path: 'detailEnchere/:idEnchere', component: DetailEnchereComponent, canActivate:[AuthGuard] }

];
