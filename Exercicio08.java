    public class Exercicio08 {

        // AtomicInteger

        int count = 0;

        void increment() {
            count++;
        }

        public static void main(String[] args) throws Exception {
            Exercicio08 contador = new Exercicio08();

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    contador.increment();
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < 1000; i++) {
                    contador.increment();
                }
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println(contador.count);
        }
    }
// Soma um e volta para a memória

// Possível solução - Syncronized
// Alterar 1000 para 10000

// Heap Memória:
// Processador ULA, cache, clock, Registro
// Problema foi no cache

// Memoization - guardar um valor ou uma cópia dele

// volátil da efeito no compilador do java
// não confiar no cache

// cache coherence protocol - processador ve que está rodando em dois core e aj
// MESI | MEOSI

// Volatile Volatile 