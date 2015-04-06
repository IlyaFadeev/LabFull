package vector.decorator;


import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.interfaces.Vector;

import java.util.Iterator;

/**
 * Created by Ilya on 24.03.2015.
 */
public class VectorDecorator implements Vector {
    Vector vector;

    public VectorDecorator(Vector vector)
    {
        this.vector = vector;
    }

    public double getElement(int index) throws VectorIndexOutOfBoundsException
    {
        return vector.getElement(index);
    }

    public void setElement(int index, double number)
    {
        throw new UnsupportedOperationException("Unsupported operation!");
    }

    public double getNorm() throws VectorIndexOutOfBoundsException
    {
        return vector.getNorm();
    }

    public int getSize()
    {
        return vector.getSize();
    }

    public Iterator iterator()
    {
        return vector.iterator();
    }

    public String toString()
    {
        return vector.toString();
    }

    public boolean equals(Vector vector)
    {
        return vector.equals(vector);
    }



}
