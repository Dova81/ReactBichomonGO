import React, { Component } from 'react';
import ApiComponent from "./ApiComponent"
import { connect } from 'react-redux';


class Selector extends Component {


  constructor(props) {
    super(props);
    this.state = {
        entrenador:'',
    } 

}

  componentDidMount(){
    
    const entrenadorElegido = this.state.entrenador
    
    ApiComponent.getEntrenador('/entrenador/'+entrenadorElegido)
    .then(responder =>this.props.dispatch({
      type:"SET",
      payload:responder
    }))
   }

   setEntrenador(elem){

    this.state.entrenador = elem.target.value;
    
    
    

    this.componentDidMount();

    this.props.history.push("/entrenador")    
}

   entrenadores() {
    const pronvincias = ["Elegir entrenador","Lucas", "Pedro", "Ash", "Misty", "Pepe", "Alberto", "Julio", "Andrea", "Ana"]
    return pronvincias.map(prov => <option>{prov}</option>);
}

  render() {
    return (
        <div class="centrarSelector">
          <form>
          <select class="form-control col-sm-4" id="selectProvinicias"  onChange={event => this.setEntrenador(event)}>{this.entrenadores()}</select>
          </form>
      </div>
    );
  }
} 

const mapStateToProps = (state) => {
  
  return{
    user: state.entrenador,  
  };
  
};

export default connect(mapStateToProps)(Selector)