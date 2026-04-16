//package cozinha;

public class Enxugador implements Runnable {
    private PilhaPratos escorredor;
    private PilhaPratos limpos;

    public Enxugador(PilhaPratos escorredor, PilhaPratos limpos) {
        this.escorredor = escorredor;
        this.limpos = limpos;
    }

    @Override
    public void run() {
        while (limpos.temEspaco()) {
            Prato prato = escorredor.removePrato();
            if (prato != null) {
                System.out.println(Thread.currentThread().getName() + " enxugando " + prato);
                prato.setEstado(Estado.Limpo);
                limpos.addPrato(prato);
            }
        }
        System.out.println(Thread.currentThread().getName() + " terminou");
    }
}
