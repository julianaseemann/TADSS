// Crie uma chave inteira com o nome "programa:execucoes" e a cada chamada do programa execute a função Incr. Verifique pela GUI se o valor está sendo incrementado.

package exercicio;

import redis.clients.jedis.RedisClient;

public class Exercicio03 {
    public static void main(String[] args) {
        RedisClient redis = RedisClient.create("redis://localhost:6379");
        redis.set("programa:execucoes", "0");
        redis.incr("programa:execucoes");
        redis.close();
        
    }
    
}
