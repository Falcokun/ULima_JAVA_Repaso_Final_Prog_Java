package pe.ulima.repaso.teoria._3_EjemploTresThreads;

/**
 * Created by Ricardo on 17/07/2015.
 * Repaso
 */

class MathSin extends Thread {
    double angulo;
    public double res;

    public MathSin(double angulo) {
        this.angulo = angulo;
    }

    @Override
    public void run() {
        System.out.println("Calculando el seno de: " + angulo);
        res = Math.sin(Math.toRadians(angulo));
        System.out.println("Fin de Sin");
    }
}

class MathCos extends Thread {
    double angulo;
    public double res;

    public MathCos(double angulo) {
        this.angulo = angulo;
    }

    @Override
    public void run() {
        System.out.println("Calculando el seno de: " + angulo);
        res = Math.cos(Math.toRadians(angulo));
        System.out.println("Fin de Sin");
    }
}

class MathTan extends Thread {
    double angulo;
    public double res;

    public MathTan(double angulo) {
        this.angulo = angulo;
    }

    @Override
    public void run() {
        System.out.println("Calculando el seno de: " + angulo);
        res = Math.tan(Math.toRadians(angulo));
        System.out.println("Fin de Sin");
    }
}

public class MathThreads {
    public static void main(String[] args) {
        MathSin st = new MathSin(45);
        MathCos ct = new MathCos(45);
        MathTan tt = new MathTan(45);

        st.start();
        ct.start();
        tt.start();


        try {
            st.join();
            ct.join();
            tt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Suma de seno, coseno y tangente: " + (st.res + ct.res + tt.res));
    }
}
