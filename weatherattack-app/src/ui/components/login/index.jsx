import React from "react";
import { Input, Button, Link } from "app-components";
import { UserService } from "app-services";
import { ValidacaoErro } from "../validacaoErro";
import { Redirect } from "react-router-dom";
import Alert from "react-s-alert";

function validateEmail(email) {
  var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}

export class Login extends React.Component {
  constructor() {
    super();

    this.userService = new UserService();

    this.state = {
      email: "",
      senha: "",
      isValid: "",
      mensagemErro: "",
      shouldRedirectDashboard: false,
      validations: {
        email: {
          isValid: true,
          message: "email inválido"
        }
      }
    };

    this.handleChange = this.handleChange.bind(this);
    this.onClickLogin = this.onClickLogin.bind(this);
    this.renderEmail = this.renderEmail.bind(this);
    this.renderErrorComponent = this.renderErrorComponent.bind(this);
    this.validacao = this.validacao.bind(this);
    this.goDashboard = this.goDashboard.bind(this);
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({
      [name]: value
    });

    this.validacao();
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

  validacao() {
    let validations = this.state.validations;
    validations.email.isValid = validateEmail(this.state.email);

    const allValidations = [validations.email.isValid];

    const hasInvalidFields =
      allValidations.filter(isValid => isValid === false).length > 0;

    if (hasInvalidFields) {
      this.setState({ validations });
    }
  }

  renderErrorComponent(isValid, message) {
    let ErrorComponent = isValid ? null : <ValidacaoErro texto={message} />;

    return ErrorComponent;
  }

  renderEmail() {
    const emailValidation = this.state.validations.email;
    return (
      <div>
        <Input
          label="E-mail"
          name="email"
          type="email"
          value={this.state.email}
          onChange={this.handleChange}
          class="input"
          funcaoError={this.renderErrorComponent(
            emailValidation.isValid,
            emailValidation.message
          )}
        />
      </div>
    );
  }

  goDashboard() {
    this.setState({
      shouldRedirectDashboard: true
    });
  }

  onClickLogin() {
    let usuario = {
      email: "",
      senha: ""
    };

    usuario.email = this.state.email;
    usuario.senha = this.state.senha;

    this.userService
      .logar(usuario)
      .then(res => {
        if (!!res.accessToken) {
          UserService.setLoggedUser(res.accessToken);
        }
        this.goDashboard();
      })
      .catch(err => {
        this.setState({
          mensagemErro: "Usuário não autenticado!"
        });
        this.showErrorAlert();
      });
  }

  render() {
    if (this.state.shouldRedirectDashboard) {
      return <Redirect to="/dashboard" />;
    }
    return (
      <div className="login-usuario">
        {this.renderEmail()}
        <Input
          label="Senha"
          name="senha"
          type="password"
          value={this.state.senha}
          onChange={this.handleChange}
          class="input"
        />

        <Button className="button-login" type="button" text="Entrar" onClick={this.onClickLogin} />
        <Link text="Criar nova conta" onClick={this.props.renderizarCadastro} />
        <Link text="Esqueci minha senha" />
      </div>
    );
  }
}
