package vector.decorator;

import vector.ArrayVector;
import vector.LinkedListVector;
import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.interfaces.Vector;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.util.Iterator;

/**
 * Created by Ilya on 25.03.2015.
 */
public class SynchronizedVector implements Vector {
    private  Vector vector;
    public SynchronizedVector(Vector vector)
    {
        this.vector = vector;
    }

    public double getElement(int index) throws VectorIndexOutOfBoundsException
    {
        synchronized (vector)
        {
            return vector.getElement(index);
        }
    }

    public double getNorm() throws VectorIndexOutOfBoundsException
    {
        synchronized (vector)
        {
           return vector.getNorm();
        }
    }

    public int getSize()
    {
        synchronized (vector)
        {
            return vector.getSize();
        }
    }

    public void setElement(int index, double value)
    {
        synchronized (vector)
        {
            vector.setElement(index,value);
        }
    }

    public Iterator iterator()
    {
        synchronized (vector)
        {
            return vector.iterator();
        }
    }

    public synchronized String toString()
    {
        return vector.toString();
    }

    public boolean equals(Object object){
        if (object == this) return true;
        if (object == null) return false;
        if (object.getClass() == this.getClass())

        {
            synchronized (vector) {
                return vector.equals(((SynchronizedVector)object).vector);
            }
        }
        else return false;
    }

    public int hashCode()
    {
        synchronized (vector)
        {
            return vector.hashCode() ^ 123567890;
        }
    }

    public Object clone()
    {
        synchronized (vector) {
            SynchronizedVector syncVector = new SynchronizedVector((LinkedListVector) vector.clone());
            return syncVector;
        }
    }

}
