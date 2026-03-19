import java.util.Random;

public class Exercicio3 {
    public static void main(String[] args) {
        short[] numeros = new short[1_000_000_000]; // Cria um array de 1 bilhão de números
        Random random = new Random();
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (short) random.nextInt(1_000);
        }
        System.out.println("Inicio"); //Soma de forma linar (Sequencial)
        long ini = System.currentTimeMillis(); // Marca o tempo de início
        Somador seq = new Somador(numeros);
        seq.run();
        System.out.println("Soma seq: " + seq.getResultado());
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));
        ini = System.currentTimeMillis(); // Soma de forma concorrente com 10 threads (Threads de Plataforma)
        int size = numeros.length / 10;
        Thread[] threads = new Thread[10];
        Somador[] somadores = new Somador[10];
        long resultado = 0;
        for (int i = 0; i < 10; i++) {
            short[] chunck = new short[size];
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofPlatform().start(somadores[i]);
        }
        for (int i = 0; i < 10; i++) { // Aguarda as threads terminarem e soma os resultados
            try {
                threads[i].join();
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 10TP: " + resultado); 
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

        ini = System.currentTimeMillis(); // Soma de forma concorrente com 100 threads (Threads de Plataforma)
        size = numeros.length / 100;
        threads = new Thread[100];
        somadores = new Somador[100];
        resultado = 0;
        for (int i = 0; i < 100; i++) { 
            short[] chunck = new short[size]; // Cria um array para cada thread com uma parte dos números
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofPlatform().start(somadores[i]);
        }
        for (int i = 0; i < 100; i++) {
            try {
                threads[i].join();
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 100TP: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

        ini = System.currentTimeMillis(); // Soma de forma concorrente com 10 threads (Threads Virtuais)
        size = numeros.length / 10;
        threads = new Thread[10];
        somadores = new Somador[10];
        resultado = 0;
        for (int i = 0; i < 10; i++) {
            short[] chunck = new short[size]; // Cria um array para cada thread com uma parte dos números
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofVirtual().start(somadores[i]); // Inicia a thread virtual para cada somador
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join(); // Aguarda a thread i terminar
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) {
            }
        }
        System.out.println("Soma 10TV: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

        ini = System.currentTimeMillis(); // Soma de forma concorrente com 100 threads (Threads Virtuais)
        size = numeros.length / 100;
        threads = new Thread[100];
        somadores = new Somador[100];
        resultado = 0;
        for (int i = 0; i < 100; i++) {
            short[] chunck = new short[size]; // Cria um array para cada thread com uma parte dos números
            System.arraycopy(numeros, i * size, chunck, 0, size);
            somadores[i] = new Somador(chunck);
            threads[i] = Thread.ofVirtual().start(somadores[i]); // Inicia a thread virtual para cada somador
        }
        for (int i = 0; i < 100; i++) { // Aguarda as threads terminarem e soma os resultados
            try {
                threads[i].join(); // Aguarda a thread i terminar
                resultado += somadores[i].getResultado();
            } catch (InterruptedException ie) { // Trata a exceção de interrupção, se ocorrer
            }
        }
        System.out.println("Soma 100TV: " + resultado);
        System.out.println("Tempo: " + (System.currentTimeMillis() - ini));

    }
}

