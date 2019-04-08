import React, { Component } from 'react';
import "./Css/PrimerDiv.css"
import {Nisman} from "./Css/Bichomones/Nisman.jpg"
import {RicardoFort} from "./Css/Bichomones/RicardoFort.jpg"
import {RicardoMilos} from "./Css/Bichomones/RicardoMilos.jpg"


export class PrimerDivMedio extends Component {
    
  render() {

    return (
      
                <div className="col-lg-5 divBorder noPadding">
                <img src={ require("./Css/Bichomones/Nisman.jpg")  } alt="" className="ajustarGim" />
                <img src={ require("./Css/Bichomones/RicardoFort.jpg")  } alt="" className="ajustarGim" />
                <img src={ require("./Css/Bichomones/RicardoMilos.jpg")  } alt="" className="ajustarGim" />
                </div>

    );
  }
}

export default PrimerDivMedio;