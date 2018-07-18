import React, { Component } from "react";
import { Switch, Route } from "react-router-dom";
import { LoginPage, Dashboard, Home, Perfil, Ranking, Loja } from "app-pages";
import "./App.css";

class App extends Component {
  constructor() {
    super();
    this.state = {};
  }

  render() {
    return (
      <div className="App">
        <Switch>
          <Route path="/" exact component={LoginPage} />
          <Route path="/login" exact component={LoginPage} />
          <Route path="/dashboard" exact component={Dashboard} />
          <Route path="/home" component={Home} />
          <Route path="/perfil" component={Perfil} />
          <Route path="/ranking" component={Ranking} />
          <Route path="/loja" component={Loja} />
        </Switch>
      </div>
    );
  }
}

export default App;
