package pe.ulima.repaso.practico.barriers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ricardo on 21/07/2015.
 * Repaso Final
 */
public class Barriers {


    public static void main(String[] args) {
        CyclicBarrier barrier1, barrier2, barrier3;
        Thread pin, pon, pun;

        barrier1 = new CyclicBarrier(2);
        barrier2 = new CyclicBarrier(2);
        barrier3 = new CyclicBarrier(2);

        pin = new PinPonPun(barrier1, barrier2);
        pon = new PinPonPun(barrier2, barrier3);
        pun = new PinPonPun(barrier3, barrier1);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pun.start();
        pon.start();
        pin.start();

        try {
            barrier1.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }


    }

    private static class PinPonPun extends Thread {
        private CyclicBarrier barrierInicio;
        private CyclicBarrier barrierFin;

        public PinPonPun(CyclicBarrier barrierInicio, CyclicBarrier barrierFin) {
            this.barrierInicio = barrierInicio;
            this.barrierFin = barrierFin;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    barrierInicio.await();
                    barrierInicio.reset();
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread());
                    barrierFin.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }

        }


    }

}
