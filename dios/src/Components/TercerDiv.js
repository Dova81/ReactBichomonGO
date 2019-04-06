import React, { Component } from 'react';
import "./Css/SegundoDiv.css"

export class TercerDiv extends Component {
  render() {
    return (
      <div className="container">
            <div className="row">
                <div className="col-xs-12 divBorder">
                    
                        
                        <img src={ require('./Css/Gims/gim1.jpg')  } alt="gim 1" className="ajustarGim" />
                        
                        
                        <img src={ require('./Css/Gims/gim2.png')  } alt="gim 2" className="ajustarGim" />
                        
                        <img src={ require('./Css/Gims/gim3.png')  } alt="gim 3" className="ajustarGim" />
                      
                        <img src={ require('./Css/Gims/gim4.png')  } alt="gim 4" className="ajustarGim" />
                       
                        <img src={ require('./Css/Gims/gim5.jpg')  } alt="gim 5" className="ajustarGim" />
                      
                        <img src={ require('./Css/Gims/gim6.jpg')  } alt="gim 6" className="ajustarGim" />
                        
                                         
                </div>
            </div>  
      </div>
    );
  }
}

export default TercerDiv;