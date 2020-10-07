import React from 'react'
import Card from '../components/card'
import FormGroup from '../components/form-group'

class CadastroUsuario extends React.Component {

    state = {
        nome: '',
        email: '',
        senha: '',
        senhaRepeticao: ''
    }

    cadastrar = () => {
        console.log(this.state)
    }

    render() {
        return (
                <Card title="Cadastro de Usuário">
                    <div className="row">
                        <div className="col-lg-12">
                            <div className="bs-component">
                                <FormGroup
                                    label="Nome: *"
                                    htmlFor="inputNome">
                                    <input onChange={ e => this.setState({ nome: e.target.value })}
                                        type="text" id="inputNome" name="nome"
                                        className="form-control"/>
                                </FormGroup>
                                <FormGroup
                                    label="Email: *"
                                    htmlFor="inputEmail">
                                    <input onChange={ e => this.setState({ email: e.target.value })}
                                        type="text" id="inputEmail" name="email"
                                        className="form-control"/>
                                </FormGroup>
                                <FormGroup
                                    label="Senha: *"
                                    htmlFor="inputSenha">
                                    <input onChange={ e => this.setState({ senha: e.target.value })}
                                        type="password" id="inputSenha" name="senha"
                                        className="form-control"/>
                                </FormGroup>
                                <FormGroup
                                    label="Repita a Senha: *"
                                    htmlFor="inputSenhaRepeticao">
                                    <input onChange={ e => this.setState({ senhaRepeticao: e.target.value })}
                                        type="password" id="inputSenhaRepeticao" name="senhaRepeticao"
                                        className="form-control"/>
                                </FormGroup>
                                <button type="button" className="btn btn-success"
                                    onClick={this.cadastrar}>Salvar</button>
                                <button type="button" className="btn btn-danger">Cancelar</button>
                            </div>
                        </div>
                    </div>
                </Card>
        )
    }
}

export default CadastroUsuario