import React, { Component } from 'react';
import ApiComponent from "../ApiComponent"
import { connect } from 'react-redux';
import './selector.css'
import logo from './Imagenes Selector/logo.png'
import tema from './theme2.mp3'
import start from './Imagenes Selector/start.gif'


class Selector extends Component {


  constructor(props) {
    super(props);
    this.selectRef= React.createRef();
    this.state = {
        entrenador:'',
        isVisible:true,
    } 

}

  componentDidMount(){
    
    const entrenadorElegido = this.state.entrenador
    
    ApiComponent.getEntrenador('/entrenador/'+entrenadorElegido)
    .then(responder =>this.props.dispatch({
      type:"SET",
      payload:responder
    }))

    document.addEventListener("keydown", this.handleKeyPress, false);
   }

   setEntrenador(elem){

    this.state.entrenador = elem.target.value;
    
    this.componentDidMount();

    this.props.history.push("/entrenador")    
}

handleKeyPress = (event) => {
  if(event.key === 'Enter'){
    this.setState({isVisible:false,})
    this.selectRef.current.focus(); 
  }
}



   entrenadores() {

    const pronvincias = ["Elegir Entrenador","Lucas", "Pedro", "Ash", "Misty", "Pepe", "Alberto", "Julio", "Andrea", "Ana"]
    return pronvincias.map(prov => <option>{prov}</option>);
}
  renderImagen(){
    return(
      <div id='fade' class="parent">
        <div class="inner"><img src={logo} alt='logo' className="ajustarGim" /></div>
      </div>
    )
  }

  render() {
    return (
        <div onKeyPress={this.handleKeyPress}>
          <div>
            <audio src={tema} autoplay="autoplay" loop="" ></audio>
            {this.renderImagen()}
          </div>

          <div id='fadeLong' class="centrarSelector">
            <form>
            <select ref={this.selectRef} class="form-control col-sm-4" id="selectProvinicias" onChange={event => this.setEntrenador(event)}>{this.entrenadores()}</select>
            </form>
          </div>
          <div class="inner">
            {this.state.isVisible?<img src={start} alt='logo' className="ajustarGim" />:null}
          </div>
        </div>  
    );
  }
} 



export default connect()(Selector)