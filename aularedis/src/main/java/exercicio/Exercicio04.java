// Salve na base de dados uma lista de 10 usuários usando o tipo de dado List.

package exercicio;

import redis.clients.jedis.RedisClient;

public class Exercicio04 {
    public static void main(String[] args) {
        RedisClient redis = RedisClient.create("redis://localhost:6379");
        redis.sadd("usuarios:lista", "Juliana","Carlos","Lorenzo");
        redis.close();
    
    }
}
