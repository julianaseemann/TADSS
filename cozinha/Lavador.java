//package cozinha;

public class Lavador implements Runnable {
    private PilhaPratos sujos;
    private PilhaPratos escorredor;

    public Lavador(PilhaPratos sujos, PilhaPratos escorredor) {
        this.sujos = sujos;
        this.escorredor = escorredor;
    }

    @Override
    public void run() {
        while (sujos.temPrato()) {
            Prato prato = sujos.removePrato();
            System.out.println("Lavando prato: " + prato);
            prato.setEstado(Estado.Molhado);
            System.out.println("Lavei prato: " + prato);
            escorredor.addPrato(prato);
        }
        escorredor.setFim();
        System.out.println("Lavador terminou");
    }
}
