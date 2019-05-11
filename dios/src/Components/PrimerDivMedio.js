import React, { Component } from 'react';
import {withRouter} from "react-router-dom";
import "./Css/PrimerDiv.css"


class PrimerDivMedio extends Component {

  dameFotos (bichos){
    return bichos.map(b => <img onClick={this.verStats} src={b.especie.urlFoto} alt={b.especie.nombre} className="ajustarGim" />)
  }

 verStats =() =>{
  this.props.history.push("/entrenador/pokemon")   
 }
    
  render() {
    
    return (
      
                <div className="col-lg-5 divBorder noPadding">
                {this.dameFotos(this.props.pokes.bichos)}
                </div>

    );
  }
}

export default withRouter (PrimerDivMedio);