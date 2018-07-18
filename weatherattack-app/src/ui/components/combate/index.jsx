import React, { Component } from "react";
import "./index.css";
import { Button, Tempo, FimDaBatalha } from "app-components";
import { UserService } from "app-services";
import audio from "../../../audio/audiohit.wav";
import audioBatalha from "../../../audio/battlesong.mp3";

export class CombateComp extends Component {
  constructor(props) {
    super(props);

    this.state = {
      idChannel: "",
      socket: "",
      token: "",
      mensagem: "",
      channel: "",
      jogadorDaVez: false,
      usuarioAtual: "",
      minhaVida: "",
      vidaOponente: "",
      minhaMana: "",
      manaOponente: "",
      mensagemfim: "",
      minutos: 0,
      segundos: 0,
      dano: 0,
      telaFimDaBatalha: false,
      vencedor: "",
      flagDano1: false,
      flagDano2: false
    };

    setInterval(() => {
      this.setState(
        {
          segundos: this.state.segundos + 1
        },
        () => {
          if (this.state.segundos === 60) {
            this.setState({
              minutos: this.state.minutos + 1,
              segundos: 0
            });
          }
        }
      );
    }, 1000);

    this.recebeMensagem = this.recebeMensagem.bind(this);
    this.atacar = this.atacar.bind(this);
    this.getChannelInfo = this.getChannelInfo.bind(this);
    this.renderBotaoAtaque = this.renderBotaoAtaque.bind(this);
    this.finalizarCombate = this.finalizarCombate.bind(this);
    this.renderPersonagemAtacado = this.renderPersonagemAtacado.bind(this);
    this.renderPersonagem1 = this.renderPersonagem1.bind(this);
    this.renderPersonagem2 = this.renderPersonagem2.bind(this);
  }

