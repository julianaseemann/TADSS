public class Somador implements Runnable {
    private short[] chunck;
    private long resultado;

    public Somador(short[] chunck) { // Construtor que recebe um array de números a serem somados
        this.chunck = chunck;
    }

    public long getResultado() { // Método para obter o resultado da soma
        return resultado;
    }

    @Override
    public void run() { // Método que realiza a soma dos números no array
        for (int i = 0; i < chunck.length; i++) {
            resultado += chunck[i];
        }
    }
}
