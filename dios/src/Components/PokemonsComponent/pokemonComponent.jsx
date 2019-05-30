import React, { Component } from 'react';
import './state.css'
import {withRouter, Link} from "react-router-dom";
import ApiComponent from "../ApiComponent"
import {Button} from 'reactstrap'



class PokemonComponent extends Component {
    
  constructor(props){
    super(props);
    const pokemon =this.props.location.state.pokemon
    const entrenador_= this.props.location.state.entrenador
    this.state = {
     nombreEntrenador:entrenador_,
     idPoke:pokemon.id,
     nombre:pokemon.especie.nombre,
     tipo:pokemon.especie.tipo,
     victorias:pokemon.victorias,
     fechaCaptura:pokemon.fechaCaptura,
     urlFoto:pokemon.especie.urlFoto

  } 
  }

  pelear(){
    ApiComponent.putDuelo('bichos/duelo/'+this.state.nombreEntrenador+'/'+this.state.idPoke)
    .then(responder =>this.damePelea(responder))
   }
  

  damePelea(infoPelea_){
    this.props.history.push({
      pathname: '/entrenador/pelea',
      state: {
        infoPelea:infoPelea_,
        nombreEntrenador:this.state.nombreEntrenador,
      },
    })
  }
    
  

  renderData(){
    return(
    <div class="row  divBorderStat  backgroundPokemon">  
        <div className='col-md-4'>
            <img src={this.state.urlFoto} alt={this.state.nombre} className="ajustar" />
        </div>
        <div className='col-md-8 pixel'>
            <h3>
                <p>
                Nombre:{ this.state.nombre}<br className="ancho"/>
                Tipo:{this.state.tipo}<br className="ancho"/>
                Victorias:{this.state.victorias}<br className="ancho"/>
                Fecha de captura:{this.state.fechaCaptura}<br className="ancho"/>
                
                </p>
                <Button onClick={this.pelear.bind(this)} color="danger">DUELO</Button>
            </h3>
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

export default PokemonComponent;