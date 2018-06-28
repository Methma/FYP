package com.FileHandle;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

import java.io.File;


/**
 * Created by Methma Samaranayake on 6/11/2017.
 */
public class Converter {
    public static void Convert(String sourcepath, String destpath) throws Exception {
        // load CSV
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(sourcepath));
        Instances data = loader.getDataSet();

        // save ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(new File(destpath));
//        saver.setDestination(new File(destpath));
        saver.writeBatch();
    }
}
