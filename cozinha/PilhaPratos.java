//package cozinha;

import java.util.Arrays;

public class PilhaPratos {
    private Prato[] pratos;
    private volatile int qtde;
    private volatile boolean fim;

    public PilhaPratos(int tamanho) {
        pratos = new Prato[tamanho];
    }

    public synchronized void addPrato(Prato prato) {
        while (qtde >= pratos.length) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Sem espaço, vou esperar");
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
        while (!fim && qtde == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Sem pratos, vou esperar");
                wait(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        notifyAll();
        if (!fim && qtde > 0) {
            qtde--;
            Prato prato = pratos[qtde];
            pratos[qtde] = null;
            return prato;
        }
        return null;
    }

    public synchronized void setFim() {
        fim = true;
        notifyAll();
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
