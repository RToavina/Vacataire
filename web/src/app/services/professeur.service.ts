import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Professeur} from "../model/professeur";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProfesseurService{

  url = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  getProfesseur(username: string) {
    const param = new HttpParams().append("username", username);
    return this.http.get<Professeur>(this.url+"/professeur",{params: param});
  }

  getAllProfesseur() {
    return this.http.get<Professeur[]>(this.url+"/professeurs");
  }


}
