import React, { Component,BrowserRouter } from 'react';
import axios from 'axios';
import './test.css';
import SignUp from './SignUp';
import PagePrincipale from './PagePrincipale';


class Login extends Component {
	constructor(props) {
		super(props);
		this.state = {login:'',
					  password:'',
					  cle: "",
					  code:"",
					  newconnection : props.connection,
					  newcurrentPage: props.currentPage,
					  error:[],
					  loginAxios:"",
					  id:"",
					  message:"",
		};
		this.onChangeLogin = this.onChangeLogin.bind(this);
		this.onChangePass = this.onChangePass.bind(this);
		this.onSubmit = this.onSubmit.bind(this);
		this.getConnect = this.getConnect.bind(this);
		this.login = React.createRef();
		this.password = React.createRef();
	}
	
	getConnect(props){
		return this.props.getConnected;
	}
	 
	reponse_login(reponse){
		if(reponse.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			this.setState({
				cle: reponse.data.cle,
				code: reponse.data.code,
				message: reponse.data.message,
				});
		}
	}
	
	reponse_L(reponseL){
		if(reponseL.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			console.log(reponseL.data);
			console.log(reponseL.data.cle);
			this.setState({
				cle: reponseL.data.cle,
				code: reponseL.data.code,
			});
		}
	}
	onChangeLogin(e) {
        this.setState({
            login: e.target.value
        });
    }
	
    onChangePass(e) {
        this.setState({
            password: e.target.value
        });
    }
	
	onSubmit(e) {
        e.preventDefault();
		const login = this.login.current.value;
		const password = this.password.current.value;
     	axios.get(`http://localhost:8080/THEPROJECT2/Login`+"?login="+login+"&password="+password)
		.then(reponse =>  this.reponse_login(reponse));
		axios.get(`http://localhost:8080/THEPROJECT2/UserCle`+"?login="+login).then(reponseL =>  this.reponse_L(reponseL));
		return false;
    }
    
	render() {
		return (
			<div className="ALL">
				<div className="topbar">
					<div className="accueil"><a id="test" href="http://www-connex.lip6.fr/~soulier/content/TechnoWeb/TechnoWeb.html">Home</a></div>
					<img id="icone_profil" src="https://fr.cdn.v5.futura-sciences.com/buildsv6/images/wide1920/0/8/e/08e8c9806b_108971_grenouille-crapaud-difference-2.jpg"/>
				</div>
				<div className="toto">
					<div className="titre">Sign in</div>
					{this.state.code === 1000 ? <p style={{'color':'red'}}>{this.state.message}</p>:""}
					{this.state.code === 1002 ? <p style={{'color':'red'}}>{this.state.message}</p>:""}
					
					<form onSubmit={this.onSubmit}>
						<p>
							<span><label>Login</label></span><input type="text" ref={this.login} name="login" value={this.state.username} onChange={this.onChangeLogin}/>
							<br/>
							<span><label>Mot de Passe</label></span><input type="password" ref={this.password} name="pass" value={this.state.password} onChange={this.onChangePass} />
						</p>
						<div className="button" id="submit_button">
							<p>
							<input type="submit" value="connexion" onClick={this.state.cle || this.state.code===1001 ? this.props.connect(this.state.cle,this.state.login): null}/> {/* onClick={this.props.connect}*/}
							</p>
						</div>
					</form>
					<br/>
					<a id="oublie" href="http://www-connex.lip6.fr/~soulier/content/TechnoWeb/TechnoWeb.html">Mot de passe oublié ?</a>
					<br/>
					
					<a value="connexion"  onClick={this.props.signUpprops}>Créer un compte</a>
					
					<br/>
					<input type="checkbox" name="rester" value="true"/>Rester connecté<br/>
				
				</div>
			</div>
		);
	}
	
}


export default Login;
