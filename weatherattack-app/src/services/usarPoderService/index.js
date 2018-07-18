import { BaseService } from "../_base";

export class PoderesService extends BaseService {
  constructor() {
    super("public/usarPoder");
  }

  getPoderesDeLocalizacao(idUsuario, idPoder, idAlvo) {
    return this.post(idUsuario, idPoder, idAlvo);
  }
}
