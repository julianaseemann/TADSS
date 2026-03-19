package Exercicio04;
// Faça um programa que crie uma lista (ArrayList) e manipule ela com as seguintes threads:

// Duas threads incluindo valores aleatórios na lista.
// Uma thread removendo o primeiro valor da lista.
// Uma thread imprimindo a cada segundo os valores existentes.

import java.util.ArrayList;
import java.util.Random;

public class Exercicio04 {
    public static void main(String[] args) {
        ArrayList<Integer> lista = new ArrayList<>();
        Random random = new Random();

        Thread t1 = new Thread() { 
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    lista.add(random.nextInt(100));
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() { 
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) { 
                    lista.add(random.nextInt(100)); 
                }
            }
        };
        t2.start();

        Thread tRemover = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!lista.isEmpty()) {
                        lista.remove(0);
                    }
                }
            }
        };
        tRemover.start();

        Thread tImprimir = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(lista);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        tImprimir.start();




    }

}