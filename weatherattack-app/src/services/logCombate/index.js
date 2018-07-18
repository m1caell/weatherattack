import { BaseService } from "../_base";
import { UserService } from "../user";

export class LogCombateService extends BaseService {
  constructor() {
    super("logcombate");
  }

  getListaCombates() {
    const user = UserService.getLoggedUser();
    let token = `?access_token=${user.token.replace("Bearer ", "")}`;
    return this.get(token);
  }
}
