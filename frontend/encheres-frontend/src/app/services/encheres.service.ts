import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Enchere} from '../models/enchere';
import {OffreRequest} from '../models/offre-request';
import {ApiConfigService} from './api-config.service';

@Injectable({
  providedIn: 'root',
})
export class EncheresService {
  constructor(
    private http: HttpClient,
    private apiConfig: ApiConfigService
  ) {}

  getAll(): Observable<Enchere[]> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.encheresEndpoints.base}`;
    return this.http.get<Enchere[]>(url);
  }

  getById(idEnchere: string): Observable<Enchere> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.encheresEndpoints.base}/${idEnchere}`;
    return this.http.get<Enchere>(url);
  }

  deposerOffreManuelle(offre: OffreRequest): Observable<Enchere> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.encheresEndpoints.offres.manuelle}`;
    return this.http.post<Enchere>(url, offre);
  }

  deposerOffreAutomatique(offre: OffreRequest): Observable<Enchere> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.encheresEndpoints.offres.automatique}`;
    return this.http.post<Enchere>(url, offre);
  }
}
