import {Matiere} from "./matiere";

export class Emargement {
  date: string;
  debut: string;
  fin: string;
  done: boolean;
  id: number;
  matiere: Matiere;

  public constructor(init?:Partial<Emargement>) {
    Object.assign(this,init);
  }
}

export interface EmargementRequest {
  username: string;
  date: string;
  debut: string;
  fin: string;
  done: boolean;
  id: number;
  matiere: string;
}
