package vector.interfaces;


import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.iterators.ArrayVectorIterator;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Ilya on 12.03.2015.
 */
public interface Vector extends Serializable {
    public double getElement(int index) throws VectorIndexOutOfBoundsException;
    public int getSize();
    public void setElement(int index, double element);
    public double getNorm() throws VectorIndexOutOfBoundsException;
    public Iterator iterator();
    public String toString();
    public boolean equals(Object object);
    public int hashCode();
    public Object clone();




}
