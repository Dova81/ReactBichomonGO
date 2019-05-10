import React, { Component } from 'react';
import './App.css';
import ApiComponent from "./Components/ApiComponent"

import {BrowserRouter} from "react-router-dom";
import { Switch, Route } from 'react-router';
import {PagStats} from "./Components/PagStats"
import {Selector} from "./Components/Selector"


export class App extends Component {


  constructor(props){
    super(props);
  }

  render() {
    return (

     
        <BrowserRouter>
          <Switch>
            <Route exact path="/" render={(props) => <Selector {...props} />} />
            <Route exact path={"/entrenador"} render={(props) => <PagStats {...props} />} />
          </Switch>
        </BrowserRouter>  
      
    );
  }
}




export default App


