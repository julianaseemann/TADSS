// Faça um programa concorrente que crie 10 threads que exibam o nome da thread e a hora atual

import java.util.Date;

public class Exercicio01 extends Thread {
    public void run() {
        System.out.println(getName() + " " + new Date());
    }

    public static void main(String[] args) {
        // cria e inicia 10 threads
        for (int i = 0; i < 10; i++) {
            Exercicio01 e1 = new Exercicio01();
            e1.start();
            
        }
    }
}