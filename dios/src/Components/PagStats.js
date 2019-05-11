import React, { Component } from 'react';
import './App.css';
import ApiComponent from "./ApiComponent"
import {DivIzquierda} from "./DivIzquierda";
import {DivDerecha} from "./DivDerecha";
import PrimerDivMedio from "./PrimerDivMedio";
import { connect } from 'react-redux';
import {Router, Route} from "react-router"

class PagStats extends Component {

  constructor(props) {
    super(props);

}

  render() {
    return (
     
      <div>
            
            { this.props.user && <DivIzquierda entrenador={this.props.user}/>}
            
            { this.props.user && <PrimerDivMedio pokes={this.props.user}/>}
            <DivDerecha/>
      
      </div>
      
    );
  }
}

const mapStateToProps = (state) => {
  return{
    user: state.entrenador,  
  };
  
};


export default connect(mapStateToProps)(PagStats)


