import {Routes} from '@angular/router';
import {ClientsComponent} from './components/clients/clients.component';
import {EncheresComponent} from './components/encheres/encheres.component';
import {DetailEnchereComponent} from './components/detail-enchere/detail-enchere.component';

export const routes: Routes = [
  { path: 'home', component: ClientsComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'encheres', component: EncheresComponent },
  {path: 'detailEnchere/:idEnchere', component: DetailEnchereComponent },

];
