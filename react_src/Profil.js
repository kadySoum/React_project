import React, { Component } from 'react';
import axios from 'axios';
import Header from './Header';
import Centre from './Centre';
import ListeAmis from './ListeAmis';
import PagePrincipale from './PagePrincipale';
import ListMessages from './Message';
import messages from './Message';
import Couleur from './Couleur';

class Profil extends Component {
	constructor(props) {
		super(props);
		this.state = {afficherTweet: false,
					  affFing: false,
					  affFers: false,
					  sugges: false,
					  etat:"",
					  cle: props.cle,
					  nom: "", 
					  id:"", 
					  login:props.login,
					  curre:props.curre,
					  followers:[],
					  following:[],
					  liste:[],
					  'items':[],
					  fw:[],		  
		};
		this.afficherTweet = this.afficherTweet.bind(this);/*pour la fonction afficherTweet*/
		this.afficherFollowing = this.afficherFollowing.bind(this);
		this.afficherFollowers = this.afficherFollowers.bind(this);
		this.logoutM = this.logoutM.bind(this);
		this.connectM = this.connectM.bind(this);
	}
	
	reponseID(reponseId){
		if(reponseId.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			this.setState({
				id: reponseId.data.id
			});
			console.log(reponseId.data);
		}
	}
	
	reponse_login(reponse){
		const followers=[]; 
		const abonnement=[];
		const liste=[];
		if(reponse.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			this.setState({
				following: reponse.data.following,
				followers: reponse.data.followers,
				liste    : reponse.data.liste,
				'items': reponse.data,
			});
		}
	}
	
	reponseS(reponseS){
		if(reponseS.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
			suggestion= reponseS.data.people
			console.log(reponseS.data);
			}
			console.log("suggestion 2:"+suggestion);
	}
	
	async componentDidMount(){
		console.log("cle profil componentDidMoount:"+this.state.cle);
		console.log("login profil componentDidMoount:"+this.state.login);
		axios.get(`http://localhost:8080/THEPROJECT2/UserId`+"?login="+this.state.login).then(reponseId =>  this.reponseID(reponseId));
		axios.get(`http://localhost:8080/THEPROJECT2/ListSuggestions`+"?key="+this.state.cle)
		.then(reponseS => this.reponseS(reponseS))
		.catch(err => {console.log(err)});
		//axios.get(`http://localhost:8080/THEPROJECT2/ListFriend`+"?id_user="+this.state.id).then(reponse =>  this.reponse_login(reponse));
		
	}
	
	getFollowing=() => {
		console.log("id getFollowing:"+this.state.id);
		if (this.state.id){
			axios.get(`http://localhost:8080/THEPROJECT2/ListFriend`+"?id_user="+this.state.id).then(reponse =>  this.reponse_login(reponse));
		}
	};
	
	afficherTweet() {
		this.setState(state => ({
		  afficherTweet: !state.afficherTweet,
		  affFing: false,
		  affFers: false,
		  etat: "tweet"
		}));
	 }
	afficherFollowing(){
		this.setState(state => ({
		  affFing: !state.affFing,
		  affFers: false,
		  afficherTweet:false,
		  etat: "following",
		  sugges:false,
		}));
		this.getFollowing();
	}
	afficherFollowers(){
		this.setState(state => ({
		  affFers: !state.affFers,
		   affFing: false,
		   afficherTweet: false,
		   sugges:false,
		  etat: "followers"
		}));
		this.getFollowing();
	}
	afficherSuggestions(){
		this.setState=({
		  affFers: !this.state.affFers,
		   affFing: false,
		   afficherTweet: false,
		   sugges:false,
		  etat: "suggestions"
		});
	}	
	
	logoutM(props){
		console.log("logoutM");
		return this.props.deconnect();
	}
	connectM(props){
		console.log("connectM");
		return this.props.connect(this.state.cle,this.state.login);
	}
	
