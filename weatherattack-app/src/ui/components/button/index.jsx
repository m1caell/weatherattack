import React, { Component } from "react";
import "./index.css";
export class Button extends Component {
  render() {
    return (
      <div className="button">
        <button
          className={this.props.className}
          onClick={this.props.onClick}
          type={this.props.type}
        >
          {this.props.text}
        </button>
      </div>
    );
  }
}
