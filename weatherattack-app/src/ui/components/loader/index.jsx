import React, { Component } from "react";
import "./index.css";

export class Loading extends Component {
  getClasses() {
    const showLoaderClass = this.props.showLoader
      ? "show-loader"
      : "hide-loader";
    return `loading ${showLoaderClass}`;
  }

  render() {
    return (
      <div id="loading" className={this.getClasses()}>
        <div id="load" className={this.getClasses()}>
          <div>G</div>
          <div>N</div>
          <div>I</div>
          <div>D</div>
          <div>A</div>
          <div>O</div>
          <div>L</div>
        </div>
      </div>
    );
  }
}
