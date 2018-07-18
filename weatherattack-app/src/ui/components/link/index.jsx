import React, { Component } from "react";
import "./index.css";

export class Link extends Component {
  render() {
    return (
      <div className="div-link">
        <a className="link" onClick={this.props.onClick} href={this.props.href}>
          {this.props.text}
        </a>
      </div>
    );
  }
}
