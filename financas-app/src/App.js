import React from 'react';
import 'bootswatch/dist/flatly/bootstrap.css';
import './custom.css';
import Login from './views/login';
import CadastroUsuario from './views/cadastroUsuario'

class App extends React.Component {

  render() {
    return (
      <div className="App">
        <CadastroUsuario />
      </div>
    )
    
  }
  
}

export default App;
