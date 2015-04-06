package com.company;

import vector.ArrayVector;
import vector.LinkedListVector;

import vector.Vectors;

import vector.decorator.SynchronizedVector;
import vector.decorator.VectorDecorator;
import vector.exceptions.VectorIndexOutOfBoundsException;
import vector.factories.LinkedListVectorFactory;
import vector.interfaces.Vector;
import vector.iterators.ArrayVectorIterator;
import vector.threads.FillVectorThread;
import vector.threads.ReadVectorThread;
import vector.threads.SynchronizedVectorReadThread;
import vector.threads.SynchronizedWriteThread;
import vector.vectorsynchronize.VectorSynchronizer;

import java.io.*;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws VectorIndexOutOfBoundsException, IOException, ClassNotFoundException, EOFException{
        FileOutputStream fos = new FileOutputStream("out.txt");
        FileInputStream fis = new FileInputStream("out.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ArrayVector vector = new ArrayVector(10);
        Vectors.setVectorFactory(new LinkedListVectorFactory());
        LinkedListVector listVector = (LinkedListVector)Vectors.vectorFactory.createInstance(0);
        System.out.println("First vector:");
        for (int i = 0; i < vector.getSize(); i++) {
            vector.setElement(i,i+1);
        }
        Vectors.mult(vector,10);
        for (int i = 0; i < vector.getSize(); i++) {
            System.out.println(vector.getElement(i));
        }

        System.out.println(vector.getNorm());
        oos.writeObject(vector);
        oos.flush();
        oos.close();
        System.out.println();
        System.out.println("Second vector:");
        ArrayVector vector1 = (ArrayVector)ois.readObject();
        for (int i = 0; i < vector1.getSize(); i++) {
            System.out.println(vector1.getElement(i));
        }



        ArrayVectorIterator iterator = vector.iterator();
        Iterator listIterator = listVector.iterator();

        for (int i = 0; i < 10; i++) {
            listVector.add(i);
        }

        Vectors.mult(listVector,10);
        System.out.println("LinkedVector Iterator");
        while (listIterator.hasNext())
        {
            System.out.println(listIterator.next());
        }
        System.out.println("ArrayVector Iterator");
        while (iterator.hasNext())
        {
            System.out.println(iterator.next());
        }

        System.out.println(vector.toString());
        System.out.println(vector.equals(listVector));
        System.out.println(vector.hashCode());

        ArrayVector vector2 = new ArrayVector(vector.getSize());
        FileWriter out = new FileWriter("stream.txt");
        Vectors.writeVector(vector, out);
        out.close();
        out.close();
        FileReader in = new FileReader("stream.txt");
        Vector vector4 = Vectors.readVector(in);
        for (int i = 0; i < vector4.getSize(); i++) {
            System.out.println(vector4.getElement(i));
        }

        Vector vectorDecorator = Vectors.unmodifableVector(vector);
        //vectorDecorator.getElement(0);
        //vectorDecorator.setElement(0, 0);
        System.out.println("Выполнение потоков:");
        FillVectorThread fillVectorThread = new FillVectorThread(vector);
        ReadVectorThread readVectorThread = new ReadVectorThread(vector);

        VectorSynchronizer synchronizer = new VectorSynchronizer(vector);
        SynchronizedVectorReadThread synchronizedVectorReadThread = new SynchronizedVectorReadThread(synchronizer);
        SynchronizedWriteThread synchronizedWriteThread = new SynchronizedWriteThread(synchronizer);
        synchronizedWriteThread.run();
        synchronizedVectorReadThread.run();

        System.out.println("End");

        VectorSynchronizer vectorSynchronizer = new VectorSynchronizer(new SynchronizedVector(vector));
        SynchronizedVectorReadThread synchronizedVector = new SynchronizedVectorReadThread(vectorSynchronizer);
        SynchronizedWriteThread synchronizedWrite = new SynchronizedWriteThread(vectorSynchronizer);
        synchronizedWrite.run();
        synchronizedVector.run();


        System.out.println("End");

        System.out.println(synchronizedVector.hashCode());
        System.out.println(vector.hashCode());



    }
}
