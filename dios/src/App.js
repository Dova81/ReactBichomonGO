import React, { Component } from 'react';
import './App.css';
import ApiComponent from "./Components/ApiComponent"
import {DivIzquierda} from "./Components/DivIzquierda";
import {DivDerecha} from "./Components/DivDerecha";
import {PrimerDivMedio} from "./Components/PrimerDivMedio";
import { connect } from 'react-redux';

class App extends Component {
  constructor(){
    super();
  }

  componentDidMount(){
    ApiComponent.getEntrenador('/entrenador/Lucas')
    .then(responder =>this.props.dispatch({
      type:"SET",
      payload:responder
    }))
   }
    

  render() {
    return (
     
      <div>
            
            { this.props.user && <DivIzquierda entrenador={this.props.user}/>}
            
            <PrimerDivMedio/>
            <DivDerecha/>
      
      </div>
      
    );
  }
}

const mapStateToProps = (state) => {
  return{
    user: state.entrenador
  };
};

export default connect(mapStateToProps)(App)


