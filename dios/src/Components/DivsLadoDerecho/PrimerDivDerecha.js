import React, { Component } from 'react';
import {Button} from 'reactstrap'
import ApiComponent from "../ApiComponent"
import { connect } from 'react-redux';



const imgTR ="./Imagenes/mapaRedTopDerecha.png";
const imgTI="./Imagenes/mapaRedTopIzquierda.png";
const imgBR="./Imagenes/mapaRedBotDerecha.png";
const imgBI="./Imagenes/mapaRedBotIzquierda.png";
const base="./Imagenes/mapa.jpg"

class PrimerDivDerecha extends Component {

  constructor(props){
    super(props);

    this.state ={
      buttonTR:base,
    }
  }

  settearEntrenador(lugar){
    
    
    ApiComponent.putMover('mapa/mover/'+this.props.user.nombre+'/'+lugar)
    .then(responder =>this.props.dispatch({
      type:"SET",
      payload:responder
    }))
   }

  cambiarImagenTR(){

    this.setState({buttonTR:imgTR});
    
    this.settearEntrenador('Plantalandia');
  }
  
  cambiarImagenTI(){ 
    
    this.setState({buttonTR:imgTI})
    this.settearEntrenador('St.Blah');
  }

  cambiarImagenBR(){
    
    this.setState({buttonTR:imgBR})

    this.settearEntrenador('Tibet Dojo');
  }

  cambiarImagenBI(){
    
    this.setState({buttonTR:imgBI})

    this.settearEntrenador('CobraKai');
  }



  render() {
    

    

    return (
      
              <div className="noPadding divBorder superponer">
                <img src={ require(this.state.buttonTR+"")  } alt="" className="ajustar" />
                <Button className="topleft" onClick={this.cambiarImagenTI.bind(this)}  variant="light"/>
                <Button className="topright" onClick={this.cambiarImagenTR.bind(this)} variant="light"/>
                <Button className="bottomleft" onClick={this.cambiarImagenBI.bind(this)} variant="light"/>
                <Button className="bottomright" onClick={this.cambiarImagenBR.bind(this)} variant="light"/>

              </div>
            
    );
  }
}

const mapStateToProps = (state) => {
  return{
    user: state.entrenador,  
  };
  
};

export default connect(mapStateToProps)(PrimerDivDerecha)