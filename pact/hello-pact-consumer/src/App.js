import logo from './logo.svg';
import './App.css';
import React, {Component} from 'react';
import Contacts from './components/contacts';

const client = require('./components/client');

class App extends Component {
  render() {
    return (
        <div className="App">

          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <div className="card-body">
              <h5 className="card-title">Hello</h5>
              <p className="card-text">Pact</p>
              <Contacts contacts={this.state.contacts}/>
            </div>
          </header>
        </div>

    );
  }

  state = {
    contacts: []
  };

  // componentDidMount() {
  //   fetch('http://jsonplaceholder.typicode.com/users')
  //   .then(res => res.json())
  //   .then((data) => {
  //     this.setState({contacts: data})
  //   })
  //   .catch(console.log)
  // }

  componentDidMount() {
    client.fetchProviderData()
    .then(response => {
      console.log(response.body);
      this.setState({contacts: response.body})
    })
  }

}

export default App;
