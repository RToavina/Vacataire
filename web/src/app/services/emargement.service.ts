import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Emargement, EmargementRequest} from "../model/emargement";

@Injectable({
  providedIn: 'root'
})
export class EmargementService {

  url = environment.apiUrl;

  constructor(private http: HttpClient) { }


  emarger(emargement: EmargementRequest) {
    return this.http.put<Emargement>(this.url+'/emargement',emargement);
  }
}
