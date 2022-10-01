import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Professeur} from "../model/professeur";
import {Emargement} from "../model/emargement";
import {FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {Matiere} from "../model/matiere";
import {DatePipe} from "@angular/common";
import {combineLatest} from "rxjs";

const MyAwesomeRangeValidator: ValidatorFn = (fg: FormGroup) => {
  const dateValue = fg.get('date');
  const startValue = fg.get('debut');
  const endValue = fg.get('fin');

  const start = new Date(dateValue.value+'T'+startValue.value).getTime();
  const end = new Date(dateValue.value+'T'+endValue.value).getTime();

  if (start !== null && end !== null && start < end){
    return null;
  }
  startValue.setErrors({range: true});
  endValue.setErrors({range: true});
  return {range: true};
};

@Component({
  selector: 'app-emargement',
  templateUrl: './emargement.component.html',
  styleUrls: ['./emargement.component.css']
})
export class EmargementComponent implements OnInit {

  form: FormGroup;

  matieres: Matiere[] = [];
  emargement: Emargement;
  duree = 0;

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

    this.form.setValidators(MyAwesomeRangeValidator);

    combineLatest([this.form.get('date').valueChanges, this.form.get('debut').valueChanges, this.form.get('fin').valueChanges])
      .subscribe(([date,debut,fin]) => {
        const start = new Date(date+'T'+debut).getTime();
        const end = new Date(date+'T'+fin).getTime();
        this.duree = this.getHoursDiff(start, end);
      });

  }

  getHoursDiff(startDate, endDate) {
    const msInHour = 1000 * 60 * 60;
    return Math.round((endDate - startDate) / msInHour);
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
