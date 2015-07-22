package pe.ulima.repaso.practico.atomicvariables.atboolean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by smorzán on 22/07/2015.
 * Repaso
 */
public class AtomicBooleanMain {
    public static void main(String[] args) {
        AtomicBoolean pinpon = new AtomicBoolean(false);
        AtomicBoolean ponpun = new AtomicBoolean(false);
        AtomicBoolean punpin = new AtomicBoolean(false);

        Thread pin, pon, pun;
        pin = new PinPonPun(punpin, pinpon);
        pon = new PinPonPun(pinpon, ponpun);
        pun = new PinPonPun(ponpun, punpin);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pun.start();
        pon.start();
        pin.start();

        punpin.set(true);
    }
}

class PinPonPun extends Thread {
    AtomicBoolean entrada, salida;

    public PinPonPun(AtomicBoolean entrada, AtomicBoolean salida) {
        this.entrada = entrada;
        this.salida = salida;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (entrada.get()) {
                    entrada.set(false);
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread());
                    salida.set(true);
                } else {
                    TimeUnit.MILLISECONDS.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}