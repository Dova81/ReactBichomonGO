import React, { Component } from 'react';
import './App.css';

import {PrimerDiv} from "./Components/PrimerDiv"
import {SegundoDiv} from "./Components/SegundoDiv"
import {TercerDiv} from "./Components/TercerDiv"
import {PrimerDivMedio} from "./Components/PrimerDivMedio"

class App extends Component {
  render() {
    return (
     
      <div class="container">
      <div class="row">
        <div class="col-lg-4">
         <SegundoDiv/>
         <PrimerDiv/>
         <TercerDiv/>
        </div>
        <div class="col-lg-4">
         
         <PrimerDivMedio/>
         
        </div>
      </div>
    </div>
      
    );
  }
}

export default App;
