import React, { Component } from "react";
import "./index.css";

export class NotificacaoConviteBatalha extends Component {
  constructor() {
    super();

    this.state = {
      time: 10,
      visible: ""
    };

    setInterval(() => {
      this.setState(
        {
          time: this.state.time - 1
        },
        () => {
          if (this.state.time === 0) {
            this.setState({
              visible: false
            });
          }
        }
      );
    }, 1000);
  }

  componentWillMount() {
    this.setState({
      visible: this.props.visibilidade
    });
  }

  render() {
    if (!this.state.visible) {
      return <div> </div>;
    }

    return (
      <div className="content-notificacao-batalha">
        <div className="desafio-jogador">
          <div className="desafio-novo">Novo desafio</div>
          <div className="jogador">
            <img className="img" src={this.props.img} alt="imagem" />
            <div className="text-jogador">{this.props.text}</div>
          </div>
        </div>

        <div className="buttons">
          <button className="button-recusar" onClick={this.props.recusar}>
            Recusar
          </button>
          <button className="button-aceitar" onClick={this.props.aceitar}>
            Aceitar ({this.state.time})
          </button>
        </div>
      </div>
    );
  }
}
