import React, { Component } from 'react';
import './App.css';
import ApiComponent from "./Components/ApiComponent"

import {BrowserRouter} from "react-router-dom";
import { Switch, Route } from 'react-router';
import PagStats from "./Components/PagStats"
import Selector from "./Components/Selector/Selector"
import Pokemon from "./Components/PokemonsComponent/pokemonComponent"
import { connect } from 'react-redux';
import PeleaComponent from './Components/Pelea/peleaComponent'


class App extends Component {


  constructor(props){
    super(props);
  }

  render() {
    return (

     
        <BrowserRouter>
          <Switch>
            <Route exact path="/" render={(props) => <Selector {...props} />} />
            <Route exact path="/entrenador" render={(props) => <PagStats {...props} />} />
            <Route exact path={"/entrenador/pokemon"} render={(props) => <Pokemon {...props} />} />
            <Route exact path={"/entrenador/pelea"} render={(props) => <PeleaComponent {...props} />} />
          </Switch>
        </BrowserRouter>  
      
    );
  }
}



export default connect()(App)


