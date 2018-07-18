import { BaseService } from "../_base";

export class PoderesService extends BaseService {
  constructor() {
    super("public/poderes");
  }

  getPoderesDeLocalizacao(localizacao) {
    return this.post(localizacao);
  }
}
