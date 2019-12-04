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

        for (int code = start; code <= end; code++) {
            int temp = code;
            for (int i = 5; i >= 0; i--) {
                codeArray[i] = temp % 10;
                temp = temp / 10;
            }

            //validity checks
            boolean containsDouble = false;
            boolean strictlyIncrementing = true;

            // check for doubles
            // probably better, especially with part2 woudl have been:
            // assert tht array is strictly incrementing,
            // then just count digits,
            // part1: at least 1 digit > 2 times
            // part2: at least 1 digit ecactly 2 times
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
                    // counter++;  // part 1
                    // additional requirement for part 2:
                    // there needs to be a shortest chain of adjacent same digits

                    boolean containsStrictDouble = false;
                    for (int i = 0; i <= 9; i++) {

                        if (chainExactlyTwo(i, codeArray)) {
                            counter++;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("result day 2 part 2: " + counter);

    }

    public static boolean chainExactlyTwo(int digit, final int[] array) { // checks for a single digit, if there is a consecutive chain of same digits which is longer than 2
        int max = array.length;
        boolean found2 = false;
        boolean found3 = false;
        for (int i = 0; i < 5; i++) {
            if (array[i] == digit
                    & array[i + 1] == digit) {
                found2 = true;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (array[i] == digit
                    & array[i + 1] == digit
                    & array[i + 2] == digit) {
                found3 = true;
                break;
            }
        }
        return (found2 & !found3); // returns true only if 2 ha sbeen found but not 3
    }
}
