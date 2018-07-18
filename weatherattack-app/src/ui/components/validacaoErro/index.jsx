import React from "react";

export class ValidacaoErro extends React.Component {
  render() {
    return <div className="validacao-erro">{this.props.texto}</div>;
  }
}
