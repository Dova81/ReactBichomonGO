import React, { Component } from 'react';
import "./Css/PrimerDiv.css"

export class PrimerDiv extends Component {
  render() {
    return (
      <div className="container">
            <div className="row">
                <div className="col-xs-12 divBorder">
                    <img src={ require('./Css/mapa.jpg')  } alt="" className="ajustar" />
                </div>
            </div>  
      </div>
    );
  }
}

export default PrimerDiv;