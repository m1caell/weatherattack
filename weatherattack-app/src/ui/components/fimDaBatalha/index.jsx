import React, { Component } from "react"
import "./index.css";
import { UserService } from "app-services";
import Winner from "../../../img/winner.svg";
import Loser from "../../../img/game-over.svg";

export class FimDaBatalha extends Component {
  constructor() {
    super();

    this.state = {
      time: 20,
      visibilidade: false,
      shouldRedirectDashboard: false,
      img: "",
      texto: ""
    };

    setInterval(() => {
      this.setState(
        {
          time: this.state.time - 1
        },
        () => {
          if (this.state.time === 0) {
            this.refreshPage();
          }
        }
      );
    }, 1000);
    this.userService = new UserService();
  }

  componentWillMount() {
    this.setState({
      visibilidade: this.props.visibilidade
    });
    this.renderVencedorOuPerdedor();
  }

  componentWillReceiveProps(nextProps) {
    this.setState(nextProps);
  }

  refreshPage() {
    window.location.reload();
  }

  renderVencedorOuPerdedor() {
    if (this.props.vencedor === UserService.getLoggedUser().id) {
      this.setState({
        img: Winner,
        texto: "Você venceu"
      });
    } else {
      this.setState({
        img: Loser,
        texto: "Você perdeu"
      });
    }
  }

  render() {
    if (!this.state.visibilidade) {
      return <div> </div>;
    }
    return (
      <div class="content-fim-batalha">
        <div class="descricao">
          <img class="imagemFim" src={this.state.img} alt="imagem-final" />
        </div>
        <button class="voltar-dashboard" onClick={this.refreshPage}>
          Sair
        </button>
      </div>
    );
  }
}
