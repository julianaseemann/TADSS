// Crie 10 entradas chave-valor aleatórios e em seguida acesse o banco Redis com algum programa GUI (RedisInsight, Another Redis Desktop Manager ou Redis for VS Code) e verifique se as chaves foram criadas. Depois volte ao programa e liste as chaves e seus respectivos valores.

package exercicio;

import java.util.Set;
import redis.clients.jedis.RedisClient;

public class Exercicio02 {
    public static void main(String[] args) {
        RedisClient redis = RedisClient.create("redis://localhost:6379");
        for (int i = 1; i <= 10; i++) {
            redis.set("chave" + i, "valor" + i);
        }
        Set<String> chaves = redis.keys("*");
        for (String chave : chaves) {
            System.out.printf("%s = %s\n", chave, redis.get(chave));
        }
        redis.close();
    }
}