export class Matiere{
  nomMatiere: string;
  id: number;

  public constructor(init?:Partial<Matiere>) {
    Object.assign(this,init);
  }

}


