package vector.threads;

import vector.interfaces.Vector;

import java.util.Random;

/**
 * Created by Ilya on 24.03.2015.
 */
public class FillVectorThread extends Thread {
    Vector vector;
    Random rnd = new Random();
    public FillVectorThread(Vector vector)
    {
        this.vector = vector;
        this.start();
    }

    public void run()
    {
        for (int i = 0; i < vector.getSize(); i++) {

            vector.setElement(i,rnd.nextDouble() + 1.0);
        }
    }
}
