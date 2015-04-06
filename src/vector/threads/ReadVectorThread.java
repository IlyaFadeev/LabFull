package vector.threads;

import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.interfaces.Vector;

import java.io.Reader;

/**
 * Created by Ilya on 24.03.2015.
 */
public class ReadVectorThread extends Thread {
    Vector vector;

    public ReadVectorThread(Vector vector)
    {
        this.vector = vector;
        this.start();
    }

    public void run()
    {
        for (int i = 0; i < vector.getSize(); i++) {
            try {
                System.out.println(vector.getElement(i));

            }
            catch (VectorIndexOutOfBoundsException e)
            {
                System.out.println("Выход за пределы вектора!");
            }
        }
    }

}
