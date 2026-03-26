package Exercicio05;


import java.util.Random;

class Transferir implements Runnable {
    private Banco banco;
    private Random random = new Random();
    private int contador;
    private boolean rodando = true;

    public Transferir(Banco banco, int contador) {
        this.banco = banco;
        this.contador = contador;
    }

    public void parar() {
        rodando = false;
    }

    @Override
    public void run() {
        int tamanho = banco.getContas().size();

        while (rodando) {
            int origem = random.nextInt(tamanho);
            int destino = random.nextInt(tamanho);

            if (origem != destino) {
                double valor = random.nextDouble() * 2500;

                banco.transferir(origem, destino, valor);
                contador++;
            }
        }
    }
}