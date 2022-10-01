import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/auth.service";
import {Role} from "./model/role";
import {User} from "./model/user";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  constructor(private authService: AuthService) {
  }

  title = 'vacataire';

  showProfesseur = false;

  ngOnInit(): void {
    this.authService.getUserConnected().subscribe(u => {
      if(u != null) {
        const user = new User(u);
        this.showProfesseur = user.hasExpectedRoles([Role.ROLE_MODERATOR.toString(),Role.ROLE_ADMIN.toString()]);
        console.log(this.showProfesseur)
      }else {
        this.showProfesseur = false;
      }
    });
  }

  get isLoggedIn() {
    return this.authService.isConnected();
  }

  logout() {
    this.authService.logout()
  }
}
