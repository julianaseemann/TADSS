package Exercicio05;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Banco banco = new Banco(100, 1000.0); // equivalente ao Banco
        int contadorTransacoes = 0; // contador de transações

        List<Transferir> transferencias = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Transferir transferir = new Transferir(banco, contadorTransacoes);
            Thread t = new Thread(transferir);
            transferencias.add(transferir);
            threads.add(t);
            t.start();
        }

        Imprimir imprimir = new Imprimir(banco);
        Thread threadImprimir = new Thread(imprimir);
        threadImprimir.start();

        Thread.sleep(180000);

        for (Transferir transferencia : transferencias) {
            transferencia.parar();
        }

        for (Thread t : threads) {
            t.join();
        }

        threadImprimir.interrupt();
        threadImprimir.join();

        double totalFinal;
        synchronized (banco) {
            totalFinal = banco.getSaldoTotal();
        }

        System.out.println("\n===== RESULTADO FINAL =====");
        System.out.println("Total de transações: " + contadorTransacoes);
        System.out.printf("Saldo final no banco: R$ %.2f%n", totalFinal);
    }
}