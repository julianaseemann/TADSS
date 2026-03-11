// Faça um programa concorrente que crie 10 threads que exibam o nome da thread e a hora atual

import java.time.LocalTime;

public class Exercicio01 extends Thread {
    public Exercicio01(String name) {
        super(name);
    }

    @Override
    public void run() {
        // imprime nome da thread e hora atual
        System.out.println(getName() + " - " + LocalTime.now());
    }

    public static void main(String[] args) {
        // cria e inicia 10 threads
        for (int i = 0; i < 10; i++) {
            Exercicio01 t = new Exercicio01("Thread-" + i);
            t.start();
        }
    }
}