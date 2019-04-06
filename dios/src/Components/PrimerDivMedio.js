import React, { Component } from 'react';
import "./Css/PrimerDiv.css"
import {Nisman} from "./Css/Bichomones/Nisman.jpg"
import {RicardoFort} from "./Css/Bichomones/RicardoFort.jpg"
import {RicardoMilos} from "./Css/Bichomones/RicardoMilos.jpg"


export class PrimerDivMedio extends Component {
    
  render() {

    function insertarImg(img1,img2,img3){ 
        let imagenes = [];
        imagenes.push(img1);
        imagenes.push(img2);
        imagenes.push(img3);
        console.log(imagenes);
        
        imagenes.forEach(element => {
            return (<img src={ require(''+element.className)  } alt="" className="ajustar" />)
        });
    }

    return (
      <div className="container">
            <div className="row">
                <div className="col-xs-12 divBorder">
                <img src={ require("./Css/Bichomones/Nisman.jpg")  } alt="" className="ajustarGim" />
                <img src={ require("./Css/Bichomones/RicardoFort.jpg")  } alt="" className="ajustarGim" />
                <img src={ require("./Css/Bichomones/RicardoMilos.jpg")  } alt="" className="ajustarGim" />

                </div>
            </div>  
      </div>
    );
  }
}

export default PrimerDivMedio;