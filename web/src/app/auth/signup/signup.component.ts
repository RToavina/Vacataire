import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Matiere} from "../../model/matiere";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  form: FormGroup;
  matieres: Matiere[] = [];


  constructor(@Inject(MAT_DIALOG_DATA) public data: { matieres: Matiere[] },
              private dialogRef: MatDialogRef<SignupComponent>,
              private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.matieres = this.data.matieres;
    this.form = this.fb.group({
      nom: [null, Validators.required],
      prenom: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      numeroTel: [null, [Validators.required, Validators.maxLength(15)]],
      identifiant: [null, Validators.required],
      password: [null, Validators.required],
      tauxHoraire: null,
      matieres: [null, Validators.required],
    })
  }

  signup() {
    if (this.form.valid) {
      this.dialogRef.close({
        nom: this.form.get('nom').value,
        prenom: this.form.get('prenom').value,
        email: this.form.get('email').value,
        phoneNumber: this.form.get('numeroTel').value,
        username: this.form.get('identifiant').value,
        password: this.form.get('password').value,
        tauxHoraire: this.form.get('tauxHoraire').value,
        matieres: this.form.get('matieres').value,
      })

    }
  }
}
