import React, { Component } from "react";
import SockJS from "sockjs-client";
import Stomp from "@stomp/stompjs";
import axios from "axios";
import "./index.css";
import {
  PoderesService,
  UserService,
  DesafioService,
  GeolocationService
} from "app-services";
import { Redirect } from "react-router-dom";
import { Home } from "app-pages";
import { CombateComp, Loading } from "app-components";
import "react-s-alert/dist/s-alert-default.css";
import "react-s-alert/dist/s-alert-css-effects/bouncyflip.css";
import { NotificacaoConviteBatalha } from "app-components";
import img from "../../../img/character.svg";
import { NotificacaoConviteRecusado } from "../../components/notificacaoConviteRecusado";
import dropMedium from "../../../img/drop.svg";
import geloMedium from "../../../img/ice-crystal.svg";
import flameMedium from "../../../img/flameMedium.svg";
import flashMedium from "../../../img/flashMedium.svg";
import hurricaneMedium from "../../../img/hurricaneMedium.svg";

export class Dashboard extends Component {
  constructor() {
    super();
    this.state = {
      loading: true,
      usuarios: [],
      conectado: false,
      idUsuario: "",
      emailUsuario: "",
      apelidoUsuario: "",
      token: "",
      mensagem: "",
      socket: null,
      notificacaoReceberDesafio: "",
      channel: "",
      conviteBatalha: false,
      conviteBatalhaRecusado: false,
      componenteAtual: "Home",
      poderes: "",
      mensagemErro: "",
      latitude: "",
      longitude: "",
      idDesafio: "",
      loading: true
    };

    this.poderesService = new PoderesService();
    this.desafioService = new DesafioService();
    this.geolocationService = new GeolocationService();

    this.carregarUsuarios = this.carregarUsuarios.bind(this);
    this.connect = this.connect.bind(this);
    this.setUsuarioAtual = this.setUsuarioAtual.bind(this);
    this.onClickLogout = this.onClickLogout.bind(this);
    this.onNewUserOnline = this.onNewUserOnline.bind(this);
    this.onUserOffline = this.onUserOffline.bind(this);
    this.onNewChallenge = this.onNewChallenge.bind(this);
    this.onRefusedChallenge = this.onRefusedChallenge.bind(this);
    this.onAcceptedChallenge = this.onAcceptedChallenge.bind(this);
    this.getPoderes = this.getPoderes.bind(this);
    this.getLocation = this.getLocation.bind(this);
    this.showPosition = this.showPosition.bind(this);
    this.renderPoderes = this.renderPoderes.bind(this);
    this.renderHome = this.renderHome.bind(this);
  }

  desactiveLoader() {
    setTimeout(() => {
      this.showLoader(false);
    }, 1000);
  }

  showLoader(loading) {
    this.setState({
      loading
    });
  }

