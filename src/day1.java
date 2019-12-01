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

public class day1 {
    public static void main(String[] args) {
        
        List<Integer> inputInt = readByLine.readAsInt("day1.txt");
        System.out.println(inputInt);
        
        // day 1 part 1: for each mass (integer), calculate the div 3 and subtract 2.
        int result = 0;
        for (int mass : inputInt) {
            int fuel = (mass / 3) - 2;
            System.out.println(fuel);
            result = result + fuel;
        }
        
        System.out.print("Result: ");
        System.out.println(result);
    }
    
}
