import { BaseService } from "../_base";
import LoggedUser from "../../models/LoggedUser";
import jwt_decode from "jwt-decode";

export class UserService extends BaseService {
  constructor() {
    super("public/login");
  }

  static getLoggedUser() {
    const saved = localStorage.getItem("logged_user");

    if (!!saved) {
      let token = localStorage.getItem("logged_user");
      let decoded = jwt_decode(token);

      return new LoggedUser(decoded.id, decoded.apelido, decoded.email, token);
    }

    return null;
  }

  static logout() {
    const logged = this.getLoggedUser();
    if (logged) {
      localStorage.removeItem("logged_user");
      return true;
    }
  }

  static setLoggedUser(token) {
    localStorage.setItem("logged_user", token);
  }

  logar(usuario) {
    return this.post(usuario);
  }
}
