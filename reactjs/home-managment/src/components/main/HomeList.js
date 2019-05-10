import React, { Component } from 'react';
import axios from 'axios';

class HomeList extends Component {
  constructor() {
    super();
    
    this.state = {
      homes: []
    }

    this.loadHomes.bind(this);
    this.loadHomes();
  }

  loadHomes() {
    axios.get("http://localhost:3001/homes")
      .then(response => {
        console.log(response.data);
        this.setState({
          homes: response.data
        });
      })
      .catch(error => {
        console.log(error);
      })
  }

  render() {
    return (
      <div>
        <h2>Lista de Hogares</h2>
        <div>
          <ul>
            {this.state.homes.map((home, index) =>
              <li key={index}>{home}</li>
            )}
          </ul>
        </div>
      </div>
    );
  }
}

export default HomeList;