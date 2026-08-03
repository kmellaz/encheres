import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../models/client';
import {ApiConfigService} from './api-config.service';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(
    private http: HttpClient,
    private apiConfig: ApiConfigService
  ) {}

  getAll(): Observable<Client[]> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.clientsEndpoints.base}`;
    return this.http.get<Client[]>(url);
  }

  getById(idClient: string): Observable<Client> {
    const url = `${this.apiConfig.baseUrl}${this.apiConfig.clientsEndpoints.base}/${idClient}`;
    return this.http.get<Client>(url);
  }
}
