import { BaseService } from "../_base";
import SockJS from "sockjs-client";
import Stomp from "@stomp/stompjs";
export class DesafioService extends BaseService {
  constructor() {
    super("desafio/");
    this.state = {
      token: localStorage.getItem("logged_user"),
      socket: null
    };
  }

  componentDidMount() {
    this.startSocket();
  }

  startSocket() {
    const socketUrl = `http://localhost:8081/weatherattack/?access_token=${this.state.token.replace(
      "Bearer ",
      ""
    )}`;
    const socket = Stomp.over(new SockJS(socketUrl));

    socket.connect(
      {},
      this.connect
    );
    socket.debug = null;

    this.state.socket = socket;
  }

  connect(usuario, funcoes) {
    this.state.socket.subscribe(`/topic/all`, funcoes.recebeNotificacao);
    this.state.socket.subscribe(`/queue/reply`, funcoes.recebeNotificacao);
    this.state.socket.subscribe(`/topic/connected`, funcoes.onNewUserOnline);
    this.state.socket.subscribe(`/topic/disconnected`, funcoes.onUserOffline);
    this.state.socket.subscribe(
      `/topic/challenge.${usuario.idUsuario}`,
      funcoes.onNewChallenge
    );
    this.state.socket.subscribe(
      `/topic/challengeRefuse.${usuario.idUsuario}`,
      funcoes.onRefusedChallenge
    );
    this.state.socket.subscribe(
      `/topic/challengeAccepted.${usuario.idUsuario}`,
      funcoes.onAcceptedChallenge
    );
    // mensagem para se registrar no servidor
    this.state.socket.send("/app/connect", {}, JSON.stringify(usuario));
  }
}
