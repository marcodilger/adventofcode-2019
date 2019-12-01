/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

public class readByLine {
    

/**
 * Open and read a file, and return the lines in the file as a list
 * of Strings.
 * (Demonstrates Java FileReader, BufferedReader, and Java5.)
 */
    static List<String> readAsString(String filename) {
        List<String> input = new ArrayList<String>();
        filename = System.getProperty("user.home") + "/github/adventofcode-2019/data/"
                + filename;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                input.add(line);
            }
            reader.close();
            return input;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
    
    static List<Integer> readAsInt(String filename) {
        List<Integer> inputInteger = new ArrayList<Integer>();
        List<String> inputString = readAsString(filename);
        
        for (String line : inputString) {
                    try {

                inputInteger.add(Integer.parseInt(line));
            } catch(NumberFormatException nfe) {
               System.out.println("Could not parse " + nfe);
            } 
        }       
        return inputInteger;
        
    }
}
