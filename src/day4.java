/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class day4 {
    public static void main(String[] args) {
        //bruteforce
        
        int[] codeArray = new int[6];
        int counter = 0;
        int start = 264793;
        int end = 803935;
        
        for (int code = start; code <= end; code++){
            int temp = code;
            for (int i = 5; i >= 0; i--) {
                codeArray[i] = temp % 10;
                temp = temp / 10;
               }
            
            //validity checks
            boolean containsDouble = false;
            boolean strictlyIncrementing = true;
            
            // check for doubles
            for (int i = 0; i < 5; i++) {
                if (codeArray[i] == codeArray[i + 1]) {
                    containsDouble = true;
                    break;
                }
            }
            // check for strictly incrementing
            if (containsDouble == true) {
                    for (int k = 0; k < 5; k++) {
                        if (codeArray[k] > codeArray[k + 1]) {
                            strictlyIncrementing = false;
                            break;
                        }
                    }
                if (strictlyIncrementing) {
                    counter++;
                }
            }
        }
        System.out.println("result day 2 part 1: " + counter);
                
    }
    
}
