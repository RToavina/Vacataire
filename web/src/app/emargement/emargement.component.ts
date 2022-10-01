import {Component, Inject, OnInit} from '@angular/core';
import {DialogRef} from "@angular/cdk/dialog";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Professeur} from "../model/professeur";
import {Emargement} from "../model/emargement";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Matiere} from "../model/matiere";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-emargement',
  templateUrl: './emargement.component.html',
  styleUrls: ['./emargement.component.css']
})
export class EmargementComponent implements OnInit {

  form: FormGroup;

  matieres: Matiere[] = [];
  emargement: Emargement;

  constructor(@Inject(MAT_DIALOG_DATA) public data: { professeur: Professeur, emargement: Emargement },
              private dialogRef: MatDialogRef<EmargementComponent>,
              private datePipe: DatePipe,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.matieres = this.data.professeur.matieres;
    this.emargement = this.data.emargement;
    this.initForm();
  }

  initForm() {
    this.form = this.fb.group({
      matiere: [{value: this.emargement?.matiere, disabled: this.emargement != null}, Validators.required],
      date: [{value: this.emargement?.date, disabled: this.emargement != null}, Validators.required],
      debut: [this.emargement?.debut, Validators.required],
      fin: [this.emargement?.fin, Validators.required],
    })
  }

  getRecord() {
    const date = new Date(this.form.get('date').value);
    return {
      id: this.emargement?.id,
      matiere: this.form.get('matiere').value,
      date: this.datePipe.transform(date, 'dd/MM/yyyy'),
      debut: this.form.get('debut').value,
      fin: this.form.get('fin').value,
    }
  }

  close() {
    if (this.form.valid) {
      this.dialogRef.close(this.getRecord());
    }
  }

  compareWith(m1: any, m2: any): boolean {
    return m1 && m2 && m1.id === m2.id;
  }
}
