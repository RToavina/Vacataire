export class Matiere{
  nom: string;
  id: number;

  public constructor(init?:Partial<Matiere>) {
    Object.assign(this,init);
  }

}


