import React, { Component } from 'react';
import "./SegundoDiv.css";


export class SegundoDiv extends Component {
  render() {
    return (
      

      <div className='container divBorder'>
        <div className='row'>
          <div className="col-lg-6">
            <p>            
            <img src={ require('./Imagenes/red.png')  } alt="" className="acomodarRed" />
            </p>                                       
          </div>
          <div className='col-lg-6 backgroundPokemon pixel'>
          Entrenador:{ this.props.entrenador.nombre}<br/>
          Ubicacion:{this.props.entrenador.ubicacion.nombre}<br/>
          Nivel:{this.props.entrenador.nivel}<br/>
          Billetera:{this.props.entrenador.monedas}<br/>
          </div>
        </div>
      </div>
    );
  }
}

export default SegundoDiv;