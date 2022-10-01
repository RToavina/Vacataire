import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {BehaviorSubject, tap} from "rxjs";
import {Router} from "@angular/router";
import {ProfesseurCreation} from "../model/professeur";
import {Role} from "../model/role";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = environment.apiUrl;
  connectUrl = this.apiUrl + '/auth/signin';
  logoutUrl = this.apiUrl + '/auth/signout';
  signupUrl = this.apiUrl + '/auth/signup';

  private userSubject = new BehaviorSubject<User>(null);

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  login(data: { username: string; password: string }) {
    return this.httpClient.post<User>(this.connectUrl, data).pipe(tap(res => {
      localStorage.setItem('user', JSON.stringify(res));
    }));
  }

  signup(data: ProfesseurCreation) {
    return this.httpClient.post(this.signupUrl, data)
  }

  getUserConnected() {
    const userString = localStorage.getItem("user");
    const user = userString != null ? new User(JSON.parse(userString)) : null;
    this.userSubject.next(user);
    return this.userSubject;
  }

  isConnected() {
    return localStorage.getItem("user") != null;
  }

  hasExpectedRole(roles: string[]) {
    const userString = localStorage.getItem("user");
    const user = userString != null ? new User(JSON.parse(userString)) : null;
    return user?.hasExpectedRoles(roles);
  }

  logout() {
    this.httpClient.post(this.logoutUrl, {}).subscribe(res => {
      localStorage.removeItem("user");
      this.userSubject.next(null);
      this.router.navigate(["/login"]);
    })
  }
}
