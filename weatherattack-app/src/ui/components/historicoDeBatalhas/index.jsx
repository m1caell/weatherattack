import React, { Component } from "react";
import "./index.css";
import { LogCombateService, UserService } from "app-services";
import imgPerfil from "../../../img/imagemPerfil.svg";

const COR_VERDE = "borda-verde-combate";
const COR_VERMELHA = "borda-vermelha-combate";
const COR_VERDE_LETRA = "letras-verde-combate";
const COR_VERMELHA_LETRA = "letras-vermelha-combate";

export class HistoricoDeBatalhas extends Component {
  constructor(props) {
    super(props);

    this.state = {
      listaHistorico: [],
      idUsuarioAtual: ""
    };

    this.renderHistorico = this.renderHistorico.bind(this);
    this.logCombateService = new LogCombateService();
  }

  componentDidMount() {
    const user = UserService.getLoggedUser();
    this.logCombateService
      .getListaCombates()
      .then(resp => {
        this.setState({
          listaHistorico: resp,
          idUsuarioAtual: user.id
        });
      })
      .catch(resp => {});
  }

  converterSegundosEmMinutos(segundos) {
    let valor = segundos / 60;
    valor = valor.toFixed(2);
    return valor;
  }

  renderHistorico() {
    return this.state.listaHistorico.map((log, key) => {
      return (
        <div key={log.id} className="historico">
          <div className="evento-historico">
            <span className="logId">#{log.id}</span>
            <div>
              <img
                src={imgPerfil}
                alt="imagem de perfil"
                className={`imagem-desafiante-evento-combate desafiante-evento ${
                  log.vencedor.id === log.desafiante.id
                    ? COR_VERDE
                    : COR_VERMELHA
                }`}
              />
              <span
                className={`nome-desafiante-evento desafiante-evento ${
                  log.vencedor.id === log.desafiante.id
                    ? COR_VERDE_LETRA
                    : COR_VERMELHA_LETRA
                }`}
              >
                {log.desafiante.apelido}
              </span>
            </div>
            <span className="vs-evento">
              <span className="vs-separador">VS</span>
              <span className="hora-evento">
                {this.converterSegundosEmMinutos(log.duracaoEmSegundos)}
              </span>
            </span>
            <div>
              <img
                src={imgPerfil}
                alt="imagem de perfil"
                className={`imagem-desafiado-evento-combate desafiante-evento ${
                  log.vencedor.id === log.desafiado.id
                    ? COR_VERDE
                    : COR_VERMELHA
                }`}
              />
              <span
                className={`nome-desafiante-evento desafiante-evento ${
                  log.vencedor.id === log.desafiado.id
                    ? COR_VERDE_LETRA
                    : COR_VERMELHA_LETRA
                }`}
              >
                {log.desafiado.apelido}
              </span>
            </div>
          </div>
        </div>
      );
    });
  }

  render() {
    return (
      <div className="historico-de-batalhas">
        <h3>Últimas Batalhas</h3>
        <div className="content-historico-batalhas">
          {this.renderHistorico()}
        </div>
      </div>
    );
  }
}
