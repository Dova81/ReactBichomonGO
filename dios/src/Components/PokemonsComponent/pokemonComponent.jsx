import React, { Component } from 'react';



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

  } 
  }

  render() {
    console.log(this.state);
    
    
    return (
      
                <div>
                
                </div>

    );
  }
}

export default PokemonComponent;