import {Component, OnInit} from '@angular/core';
import {Professeur} from "../model/professeur";
import {LoginComponent} from "../auth/login/login.component";
import {MatDialog} from "@angular/material/dialog";
import {SignupComponent} from "../auth/signup/signup.component";

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

  constructor( private dialog:MatDialog) { }

  ngOnInit(): void {
  }

  displayedColumns: string[] = ['nom_prenom','matieres','actions'];
  dataSource = ELEMENT_DATA;

  openDialog() {
    const diaglogRef = this.dialog.open(SignupComponent,{
      width : '550px',
    });

    diaglogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
