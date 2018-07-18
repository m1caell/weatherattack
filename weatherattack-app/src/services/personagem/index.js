import { BaseService } from "../_base";
import { UserService } from "../user";

export class PersonagemService extends BaseService {
  constructor() {
    super("personagem");
  }

  getInfoPersonagem(idUsuario) {
    const user = UserService.getLoggedUser();
    let token = `?access_token=${user.token.replace("Bearer ", "")}`;
    return this.getById(idUsuario, token);
  }
}
