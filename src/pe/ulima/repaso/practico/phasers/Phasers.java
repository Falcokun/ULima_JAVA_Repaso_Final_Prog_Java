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

        pun.start();
        pon.start();
        pin.start();

        phaser.arrive();

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
                phaser.awaitAdvance(2);
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(Thread.currentThread());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
