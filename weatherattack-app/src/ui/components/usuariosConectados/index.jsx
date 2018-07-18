import React, { Component } from "react";
import { Button, NotificacaoConviteRecusado } from "app-components";
import "./index.css";
import axios from "axios";
import { UserService } from "../../../services/user";
import { CardOnline } from "../cardOnline";

export class UsuariosConectados extends Component {
  constructor(props) {
    super(props);
    this.state = {
      idUsuarioAtual: "",
      conectado: false,
      idUsuario: "",
      emailUsuario: "",
      apelidoUsuario: "",
      token: "",
      latitude: "",
      longitude: "",
      mensagemErro: "",
      mensagem: "",
      socket: null,
      notificacaoReceberDesafio: "",
      channel: "",
      notificacaoGeral: "",
      carregar: true,
      mostrarConvite: false
    };

    this.carregarListaUsuarios = this.carregarListaUsuarios.bind(this);
  }

  componentDidMount() {
    this.carregarListaUsuarios();
    this.setState({
      idUsuarioAtual: UserService.getLoggedUser().id,
      socket: this.props.socket
    });
  }

  carregarListaUsuarios() {
    if (this.props.listaUsuariosConectados) {
      let lista = this.props.listaUsuariosConectados;
      return lista.map(usuario => {
        if (this.props.idUsuarioAtual !== usuario.id) {
          return (
            <CardOnline lista={usuario}/>
          );
        } else {
          return undefined;
        }
      });
    }
  }

  render() {
    return (
      <div className="usuarios-conectados">
        <span className="titulo-usuarios-conectados">Usuário Online</span>
        {this.carregarListaUsuarios()}
      </div>
    );
  }
}
