package Exercicio05;


import java.util.ArrayList;
import java.util.List;

class Banco {
    private final List<Conta> contas;

    public Banco(int numContas, double saldoInicial) {
        contas = new ArrayList<>();
        for (int i = 0; i < numContas; i++) {
            contas.add(new Conta(saldoInicial));
        }
    }

    public List<Conta> getContas() {
        return contas;
    }

    // Método sincronizado para evitar inconsistência
    public synchronized void transferir(int origem, int destino, double valor) {
        Conta c1 = contas.get(origem);
        Conta c2 = contas.get(destino);

        if (c1.getSaldo() >= valor) {
            c1.sacar(valor);
            c2.depositar(valor);
        }
    }

    public synchronized double getSaldoTotal() {
        return contas.stream().mapToDouble(Conta::getSaldo).sum();
    }
}