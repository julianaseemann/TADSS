// Liste os filmes produzidos nos anos 1980's ordenados do maior ao menor valor no campo "imdb.rating"

package exercicio;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Sorts.descending;

public class Exercicio03 {
    public static void main(String[] args) {
        MongoClient mongo = MongoClients.create();
        MongoDatabase mflix = mongo.getDatabase("mflix");
        MongoCollection<Document> movies = mflix.getCollection("movies");
        //FindIterable<Document> docs = movies.find(Filters.lt("year", 1990));
        FindIterable<Document> docs = movies.find(
            Filters.and(
                Filters.gte("year", 1980), 
                Filters.lte("year", 1990)
            )
        ).sort(descending("imdb.rating"));
        

        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }

        mongo.close();
    }

}
