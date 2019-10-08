package bd;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



public class Mongo {
	
	
	public static void main(String[] args) {
		try {
		MongoClient mongo = new MongoClient();
		MongoDatabase bd  = mongo.getDatabase("ly_soumahoro_mongo");
		MongoCollection<Document> mc = bd.getCollection("comments");
		Document query = new Document();
		query.append("id_auteur",3);
		query.append("nom_auteur","giraya");
		query.append("date", System.currentTimeMillis());
		query.append("texte"," hey!!!!");
		mc.insertOne(query);
		MongoCursor<Document> cursor = mc.find(query).iterator();
		while(cursor.hasNext()) {
			Document obj = cursor.next();
			System.out.println(obj);
		}
		}catch ( MongoException e) {
			e.printStackTrace ();
		}
	}
}
