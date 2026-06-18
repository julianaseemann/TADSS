//Liste, , os nomes e a quantidade de prêmios recebidos dos 

package exercicio;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import static com.mongodb.client.model.Sorts.ascending;

public class Exercicio05 {
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create();
        MongoDatabase mflix = mongo.getDatabase("mflix");
        MongoCollection<Document> movies = mflix.getCollection("movies");
        FindIterable<Document> docs = movies.find(
            Filters.gte("awards.wins", 3)
            ).sort(ascending("title"))
                .projection(Projections.fields(
                    Projections.include("title", "awards.wins"),
                    Projections.excludeId()
                    ));;



        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }

        mongo.close();
    }

}