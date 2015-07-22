package pe.ulima.repaso.practico.colaboracion;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by smorzán on 22/07/2015.
 * Repaso
 */
public class Colaboracion {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Thread pin, pon, pun;

        pin = new PinPonPun(buffer);
        pon = new PinPonPun(buffer);
        pun = new PinPonPun(buffer);

        pin.setName("PIN");
        pon.setName("PON");
        pun.setName("PUN");

        pun.start();
        pon.start();
        pin.start();
    }
}

class PinPonPun extends Thread {
    Buffer buffer;

    public PinPonPun(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (buffer.leer().equals(Thread.currentThread().getName())) {
                    TimeUnit.MILLISECONDS.sleep(300);
                    System.out.println(Thread.currentThread());
                    buffer.escribir(Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Buffer {

    String valor;

    public Buffer() {
        valor = "PIN";
    }

    public synchronized void escribir(String escribir) {
        switch (escribir) {
            case ("PIN"): {
                valor = "PON";
                break;
            }
            case ("PON"): {
                valor = "PUN";
                break;
            }
            case ("PUN"): {
                valor = "PIN";
                break;
            }
            default: {
                valor = escribir;
                break;
            }
        }
    }

    public synchronized String leer() {
        return valor;
    }

}
