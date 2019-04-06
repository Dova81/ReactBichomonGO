import React, { Component } from 'react';
import "./Css/SegundoDiv.css"

export class SegundoDiv extends Component {
  render() {
    return (
      <div className="container">
            <div className="row">
                <div className="col-xs-2 divBorder">
                    
                    <p>
                    <img src={ require('./Css/red.png')  } alt="" className="acomodarRed" />
                    </p>
                    

                    
                    <img src={ require('./Css/stats.png')  } alt="" className="acomodarStats" />
                    
                    
                </div>
            </div>  
      </div>
    );
  }
}

export default SegundoDiv;