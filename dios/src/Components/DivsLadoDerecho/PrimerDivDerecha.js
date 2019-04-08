import React, { Component } from 'react';
import {Button} from 'reactstrap'



const imgTR ="./Imagenes/mapaRedTopDerecha.png";
const imgTI="./Imagenes/mapaRedTopIzquierda.png";
const imgBR="./Imagenes/mapaRedBotDerecha.png";
const imgBI="./Imagenes/mapaRedBotIzquierda.png";
const base="./Imagenes/mapa.jpg"

export class PrimerDivDerecha extends Component {

  constructor(props){
    super(props);
    this.state ={
      buttonTR:base,
    }
  }

  cambiarImagenTR(){
    this.setState({buttonTR:imgTR});
  }
  
  cambiarImagenTI(){ 
    
    this.setState({buttonTR:imgTI})
  }

  cambiarImagenBR(){
    
    this.setState({buttonTR:imgBR})
  }

  cambiarImagenBI(){
    
    this.setState({buttonTR:imgBI})
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

export default PrimerDivDerecha;