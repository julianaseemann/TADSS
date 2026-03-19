import java.util.Random;

public class Teste {
    public static void main(String[] args) throws InterruptedException {
        int[] lista = new int[1_000_000_000];
        Random random = new Random();
        for (int i = 0; i < lista.length; i++) {
            lista[i] = random.nextInt(1000);
        }
    }
}
