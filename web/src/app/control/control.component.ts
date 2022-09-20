import { Component, OnInit } from '@angular/core';
import {Matiere} from "../model/matiere";
import {Professeur} from "../model/professeur";

const PC = {nom:"PC",id:1};
const SVT = {nom:"SVT",id:2};
const MATH = {nom:"Math",id:3}

const ELEMENT_DATA: Professeur[] = [
  {username: 'svt.p1' , nom: 'Professeur1', prenom: 'Professeur1', matieres: [PC,SVT]},
  {username: 'math.p2',nom: 'Professeur2',prenom: 'Professeur2', matieres: [MATH]},
  {username: 'svt.p2',nom: 'Professeur3',prenom: 'Professeur3', matieres: [SVT]},
  {username: 'svt.p3',nom: 'Professeur4',prenom: 'Professeur4', matieres: [MATH,SVT]},
  {username: 'pc.p1',nom: 'Professeur5',prenom: 'Professeur5', matieres: [PC]},
];

@Component({
  selector: 'app-control',
  templateUrl: './control.component.html',
  styleUrls: ['./control.component.css']
})
export class ControlComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  displayedColumns: string[] = ['username','nom_prenom','matieres','actions'];
  dataSource = ELEMENT_DATA;

}
