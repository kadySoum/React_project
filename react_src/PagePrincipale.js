import React, { Component } from 'react';
import './test.css';
import Header from './Header';
import Centre from './Centre';
import Message from './Message';
import axios from 'axios';

class PagePrincipale extends Component{
	constructor (props){
		super(props);
		this.state={posts:[] ,
					cle : props.cle,
					login: props.login,
					curre: props.currentPageProps,
		};
		this.handleSubmit = this.handleSubmit.bind(this);
		this.logoutM = this.logoutM.bind(this);
		this.connectM = this.connectM.bind(this);
		}
	
	handleSubmit(event) {
		 event.preventDefault();
		this.handleAddTodoItem()
	}
	
	logoutM(props){
		console.log("logoutM");
		return this.props.deconnect();
	}
	connectM(props){
		console.log("connectM");
		return this.props.connect(this.state.cle,this.state.login);
	}
	
	render(){
		return (
		<div className="ALL">
			<Header  connect={this.connectM} curreProps={this.state.curre} logout={this.logoutM} cle ={this.state.cle} login ={this.state.login} />
			<body >
				<div className="mur">
					<Centre login={this.state.login} cle={this.state.cle} currePageProps={this.state.curre} />
				</div>
			</body>
			<footer>
					<p> &copy SKJBCorp 2019</p>
			</footer> 
		</div>
		);
	}
}


export default PagePrincipale;	
