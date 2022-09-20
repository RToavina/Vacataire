import {Matiere} from "./matiere";

export class Professeur{
  username: string;
  nom: string;
  prenom: string;
  matieres: Matiere[];

  public constructor(init?:Partial<Professeur>) {
    Object.assign(this,init);
  }
}
