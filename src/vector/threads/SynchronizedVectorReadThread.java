package vector.threads;

import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.interfaces.Vector;
import vector.vectorsynchronize.VectorSynchronizer;

/**
 * Created by Ilya on 24.03.2015.
 */
public class SynchronizedVectorReadThread implements Runnable {
    VectorSynchronizer synchronizer;
    public SynchronizedVectorReadThread(VectorSynchronizer synchronizer)
    {
        this.synchronizer = synchronizer;
    }
    public void run()
    {

        try {
            synchronizer.read();
        }
        catch (VectorIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
