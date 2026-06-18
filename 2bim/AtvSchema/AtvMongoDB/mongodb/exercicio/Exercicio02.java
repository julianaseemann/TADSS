package exercicio;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Exercicio02 {
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create();
        MongoDatabase mflix = mongo.getDatabase("mflix");
        MongoCollection<Document> movies = mflix.getCollection("movies");
        FindIterable<Document> docs = movies.find(Filters.lte("runtime", 15));

        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }

        mongo.close();
    }

}
