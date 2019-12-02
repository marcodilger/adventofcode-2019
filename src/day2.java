/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class day2 {
        public static void main(String[] args) {
            String input = readByLine.readAsString("day2test.txt").get(0);
            String[] opsString = input.split(",");
            int[] ops = new int[opsString.length];
            int i = 0;
            for (String s : opsString) {
                ops[i] = Integer.parseInt(s);
                i++;
            }
            // custom modifications as per description:
            ops[1] = 12;
            ops[2] = 02;
            
            // run op code
            boolean breakFound = false;
            while (breakFound == false) {
            
                for (int op = 0; op <= opsString.length; op = op + 4) {
                    if (ops[op] == 99) {
                        breakFound = true;
                        break; // this round is over
                    } else if (ops[op] == 1) { // addition
                        ops[ops[op + 3]] = ops[ops[op + 1]] + ops[ops[op + 2]];
                    } else if (ops[op] == 2) { // multiplication
                        ops[ops[op + 3]] = ops[ops[op + 1]] * ops[ops[op + 2]];
                    }
                }
//                System.out.println("\nend of round:");
//                for (int k : ops) {
//                    System.out.println(k);
//                }
            }
            System.out.println("result day 1 part 1: " + ops[0]); // should work for part 1

        }
}
