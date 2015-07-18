package pe.ulima.repaso.teoria._1_Threads;

/**
 * Created by Ricardo on 17/07/2015.
 * Repaso
 */


class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(" this thread is running ...");
    }
}

/**
 * 1ra Forma: Extender Thread
 */
public class ThreadEx1 extends Thread {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
