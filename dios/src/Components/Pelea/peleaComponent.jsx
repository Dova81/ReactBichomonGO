import React, { Component } from 'react';
import './pelea.css';
import gifPelea from './cambio.gif'
import {Button} from 'reactstrap'
import { connect } from 'react-redux';
import ApiComponent from "../ApiComponent"


class PeleaComponent extends Component {
    
  constructor(props){
    super(props);
    const informe_ =this.props.location.state
    this.state = {
      entrenador:informe_.nombreEntrenador,
      ganador:informe_.infoPelea.ganador.especie.nombre,
      perdedor:informe_.infoPelea.perdedor,
      ataques:informe_.infoPelea.ataques,
    } 
  }


  dameAtaques(){
    return this.state.ataques.map(b => 
     <p className={'typewriter-text'+this.state.ataques.indexOf(b)+3}>{b.atacante.especie.nombre} ataco por {b.puntos} de daño</p>
    )
  }

  chequearCampeonInexistente(){
    
    if(this.state.perdedor == undefined){
      return <p className='typewriter-text2'>No habia Campeon</p>
    }else{
      return <p className='typewriter-text2'>{this.state.perdedor.especie.nombre} Perdio!</p>
    }
  }

  volver(){
    ApiComponent.getEntrenador('/entrenador/'+this.state.entrenador)
    .then(responder =>this.props.dispatch({
      type:"SET",
      payload:responder
    }))
    this.props.history.push('/entrenador',)
  }
   

  
  renderData(){
    
    
    return(
    <div class="row  divBorderStat  backgroundPokemon">
        <div className='col-md-9 noMargin'>  
        <img src={gifPelea} alt={'pum pum paw'} className="imgAjustar" />
        </div>
        <div className='col-md-3 pixel'>       
          <p className='typewriter-text1'>{this.state.ganador} es Victorioso!</p><br/>
          {this.chequearCampeonInexistente()}
          {this.dameAtaques()}
          <Button onClick={this.volver.bind(this)} color="danger">Inicio</Button>
        </div>
        
    </div>
    )}

  render() {
    
    return (
      
      <div class="container containerEspecial">
        {this.renderData()}
      </div>

    );
  }
}

export default connect()(PeleaComponent);