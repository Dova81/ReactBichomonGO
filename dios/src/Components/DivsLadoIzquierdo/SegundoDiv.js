import React, { Component } from 'react';
import ApiComponent from "/Users/user//Documents/GitHub/ReactBichomonGO/dios/src/Components/ApiComponent"
import "./SegundoDiv.css"

export class SegundoDiv extends Component {
  constructor(props){
    super(props);
    this.state ={
      nombre:{
        
          nombre: '',
          ubicacion: {
              type: '',
              id: 0,
              nombre: ''
          },
          bichos: [
              {
                  id: 6,
                  especie: {
                      id: 2,
                      nombre: '',
                      altura: 0,
                      peso: 0,
                      tipo: '',
                      energiaInicial: '',
                      urlFoto: '',
                      cantidadBichos: '',
                      evolucion: ''
                  },
                  energia: '',
                  victorias: '',
                  fechaCaptura: ''
              }
          ],
          xp: '',
          monedas: '',
          nivel: '',
          ultimaCaptura:''
      

      },
    };
  }


 componentDidMount(){
  ApiComponent.get('/entrenador/Lucas').then(responder =>this.setState({nombre:responder}))
 }
  



  render() {
    return (
      
      <div className="divBorder ">

        <p>            
        <img src={ require('./Imagenes/red.png')  } alt="" className="acomodarRed" />
         {console.log(this.state.nombre)} 
         
          
          { this.state.nombre.nombre}<br/>
          { this.state.nombre.ubicacion.nombre}

        </p>                                       
        <img src={ require('./Imagenes/stats.png')  } alt="" className="acomodarStats" />
      </div>
    );
  }
}

export default SegundoDiv;