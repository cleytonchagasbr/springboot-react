import React from 'react';
import 'bootswatch/dist/flatly/bootstrap.css';
import './custom.css';
import Login from './views/login';

class App extends React.Component {

  render() {
    return (
      <div className="App">
        <Login />
      </div>
    )
    
  }
  
}

export default App;
