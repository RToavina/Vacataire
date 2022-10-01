import {LoginComponent} from './auth/login/login.component';
import {CalendarComponent} from './calendar/calendar.component';

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ControlComponent} from "./control/control.component";
import {AuthGuard} from "./services/auth.guard";
import {RoleGuard} from "./services/role.guard";
import {Role} from "./model/role";

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
    canActivate: [AuthGuard, RoleGuard],
    data: {roles: [Role.ROLE_MODERATOR,Role.ROLE_ADMIN]}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
