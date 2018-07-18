import React, { Component } from "react";
import { Input, Button } from "app-components";
import "./index.css";

class ListaDePoderes extends Component {
  constructor() {
    super();
    this.state = {
      poderes: [],
      poder: ""
    };
    this.onClickAtacar = this.onClickAtacar.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  handleChange(event) {
    //deve vir por props
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({
      [name]: value
    });
  }

  componentDidMount() {
    this.setState({
      poderes: this.props.poderes
    });
  }

  renderPoderes() {
    if (this.state.poderes.length > 0) {
      return this.state.poderes.map((poder, key) => {
        return (
          <div className="ListaDePoderes-lista-poder">
            <Button
              onClick={() => this.props.onClickAtacar(poder.id)}
              type={"button"}
              text={`Lançar ${poder.nome}!!!`}
            />
          </div>
        );
      });
    } else {
      return null;
    }
  }

  render() {
    return (
      <div className="ListaDePoderes-lista">
        <form>{this.renderPoderes()}</form>
      </div>
    );
  }
}

export default ListaDePoderes;
