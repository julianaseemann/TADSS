//package Exercicio04;

import java.util.ArrayList;
import java.util.List;

public class Exercicio4 {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        Thread.ofPlatform().start(new Incluir(lista));
        Thread.ofPlatform().start(new Incluir(lista));
        Thread.ofPlatform().start(new Remove(lista));
        //Thread.ofPlatform().start(new Imprimir(lista));
        // List<Integer> numeros = new ArrayList<>();
        // Incluir i1 = new Incluir(numeros);
        // Incluir i2 = new Incluir(numeros);
        // Remove r1 = new Remove(numeros);
        // Incluir p1 = new Incluir(numeros);
        // i1.start();
        // i2.start();
        // r1.start();
        // p1.start();
    }
}

// Collection não são thread-safe, ou seja, não são seguras para serem usadas por múltiplas threads ao mesmo tempo.

// As 4 threads influenciam uma na outra
// recurso do banco de dados para impedir que mais de um seja executado ao mesmo tempo -> lock/transação
// MUTEX - Se está em um crítico, só um vai executar
