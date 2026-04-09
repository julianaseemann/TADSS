//package Exercicio04;

import java.util.List;

public class Imprimir extends Thread {
    private List<Integer> numeros;

    public Imprimir(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (numeros) {
                System.out.println(numeros);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}