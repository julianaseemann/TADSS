// Faça um programa concorrente que crie 10 threads que exibam o nome da thread e a hora atual

public class Exercicio01 extends Thread {
    private int id;

    public Exercicio01(int id) {
        this.id = id;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(id + ":" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
            }
        }
    }

    public static void main(String[] args) {
        for (int x = 0; x < 10; x++) {
            Exercicio01 ex1 = new Exercicio01(x);
            ex1.setDaemon(true);
            ex1.start();
        }
        System.out.println("ok");
    }
}