// Faça um programa concorrente que crie 10 threads que exibam o nome da thread e um contador que varia de 1 a 10. Execute várias vezes e observe as variações na saída. Os valores são sempre impressos na mesma ordem?

public class Exercicio02 extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        // cria e inicia 10 threads
        for (int i = 0; i < 10; i++) {
            
            
            
        }
    }
}

// Porque os valores não são sempre impressos na mesma ordem? Porque as threads são executadas de forma concorrente e o sistema operacional pode escolher qual thread executar a qualquer momento, resultando em uma ordem de execução diferente a cada vez que o programa é executado.
// scedule coloca elas para serem executadas, mas não garante a ordem de execução.