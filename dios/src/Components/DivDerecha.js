import React, { Component } from 'react';

import Primero from "./DivsLadoDerecho/PrimerDivDerecha";
import Segundo from "./DivsLadoDerecho/SegundoDivDerecha";

export class DivDerecha extends Component {
    
  render() {

    return (
      
    <div className="col-lg-4 noPadding divAy">
       <Primero/>
       <Segundo/>
    </div>
 
    );
  }
}

export default DivDerecha;