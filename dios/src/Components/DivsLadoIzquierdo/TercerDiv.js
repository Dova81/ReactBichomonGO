import React, { Component } from 'react';
import "./SegundoDiv.css"

export class TercerDiv extends Component {
  render() {
    return (
      
                <div className="divBorder">
                    
                        
                        <img src={ require('./Gims/gim1.jpg')  } alt="gim 1" className="ajustarGim" />
                        
                        
                        <img src={ require('./Gims/gim2.png')  } alt="gim 2" className="ajustarGim" />
                        
                        <img src={ require('./Gims/gim3.png')  } alt="gim 3" className="ajustarGim" />
                      
                        <img src={ require('./Gims/gim4.png')  } alt="gim 4" className="ajustarGim" />
                       
                        <img src={ require('./Gims/gim5.jpg')  } alt="gim 5" className="ajustarGim" />
                      
                        <img src={ require('./Gims/gim6.jpg')  } alt="gim 6" className="ajustarGim" />
 
      </div>
    );
  }
}

export default TercerDiv;