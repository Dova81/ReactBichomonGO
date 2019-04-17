import React, { Component } from 'react';
import './App.css';
import ApiComponent from "./Components/ApiComponent"

import {Router, Route} from "react-router";
import {PagStats} from "./Components/PagStats"
import {Selector} from "./Components/Selector"


export class App extends Component {


  render() {
    return (


      <Router>
      <div>
        <Route path={"/"} component={Selector}/>
      </div>
      </Router>  
    );
  }
}




export default App


