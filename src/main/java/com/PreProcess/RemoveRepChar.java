package com.PreProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Methma Samaranayake on 6/8/2017.
 */
public class RemoveRepChar {
//    public static void main(String args[])throws IOException {

        public String removeRep(String s){
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("Enter any word: "); // Inputting the word
//            String s = br.readLine();

            s = s + " "; // Adding a space at the end of the word
            int l = s.length(); // Finding the length of the word
            String ans = ""; // Variable to store the final result
            char ch1, ch2;

            for (int i = 0; i < l - 1; i++) {
                ch1 = s.charAt(i); // Extracting the first character
                ch2 = s.charAt(i + 1); // Extracting the next character

// Adding the first extracted character to the result if the current and the next characters are different

                if (ch1 != ch2) {
                    ans = ans + ch1;
                }
            }
//            System.out.println("Word after removing repeated characters = " + ans); // Printing the result

            return ans;
        }


}
