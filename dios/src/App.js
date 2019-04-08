import React, { Component } from 'react';
import './App.css';

import {DivIzquierda} from "./Components/DivIzquierda";
import {DivDerecha} from "./Components/DivDerecha";

import {PrimerDivMedio} from "./Components/PrimerDivMedio";

class App extends Component {
  render() {
    return (
     
      <div>
        
            <DivIzquierda/>
            <PrimerDivMedio/>
            <DivDerecha/>
      
      </div>
      
    );
  }
}

export default App;
