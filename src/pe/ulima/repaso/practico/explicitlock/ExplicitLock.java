package pe.ulima.repaso.practico.explicitlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Ricardo on 23/07/2015.
 * Repaso
 */
public class ExplicitLock {
    public static void main(String[] args) {
        Lock myLock = new ReentrantLock(true);
        Thread pin, pon, pun;
        pin = new PinPonPun(myLock);
        pon = new PinPonPun(myLock);
        pun = new PinPonPun(myLock);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        try {
            myLock.lock();
            pin.start();
            TimeUnit.MILLISECONDS.sleep(300);
            pon.start();
            TimeUnit.MILLISECONDS.sleep(300);
            pun.start();
            myLock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class PinPonPun extends Thread {
    private Lock myLock;

    public PinPonPun(Lock myLock) {
        this.myLock = myLock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                myLock.lock();
                System.out.println(Thread.currentThread());
                TimeUnit.MILLISECONDS.sleep(300);
                myLock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
