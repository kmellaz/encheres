import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  baseUrl: string = 'http://localhost:8080/api/encheres/clients';

  constructor(private http: HttpClient) {}

  // Récupérer tous les éléments
  getAll(endPoint: string): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseUrl + endPoint);
  }
}
