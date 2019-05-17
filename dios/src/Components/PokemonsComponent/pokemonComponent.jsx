import React, { Component } from 'react';
import './state.css'



class PokemonComponent extends Component {
    
  constructor(props){
    super(props);
    const pokemon =this.props.location.state.pokemon
    this.state = {
     idPoke:pokemon.id,
     nombre:pokemon.especie.nombre,
     tipo:pokemon.especie.tipo,
     victorias:pokemon.victorias,
     fechaCaptura:pokemon.fechaCaptura,
     urlFoto:pokemon.especie.urlFoto

  } 
  }

  renderData(){
    return(
    <div class="row  divBorderStat  backgroundPokemon pixel">  
    <div className='col-md-3'>
    <img src={this.state.urlFoto} alt={this.state.nombre} className="ajustar" />
    </div>
    <div className='col-md-2'>
    <h3>
    Nombre:{ this.state.nombre}<br/>
    Tipo:{this.state.tipo}<br/>
    Victorias:{this.state.victorias}<br/>
    Fecha de captura:{this.state.fechaCaptura}<br/>
    </h3>
    </div>
    </div>
    )}

  render() {
    console.log(this.state)
    
    
    return (
      
      <div class="container containerEspecial">
        {this.renderData()}
      </div>

    );
  }
}

export default PokemonComponent;