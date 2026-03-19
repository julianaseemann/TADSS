package Exercicio04;

import java.util.List;

public class Remover implements Runnable {
    private List<Integer> lista;

    public Remover(List<Integer> lista) {
        this.lista = lista;
    }
    
    @Override
    public void run() {
        // Lógica para remover o primeiro valor da lista
        while (true) {
            if (!lista.isEmpty()) {
                lista.remove(0); // Remove o primeiro valor da lista
            }
        }
    }
    
}
