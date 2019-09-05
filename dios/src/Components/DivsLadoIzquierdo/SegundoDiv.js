import React, { Component } from 'react';
import "./SegundoDiv.css";


export class SegundoDiv extends Component {
  render() {

    
    return (
      

      <div className='container divBorder'>
        <div className='row'>
          <div className="col-lg-5">
            <p>            
            <img src={ require('./Imagenes/red.png')  } alt="" className="acomodarRed" />
            </p>                                       
          </div>
          <div className='col-lg-6 backgroundPokemon pixel centrarStats'>
          <p>
          <u>Entrenador</u>: { this.props.entrenador.nombre}<br/>
          <u>Ubicacion</u>: {this.props.entrenador.ubicacion.nombre}<br/>
          <u>Nivel</u>: {this.props.entrenador.nivel}<br/>
          <u>Billetera</u>: {this.props.entrenador.monedas}<br/>
          </p>
          </div>
        </div>
      </div>
    );
  }
}

export default SegundoDiv;