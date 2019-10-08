import React, { Component } from 'react';
import axios from 'axios';
import './test.css';

class SignUp extends Component {
	constructor(props) {
		super(props);
		this.setSignUp = this.setSignUp.bind(this);
		this.state = {
			nom: "",
			prenom:"",
			login:"",
			mail :"",
			password:"",
			sexe:"",
			age:"",
			res:"",
			code:"",
			message:"",
		}
		this.onSubmit = this.onSubmit.bind(this);
		this.handleOptionChange =this.handleOptionChange.bind(this);
		this.nom = React.createRef();
		this.prenom= React.createRef();
		this.login = React.createRef();
		this.password = React.createRef();
		this.mail = React.createRef();
		this.age=React.createRef();
		
	}
	setSignUp(){
	}
	
	onSubmit(event) {
		event.preventDefault();
		const nom = this.nom.current.value;
		const prenom = this.prenom.current.value;
		const login = this.login.current.value;
		const password = this.password.current.value;
        const mail = this.mail.current.value;
		const age =this.age.current.value;
		axios.get(`http://localhost:8080/THEPROJECT2/CreateUser`+"?nom="+nom+"&prenom="+prenom+"&login="+login+"&password="+password+"&mail="+mail+"&age="+age+"&sexe="+this.state.sexe).
			then(reponse =>   this.setState({ res: reponse.data.Ok,
										  code:reponse.data.code,
										  message:reponse.data.message,}))
			.catch(err => {console.log(err)});
	}
	
	handleOptionChange (changeEvent) {
	  this.setState({
		sexe: changeEvent.target.value
	  });
	}
	render() {
	const res =this.state.res;
		return (
		<div className="ALL">
			<div className="topbar">
				<div className="accueil"><a id="test" href="#">Home</a></div>
                <img id="icone_profil" src="https://images4.alphacoders.com/637/637345.jpg"/>
            </div>
			<div className="toto">
				<div className="titre">Join us</div>
				
				<p>{this.state.code === 1001 ? <p style={{'color':'red'}}>Ce login est déjà pris! </p> : null}</p>
				<p>{this.state.code === 1002 ? <p style={{'color':'red'}}> {this.state.message}</p> : null}</p>
				<p>{this.state.code === -1 ? <p style={{'color':'red'}}>Vous devez remplir tous les champs!! </p>: null}</p>
				
				<form onSubmit={this.onSubmit}>
					<div className="ids"><span id="e"><label>Nom</label></span>
						<input type="text" name="nom" ref={this.nom}  required />
					</div>
					<div className="ids"><span id="e"><label>Prénom</label></span>
						<input type="text" name="lprenom" ref={this.prenom} required />
					</div>
					<div className="ids"><span id="e"><label>Login</label></span>
						<input type="text" name="login" ref={this.login}   required />
					</div>
					<div className="ids"><span id="e"><label>Email</label></span>
						<input type="email" name="mail" ref={this.mail}  required/>
					</div>
					<div className="ids"><span id="m"><label>Mot de Passe</label></span>
						<input type="password" name="password" ref={this.password} id="inputm"  required /></div>
					<br/>
					<div className="ids"><span id="e"><label>Age</label></span>
						<input type="number" name="age" ref={this.age}  required/>
					</div>
					<input type="radio" value="H"  onChange={this.handleOptionChange} checked={this.state.sexe === "H"}/>Homme 
					<input type="radio" value="F"  onChange={this.handleOptionChange} checked={this.state.sexe === "F"}/>Femme<br/>
					<div className="button" id="submit_button">
						<p>
							<input id="id_but" type="submit" value="Sign up" 
							       onClick={res==="Ok creation user" ? this.props.loginprops : null}/>
						</p>
					</div>
				</form>
			</div>
		</div>
		);
	}
}
export default SignUp;
