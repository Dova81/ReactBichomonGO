import React, { Component } from 'react';
import './App.css';
import ApiComponent from "./Components/ApiComponent"

import {BrowserRouter} from "react-router-dom";
import { Switch, Route } from 'react-router';
import {PagStats} from "./Components/PagStats"
import {Selector} from "./Components/Selector"


export class App extends Component {


  render() {
    return (


      <BrowserRouter>
        <Switch>
          <Route exact path="/" render={proops => <Selector {...proops} />} />
          <Route exact path={"/entrenador"} render={proops => <PagStats {...proops} />} />
        </Switch>
      </BrowserRouter>  

      
    );
  }
}




export default App


