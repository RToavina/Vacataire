import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Matiere} from "../model/matiere";

@Injectable({
  providedIn: 'root'
})
export class MatiereService {

  url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getMatieres() {
    return this.http.get<Matiere[]>(this.url+"/matieres");
  }
}
