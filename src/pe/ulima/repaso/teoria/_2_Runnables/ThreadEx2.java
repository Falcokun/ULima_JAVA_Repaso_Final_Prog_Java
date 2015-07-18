package pe.ulima.repaso.teoria._2_Runnables;

/**
 * Created by Ricardo on 17/07/2015.
 * Repaso
 */

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(" this thread is running...");
    }
}

public class ThreadEx2 {
    public static void main(String[] args) {
        Thread t = new Thread(new MyThread());
        t.start();
    }
}
