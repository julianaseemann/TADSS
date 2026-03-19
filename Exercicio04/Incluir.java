package Exercicio04;

import java.util.List;
import java.util.Random;

public class Incluir implements Runnable {
    private List<Integer> lista;

    public Incluir(List<Integer> lista) {
        this.lista = lista;
    }
    
    
    @Override
    public void run() {
        // Lógica para incluir valores na lista
        Random random = new Random();
        while (true) {
            lista.add(random.nextInt(100)); // Adiciona um valor aleatório entre 0 e 99
        }
    }
    
}
