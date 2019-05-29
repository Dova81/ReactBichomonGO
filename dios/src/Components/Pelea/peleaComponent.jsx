import React, { Component } from 'react';
import './pelea.css';
import gifPelea from './cambio.gif'
import {Button} from 'reactstrap'



class PeleaComponent extends Component {
    
  constructor(props){
    super(props);
    const informe_ =this.props.location.state
    this.state = {
      ganador:informe_.ganador.especie.nombre,
      perdedor:informe_.perdedor
    } 
  }

  chequearCampeonInexistente(){
    if(!this.perdedor){
      return <p className='typewriter-text2'>No habia Campeon</p>
    }else{
      return <p className='typewriter-text2'>El Pokemon {this.state.perdedor.especie.nombre} Perdio</p>
    }
  }

  volver(){
    this.props.history.push('/entrenador',)
  }
   

  
  renderData(){
    
    
    return(
    <div class="row  divBorderStat  backgroundPokemon">
        <div className='col-md-9 noMargin'>  
        <img src={gifPelea} alt={'pum pum paw'} className="imgAjustar" />
        </div>
        <div className='col-md-3 pixel'>       
          <p className='typewriter-text1'>El Pokemon {this.state.ganador} es Victorioso</p><br/>
          {this.chequearCampeonInexistente()}
          <Button onClick={this.volver.bind(this)} color="danger">DUELO</Button>
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

export default PeleaComponent;