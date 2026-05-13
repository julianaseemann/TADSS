// Faça um programa que conecte numa base Redis® e chame as funções Ping e Echo. Utilize a linguagem Java™ com a biblioteca Jedis.

package exercicio;

import redis.clients.jedis.RedisClient;

//Jedis (simpes e direta)
//Lettece (Resiliente, IO de alta performace)

public class Exercicio01 {
    public static void main(String[] args) {
        RedisClient redis = RedisClient.create("redis://localhost:6379");
        System.out.println(redis.ping());
        System.out.println(redis.echo("Olá mundo"));
        redis.close();
    }
    
}
