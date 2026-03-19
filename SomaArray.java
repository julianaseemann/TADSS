import java.util.Random;

public class SomaArray {

    static final int TAMANHO = 1_000_000;
    static int[] array = new int[TAMANHO];

    public static void main(String[] args) throws Exception {

        Random rand = new Random();

        // Gerar números aleatórios
        for (int i = 0; i < TAMANHO; i++) {
            array[i] = rand.nextInt(100);
        }

        // Soma sequencial
        long inicio = System.currentTimeMillis();

        long soma = 0;
        for (int i = 0; i < TAMANHO; i++) {
            soma += array[i];
        }

        long fim = System.currentTimeMillis();
        System.out.println("Soma sequencial: " + soma);
        System.out.println("Tempo: " + (fim - inicio) + " ms");

        // Soma com 10 threads
        executarConcorrente(10);

        // Soma com 100 threads
        executarConcorrente(100);
    }

    static void executarConcorrente(int numThreads) throws Exception {

        Thread[] threads = new Thread[numThreads];
        SomaThread[] tarefas = new SomaThread[numThreads];

        int bloco = TAMANHO / numThreads;

        long inicio = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {

            int inicioIndex = i * bloco;
            int fimIndex = (i == numThreads - 1) ? TAMANHO : inicioIndex + bloco;

            tarefas[i] = new SomaThread(inicioIndex, fimIndex);
            threads[i] = new Thread(tarefas[i]);
            threads[i].start();
        }

        long somaTotal = 0;

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            somaTotal += tarefas[i].getSoma();
        }

        long fim = System.currentTimeMillis();

        System.out.println("Soma com " + numThreads + " threads: " + somaTotal);
        System.out.println("Tempo: " + (fim - inicio) + " ms");
    }

}

class SomaThread implements Runnable {

    int inicio;
    int fim;
    long soma = 0;

    SomaThread(int inicio, int fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public void run() {
        for (int i = inicio; i < fim; i++) {
            soma += SomaArray.array[i];
        }
    }

    long getSoma() {
        return soma;
    }
}