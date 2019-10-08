package tools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.util.JSON;

public class mongoTools {
	/**
	 * list tous les elements en lien avec signature = nom_auteur
	 * @param collection
	 * @param signature
	 * @return
	 */
	public static List<Document> trouverALL(String collection, String signature) {
		List<Document> res = new ArrayList<Document>();
		MongoClient mongo = new MongoClient();
		
			MongoDatabase db = mongo.getDatabase("ly_soumahoro_mongo");
			MongoCollection<Document> coll = db.getCollection(collection);
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("nom_auteur", signature);
			
			Iterable<Document> docs = coll.find(searchQuery);
			
			for (Document doc : docs) {
				res.add(doc);
			}
			System.out.println(res);
		return res;
	}
	
	public static List<Document> trouver(String collection, String signature) {
		List<Document> res = new ArrayList<Document>();
		MongoClient mongo = new MongoClient();
		
			MongoDatabase db = mongo.getDatabase("ly_soumahoro_mongo");
			MongoCollection<Document> coll = db.getCollection(collection);
			Document searchQuery = new Document();
			searchQuery.put("nom_auteur", signature);

			
			Iterable<Document> docs = coll.find(searchQuery).projection(Projections.exclude("_id","nom_auteur","id_auteur"));
					//include("_id:0","texte"))
			
			for (Document doc : docs) {
				res.add(doc);
			}
			System.out.println(res);
		return res;
	}
	
	public static List<Document> trouver(String key,String query,int id_friend) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Document> res = new ArrayList<Document>();
		MongoClient mongo = new MongoClient();
		MongoDatabase db = mongo.getDatabase("ly_soumahoro_mongo");
		MongoCollection<Document> coll = db.getCollection("comments");
		Document searchQuery = new Document();
		//searchQuery.put("id_auteur", id_friend);
		//searchQuery.put("texte", query);
		//searchQuery.put($text, {$search: "j'ai"});
		//coll.createIndex(new BasicDBObject("texte" , query),new BasicDBObject ("id_auteur", id_friend));
		 Document findCommand = new Document("$text", new BasicDBObject("$search", query));
		 Document fi = new Document("id_auteur", new BasicDBObject("$search", id_friend));
	      
		 FindIterable<Document> docs = coll.find(findCommand).sort(fi);
		// Iterable<Document> docs = db.getCollection("comments").find(findCommand,fi);//.sort(fi);
		 
		//coll.find('{$text: {$search: "j'ai"}}');
		//Iterable<Document> docs = coll.find(searchQuery).projection(Projections.exclude("_id","nom_auteur","id_auteur"));
			//include("_id:0","texte"))
		


         //build the query
         BasicDBObject q = new BasicDBObject();
         q.put("id_auteur",id_friend);

         //build the query with sorting by date
         BasicDBObject sortby = new BasicDBObject();
         sortby.put("texte", query);

         FindIterable<Document> d = coll.find(q).sort(sortby);

		 
		for (Document doc : docs) {
			res.add(doc);
		}
		System.out.println(res);
	return res;
		
	}
	
	/**
	 * ajouter un commentaire dans la base de donn�e
	 * @param collection
	 * @param signature
	 * @param comment
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void addComment(String collection, String signature,String comment) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Document> liste = new ArrayList<Document>();
		try {
			MongoClient mongo = new MongoClient();
			MongoDatabase bd  = mongo.getDatabase("ly_soumahoro_mongo");
			MongoCollection<Document> mc = bd.getCollection("comments");
			Document query = new Document();
			int id=tools.UserTools.getIdUser(signature);
			GregorianCalendar calendar = new java.util.GregorianCalendar();
			Date date_du_jour = calendar.getTime();
			query.append("id_auteur",id); 
			query.append("nom_auteur",signature);	
			//query.append("date", System.currentTimeMillis());
			query.append("date", date_du_jour);
			query.append("texte",comment);
			//query.append("friend", tools.FriendTools.getFriendships(id));
			mc.insertOne(query);
			MongoCursor<Document> cursor = mc.find(query).iterator();
			
			while(cursor.hasNext()) {
				Document obj = cursor.next();
				liste.add(obj);
				System.out.println(obj);
			}
			}catch ( MongoException e) {
				e.printStackTrace ();
			}
		}

	public static List<Document> color() {
		List<Document> res = new ArrayList<Document>();
		MongoClient mongo = new MongoClient();
		
			MongoDatabase db = mongo.getDatabase("ly_soumahoro_mongo");
			MongoCollection<Document> coll = db.getCollection("color");
			Document searchQuery = new Document();
			
			Iterable<Document> docs = coll.find().projection(Projections.exclude("_id"));;
			for (Document doc : docs) {
				res.add(doc);
			}
			System.out.println(res);
		return res;
	}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Document query = new Document();
		int id_friends = 2;
		//trouverALL("comments","hisoka");
		color();
	//	trouver("mlrwvuvnyhtcjfupvhsrtkxvknzrnper","j'ai",3) ;
	//	addComment("comments", "chiant","OK") ;
			
		
		//recherche("id_auteur",query ,id_friends);
	}
	
}
