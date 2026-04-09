//package Exercicio04;

import java.util.List;

public class Remove extends Thread {
    private List<Integer> numeros;

    public Remove(List<Integer> numeros) {
        this.numeros = numeros;
    }

    @Override
    public void run() { // Lógica para remover o primeiro valor da lista
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (numeros) {
                if (!numeros.isEmpty()) {
                    numeros.removeFirst();
                }
            }
        }
    }
}