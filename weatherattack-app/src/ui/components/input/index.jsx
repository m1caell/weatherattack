import React, { Component } from "react";
import "./index.css";

export class Input extends Component {
  render() {
    return (
      <div className="class-input">
        <input
          name={this.props.name}
          placeholder={this.props.placeholder}
          funcaoError={this.props.funcaoError}
          type={this.props.type}
          onChange={this.props.onChange}
          value={this.props.value}
          className={this.props.class}
          required
          id={this.props.name}
          autoComplete="off"
        />
        <label for={this.props.name}>
          <span>{this.props.label}</span>
        </label>
      </div>
    );
  }
}