  componentWillMount() {
    let token1 = localStorage.getItem("logged_user");
    let token = token1.replace(/["]/g, "");
    this.setState({
      token: token
    });
  }

  componentDidMount() {
    this.setUsuarioAtual();
    this.carregarUsuarios();
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

    this.setState({ socket: socket });
    this.getLocation();
  }

  getLocation() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        this.showPosition,
        this.showError
      );
    } else {
      this.setState({
        mensagemErro: "Geolocalização não é suportado nesse browser"
      });
    }
  }

  showPosition(position) {
    this.setState(
      {
        latitude: position.coords.latitude,
        longitude: position.coords.longitude
      },
      () => this.getPoderes()
    );
  }

  getPoderes() {
    const localizacao = {
      latitude: this.state.latitude,
      longitude: this.state.longitude
    };

    this.poderesService
      .getPoderesDeLocalizacao(localizacao)
      .then(res => {
        this.setState(
          {
            poderes: res
          },
          () => this.renderPoderes()
        );
      })
      .catch(() => {
        this.setState({
          mensagemErro: "Falha ao carregar poderes!"
        });
      }).finally(() => {
        this.desactiveLoader();
      })
  }

  setUsuarioAtual() {
    const usuario = UserService.getLoggedUser();
    this.setState({
      idUsuario: usuario.id,
      emailUsuario: usuario.email,
      apelidoUsuario: usuario.apelido
    });
  }

  carregarUsuarios() {
    axios
      .get(
        `http://localhost:8081/usuario/todos-usuarios?access_token=${this.state.token.replace(
          "Bearer ",
          ""
        )}`
      )
      .then(response => {
        this.setState({
          usuarios: response.data
        });
      })
      .catch(resp => {
        console.error(resp);
      });
  }

  aceitarDesafio() {
    this.setState({
      conviteBatalha: false
    });
    const token = this.state.token.replace("Bearer ", "");
    const url = `http://localhost:8081/desafio/${
      this.state.idDesafio
    }/?access_token=${token}`;

    axios
      .post(url, {})
      .then(response => {
        this.setState({
          notificacaoGeral: response.data.mensagem,
          channel: response.data.channel,
          componenteAtual: "Combate"
        });

        this.state.socket.subscribe(
          `/topic/challenge.${response.channel.id}`,
          this.combate
        );
      })
      .catch(error => {
        console.error("error", error);
      });
  }

  recusarDesafio() {
    this.setState({
      conviteBatalha: false
    });
    const token = this.state.token.replace("Bearer ", "");
    const url = `http://localhost:8081/desafio/recusar/${
      this.state.channel.id
    }/?access_token=${token}`;

    axios
      .post(url, {})
      .then(response => {})
      .catch(error => {
        console.error("error", error);
      });
  }

  connect() {
    const usuario = {
      id: this.state.idUsuario,
      email: this.state.emailUsuario,
      apelido: this.state.apelidoUsuario
    };

    this.state.socket.subscribe(`/topic/all`, this.recebeNotificacao);
    this.state.socket.subscribe(`/queue/reply`, this.recebeNotificacao);
    this.state.socket.subscribe(`/topic/connected`, this.onNewUserOnline);
    this.state.socket.subscribe(`/topic/disconnected`, this.onUserOffline);
    this.state.socket.subscribe(
      `/topic/challenge.${this.state.idUsuario}`,
      this.onNewChallenge
    );
    this.state.socket.subscribe(
      `/topic/challengeRefuse.${this.state.idUsuario}`,
      this.onRefusedChallenge
    );
    this.state.socket.subscribe(
      `/topic/challengeAccepted.${this.state.idUsuario}`,
      this.onAcceptedChallenge
    );
    // mensagem para se registrar no servidor
    this.state.socket.send("/app/connect", {}, JSON.stringify(usuario));
  }

  recebeNotificacao(message, topic) {
    this.setState({
      notificacao: message
    });
  }

  onNewUserOnline(message, queue) {
    this.setState({ usuarios: JSON.parse(message.body) });
  }

  onUserOffline(message, queue) {
    this.setState({ usuarios: JSON.parse(message.body) });
  }

  onNewChallenge(message, topic) {
    const mensagem = JSON.parse(message.body);
    console.log(mensagem);
    this.setState({
      conviteBatalha: true,
      mensagem: mensagem.mensagem,
      idDesafio: mensagem.idDesafio
    });
  }

  onRefusedChallenge(message, queue) {
    this.setState({
      conviteBatalhaRecusado: true,
      componenteAtual: "Home"
    });
  }

  onAcceptedChallenge(message, queue) {
    const info = JSON.parse(message.body);
    this.setState({
      notificacaoGeral: info.mensagem,
      channel: info.channel,
      componenteAtual: "Combate"
    });
    this.state.socket.subscribe(
      `/topic/challenge.${info.channel.id}`,
      this.combate
    );
  }

  onClickLogout() {
    UserService.logout();
  }

  renderNotificacaoReceberDesafio() {
    if (this.state.conviteBatalha) {
      return (
        <div>
          <NotificacaoConviteBatalha
            visibilidade={this.state.conviteBatalha}
            text={this.state.mensagem}
            img={img}
            aceitar={() => this.aceitarDesafio()}
            recusar={() => this.recusarDesafio()}
          />;
        </div>
      );
    }
  }

  renderNotificacaoConviteRecusado() {
    if (this.state.conviteBatalhaRecusado) {
      return (
        <div>
          <NotificacaoConviteRecusado
            visibilidade={this.state.conviteBatalhaRecusado}
            text={this.state.apelidoUsuario}
            img={img}
          />;
        </div>
      );
    }
  }

  renderHome() {
    this.setState({
      componenteAtual: "Home"
    });
  }

  renderComponentePrincipal() {
    if (this.state.componenteAtual === "Home") {
      return (
        <div>
          <Home
            usuariosConectados={this.state.usuarios}
            socket={this.state.socket}
          />
        </div>
      );
    }
    if (this.state.componenteAtual === "Combate") {
      return (
        <CombateComp
          socket={this.state.socket}
          channel={this.state.channel}
          poderesEnviados={this.state.poderes}
        />
      );
    }
  }

  renderIconePoder(nome, status) {
    if (nome === "LANÇA DE FOGO") {
      status[0] = true;
      return <img src={flameMedium} className="img-dash-poderes" alt="flameSmall" />;
    }
    if (nome === "CHICOTE TINHOSO" && status[0].fogo === false) {
      status[0] = true;
      return (
        <img src={flameMedium} alt="flameMedium" className="img-dash-poderes" />
      );
    }
    if (nome === "BALDE DE ÁGUA" && status[1].agua === false) {
      status[1] = true;
      return (
        <img src={dropMedium} alt="dropMedium" className="img-dash-poderes" />
      );
    }
    if (nome === "FLOCO DE NEVE" && status[2].gelo === false) {
      status[2] = true;
      return (
        <img src={geloMedium} alt="geloMedium" className="img-dash-poderes" />
      );
    }
    if (nome === "SOPRO GELADO" && status[3].vento === false) {
      status[3] = true;
      return (
        <img
          src={hurricaneMedium}
          alt="hurricaneMedium"
          className="img-dash-poderes"
        />
      );
    }
    if (nome === "CHOQUE NO 220v" && status[4].raio === false) {
      status[4] = true;
      return (
        <img src={flashMedium} alt="flashMedium" className="img-dash-poderes" />
      );
    }
  }

  renderPoderes() {
    let status = [
      { fogo: false },
      { agua: false },
      { gelo: false },
      { vento: false },
      { raio: false }
    ];

    if (this.state.poderes.length > 0) {
      return this.state.poderes.map(poder => {
        return (
          <div key={poder.id}>{this.renderIconePoder(poder.nome, status)}</div>
        );
      });
    } else {
      return <span>{this.state.mensagemErro}</span>;
    }
  }

  render() {
    if (!localStorage.getItem("logged_user")) {
      return <Redirect to="/" />;
    } else if (this.state.renderNotificacaoDesafio) {
      return;
    }
    if (this.state.componenteAtual === "Home") {
      <Loading showLoader={this.state.loading} />;
    }
    return (
      <div className="dashboard">
        <Loading showLoader={this.state.loading} />
        <div className="header-dashboard">
          <div className="usuario-dashboard">
            <div className="avatar-dashboard" />
            <span className="nome-dashboard">
              Olá, {UserService.getLoggedUser().apelido}
            </span>
          </div>
          <div className="center-header-dashboard" />
          <div className="controles-dashboard">
            <div className="content-controles">
              <div className="diagonal-controles" />
              <a href="/" onClick={this.onClickLogout}>
                <div className="logout-controles" />
              </a>
            </div>
          </div>
        </div>
        <div className="conteudo-dashboard">
          <div className="menu-esquerda-dashboard">
            <div className="poderes-menu-esquerda-dashboard">
              {this.renderPoderes()}
            </div>
            <div className="botoes-menu-esquerda-dashboard">
              <div className="botao botao-home active">
                <a href="/dashboard">
                  <span className="icone-home" />
                  <div>Home</div>
                </a>
              </div>
              <div className="botao botao-perfil">
                <a href="#">
                  <span className="icone-perfil" />
                  <div>Meu Perfil</div>
                </a>
              </div>
              <div className="botao botao-ranking">
                <a href="#">
                  <span className="icone-ranking" />
                  <div>Ranking</div>
                </a>
              </div>
              <div className="botao botao-shop">
                <a href="#">
                  <span className="icone-shop" />
                  <div>Shop</div>
                </a>
              </div>
            </div>
            <div className="logo-dashboard">
              <div className="efeito-diagonal" />
              <div className="logo-em-dashboard" />
              <span className="corporation">
                Copyright © 2018 Weather Attack. Todos os Direitos Reservados.
              </span>
            </div>
          </div>
        </div>
        <div className="direita-dashboard">
          {this.renderComponentePrincipal()}
          {this.renderNotificacaoReceberDesafio()}
          {this.renderNotificacaoConviteRecusado()}
        </div>
      </div>
    );
  }
}
