import React, { Component } from "react";
import "./index.css";
import Alert from "react-s-alert";
import "react-s-alert/dist/s-alert-default.css";
import "react-s-alert/dist/s-alert-css-effects/bouncyflip.css";
import { Login, Cadastro, Tempo, Loading } from "app-components";

export class LoginPage extends Component {
  constructor() {
    super();
    this.state = {
      latitude: "",
      longitude: "",
      mensagemErro: "",
      shouldLogin: true,
      shouldRegister: false,
      loading: true
    };
    this.trocarParaLogin = this.trocarParaLogin.bind(this);
    this.trocarParaCadastro = this.trocarParaCadastro.bind(this);

    this.showLoader(true);
    this.desactiveLoader();
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

  showErrorAlert(e) {
    Alert.warning(this.state.mensagemErro, {
      position: "top-right",
      effect: "bouncyflip",
      beep: false,
      timeout: 5000,
      offset: 100
    });
  }

  renderCard() {
    if (this.state.mensagemErro === "") {
      return <Tempo className="tempo-component" />;
    } else {
      this.showErrorAlert();
    }
  }

  trocarParaCadastro() {
    this.setState({
      shouldLogin: false,
      shouldRegister: true
    });
  }

  trocarParaLogin() {
    this.setState({
      shouldLogin: true,
      shouldRegister: false
    });
  }

  renderForm() {
    if (this.state.shouldLogin) {
      return (
        <Login
          renderizarCadastro={this.trocarParaCadastro}
          getLocation={this.getLocation}
          latitude={this.latitude}
          longitude={this.longitude}
        />
      );
    } else if (this.state.shouldRegister) {
      return <Cadastro renderizarLogin={this.trocarParaLogin} />;
    }
  }

  render() {
    return (
      <div className="container-login">
        <Loading showLoader={this.state.loading} />
        <div className="content-login-left">
          <div className="content-login-inside">
            <div className="logo" />
            {this.renderForm()}
          </div>
        </div>
        {this.renderCard()}
        <Alert stack={{ limit: 3 }} />
        <div className="logo-crescer" />
      </div>
    );
  }
}
