package Exercicio05;


public class Imprimir implements Runnable {

    private final Banco banco;

    public Imprimir(Banco banco) {
        this.banco = banco;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                double total = banco.getSaldoTotal();

                System.out.printf("Saldo total no banco: R$ %.2f%n", total);

                if (Math.abs(total - 100000.0) > 0.01) {
                    System.out.println("ERRO: saldo inconsistente!");
                }

                Thread.sleep(5000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}