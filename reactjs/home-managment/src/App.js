import React, { Component } from 'react';
import './App.css';
import Header from './components/main/Header'
import FormExample from './components/main/FormExample';
import HomeList from './components/main/HomeList';
import ECommerce from './components/main/ECommerce';

class App extends Component {
  render() {
    return (
      <div>
        <Header />
        <hr/>
        <div>
          <ECommerce />
        </div>
      </div>
    );
  }
}

export default App;
