import React, { Component } from 'react';
import Login from './Login';
import PagePrincipale from './PagePrincipale';
import Profil from './Profil';
import SignUp from './SignUp';
import Header from './Header';
import Couleur from './Couleur';

export default class App extends Component {
	constructor(props) {
		super(props);
		this.state={
		    connection : "false", //utilisateur non connecte
		    currentPage : "login", //la page courante
		    cle:'', //cle de connection dans la table Session
		    login:'',
		    id:'', //id de l'utilisateur
		}
		this.getConnected =  this.getConnected.bind(this); //
		this.setLogout = this.setLogout.bind(this);
		this.signUp = this.signUp.bind(this);
		this.getLog= this.getLog.bind(this);
	}

	getConnected(cle,login,id) {
		//on met l'etat a jours
		this.setState({
		     cle: cle,
		     login:login,
		     id: id,
		})
		
		if(this.state.currentPage==="pagePrincipale"){ // si la page courante est la page principale (avec les postes...)
			this.setState({
			    connection :"true", //utilisateur est connecte
			    currentPage : "profil" // on peut acceder a la page de profil 
		    })
		 }else{
			if(this.state.currentPage==="profil"){
			    this.setState({
			        connection :"true",
			        currentPage : "pagePrincipale"
			    })
			 }else{
			    this.setState({
				    connection :"true",
				    currentPage : "pagePrincipale"
			    })
	        }
	    }
		alert("cle App"+this.state.cle); 
	}
	
	//pour deconnecter un utilisateur 
	setLogout() { 
	   this.setState({
			connection :"false", //met l'instance a false 
			currentPage : "login", // on va sur la page de login 
			cle:"", //on reinitialise la cle
	    })
	}
	
	//pour enregistrer un nouvel utilisateur
	signUp(){
		this.setState({
			connection :"false",
			currentPage : "signUp"
		})
	}
	
	getLog(){
	     if(this.state.currentPage==="signUp"){
			this.setState({
			    connection :"false",
			    currentPage : "login"
		    })
		}
	}

	render() {
		if(this.state.connection==="false" && this.state.currentPage==="login"){
		    return(<div><Login connect={this.getConnected} signUpprops={this.signUp} connection ={this.state.connection} currentPage ={this.state.currentPage} /></div>);
		}
		if(this.state.connection==="true" && this.state.currentPage==="pagePrincipale"){
			return(<div><PagePrincipale  connect={this.getConnected} currentPageProps={this.state.currentPage} deconnect={this.setLogout} cle ={this.state.cle} login ={this.state.login} /></div>);
		}
		if(this.state.connection==="true" && this.state.currentPage==="profil"){
			return(<div><Profil connect={this.getConnected} curre={this.state.currentPage} deconnect={this.setLogout} cle ={this.state.cle} login ={this.state.login} id ={this.state.id}/></div>);
		}
		if(this.state.connection==="false" && this.state.currentPage==="signUp"){
			return (<div><SignUp  loginprops={this.getLog} connectprops={this.getConnected}/></div>)
		}
		return (<div>Echec</div>);
	}
}
