import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Client} from '../models/client';
import {Enchere} from '../models/enchere';

@Injectable({
  providedIn: 'root',
})
export class EncheresService {
  baseUrl: string = 'http://localhost:8080/api/encheres';

  constructor(private http: HttpClient) {}

  // Récupérer les enchères actives
  getAll(endPoint: string): Observable<Enchere[]> {
    return this.http.get<Enchere[]>(this.baseUrl + endPoint);
  }

  // Récupérer une enchère par son id
  getById(endPoint: string, idEnchere: string): Observable<Enchere> {
    return this.http.get<Enchere>(this.baseUrl + endPoint + '/' + idEnchere);
  }
}
