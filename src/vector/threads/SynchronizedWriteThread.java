package vector.threads;

import vector.ArrayVector;
import vector.interfaces.Vector;
import vector.vectorsynchronize.VectorSynchronizer;

import java.util.Random;

/**
 * Created by Ilya on 24.03.2015.
 */
public class SynchronizedWriteThread implements Runnable {
    VectorSynchronizer synchronizer;
    Random rnd = new Random();
    public SynchronizedWriteThread(VectorSynchronizer synchronizer)
    {
        this.synchronizer = synchronizer;
    }
    public void run()
    {

        try {
            synchronizer.write(rnd.nextDouble());
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
