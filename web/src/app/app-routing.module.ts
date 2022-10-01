import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { EmargementComponent } from './emargement/emargement.component';
import { CalendarComponent } from './calendar/calendar.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ControlComponent} from "./control/control.component";
import {AuthGuard} from "./services/auth.guard";

const routes: Routes = [  {
  path: '',
  redirectTo: 'calendar',
  pathMatch: 'full'
},
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'calendar',
    component: CalendarComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'control',
    component: ControlComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
