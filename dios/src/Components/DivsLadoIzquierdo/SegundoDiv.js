import React, { Component } from 'react';
import axios from 'axios'
import "./SegundoDiv.css"

export class SegundoDiv extends Component {
  render() {
    return (

      <div className="divBorder ">

        <p>            
        <img src={ require('./Imagenes/red.png')  } alt="" className="acomodarRed" />
        </p>                                       
        <img src={ require('./Imagenes/stats.png')  } alt="" className="acomodarStats" />
      </div>
    );
  }
}

export default SegundoDiv;