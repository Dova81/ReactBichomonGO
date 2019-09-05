import React, { Component } from 'react';
import "./SegundoDiv.css"

export class TercerDiv extends Component {
  render() {
    return (
      
                <div className="divBorder">
                  <img src={ require('./Imagenes/busqueda.gif')  } alt="buscarPokemon" className="ajustarBusqueda"/>
                </div>
    );
  }
}

export default TercerDiv;