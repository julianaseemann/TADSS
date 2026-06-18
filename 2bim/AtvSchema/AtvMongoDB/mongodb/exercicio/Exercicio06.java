package exercicio;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import static com.mongodb.client.model.Sorts.ascending;

public class Exercicio06 {
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create();
        MongoDatabase mflix = mongo.getDatabase("mflix");
        MongoCollection<Document> movies = mflix.getCollection("movies");
                
        Document awards = new Document()
                    .append("wins", 10);

        Document doc = new Document("_id", new ObjectId())
                    .append("title", "Triumph of the Nerds")
                    .append("awards", awards);
                    


        
            System.out.println(doc.toJson());
        

        mongo.close();
    }

}