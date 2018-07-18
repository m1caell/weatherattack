import { BaseService } from "../_base";

export class GeolocationService extends BaseService {
  constructor() {
    super("public/clima");
  }

  enviarInformacoes(geolocation) {
    return this.post(geolocation);
  }
}
