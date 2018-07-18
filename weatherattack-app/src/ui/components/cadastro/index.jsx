import React, { Component } from "react";
import { Button, Input, Link } from "app-components";
import Alert from "react-s-alert";
import { RegisterService } from "app-services";
import { ValidacaoErro } from "../validacaoErro";

function validateEmail(email) {
  var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}

function validateSenha(senha, confirmarSenha) {
  return senha === confirmarSenha;
}

export class Cadastro extends Component {
  constructor() {
    super();
    this.state = {
      nome: "",
      apelido: "",
      email: "",
      senha: "",
      confirmarSenha: "",
      validations: {
        email: {
          isValid: true,
          message: "email inválido"
        },
        senha: {
          isValid: true,
          message: "senha não confere"
        }
      }
    };
    this.handleChange = this.handleChange.bind(this);
    this.onClickCadastrar = this.onClickCadastrar.bind(this);
    this.renderErrorComponent = this.renderErrorComponent.bind(this);
    this.validacao = this.validacao.bind(this);
    this.registerService = new RegisterService();
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

  validacao() {
    let validations = this.state.validations;
    validations.email.isValid = validateEmail(this.state.email);
    validations.senha.isValid = validateSenha(
      this.state.senha,
      this.state.confirmarSenha
    );

    const allValidations = [
      validations.email.isValid,
      validations.senha.isValid
    ];
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
    return (
      <div>
        <Input
          name="email"
          onChange={this.handleChange}
          value={this.state.email}
          label="E-mail"
          type="email"
          class="input"
        />
      </div>
    );
  }

  renderSenha() {
    return (
      <div>
        <Input
          name="senha"
          onChange={this.handleChange}
          value={this.state.senha}
          label="Senha"
          type="password"
          class="input"
        />

        <Input
          name="confirmarSenha"
          onChange={this.handleChange}
          value={this.state.confirmarSenha}
          label="Confirmar Senha"
          type="password"
          class="input"
        />
      </div>
    );
  }

  showErrorAlert() {
    Alert.success("Cadastro Efetuado!", {
      position: "bottom-left",
      effect: "bouncyflip",
      beep: false,
      timeout: 5000,
      offset: 100
    });
  }

  onClickCadastrar() {
    const usuario = {
      nome: this.state.nome,
      apelido: this.state.apelido,
      email: this.state.email,
      senha: this.state.senha
    };

    this.registerService
      .save(usuario)
      .then(() => {})
      .catch(resp => {
        this.setState({
          error: resp.response.data.error
        });
      });
    this.showErrorAlert();
    this.props.renderizarLogin();
  }

  render() {
    return (
      <div className="cadastro-usuario">
        <Input
          name="nome"
          onChange={this.handleChange}
          value={this.state.nome}
          label="Nome"
          type="text"
          class="input"
        />
        <Input
          name="apelido"
          onChange={this.handleChange}
          value={this.state.apelido}
          label="Apelido"
          type="text"
          class="input"
        />
        {this.renderEmail()}
        {this.renderSenha()}{" "}
        <Button
          className="button-login"
          text="Cadastrar"
          type="Button"
          onClick={this.onClickCadastrar}
        />
        <Link text="Voltar" onClick={this.props.renderizarLogin} />
        <Alert stack={{ limit: 3 }} />
      </div>
    );
  }
}
