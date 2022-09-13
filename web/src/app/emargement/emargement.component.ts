import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-emargement',
  templateUrl: './emargement.component.html',
  styleUrls: ['./emargement.component.css']
})
export class EmargementComponent implements OnInit {

  title = 'myangularproject';
  public dateValue: Date = new Date("04/23/2022 02:00");
  public minValue: Date = new Date("04/23/2022 01:00");
  public maxValue: Date = new Date("04/23/2022 17:00");

  constructor() { }

  ngOnInit(): void {
  }

}
