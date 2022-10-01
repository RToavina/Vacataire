import {Matiere} from "./matiere";
import {Emargement} from "./emargement";

export class Professeur{
  username: string;
  nom: string;
  prenom: string;
  tauxHoraire: number;
  email: string;
  phoneNumber: string;
  emargements: Emargement[];
  matieres: Matiere[];

  public constructor(init?:Partial<Professeur>) {
    Object.assign(this,init);
  }
}

export interface ProfesseurCreation{
  username: string;
  nom: string;
  prenom: string;
  tauxHoraire?: number;
  email: string;
  phoneNumber: string;
  matieres: string[];
  roles?: string[];
  password: string;
}