	render() {
		return (
		<div className="ALL">
			<Header  connect={this.connectM} curreProps={this.state.curre} 
						logout={this.logoutM} cle ={this.state.cle} login ={this.state.login} />
			 <div className="mur">
                     <div className="couverture"></div>
                     <div className="image">
                         <img src="https://vignette.wikia.nocookie.net/naruto/images/a/aa/Jiraya.PNG/revision/latest?cb=20130117000023&path-prefix=fr" />
                     </div>
					 {this.state.login}
					
                    <div className="profil">
                        <ul className="liste">
                            <li className="item">
								<div>
									<button  onClick={this.afficherTweet} style={{"border":"none", "backgroundColor":"#e2ffe3"}}>
									<span className="nom_stats" >Tweets</span>
										{/*this.state.afficherTweet ? '-' : '+'*/}
									</button>
								</div>
                                <span id="nb_stats" className="nb_stats">0</span>
								
                            </li>
                            <li className="item">
								<div>
									<button onClick={this.afficherFollowing} style={{"border":"none", "backgroundColor":"#e2ffe3"}}>
									<span className="nom_stats">Following</span>
										{/*this.state.affFing ? 'less' : 'more'*/}
									</button>
								</div>
	<span className="nb_stats">{this.state.following.length}</span>
                            </li>
                            <li className="item">
								<div>
									<button onClick={this.afficherFollowers} style={{"border":"none", "backgroundColor":"#e2ffe3"}}>
									<span className="nom_stats">Followers</span>
										{/*this.state.affFers ? 'moins' : 'plus'*/}
									</button>
									<span className="nb_stats">{this.state.followers.length}</span>
								</div>
								
                            </li>
                        </ul>
                    </div>
					<div className="listFriends">
						<p>
						<AfficherTweet tweet={this.state.afficherTweet} etatE={this.state.etat}  />
						<AfficherFg following={this.state.affFing} etatE={this.state.etat} 
									fwg = {this.state.following} onRemoveItem={this.onRemoveItem} cle={this.state.cle}/>
						<AfficherFs followers={this.state.affFers} etatE={this.state.etat}
									fws = {this.state.followers}/>
						</p>
					</div>
					
					<div>
						<button onClick={this.afficherSuggestions} style={{"border":"none", "backgroundColor":"#e2ffe3"}}>
							<span className="suggestions">Suggestions</span>
						</button>
					</div>
					
					<Suggestions followers={this.state.affFers} etatE={this.state.etat}
									fws={this.state.followers}/>
				
					<div className="centreProfil">
						<Centre login={this.state.login} cle={this.state.cle} currePageProps={this.state.curre} />
					</div>
            </div>
           
		</div>
		);
	}
}



function AfficherTweet(props) {
	if (!props.tweet ) {return null;}
	if(props.etatE === "tweet"){
		return (
			<div className="listeAmis">
			{props.etatE}
			</div>
		);
	}
}
const id_tabfg=[];
function AfficherFg(props){
	console.log	(props.fwg);	
	if(!props.following){return null;}
	
	const amis =(props.fwg.map(
				  function(d, idx){
					return (<li>
						{d.login}
							{id_tabfg.push(d.id_test)}
							</li>)})
		)
		console.log("tab"+id_tabfg);
	if(props.etatE === "following"){
		return (
		 <ListeAmis amis={amis} id_tabfg={id_tabfg} cle={props.cle} etat={props.etatE}/>
		);
	}
}
const id_tabfs=[];
function AfficherFs(props){
	console.log	(props.fws);
	if(!props.followers){return null;}
	const amisfs =(props.fws.map(
				  function(d, idx){
					return (<li>
						{d.login}
						{id_tabfs.push(d.id_test)}
							</li>)})
		)
		
	if(props.etatE === "followers"){
		return (
		 <ListeAmis amis={amisfs} id_tabfg={id_tabfs} cle={props.cle} etat={props.etatE}/>
		);
	}
}
let suggestion=[];
function Suggestions(props){
	const sugg =(suggestion.map((d) =>(<li>{d}</li>)));
	
	return (
		 <ListeAmis amis={sugg} id_tabfg={id_tabfg} cle={props.cle} etat={props.etatE}/>
	);
		
}


export default Profil;
