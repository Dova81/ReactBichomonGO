import React, { Component } from 'react';

import {withRouter, Link} from "react-router-dom";
import "./Css/PrimerDiv.css"


class PrimerDivMedio extends Component {

  dameFotos (bichos){
    return bichos.map(b => 
      <Link to={{
        pathname:'/entrenador/pokemon',
        state:{
          pokemon:b
        }
      }}>
      
    <img src={b.especie.urlFoto} alt={b.especie.nombre} className="ajustarGim" />
    </Link>
  )
  }

    
  render() {
    
    return (
      
                <div className="col-lg-5 divBorder noPadding backgroundPokemon divAy">
                {this.dameFotos(this.props.pokes.bichos)}
                </div>

    );
  }
}

export default withRouter (PrimerDivMedio);