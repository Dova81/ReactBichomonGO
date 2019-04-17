import React, { Component } from 'react';
import "./Css/PrimerDiv.css"


export class PrimerDivMedio extends Component {

  dameFotos (bichos){
    return bichos.map(b => <img src={b.especie.urlFoto} alt={b.especie.nombre} className="ajustarGim" />)
  }

 
    
  render() {
    
    return (
      
                <div className="col-lg-5 divBorder noPadding">
                {this.dameFotos(this.props.pokes.bichos)}
                </div>

    );
  }
}

export default PrimerDivMedio;