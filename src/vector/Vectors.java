package vector;


import vector.decorator.VectorDecorator;
import vector.exceptions.IncompatibleVectorSizesException;
import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.factories.ArrayVectorFactory;
import vector.interfaces.Vector;
import vector.interfaces.VectorFactory;

import java.io.*;

/**
 * Created by Ilya on 12.03.2015.
 */
public class Vectors {

    public static VectorFactory vectorFactory = new ArrayVectorFactory();

    public static void setVectorFactory(VectorFactory vectorFactory)
    {
        Vectors.vectorFactory = vectorFactory;
    }

    public static void mult(Vector vector, double number) throws VectorIndexOutOfBoundsException
    {
        for (int i = 0; i < vector.getSize(); i++) {
            vector.setElement(i, vector.getElement(i) * number);
        }
    }

    public static Vector scalarMult(Vector vector, Vector vector1) throws VectorIndexOutOfBoundsException
    {
        Vector result = createInstance(vector.getSize());
        String message = "Incompatible Vector Sizes";

        if (vector.getSize() != vector1.getSize()) throw new IncompatibleVectorSizesException(message);

        for (int i = 0; i < vector.getSize(); i++) {
            result.setElement(i,vector.getElement(i) * vector1.getElement(i));
        }

        return result;
    }

    public static Vector sum(Vector vector, Vector vector1) throws VectorIndexOutOfBoundsException
    {
        Vector result = createInstance(vector.getSize());
        String message = "Incompatible Vector Sizes";
        if (vector.getSize() != vector1.getSize()) throw new IncompatibleVectorSizesException(message);
        for (int i = 0; i < vector.getSize(); i++) {
            result.setElement(i, vector.getElement(i) + vector1.getElement(i));
        }

        return result;
    }

    public static void outputVector(Vector vector, OutputStream out) throws VectorIndexOutOfBoundsException, IOException
    {

        DataOutputStream dataOutput = new DataOutputStream(out);

        for (int i = 0; i < vector.getSize(); i++) {
            dataOutput.writeDouble(vector.getElement(i));
        }

        dataOutput.flush();


    }

    public static Vector inputVector(InputStream in) throws IOException
    {

        DataInputStream dataInput = new DataInputStream(in);

        LinkedListVector vector = new LinkedListVector();
        double currNumber = 0;
        while (dataInput.available() > 0)
        {
             vector.add(dataInput.readDouble());
        }

        return vector;
    }

    public static void writeVector(Vector vector, Writer out) throws VectorIndexOutOfBoundsException, IOException
    {

        PrintWriter printWriter = new PrintWriter(out);
        for (int i = 0; i <vector.getSize() ; i++) {
            printWriter.println(vector.getElement(i));
        }


    }

    public static Vector readVector(Reader in) throws IOException
    {
        LinkedListVector vector = new LinkedListVector();
        StreamTokenizer st = new StreamTokenizer(in);

        while (st.nextToken() != StreamTokenizer.TT_EOF)
        {
            if (st.ttype == StreamTokenizer.TT_NUMBER)
            {
                vector.add(st.nval);
            }
        }
        return vector;
    }

    public static Vector createInstance(int size)
    {
        return vectorFactory.createInstance(size);
    }

    public static Vector unmodifableVector(Vector vector)
    {
        return new VectorDecorator(vector);
    }


}
