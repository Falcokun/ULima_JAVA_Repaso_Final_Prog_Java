package pe.ulima.repaso.practico.phasers;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ricardo on 21/07/2015.
 * Repaso
 */
public class Phasers {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        Thread pin, pon, pun;
        pin = new PinPonPun(phaser);
        pon = new PinPonPun(phaser);
        pun = new PinPonPun(phaser);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pin.setPriority(Thread.MAX_PRIORITY);
        pon.setPriority(Thread.NORM_PRIORITY);
        pun.setPriority(Thread.MIN_PRIORITY);

        pin.start();
        pon.start();
        pun.start();

    }
}

class PinPonPun extends Thread {
    private Phaser phaser;

    public PinPonPun(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        while (!phaser.isTerminated()) {
            try {
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread());
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
