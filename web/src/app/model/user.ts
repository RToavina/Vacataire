export class User {
  username: string;
  email: string;
  nom: string;
  prenom: string;
  phoneNumber: string;
  roles: string[];

  public constructor(init?:Partial<User>) {
    Object.assign(this,init);
  }

  hasExpectedRoles(roles: string[]) {
    return roles.some(r => this.roles.includes(r));
  }

}
