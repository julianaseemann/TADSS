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
        Thread l1 = Thread.ofPlatform().name("Lavador1").start(lavador);
        Enxugador enxugador = new Enxugador(escorredor, limpos);
        Thread e1 = Thread.ofPlatform().name("Enxugador1").start(enxugador);
        Enxugador enxugador2 = new Enxugador(escorredor, limpos);
        Thread e2 = Thread.ofPlatform().name("Enxugador2").start(enxugador2);
        l1.join();
        e1.join();
        e2.join();
        System.out.println(sujos);
        System.out.println(limpos);
    }
}
