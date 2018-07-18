import React, { Component } from "react";
import "./index.css";

export class NotificacaoConviteRecusado extends Component {
  constructor() {
    super();

    this.state = {
      time: 10,
      visibilidade: false
    };

    setInterval(() => {
      this.setState(
        {
          time: this.state.time - 1
        },
        () => {
          if (this.state.time === 0) {
            this.closeIt();
          }
        }
      );
    }, 1000);
  }

  closeIt() {
    this.setState({
      visibilidade: false
    });
  }

  componentWillReceiveProps(nextProps) {
    this.setState(nextProps);
  }

  componentWillMount() {
    this.setState({
      visibilidade: this.props.visibilidade
    });
  }

  render() {
    if (!this.state.visibilidade) {
      return <div> </div>;
    }

    return (
      <div
        class="content-notificacao-batalha-recusada"
        onClick={() => this.closeIt()}
      >
        <div class="desafio-jogador">
          <div class="desafio-recusado">Convite recusado</div>
          <div class="jogador">
            <img class="img" src={this.props.img} alt="imgagem_perfil" />
            <div class="text-jogador">{this.props.text}</div>
          </div>
        </div>
      </div>
    );
  }
}
