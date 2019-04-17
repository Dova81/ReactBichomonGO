import React, { Component } from 'react';
import {Dropdown} from "reactstrap"
import ApiComponent from "./ApiComponent"
import { connect } from 'react-redux';


export class Selector extends Component {

  componentDidMount(){
    ApiComponent.getEntrenador('/entrenador/'+{})
    .then(responder =>this.props.dispatch({
      type:"SET",
      payload:responder
    }))
   }

  render() {
    return (
        <div class="centrarSelector">
          <form>
            <select>
              <option value="Lucas">Lucas</option>
              <option value="Pedro">Pedro</option>
              <option value="Ash">Ash</option>
              <option value="Misty">Misty</option>
              <option value="Pepe">Pepe</option>
              <option value="Alberto">Alberto</option>
              <option value="Julio">Julio</option>
              <option value="Andrea">Andrea</option>
              <option value="Ana">Ana</option>
            </select>
          </form>
      </div>
    );
  }
} 

const mapStateToProps = (state) => {
  
  return{
    user: state.entrenador,  
  };
  
};

export default connect(mapStateToProps)(Selector)