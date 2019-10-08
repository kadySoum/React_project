import React, { Component } from 'react';
import Login from './Login';
import PagePrincipale from './PagePrincipale';
import Profil from './Profil';
import SignUp from './SignUp';
import Header from './Header';

import axios from 'axios';

export default class Couleur  extends Component {
	constructor(props) {
		super(props);
		this.state={
			connection : "false",
		    currentPage : "login",
		    cle:'',
		    login:'',
			id:'',
			couleur:'',
			color:'',
		}
		this.couleur= React.createRef();
		this.onChangeCouleur =this.onChangeCouleur.bind(this);
		this.onSubmit =this.onSubmit.bind(this);
	}
	
	onChangeCouleur(e) {
        this.setState({
            couleur: e.target.value
        });
    }
	reponse_couleur(reponse){
		if(reponse.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			this.state.setState({
				color: reponse.data.color,
				code: reponse.data.code,
				message: reponse.data.message,
				});
		}
	}
	
	onSubmit(e) {
        e.preventDefault();
		const couleur = this.couleur.current.value;
		axios.get(`http://localhost:8080/THEPROJECT2/Couleurs`+"?couleur="+couleur)
		.then(reponse =>  this.reponse_couleur(reponse));
    }
	
	render(){
	return (<div>
				<form onSubmit={this.onSubmit}>
				<span>
				<label>couleur</label>
				</span>
				<input type="text" ref={this.couleur} name="login" value={this.state.couleur} 
				onChange={this.onChangeCouleur}/>
				{this.state.color ? "coco": "lolo" }	
					
				</form>
	
	Couleur</div>);	
	}
}