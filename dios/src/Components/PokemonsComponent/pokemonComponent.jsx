import React, { Component } from 'react';
import './state.css'
import {withRouter, Link} from "react-router-dom";
import ApiComponent from "../ApiComponent"
import {Button} from 'reactstrap'
import Modal from 'react-modal';
import ModalImage from 'react-modal-image'
import gifRonny from './Ronny.gif'




class PokemonComponent extends Component {
    
  constructor(props){
    super(props);
    const pokemon =this.props.location.state.pokemon
    const entrenador_= this.props.location.state.entrenador
    this.state = {
      modalIsOpen:false,
     nombreEntrenador:entrenador_,
     idPoke:pokemon.id,
     nombre:pokemon.especie.nombre,
     tipo:pokemon.especie.tipo,
     victorias:pokemon.victorias,
     fechaCaptura:pokemon.fechaCaptura,
     urlFoto:pokemon.especie.urlFoto

  } 
  this.openModal = this.openModal.bind(this);
  this.closeModal = this.closeModal.bind(this);
  }

  pelear(){
    ApiComponent.putDuelo('bichos/duelo/'+this.state.nombreEntrenador+'/'+this.state.idPoke)
    .then(responder =>this.damePelea(responder)).catch(this.openModal())
   }
  
   openModal() {
    this.setState({modalIsOpen: true});
  }

  closeModal() {
    this.setState({modalIsOpen: false});
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
    
  volver(){
    this.props.history.push({
      pathname: '/entrenador',
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
                <Button onClick={this.volver.bind(this)} color="success">VOLVER</Button>
            </h3>
        </div>
    </div>
    )}

  render() {
    
    return (
      
      <div class="container containerEspecial">
         <Modal
          isOpen={this.state.modalIsOpen}
          onAfterOpen={this.afterOpenModal}
          onRequestClose={this.closeModal}
          contentLabel='gifRonny'
        >
          <img src={gifRonny} alt='Ronny' className="" />
          <p className='pixel'>
          Ah ah ah, El pokemon no puede luchar porque no esta en un Gimnasio
          </p>
        </Modal>
        {this.renderData()}
      </div>

    );
  }
}

export default PokemonComponent;