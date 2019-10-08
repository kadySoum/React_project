import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import './test.css';
import axios from 'axios';

const myComments = [];
const dates=[];
let test=[];

class Message extends Component{
	constructor (props){
		super(props);
		this.state={
			mess : props.postsprops,
			login: props.loginprops,
			cle: props.cle,
			dates:[],
		}
	}
	
	reponse(reponse){
		if(reponse.data["status"]=="error"){
			this.state({status:"error"});
		}else{
			this.state={status:""};
				{/*this.setState({
				myComments: reponse.data.liste,
				});*/}
			console.log("comments "+reponse.data.liste);
			/*let valuesArrObj = []
			reponse.data.liste.forEach((val) => {
				valuesArrObj.push({
				  [val]: val
				})
			})
			console.log('target2', valuesArrObj);*/
			test=reponse.data.liste;
		}
		{/*//juste pour verifier sur la console
		const res =reponse.data.liste.map(
			function(d, idx){
				{console.log("texte : "+  d.texte+ " à "+ d.date )}
				myComments.push(d.texte);
				dates.push(d.date);
				return (<li>{d.date}{d.texte}</li>)
			}
		);
		console.log('myComments', myComments);
		console.log('myComments',dates);*/}
	}
	
	// pour recuperer les commentaire de mes amis ains que les miens
	componentDidMount(){
		console.log("dans le componentDidMount cle2 "+ this.state.cle);
		axios.get(`http://localhost:8080/THEPROJECT2/MyComment`+"?key="+this.state.cle)
		.then(reponse =>  this.reponse(reponse));
		
		/*axios.get(`http://localhost:8080/THEPROJECT2/MyComment`+"?key="+this.state.cle)
		.then(reponse =>  this.reponse(reponse));*/
		
	}
	render(){
		alert("cle message"+this.state.cle);
		return(
			<div class="mur">
				<div>
					<section>
						<article>
							<label id="mg" forName="ameliorer"></label>
							<br/>
							<div>
							<ListMessages messages={test.reverse()} login={this.state.login}/>						
							{myComments.map(function(d, idx){return (<li key={idx} >d.texte</li>)})}
							</div>
						</article>
					</section>
				</div>
			</div>
		);
	}
}


{/*const messages = props =>this.props.postprops;
const listItems = props => props.postprops.map((message) =>
  <li key={message.toString()}>
    {message}
  </li>
);*/}

function Form(props) {
  if (!props.formulaire) {
    return null;
  }

	return (
		<div className="formulaire">
			<div >
				<form name="commentaire_saisie" id="commentaire_saisie" action="" method="" >
					<div>{props.login}
						<textarea name="commentaire" cols="60" rows="4" wrap="soft"></textarea>
					</div>
					<input name="envoyermodifier" type="button" value="Envoyer"/>&nbsp;&nbsp;
				</form>
			</div>
		</div>
	);
}
// const Quote = props => {
class Quote extends Component{
	constructor(props){
		super(props);
		this.state = {showForm: true};
		this.handleToggleClick = this.handleToggleClick.bind(this);
		this.state = {isToggleOn: true};
		this.handleClick = this.handleClick.bind(this);
		this.state={login: props.login,}
	}
	handleClick() {
		this.setState(state => ({
			isToggleOn: !state.isToggleOn
		}));
	}
	handleToggleClick() {
		this.setState(state => ({
			showForm: !state.showForm
		}));
	}
	render(){
		return (
			<div className="liste">
				<div>
					<div className="profil_nom"><p><img id="icone_profil" src="https://images4.alphacoders.com/637/637345.jpg"/>{this.props.date}</p>
					</div>
					<p>{this.props.texte_C}</p>
				</div>
				{/*<Form symbol/>*/}
				<div id="laisseruncommentaire">
					<div>
						<button onClick={this.handleToggleClick}>
						  {this.state.showForm ? 'Hide' : 'commentez'}
						</button>
						<Form formulaire={this.state.showForm} login={this.state.login}/>
						<button onClick={this.handleClick}>
							{this.state.isToggleOn ? '+' : '-'}
						</button>
					</div>		
					<div id="liste_des_messages">&nbsp;</div>
				</div>
			</div>
		);
	}
}


Quote.propTypes = {
  texte_C: React.PropTypes,
  date: React.PropTypes
}
 
const ListMessages = props => {
  const quoteArray =props.messages.map((quote) => {
	  var key = -1;
    return (
      <Quote  texte_C={quote.texte} date={quote.date} login={props.login} />
    );
  });
  return (
    <div className="listeMessages" >
      <h2>publications</h2>
      {quoteArray}
    </div>
  );
};
ListMessages.propTypes = {
  messages: React.PropTypes
}


export default Message;	