import React, { Component } from "react";
import "./index.css";
import { Button } from "app-components";
import axios from "axios";
import Alert from "react-s-alert";
import {
  PoderesService,
  PersonagemService,
  UserService,
  GerarInteiroAleatorio
} from "app-services";
import { UsuariosConectados, Tempo } from "app-components";
import iconName from "../../../img/iconName.svg";
import iconCoins from "../../../img/iconCoins.svg";
import iconDerrotas from "../../../img/iconDerrotas.svg";
import iconLutas from "../../../img/iconLutas.svg";
import iconVitorias from "../../../img/iconVitorias.svg";
import dropSmall from "../../../img/dropSmall.svg";
import flameSmall from "../../../img/flameSmall.svg";
import flashSmall from "../../../img/flashpower-flash.svg";
import hurricaneSmall from "../../../img/hurricaneSmall.svg";
import icecrystalSmall from "../../../img/icecrystalSmall.svg";
import image4 from "../../../img/image 4.svg";
import image5 from "../../../img/image 5.svg";
import image3 from "../../../img/image 3.svg";
import image6 from "../../../img/image 6.svg";

export class Desafio extends Component {
  constructor(props) {
    super(props);

    this.localizacao = {
      latitude: this.props.latitude,
      longitude: this.props.longitude
    };

    this.poderesService = new PoderesService();
    this.personagemService = new PersonagemService();
    let gerador = new GerarInteiroAleatorio();

    this.state = {
      mensagemErro: "",
      mostrarPoderes: false,
      personagem: "",
      usuarioAtual: "",
      poderes: ""
    };

    this.personagemAleatorio = gerador.gerarInteiroAleatorio(1, 8);
    this.renderPoderes = this.renderPoderes.bind(this);
    this.showErrorAlert = this.showErrorAlert.bind(this);
    this.lutaAleatoria = this.lutaAleatoria.bind(this);
  }

  showErrorAlert(e) {
    Alert.warning(this.state.mensagemErro, {
      position: "top-right",
      effect: "bouncyflip",
      beep: false,
      timeout: 5000,
      offset: 100
    });
  }

  componentDidMount() {
    const usuarioAtual = UserService.getLoggedUser();

    this.personagemService
      .getInfoPersonagem(usuarioAtual.id)
      .then(resp => {
        this.setState({
          personagem: resp,
          usuarioAtual: resp.usuario
        });
      })
      .catch(() => {
        console.error("Falha ao carregar personagens");
      });

    this.poderesService
      .getPoderesDeLocalizacao(this.localizacao)
      .then(res => {
        this.setState({
          poderes: res,
          mostrarPoderes: true
        });
      })
      .catch(() => {
        this.setState({
          mensagemErro: "Falha ao carregar poderes!"
        });
        this.showErrorAlert();
      });
  }

  renderIconePoder(nome) {
    if (nome === "LANÇA DE FOGO")
      return <img src={flameSmall} className="imagem-poder" alt="flameSmall" />;
    if (nome === "CHICOTE TINHOSO")
      return <img src={flameSmall} className="imagem-poder" alt="flameSmall" />;
    if (nome === "BALDE DE ÁGUA")
      return <img src={dropSmall} className="imagem-poder" alt="dropSmall" />;
    if (nome === "FLOCO DE NEVE")
      return (
        <img
          src={icecrystalSmall}
          alt="icecrystalSmall"
          className="imagem-poder"
        />
      );
    if (nome === "SOPRO GELADO")
      return (
        <img
          src={hurricaneSmall}
          alt="hurricaneSmall"
          className="imagem-poder"
        />
      );
    if (nome === "CHOQUE NO 220v")
      return <img src={flashSmall} alt="flash" className="imagem-poder" />;
  }

  renderPoderes() {
    if (this.state.poderes.length > 0) {
      return this.state.poderes.map(poder => {
        return (
          <div className="lista-poder" key={poder.id}>
            <span className="nome-poder">{this.capitalize(poder.nome)}</span>
            {this.renderIconePoder(poder.nome)}
          </div>
        );
      });
    }
  }

  enviarDesafio(objeto) {
    if (objeto != null) {
      const url = `http://localhost:8081/desafio/?access_token=${localStorage
        .getItem("logged_user")
        .replace("Bearer ", "")}`;

      const data = {
        idDesafiante: UserService.getLoggedUser().id,
        idDesafiado: objeto.id,
        apelidoDesafiado: objeto.desafiado
      };
      axios
        .post(url, data)
        .then(response => {})
        .catch(error => {
          console.error("error", error);
        });
    }
  }

  lutaAleatoria() {
    let usuarios = this.props.usuariosConectados;
    let escolherUsuarioRandom = new GerarInteiroAleatorio();
    let usuario = escolherUsuarioRandom.gerarInteiroAleatorio(
      0,
      usuarios.length
    );
    usuario = usuarios[usuario];

    if (usuario.id === UserService.getLoggedUser().id) {
      for (let i = 0; i < 3; i++) {
        usuario = escolherUsuarioRandom.gerarInteiroAleatorio(
          0,
          usuarios.length
        );
      }
    } else {
      this.enviarDesafio(usuario);
    }
  }

  capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();
  }

  render() {
    return (
      <div className="desafio">
        <span className="titulo">Dashboard</span>
        <div className="header">
          <div className="header-left">
            <div className="personagem">
              <div className="imagem-personagem" />
              <div className="detalhes-personagem">
                <span className="detalhe-personagem">
                  <img src={iconName} alt="iconName" />
                  {this.state.usuarioAtual.nome}
                </span>
                <span className="detalhe-personagem">
                  <img src={iconCoins} alt="iconCoins" />
                  {this.state.personagem.coins} Coins
                </span>
                <span className="detalhe-personagem">
                  <img src={iconLutas} alt="iconLutas" />
                  {this.state.personagem.battles} Lutas
                </span>
                <span className="detalhe-personagem">
                  <img src={iconVitorias} alt="iconVitorias" />
                  {this.state.personagem.wins} Vitórias
                </span>
                <span className="detalhe-personagem">
                  <img src={iconDerrotas} alt="iconDerrotas" />
                  {this.state.personagem.loses} Derrotas
                </span>
              </div>
            </div>
            <Button
              className="button-lutar"
              text="Lutar"
              onClick={this.lutaAleatoria}
            />
          </div>
          <div className="header-right">
            <div className="equipamentos-poderes">
              <div className="equipamentos">
                <span className="titulo-equipamentos">Equipamentos</span>
                <div className="container-equipamentos">
                  <div className="equipamento">
                    <img src={image4} alt="chapeu" />
                  </div>
                  <div className="equipamento">
                    <img src={image3} alt="" />
                  </div>
                  <div className="equipamento">
                    <img src={image6} alt="" />
                  </div>
                  <div className="equipamento">
                    <img src={image5} alt="" />
                  </div>
                </div>
              </div>
              <div className="poderes">
                <span className="titulo-poderes">Poderes</span>
                {this.renderPoderes()}
              </div>
            </div>
            <div className="tempo">
              <Tempo className="tempo-home" />
            </div>
          </div>
        </div>
        <div className="combate" />
        <UsuariosConectados
          listaUsuariosConectados={this.props.listaUsuariosConectados}
          idUsuarioAtual={this.state.usuarioAtual.id}
          socket={this.props.socket}
        />
      </div>
    );
  }
}
