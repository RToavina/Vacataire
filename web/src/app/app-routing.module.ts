import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { EmargementComponent } from './emargement/emargement.component';
import { CalendarComponent } from './calendar/calendar.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [  {
  path: '',
  redirectTo: '/login',
  pathMatch: 'full'
},
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'emarg',
    component: EmargementComponent
  },
  {
    path: 'calendar',
    component: CalendarComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