  componentDidMount() {
    let token1 = localStorage.getItem("logged_user");
    let token = token1.replace(/["]/g, "");
    this.setState(
      {
        usuarioAtual: UserService.getLoggedUser(),
        channel: this.props.channel,
        idChannel: this.props.channel.id,
        token: token,
        socket: this.props.socket
      },
      () => this.getChannelInfo()
    );

    const audio = document.getElementById("audioBatalha");
    audio.volume = 0.2;
    audio.play();

  }

  getChannelInfo() {
    this.setState({
      jogadorDaVez: this.state.channel.combate.idJogadorDaVez,
      minhaVida: this.state.channel.combate.personagem1.vida,
      vidaOponente: this.state.channel.combate.personagem2.vida,
      minhaMana: this.state.channel.combate.personagem1.mana,
      manaOponente: this.state.channel.combate.personagem2.mana,
      mensagemfim: this.state.channel.combate.mensagemFim
    });

    this.state.socket.subscribe(
      `/topic/challenge.${this.state.idChannel}`,
      this.recebeMensagem
    );
  }

  recebeMensagem(message, topic) {
    let mensagem = JSON.parse(message.body);

    if (mensagem.tipo === "FIM") {
      this.finalizarCombate(mensagem.idVencedor, mensagem.idPerdedor);
    }
    if (mensagem.idAtacado === this.state.usuarioAtual.id) {
      this.setState({
        minhaVida: mensagem.vidaRestante,
        manaOponente: mensagem.manaRestante,
        minhaMana: mensagem.manaDoAtacado,
        dano1: mensagem.dano
      });
      this.mostrarDano("flagDano1");
    } else {
      this.setState({
        vidaOponente: mensagem.vidaRestante,
        minhaMana: mensagem.manaRestante,
        manaOponente: mensagem.manaDoAtacado,
        dano2: mensagem.dano
      });
      this.mostrarDano("flagDano2");
    }
    this.setState(
      {
        mensagem: mensagem.tipo,
        jogadorDaVez: mensagem.idAtacado
      },
      () => this.renderBotaoAtaque()
    );
  }

  atacar(poder) {
    const mensagem = {
      temperatura: Tempo.getTemperatura(),
      idPoder: poder.id
    };
    this.state.socket.send(
      `/app/challenge.${this.state.idChannel}`,
      {},
      JSON.stringify(mensagem)
    );
    const audio = document.getElementById("audioAtaque");
    audio.play();
  }

  esconderDano(flag) {
    setTimeout(() => {
      this.setState({
        [flag]: false
      });
    }, 2000);
  }

  mostrarDano(flag) {
    this.setState(
      {
        [flag]: true
      },
      () => this.esconderDano(flag)
    );
  }

  renderBotaoAtaque() {
    if (
      this.state.jogadorDaVez === this.state.usuarioAtual.id &&
      this.props.poderesEnviados.length > 0
    ) {
      return this.props.poderesEnviados.map((poder, key) => {
        return (
          <div>
            <Button
              key={(key = poder.id)}
              className="poderes-button"
              onClick={() => this.atacar(poder)}
              text={`${poder.nome} (${poder.custoDeMana})`}
            />
          </div>
        );
      });
    } else if (this.props.poderesEnviados.length > 0) {
      return this.props.poderesEnviados.map((poder, key) => {
        return (
          <Button
            key={(key = poder.id)}
            className="desabilitaOpcoes"
            onClick={() => this.atacar(poder)}
            text={`${poder.nome} (${poder.custoDeMana})`}
          />
        );
      });
    }
  }

  renderFimDaBatalha() {
    if (this.state.telaFimDaBatalha) {
      return (
        <FimDaBatalha
          vencedor={this.state.vencedor}
          visibilidade={this.state.telaFimDaBatalha}
        />
      );
    } else {
      return <div />;
    }
  }

  finalizarCombate(idVencedor) {
    this.setState({
      vencedor: idVencedor,
      telaFimDaBatalha: true
    });
    this.state.socket.unsubscribe();
  }

  renderPersonagemAtacado() {
    if (this.state.flagDano1) {
      return <span className="danoPersonagem1">{this.state.dano1}</span>;
    } else {
      return undefined;
    }
  }

  renderPersonagem1() {
    return (
      <div className="efeito-personagem1">
        <span className="voce" />
        {this.renderPersonagemAtacado()}
      </div>
    );
  }

  renderPersonagem2() {
    return (
      <div className="efeito-personagem2">
        {this.state.flagDano2 && (
          <span className="danoPersonagem2">{this.state.dano2}</span>
        )}
      </div>
    );
  }

  render() {
    let styleVidaPersonagem1 = { width: `${this.state.minhaVida}%` };
    let styleVidaPersonagem2 = { width: `${this.state.vidaOponente}%` };
    let styleManaPersonagem1 = { width: `${this.state.minhaMana}%` };
    let styleManaPersonagem2 = { width: `${this.state.manaOponente}%` };

    return (
      <div className="combate-x1">
        <span className="pagina-combate">Batalha</span>
        <audio src={audioBatalha} id="audioBatalha" loop="-1" />
        <div className="container-combate">
          <audio src={audio} id="audioAtaque" />
          <div className="efeito-sombra-direita" />
          <div className="efeito-sombra-esquerda" />
          <div className="container-informacoes">
            <div className="progressos">
              <div className="vida-personagem1">
                <div className="porcentagem">{this.state.minhaVida}</div>
                <div
                  style={styleVidaPersonagem1}
                  className="status-vida-personagem1"
                />
              </div>
              <div className="mana-personagem1">
                <div className="porcentagem">{this.state.minhaMana}</div>
                <div
                  style={styleManaPersonagem1}
                  className="status-mana-personagem1"
                />
              </div>
            </div>
            <div className="tempo-combate">
              {`${
                this.state.minutos <= 9
                  ? `0${this.state.minutos}`
                  : this.state.minutos
              }:${
                this.state.segundos <= 9
                  ? `0${this.state.segundos}`
                  : this.state.segundos
              }`}
            </div>
            <div className="progressos">
              <div className="vida-personagem2">
                <div className="porcentagem">{this.state.vidaOponente}</div>
                <div
                  style={styleVidaPersonagem2}
                  className="status-vida-personagem2"
                />
              </div>
              <div className="mana-personagem2">
                <div className="porcentagem">{this.state.manaOponente}</div>
                <div
                  style={styleManaPersonagem2}
                  className="status-mana-personagem1"
                />
              </div>
            </div>
          </div>
          <div className="poderes-combate">
            <div className="titulo-poderes-combate">Poderes</div>
            {this.renderBotaoAtaque()}
          </div>
          <div className="container-tela-combate">
            <div className="personagem1">
              {this.state.flagDano1 && this.renderPersonagem1()}
              <span className="voce" />
            </div>
            <div className="personagem2">
              {this.state.flagDano2 && this.renderPersonagem2()}
            </div>
          </div>
        </div>
        {this.renderFimDaBatalha()}
      </div>
    );
  }
}
