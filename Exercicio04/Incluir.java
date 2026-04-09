//package Exercicio04;

import java.util.List;
import java.util.Random;

public class Incluir extends Thread {
    private List<Integer> numeros;

    public Incluir(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (numeros) {
                numeros.add(r.nextInt(1000));
            }
        }
    }
}