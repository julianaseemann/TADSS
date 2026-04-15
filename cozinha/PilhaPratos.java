package cozinha;

import java.util.Arrays;

public class PilhaPratos {
    private Prato[] pratos;
    private volatile int qtde;

    public PilhaPratos(int tamanho) {
        pratos = new Prato[tamanho];
    }

    public synchronized void addPrato(Prato prato) {
        while (qtde >= pratos.length) {
            try {
                System.out.println("Sem espaço, vou esperar");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        pratos[qtde] = prato;
        qtde++;
        notifyAll();
    }

    public synchronized Prato removePrato() {
        while (qtde == 0) {
            try {
                System.out.println("Sem pratos, vou esperar");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        qtde--;
        Prato prato = pratos[qtde];
        pratos[qtde] = null;
        notifyAll();
        return prato;
    }

    public boolean temPrato() {
        return qtde > 0;
    }

    public boolean temEspaco() {
        return qtde < pratos.length;
    }

    @Override
    public String toString() {
        return "PilhaPratos [" + Arrays.toString(pratos) + "]";
    }
}
