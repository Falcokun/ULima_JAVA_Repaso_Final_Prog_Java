package pe.ulima.repaso.practico.semaforos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by smorzán on 22/07/2015.
 * Repaso
 */
public class Semaforos {

    final static int THREAD_CONCURRENCIA = 1;

    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(THREAD_CONCURRENCIA,true);


        Thread pin, pon, pun;
        pin = new PinPonPun(semaforo);
        pon = new PinPonPun(semaforo);
        pun = new PinPonPun(semaforo);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pin.setPriority(Thread.MAX_PRIORITY);
        pon.setPriority(Thread.NORM_PRIORITY);
        pun.setPriority(Thread.MIN_PRIORITY);

        try {
            semaforo.acquire();

            pin.start();
            TimeUnit.MILLISECONDS.sleep(300);
            pon.start();
            TimeUnit.MILLISECONDS.sleep(300);
            pun.start();

            semaforo.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}

class PinPonPun extends Thread {
    private Semaphore semaforo;

    public PinPonPun(Semaphore semaforo) {
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaforo.acquire();
                System.out.println(Thread.currentThread());
                TimeUnit.MILLISECONDS.sleep(300);
                semaforo.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
