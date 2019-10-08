import React, { Component } from 'react';
import './App.css';
import './test.css';
import Message from './Message';
import axios from 'axios';

class Centre extends Component{
	constructor(props){
		super(props);
		this.state={value:"",
					text: '', 
					inputText: '',
					mode: '',
					posts:[] ,
					cle : props.cle,
					login: props.login,
					curre: props.currentPageProps,
					poster:'', };
		this.handleChange = this.handleChange.bind(this);
		this.handleSave = this.handleSave.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleAddTodoItem = this.handleAddTodoItem.bind(this);
		}
	
	handleSubmit(event) {
		alert('Le message suivant va d\'etre poster :' + this.state.text);
		 event.preventDefault();
		this.handleAddTodoItem()
	}
	
	handleChange(e) {
		this.setState({ inputText: e.target.value });	
	}
	handleSave() {
		this.setState({text: this.state.inputText, mode:'not'});
	}
	handleStop(){
			this.setState({mode:'not'});
	}
	
	/*handleAddTodoItem() {
		this.state.posts.push(this.state.text)
			this.setState(
			  this.state
			)
		console.log(this.state.posts)
	}*/
	
	
	reponse(reponse){
		if(reponse.data["status"]==="error"){
			this.state({status:"error"});
		}else{
			this.setState=({status:""});
			this.setState=({
				poster: reponse.data.Ok
			});
			console.log(reponse.data);
			}
			console.log("id profil componentDidMoount:"+this.state.poster);
	}
	handleAddTodoItem() {
		this.state.posts.push(this.state.text)
			this.setState(
			  this.state
			)
		console.log(this.state.posts)
		alert("dans add comment 2 "+ this.state.cle);
		axios.get(`http://localhost:8080/THEPROJECT2/AddComment`+"?key="+this.state.cle+"&comment="+this.state.text).then(reponse =>  this.reponse(reponse));
		
	}
	
	render () {
	 let { posts } = this.state;
	 alert("cle Centre"+this.state.cle);
      return (		
	<div>	
		<div className="centre">
            <div className="publier">
                <img className="profil_icone_tweet" alt=""  src="https://vignette.wikia.nocookie.net/naruto/images/a/aa/Jiraya.PNG/revision/latest?cb=20130117000023&path-prefix=fr" />
                <div className="placement_img">
                    <form onSubmit={this.handleSubmit} >
						<div>
							<textarea id="zone_de_texte" ref="coco" className="zone_de_texte" placeholder="Exprimez-vous !!" onChange={this.handleChange}
								value={this.state.mode !== 'not' ?  this.state.inputText : null}></textarea>
							<div className="nb_caractere">
								<span id="totalchars">{this.state.text.length}/280</span>
							</div>
						</div>
						<div className="bouton_tweet">
							<button id="tweet" onClick={this.state.inputText.lengh > 280 ? this.handleStop :this.handleSave}  className="tweet" type="submit">Tweet</button>
						</div>
					</form>
				</div>
			</div> 		  
        </div>
		<div className="publication">
		{/*<div>
				<p>Post: {this.state.text}</p>
		</div>*/}
				{/*posts.map((v) => {return <div><p className="font">{v}</p></div>})*/}
				<Message style={{'textAlign':'center'}} postsprops={this.state.posts} loginprops={this.state.login} cle={this.state.cle}/>
			</div><p><br></br></p>
		</div>
	
      );
    
  }
	
}


export default Centre;