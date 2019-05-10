import React, { Component } from 'react';

class FormExample extends Component {
  constructor() {
    super();
    this.state = {
      name: "",
      terms: false
    }
  }

  render() {
    return (
      <div>
        <input type="text" value={this.state.name} onChange={this.updateName.bind(this)}/>
        <div>
          <input type="checkbox" checked={this.state.terms} onChange={this.updateTerms.bind(this)}/>Acepto Términos
        </div>
        <button onClick={this.sayHi.bind(this)}>Say Hi</button>
      </div>
    );
  }

  updateTerms(event) {
    this.setState({
      terms: event.target.checked
    });
  }

  updateName(event) {
    this.setState({
      name: event.target.value
    });
  }

  sayHi() {
    if (this.state.terms) {
      alert('Hola ' + this.state.name);
    } else {
      alert('Debes aceptar los términos');
    }
    
  }
}

export default FormExample;