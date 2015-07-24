package pe.ulima.repaso.practico.latcher;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ricardo on 23/07/2015.
 * Repaso
 */
public class Latcher {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

    }
}
//Incompleto
class PinPonPun extends Thread {
    CountDownLatch latch;

    public PinPonPun(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        while (true) {
            try {
                latch.await();
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(Thread.currentThread());
                latch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
