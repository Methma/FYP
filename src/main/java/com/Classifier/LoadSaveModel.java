package com.Classifier;

import weka.classifiers.Classifier;

import java.io.*;

/**
 * Created by Methma Samaranayake on 6/11/2017.
 */
public class LoadSaveModel {

    public static Classifier loadModel(String name) {

        Classifier classifier;

        FileInputStream fis;
        try {
            fis = new FileInputStream(name + ".model");
            ObjectInputStream ois = new ObjectInputStream(fis);
            classifier = (Classifier) ois.readObject();
            ois.close();
            return classifier;
        } catch (Exception e) {
            System.out.println("Error in load model");
            e.printStackTrace();
        }

        return null;
    }

    public static void saveModel(Classifier c, String name) {

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(name + ".model"));
            oos.writeObject(c);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
