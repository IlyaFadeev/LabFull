package vector.iterators;

import vector.LinkedListVector;
import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.interfaces.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ilya on 21.03.2015.
 */
public class LinkedListVectorIterator implements Iterator<Double> {

    List<Double> vector;
    int currIndex = 0;
    public LinkedListVectorIterator(List<Double> vector)
    {
        this.vector = vector;
    }

    public boolean hasNext()
    {
        if (currIndex < vector.size()) return true;
        return false;
    }

    public Double next()
    {

        double currElement = vector.get(currIndex);
        currIndex++;

        return currElement;
    }

    public void remove()
    {

    }

}
