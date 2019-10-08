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
			connection : "false",
		    currentPage : "login",
		    cle:'',
		    login:'',
			id:'',
		}
		this.getConnected =  this.getConnected.bind(this);
		this.setLogout = this.setLogout.bind(this);
		this.signUp = this.signUp.bind(this);
		this.getLog= this.getLog.bind(this);
	}

	getConnected(cle,login,id) {
		this.setState({
		     cle: cle,
		     login:login,
			 id: id,
		})
		
		if(this.state.currentPage==="pagePrincipale"){
			this.setState({
			    connection :"true",
			    currentPage : "profil"
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

	setLogout() {
	   this.setState({
			connection :"false",
			currentPage : "login",
			cle:"",
	    })
	}
	
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
