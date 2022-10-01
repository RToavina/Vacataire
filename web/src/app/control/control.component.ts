import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {SignupComponent} from "../auth/signup/signup.component";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ProfesseurService} from "../services/professeur.service";
import {Professeur} from "../model/professeur";
import {Matiere} from "../model/matiere";
import {MatiereService} from "../services/matiere.service";
import {combineLatest, Observable, Subject} from "rxjs";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-control',
  templateUrl: './control.component.html',
  styleUrls: ['./control.component.css']
})
export class ControlComponent implements OnInit {
  displayedColumns: string[] = ['username', 'nom_prenom', 'matieres', 'actions'];
  professeurs = [];
  selectedProfesseur: Professeur;
  form: FormGroup;
  matieres: Matiere[] = [];
  reload = new Subject<void>();

  constructor(private dialog: MatDialog,
              private fb: FormBuilder,
              private authService: AuthService,
              private professeurService: ProfesseurService,
              private matiereService: MatiereService) {
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      nom: null,
      prenom: null,
      email: null,
      numeroTel: null,
      identifiant: {value: null, disabled: true},
      tauxHoraire: null,
      matieres: null,
    })
    this.init();
    this.reload.subscribe(() => {
      this.init();
    })
  }

  init() {
    combineLatest([this.professeurService.getAllProfesseur(), this.matiereService.getMatieres()])
      .subscribe(([p, m]) => {
        this.professeurs = p;
        this.matieres = m;
        if (p?.length > 0) {
          this.selectedProfesseur = p[0];
          this.fillForm(this.selectedProfesseur);
        }
      });
  }

  openDialog() {
    const diaglogRef = this.dialog.open(SignupComponent, {
      width: '800px',
      data: {matieres: this.matieres}
    });

    diaglogRef.afterClosed().subscribe(result => {
      if (result != null) {
      this.authService.signup({
          email: result.email,
          matieres: result.matieres?.map(x => x.nomMatiere),
          nom: result.nom,
          password: result.prenom,
          phoneNumber: result.phoneNumber,
          prenom: result.prenom,
          username: result.username,
          tauxHoraire: result.tauxHoraire
        }).subscribe( ()=> this.reload.next());
      }
    });
  }

  fillForm(prof: Professeur) {
    this.selectedProfesseur = prof;
    this.form = this.fb.group({
      nom: prof.nom,
      prenom: prof.prenom,
      email: prof.email,
      numeroTel: prof.phoneNumber,
      identifiant: {value: prof.username, disabled: true},
      tauxHoraire: prof.tauxHoraire,
      matieres: this.fb.control(prof.matieres),
    })
  }

  compareWith(m1: any, m2: any): boolean {
    return m1 && m2 && m1.id === m2.id;
  }

  displayMatieres(matieres: Matiere[]) {
    return matieres.map(x=>x.nomMatiere).join(', ');
  }
}
