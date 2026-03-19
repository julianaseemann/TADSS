// Faça um programa que gere um array de 1 bilhão de números inteiros aleatórios e faça as seguintes tarefas:

// Some todos os valores de forma sequencial e meça o tempo de execução;
// Some todos os valores de forma concorrente com 10 threads e meça o tempo de execução;
// Some todos os valores de forma concorrente com 100 threads e meça o tempo de execução.
// Responda as seguintes perguntas:
// Qual foi o speedup1 obtido?
// Teve algum caso que o speedup foi negativo?
// Repita os testes acima usando Threads virtuais.
// Houve diferenças entre Threads de plataforma e Threads virtuais? Se houve, quais foram e explique o porquê das diferenças.

import java.util.Random;

public class Exercicio03 implements Runnable {

    private int[] lista;
    private int inicio;
    private int fim;
    public long somaParcial = 0;

    public Exercicio03(int[] lista, int inicio, int fim) {
        this.lista = lista;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Override
    public void run() {
        for (int i = inicio; i < fim; i++) {
            somaParcial += lista[i];
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int[] lista = new int[1_000_000];
        Random random = new Random();

        for (int i = 0; i < lista.length; i++) {
            lista[i] = random.nextInt();
        }

        // 1 - Some todos os valores de forma sequencial e meça o tempo de execução
        long inicio = System.nanoTime();

        long soma = 0;
        for (int i = 0; i < lista.length; i++) {
            soma += lista[i];
        }

        long fim = System.nanoTime();

        long tempoSequencial = (fim - inicio) / 1_000_000;

        System.out.println("Soma sequencial: " + soma);
        System.out.println("Tempo sequencial: " + tempoSequencial + " ms\n");

        // 2 - Some todos os valores de forma concorrente com 10 threads e meça o tempo de execução (Threads de Plataforma)
        executarTeste(lista, 10, tempoSequencial, false);
        // 3 - Some todos os valores de forma concorrente com 100 threads e meça o tempo de execução (Threads de Plataforma)
        executarTeste(lista, 100, tempoSequencial, false);

        // 5 - Execução com Threads Virtuais 
        executarTeste(lista, 10, tempoSequencial, true);
        executarTeste(lista, 100, tempoSequencial, true);

    }

    public static void executarTeste(int[] lista, int nThreads, long tempoSequencial, boolean virtual)
            throws InterruptedException {

        Thread[] threads = new Thread[nThreads];
        Exercicio03[] tarefas = new Exercicio03[nThreads];

        int bloco = lista.length / nThreads;

        long inicio = System.nanoTime();

        for (int i = 0; i < nThreads; i++) {

            int inicioBloco = i * bloco;
            int fimBloco = (i == nThreads - 1) ? lista.length : inicioBloco + bloco;

            tarefas[i] = new Exercicio03(lista, inicioBloco, fimBloco);

            if (virtual) {
                threads[i] = Thread.ofVirtual().start(tarefas[i]);
            } else {
                threads[i] = Thread.ofPlatform().start(tarefas[i]);
            }
        }

        long somaTotal = 0;

        for (int i = 0; i < nThreads; i++) {
            threads[i].join();
            somaTotal += tarefas[i].somaParcial;
        }

        long fim = System.nanoTime();

        long tempoConcorrente = (fim - inicio) / 1_000_000;

        double speedup = (double) tempoSequencial / tempoConcorrente;

        String tipo = virtual ? "Threads Virtuais" : "Threads de Plataforma";

        System.out.println(tipo + " - " + nThreads + " threads");
        System.out.println("Tempo concorrente: " + tempoConcorrente + " ms");
        System.out.println("Speedup: " + speedup);

        if (speedup < 1) {
            System.out.println("Speedup negativo (execução mais lenta)");
        }

        System.out.println();

        /*4.1 e 4.2 - Durante as execuções utilizando threads de plataforma, foi observado que em 100% dos testes o speedup foi negativo.
        Isso significa que o tempo de execução da soma sequencial (sem uso de threads) foi menor do que o tempo utilizando múltiplas threads.*/

        /*5 - Durante os testes com threads virtuais, observou-se que ao utilizar 10 threads o speedup continuou sendo negativo.
        Entretanto, ao utilizar 100 threads virtuais, o speedup passou a ser positivo. Isso ocorre porque as threads virtuais são muito mais leves
        que as threads de plataforma, pois são gerenciadas pela própria JVM e não diretamente pelo sistema operacional*/
    }
}