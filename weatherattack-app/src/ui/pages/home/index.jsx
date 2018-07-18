import React, { Component } from "react";
import { GeolocationService } from "app-services";
import { Desafio, HistoricoDeBatalhas } from "app-components";
import "./index.css";

export class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      latitude: "",
      longitude: "",
      mensagemErro: "",
      temperatura: "",
      vento: "",
      chuva: "",
      neve: "",
      tempestade: "",
      cidade: "",
      loading: true
    };

    this.mostrarDesafio = this.mostrarDesafio.bind(this);
    this.geolocationService = new GeolocationService();
    this.getLocation = this.getLocation.bind(this);
    this.sendGeolocation = this.sendGeolocation.bind(this);
    this.showPosition = this.showPosition.bind(this);
  }

  componentDidMount() {
    this.getLocation();
  }

  desactiveLoader() {
    setTimeout(() => {
      this.showLoader(false);
    }, 5000);
  }

  showLoader(loading) {
    this.setState({
      loading
    });
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
        this.setState({
          temperatura: response.temperatura,
          chuva: response.chuva,
          neve: response.neve,
          tempestade: response.tempestade,
          vento: response.vento,
          cidade: response.nomeDaCidade,
          mostrarPoderes: true
        });
        this.mostrarDesafio();
      })
      .catch(() => {
        this.setState({
          mensagemErro: "Não pode contatar o servidor"
        });
      });
  }

  mostrarDesafio() {
    if (this.state.mostrarPoderes) {
      return (
        <Desafio
          listaUsuariosConectados={this.props.usuariosConectados}
          latitude={this.state.latitude}
          longitude={this.state.longitude}
          socket={this.props.socket}
          usuariosConectados={this.props.usuariosConectados}
        />
      );
    }
  }

  render() {
    return (
      <div className="home">
        <span className="pagina-home">Home</span>
        <div className="conteudo-home">
          <div className="historico-em-home">
            <HistoricoDeBatalhas />
          </div>
          <div className="dashboard-em-home">{this.mostrarDesafio()}</div>
        </div>
      </div>
    );
  }
}
