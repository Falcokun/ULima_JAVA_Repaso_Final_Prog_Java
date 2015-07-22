package pe.ulima.repaso.practico.atomicvariables;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by smorzán on 22/07/2015.
 * Repaso
 */
public class AtomicIntegerMain {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        Thread pin, pon, pun;
        pin = new PinPonPun(integer, 0);
        pon = new PinPonPun(integer, 1);
        pun = new PinPonPun(integer, 2);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pun.start();
        pon.start();
        pin.start();
    }
}

class PinPonPun extends Thread {
    AtomicInteger integer;
    int orden;

    public PinPonPun(AtomicInteger integer, int orden) {
        this.integer = integer;
        this.orden = orden;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if ((integer.get() % 3) == orden) {
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread());
                    integer.addAndGet(1);
                } else {
                    TimeUnit.MILLISECONDS.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}