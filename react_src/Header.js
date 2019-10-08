import React, { Component } from 'react';
import './App.css';
import axios from 'axios';
import PagePrincipale from './PagePrincipale';

import Couleur from './Couleur';

class Header extends Component {
	constructor (props){
		super(props);
		this.state={cle:props.cle,
		            deconnexion:"",
		            login: props.login,
					color:"off",
					couleur:"",
		};
		this.onSubmit = this.onSubmit.bind(this);
		this.couleur = React.createRef();//
		this.changeColor = this.changeColor.bind(this);
	}
	getHeader(){
	}
//
	handleSubmit(event) {
		alert('vous venez d\'ffectuer un recherche ');
		 event.preventDefault();
		
	}
	
	onSubmit(e){
	    e.preventDefault();
		console.log("cle header " + this.state.cle);
		axios.get(`http://localhost:8080/THEPROJECT2/Logout`+"?key="+this.state.cle).then(reponse =>  this.reponse_logout(reponse));
	}
	
	changeColor(e){
		e.preventDefault();
	   alert('changement de couleur');
	   this.setState({
		  color:"on",
		});
		
	}

	reponse_logout(reponse){
		if(reponse.data["status"]==="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			console.log("code si erreur"+reponse.data.code);
			console.log("cle dans reponse_login"+reponse.data.Ok);
			this.setState({
				deconnexion: reponse.data.Ok,
				code: reponse.data.code,
			});
			console.log("deconnexion : "+this.state.deconnexion);
			console.log(this.state.code);
		}
	}
	
	
	render(){
		alert("cle Header"+this.state.cle);
	   return(
		<div className="container">
			<img id="logo" src="https://fr.cdn.v5.futura-sciences.com/buildsv6/images/wide1920/0/8/e/08e8c9806b_108971_grenouille-crapaud-difference-2.jpg"/>
			<div className="menu" >
				<a id="test" href="#" style={{"color":"white"}}>Accueil</a>
				<a id="notif" href="#" style={{"color":"white"}}>Notifications</a>
				<a id="mess"href="#" style={{"color":"white"}}>Messages</a>
			</div>
			<div className='recherche' id="recherche">
				<form onSubmit={this.handleSubmit}>
					<input id="recherche" type="text" placeholder="recherche"/>
					<input type="submit" value="rechercher" />
				</form>
			</div>
			<div id="jiraya">
				 <a onClick={this.props.connect}>{this.state.login}</a>
				 {/*this.props.curre === "profil" ? null : this.props.connect*/}
				<a >
					 <img id="profil"  onClick={this.props.curreProps === "profil" ? null : this.props.connect,this.chargerID} src="https://vignette.wikia.nocookie.net/naruto/images/a/aa/Jiraya.PNG/revision/latest?cb=20130117000023&path-prefix=fr"/>
				</a>
				<button onClick={this.props.curre === "profil" ? this.props.connect: null}>retour</button>          
				<form onSubmit={this.onSubmit}>
				    <button  onClick={this.state.deconnexion === "OK pour la deconnexion" ? this.props.logout: null}>deconnexion</button>{/*onClick={this.props.deconnect}*/}
					{/*this.props.logout*/}
				</form>
				<form onSubmit={this.changeColor}>
				    <button >couleur</button>
					
				</form>
				{this.state.color === "on" ? <Couleur symbol/> : ""}
			</div>
		</div>
		);
	}
	
}

export default Header;
