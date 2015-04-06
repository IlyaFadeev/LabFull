package vector;

import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.factories.ArrayVectorFactory;
import vector.factories.LinkedListVectorFactory;
import vector.interfaces.Vector;
import vector.iterators.LinkedListVectorIterator;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ilya on 12.03.2015.
 */
public class LinkedListVector implements Vector{
    List<Double> vector = new LinkedList<Double>();

    public double getElement(int i) throws VectorIndexOutOfBoundsException
    {
        String message = "Vector Index Out Of Bounds";
        if (i > vector.size() - 1) throw new VectorIndexOutOfBoundsException(message);
        return vector.get(i);
    }

    public void setElement(int index, double element)
    {
        vector.set(index,element);
    }

    public int getSize()
    {
        return vector.size();
    }

    public double getNorm() throws VectorIndexOutOfBoundsException
    {
        double norm = 0;

        for (int i = 0; i < vector.size(); i++)  {

            norm += (vector.get(i) * vector.get(i));
        }

        return Math.sqrt(norm);
    }

    public void add(double value)
    {
        vector.add(value);
    }

    public void remove(int index)
    {
        vector.remove(index);
    }

    public LinkedListVectorIterator iterator()
    {
        LinkedListVectorIterator linkedListIterator = new LinkedListVectorIterator(vector);
        return linkedListIterator;
    }


    public String toString()
    {
        StringBuffer stringVector = new StringBuffer();

        for (int i = 0; i < vector.size(); i++) {
            stringVector.append(vector.get(i));
            stringVector.append(" ");
        }

        return stringVector.toString();
    }

    public boolean equals(Object object)
    {
        Vector vector;
        if (object.getClass().getName() == "vector.LinkedListVector") vector = (LinkedListVector)object;
        else return false;
        if (object.getClass().getName() == "vector.ArrayVector") vector = (ArrayVector)object;

        for (int i = 0; i <vector.getSize(); i++) {
            try {
                if (this.vector.get(i) != vector.getElement(i)) return false;
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
        Vectors.setVectorFactory(new LinkedListVectorFactory());
        LinkedListVector clonedVector = (LinkedListVector)Vectors.vectorFactory.createInstance(0);
        for (int i = 0; i < vector.size(); i++) {
            clonedVector.setElement(i,vector.get(i));
        }
        return clonedVector;
    }

}
