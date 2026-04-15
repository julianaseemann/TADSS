//package cozinha;

public class Cozinha {
    public static void main(String[] args) throws Exception {
        PilhaPratos sujos = new PilhaPratos(100);
        PilhaPratos escorredor = new PilhaPratos(10);
        PilhaPratos limpos = new PilhaPratos(100);

        for (int i = 1; i <= 100; i++) {
            sujos.addPrato(new Prato(i, Estado.Sujo));
        }

        System.out.println(sujos);
        System.out.println(limpos);

        Lavador lavador = new Lavador(sujos, escorredor);
        Thread l1 =Thread.ofPlatform().start(lavador);
        Enxugador enxugador = new Enxugador(escorredor, limpos);
        Thread e1 = Thread.ofPlatform().start(enxugador);
        l1.join();
        e1.join();
        System.out.println(sujos);
        System.out.println(limpos);
    }
}
