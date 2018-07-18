import React from "react";
import "./index.css";
import { GeolocationService } from "app-services";
import { Button, Loading } from "app-components";
import { Alert } from "react-s-alert";

const CHUVOSO = "chuvoso";
const ENSOLARADO = "ensolarado";
const NEVE = "neve";
const TEMPESTADE = "tempestade";
let temperaturaGlobal = 0;

export class Tempo extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      icone: "",
      situacaoClima: "Ensolarado",
      temperatura: "",
      vento: "",
      chuva: "",
      neve: "",
      tempestade: "",
      cidade: "",
      mostrarClima: false,
      mensagemErro: ""
    };

    this.geolocationService = new GeolocationService();
    this.getLocation = this.getLocation.bind(this);
    this.sendGeolocation = this.sendGeolocation.bind(this);
    this.showPosition = this.showPosition.bind(this);
    this.showError = this.showError.bind(this);
    this.verificaClima = this.verificaClima.bind(this);
    this.populaDadosNoComponente = this.populaDadosNoComponente.bind(this);
    this.onClickAtualizarTempo = this.onClickAtualizarTempo.bind(this);
  }

  static getTemperatura() {
    return temperaturaGlobal;
  }

  verificaClima() {
    if (this.state.chuva <= 0 && this.state.tempestade <= 0) {
      this.setState({
        situacaoClima: "Ensolarado",
        icone: ENSOLARADO
      });
    }
    if (this.state.chuva > 0) {
      this.setState({
        situacaoClima: "Chuvoso",
        icone: CHUVOSO
      });
    }
    if (this.state.tempestade > 0) {
      this.setState({
        situacaoClima: "Tempestade",
        icone: TEMPESTADE
      });
    }
    if (this.state.neve > 0) {
      this.setState({
        situacaoClima: "Neve",
        icone: NEVE
      });
    }

    this.populaDadosNoComponente();
    this.setState({
      mostrarClima: true
    });
  }

  componentDidMount() {
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
      () => {
        this.sendGeolocation();
      }
    );
  }

  sendGeolocation() {
    const geolocation = {
      latitude: this.state.latitude,
      longitude: this.state.longitude
    };
    this.geolocationService
      .enviarInformacoes(geolocation)
      .then(response => {
        (this.temperaturaGlobal = response.temperatura),
          this.setState({
            temperatura: response.temperatura,
            chuva: response.chuva,
            neve: response.neve,
            tempestade: response.tempestade,
            vento: response.vento,
            cidade: response.nomeDaCidade
          });
        this.verificaClima();
      })
      .catch(() => {
        this.setState({
          mensagemErro: "Não pode contactar o servidor"
        });
      });
  }

  showError(error) {
    switch (error.code) {
      case error.PERMISSION_DENIED:
        this.setState({
          mensagemErro: "Aceite a geolocalização para poder jogar."
        });
        break;
      case error.POSITION_UNAVAILABLE:
        this.setState({
          mensagemErro: "Informações da localização indispoível."
        });
        break;
      case error.TIMEOUT:
        this.setState({
          mensagemErro:
            "A solicitação para obter a localização do usuário atingiu o tempo limite."
        });
        break;
      case error.UNKNOWN_ERROR:
        this.setState({
          mensagemErro: "Ocorreu um erro desconhecido."
        });
        break;
      default:
        this.setState({
          mensagemErro: ""
        });
    }
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

  onClickAtualizarTempo() {
    this.getLocation();
    this.populaDadosNoComponente();
  }

  populaDadosNoComponente() {
    return (
      <div className="dados">
        <div className="dados-wrapper">
          <div className="graus">{parseInt(this.state.temperatura, 10)}º</div>
          <div className="clima">
            <div className="clima-info">{this.state.situacaoClima}</div>
            <div className="clima-info">Vento: {this.state.vento}Km/h</div>
            <div className="clima-info">{this.state.cidade}</div>
          </div>
        </div>
        <div className="dados-wrapper">
          <div className={`${this.state.icone} icone`} />
          <Button
            className="button-atualizar-tempo"
            onClick={this.onClickAtualizarTempo}
            text="Atualizar"
          />
        </div>
      </div>
    );
  }

  render() {
    if (this.state.mostrarClima) {
      return (
        <div className={this.props.className}>
          <div className="tempo-blur" />
          {this.populaDadosNoComponente()}
        </div>
      );
    } else {
      return (
        <div className={this.props.className}>
          <div className="tempo-blur" />
          <div className="dados">
            <div className="erro-tempo">Não pode contactar o servidor</div>
          </div>
        </div>
      );
    }
  }
}
