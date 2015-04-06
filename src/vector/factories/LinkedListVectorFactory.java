package vector.factories;

import vector.LinkedListVector;
import vector.interfaces.Vector;
import vector.interfaces.VectorFactory;

/**
 * Created by Ilya on 21.03.2015.
 */
public class LinkedListVectorFactory implements VectorFactory {
    public LinkedListVector createInstance(int size){
        LinkedListVector vector = new LinkedListVector();

        return vector;
    }
}
