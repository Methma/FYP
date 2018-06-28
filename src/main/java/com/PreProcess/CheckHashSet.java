package com.PreProcess;

import java.util.HashSet;

/**
 * Created by Methma Samaranayake on 6/6/2017.
 */
public class CheckHashSet {
    public static void main(String[] args) {

        //create object of HashSet
        HashSet hSet = new HashSet();

        //add elements to HashSet object
        hSet.add(new Integer("1"));
        hSet.add(new Integer("2"));
        hSet.add(new Integer("3"));

    /*
      To check whether a particular value exists in HashSet use
      boolean contains(Object value) method of HashSet class.
      It returns true if the HashSet contains the value, otherwise false.
    */

        boolean blnExists = hSet.contains(new Integer("3"));
        System.out.println("3 exists in HashSet ? : " + blnExists);

    }
}
