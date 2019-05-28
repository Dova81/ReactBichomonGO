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