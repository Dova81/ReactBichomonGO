import React, { Component } from 'react';
import "./Css/PrimerDiv.css"

import {SegundoDiv} from "./DivsLadoIzquierdo/SegundoDiv"
import {TercerDiv} from "./DivsLadoIzquierdo/TercerDiv"

export class DivIzquierda extends Component {
  render() {
    return (
      
              <div className="col-lg-3 noPadding">
                <SegundoDiv/>
                <TercerDiv/>
              </div>
            
    );
  }
}

export default DivIzquierda;