import React, { Component } from 'react';
import './App.css';
import './test.css';
import axios from 'axios';
class ListeAmis extends Component{
	constructor(props){
		super(props);
		this.state = {
			list: props.amis,
			cle: props.cle,
			supprimer:"",
			id_friend:"",
			etat: props.etat,
			amis :props.amis,
			tab_fs: props.id_tabfg,
			list2:[],
		
		}
	}
	onRemoveItem = i => {
		/*alert("cle"+ this.state.cle);
		console.log("cle"+ this.state.cle);
		console.log("i "+ i);
		console.log("i "+ this.state.list[i]);
		*/
		this.state.tab_fs.filter((item,j)  => i === j,
		//console.log("id friend 2"+ this.state.tab_fs[i]),
		
			axios.get(`http://localhost:8080/THEPROJECT2/RemoveFriend`+"?key="+this.state.cle+"&id_friend="+ this.state.tab_fs[i])
				.then(reponse => this.setState({ supprimer: reponse.data.Ok,}))
				.catch(err => {console.log(err)})
		)
		//console.log("reponse.data.Ok =>"+ this.state.supprimer);
		const list2 = this.state.list.filter((item, j) => i !== j);
		this.setState({
			list : list2,
		});
	};
	onAddItem = i => {
		//if(typeof tab_fs == 'undefined' ){this.setState({tab_fs:[]});}
	
		this.state.tab_fs.filter((item,j)  => i === j,
		console.log("id friend 2"+ this.state.tab_fs[i]),
		axios.get(`http://localhost:8080/THEPROJECT2/AddFriend`+"?key="+this.state.cle+"&id_friend="+  this.state.tab_fs[i])
				.then(reponse => this.setState({ ajouter: reponse.data.Ok,}))
				.catch(err => {console.log(err)}),
		);
		this.state.list2 = this.state.list.push(i);
		this.setState({
			list : this.state.list2,
		})
		
	};

	render() {
	//	console.log("ETAT => "+this.state.etat);
    return (
	<div>
       <ul>
		{this.state.list.map((item, index) => (
            <li key={index}>
             {item}
				
			 {this.state.etat === "suggestions"  ?  
			 <button type="button" onClick={() => this.onAddItem(index)}>ajouter</button> :"" }
			
			 {this.state.etat === "followers"  ? 
			 <button type="button" onClick={() => this.onAddItem(index)}>ajouter</button> 
			:
			 <button type="button" onClick={() => this.onRemoveItem(index)}>supprimer</button>
			} 
			</li>
          ))}
        </ul>
	</div>
    );
  }
}


export default ListeAmis;	
	