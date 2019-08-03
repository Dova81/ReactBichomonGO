import React, { Component } from 'react';
import ApiComponent from "../ApiComponent"
import { connect } from 'react-redux';


class SegundoDivDerecha extends Component {

  constructor(props){
    super(props);
    this.state = {
      eventos:[],
    } 
  }
    
  componentDidMount(){
    ApiComponent.getEntrenador('feed/entrenador/'+this.props.user.nombre)
    .then(eventos =>this.setState({eventos}))
  }


  dameEventos (){
    return this.state.eventos.map(e =>{

    if (e.type === 'EventoArribo'){
    return (<p>{e.entrenador} arribo a {e.destino} </p>)
    }else {
      return (<p>Coronaron a {e.coronado} </p>)
    }
  }
  )
  }

  render() {
    console.log(this.state.eventos);
    

    return (
      
    <div className="noPadding divBorder backgroundPokemon pixel">
       <p>{this.dameEventos()}</p>
    </div>
 
    );
  }
}

const mapStateToProps = (state) => {
  return{
    user: state.entrenador,  
  };
  
};

export default connect(mapStateToProps)(SegundoDivDerecha)