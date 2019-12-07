/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
import java.util.Scanner;

public class day5 {
    public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
            String input = readByLine.readAsString("day5.txt").get(0);
            String[] opsString = input.split(",");
            int[] ops = new int[opsString.length];
            int i = 0;
            for (String s : opsString) {
                ops[i] = Integer.parseInt(s);
                i++;
            }
            
            
                        // run op code
            boolean breakFound = false;
            int pos = 0;
            int jump = 4;
      //      int parameterMode1 = 0;
      //      int parameterMode2 = 0;

            int opCode;
            
            int param1 = 0; // to store the vlaue of parameter 2 depending on parametermode
            int param2 = 0; // to store the value of par 1 ... 
            
            while (breakFound == false & pos <= opsString.length) {
                
                //determine opCode
                opCode = ops[pos] % 100;
                
                //determine parametermodes
                if (opCode != 99) {
                if (((ops[pos] / 100) % 10) == 0) { // position mode
                    param1 = ops[ops[pos + 1]];
                } else {    // 1 = immediate mode
                    param1 = ops[pos + 1];
                }
                }

                if (opCode != 3 & opCode != 4 & opCode != 99){ //opCodes 3 and 4 don't use paramter/only have 1 parameter
                //assumes 10000-position is always 0
                if (ops[pos] / 1000 == 0) { // position mode
                    param2 = ops[ops[pos + 2]];
                } else {    // 1 = immediate mode
                    param2 = ops[pos + 2];
                } 
                }

                // default jump = 4
                jump = 4;
            
                    if (opCode == 99) {
                        breakFound = true;
                      break; // this round is over
                    } else if (opCode == 1) { // addition
                        //Parameters that an instruction writes to will never be in immediate mode.
                        ops[ops[pos + 3]] = param1 + param2;
                        
                    } else if (opCode == 2) { // multiplication 
                        ops[ops[pos + 3]] = param1 * param2;  //Parameters that an instruction writes to will never be in immediate mode.
                    } else if (opCode == 3) { // input
                        jump = 2;
                       // read single integer and store at following argument
                       System.out.println("give int:");
                       ops[ops[pos + 1]] = Integer.parseInt(reader.nextLine());
                    } else if (opCode == 4) { // output
                        jump = 2;
                        System.out.println("-----------------");
                        System.out.println(param1);
                        System.out.println("-----------------");
                    } else if (opCode == 5) { // jump if true
                        jump = 3;
                        if (param1 != 0) {
                            jump = param2 - pos;
                        }
                    } else if (opCode == 6) { // jump if flase
                        jump = 3;
                        if (param1 == 0) {
                            jump = param2 - pos;
                        }
                    } else if (opCode == 7) { // less than
                        jump = 4;
                        if (param1 < param2) {
                            ops[ops[pos + 3]] = 1;
                        } else {
                            ops[ops[pos + 3]] = 0;
                        }
                    } else if (opCode == 8) { // equals
                        jump = 4;
                        if (param1 == param2) {
                            ops[ops[pos + 3]] = 1;
                        } else {
                            ops[ops[pos + 3]] = 0;
                        }
                    }      
            pos = pos + jump;
            
//            System.out.println("Code: ");
//            for (int opCodes : ops) {
//                System.out.print("," + opCodes);
//            }
            }
            
    }
    
}
