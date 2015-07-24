package pe.ulima.repaso.practico.latcher;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Ricardo on 23/07/2015.
 * Repaso
 */
public class Latcher {

    public static final int COUNT_LATCHERS = 2;

    public static void main(String[] args) {
        AtomicReference<CountDownLatch> punpin = new AtomicReference<>();
        AtomicReference<CountDownLatch> pinpon = new AtomicReference<>();
        AtomicReference<CountDownLatch> ponpun = new AtomicReference<>();

        punpin.set(new CountDownLatch(COUNT_LATCHERS));
        pinpon.set(new CountDownLatch(COUNT_LATCHERS));
        ponpun.set(new CountDownLatch(COUNT_LATCHERS));

        Thread pin, pon, pun;
        pin = new PinPonPun(punpin, pinpon, ponpun);
        pon = new PinPonPun(pinpon, ponpun, punpin);
        pun = new PinPonPun(ponpun, punpin, pinpon);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pin.start();
        pon.start();
        pun.start();

        pinpon.get().countDown();
        punpin.get().countDown();
        punpin.get().countDown();

    }
}

//Incompleto
class PinPonPun extends Thread {
    AtomicReference<CountDownLatch> disparador, otro1, otro2;

    public PinPonPun(AtomicReference<CountDownLatch> disparador, AtomicReference<CountDownLatch> otro1, AtomicReference<CountDownLatch> otro2) {
        this.disparador = disparador;
        this.otro1 = otro1;
        this.otro2 = otro2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                disparador.get().await();
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(Thread.currentThread());
                disparador.set(new CountDownLatch(Latcher.COUNT_LATCHERS));
                otro1.get().countDown();
                otro2.get().countDown();
                /*
                System.out.println("este " + disparador.toString());
                System.out.println("otro1 " + otro1.toString());
                System.out.println("otro2 " + otro2.toString());
                */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
