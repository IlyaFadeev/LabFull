package vector;


import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.factories.ArrayVectorFactory;
import vector.factories.LinkedListVectorFactory;
import vector.interfaces.Vector;
import vector.iterators.ArrayVectorIterator;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Ilya on 08.03.2015.
 */
public class ArrayVector implements Vector{

    private double[] vector;
    ArrayVector()
    {

    }

    public ArrayVector(int size)
    {
        vector = new double[size];
    }

    public double getElement(int index) throws VectorIndexOutOfBoundsException
    {
        String message = "Vector Index Out Of Bounds";
        if (index > vector.length - 1) throw new VectorIndexOutOfBoundsException(message);

        return vector[index];
    }

    public int getSize()
    {
        return vector.length;
    }

    public void setElement(int index, double element)
    {

        vector[index] = element;

    }



public double getNorm() throws VectorIndexOutOfBoundsException
{
    double norm = 0;

    for (int i = 0; i < vector.length; i++) {

        norm += (vector[i] * vector[i]);
    }

    return Math.sqrt(norm);
}
private class LinkedListVectorIterator implements java.util.Iterator<Double> {
    int currIndex = 0;
    public LinkedListVectorIterator()
    {

    }

    public boolean hasNext()
    {
        if (currIndex < vector.length) return true;
        return false;
    }

    public Double next()
    {

        double currElement = vector[currIndex];
        currIndex++;

        return currElement;
    }

    public void remove()
    {

    }
}

    public ArrayVectorIterator iterator()
    {
        ArrayVectorIterator iterator = new ArrayVectorIterator(vector);
        return iterator;
    }


    public String toString()
    {
        StringBuffer stringVector = new StringBuffer();

        for (int i = 0; i < vector.length; i++) {
            stringVector.append(vector[i]);
            stringVector.append(" ");
        }

        return stringVector.toString();
    }

    public boolean equals(Object object)
    {
        Vector vector;
        if (object == this) return true;
        if (object == null) return false;
        if (object.getClass() == this.getClass()) vector = (LinkedListVector)object;
        else return false;
        if (object.getClass() == this.getClass()) vector = (ArrayVector)object;

            for (int i = 0; i <vector.getSize(); i++) {
                try {
                    if (this.vector[i] != vector.getElement(i)) return false;
                }
                catch (VectorIndexOutOfBoundsException e)
                {
                    System.out.println("Индекс вне границ вектора");
                }
            }


        return true;
    }


    public int hashCode() {
        int result = 0;
        for (Double i : vector) {
            long bits = Double.doubleToRawLongBits(i);
            result ^= ((int)(bits & 0x00000000FFFFFFFFL)) ^ ((int)((bits & 0xFFFFFFFF00000000L) >> 32));
        }
        return result;
    }

    public Object clone()
    {
        Vectors.setVectorFactory(new ArrayVectorFactory());
        ArrayVector clonedVector = (ArrayVector)Vectors.vectorFactory.createInstance(vector.length);
        for (int i = 0; i < vector.length; i++) {
            clonedVector.vector[i]=vector[i];
        }
        return clonedVector;
    }

}

