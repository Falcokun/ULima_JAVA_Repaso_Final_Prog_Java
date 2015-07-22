package pe.ulima.repaso.practico.atomicvariables;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by smorzán on 22/07/2015.
 * Repaso
 */
public class AtomicInteger {
    public static void main(String[] args) {

    }
}
class PinPonPun extends Thread {
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