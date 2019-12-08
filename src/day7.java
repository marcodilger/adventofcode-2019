
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class day7 {
        public static void main(String[] args) {
            String input = readByLine.readAsString("day7.txt").get(0);
            String[] opsString = input.split(",");
            int[] ops = new int[opsString.length];
            int i = 0;
            for (String s : opsString) {
                ops[i] = Integer.parseInt(s);
                i++;
            }
            
            List<Integer> fullSettings = new ArrayList<>();
            fullSettings.addAll(Arrays.asList(0, 1, 2, 3, 4));
            
            int maxResponse = 0;
            
            for (int s0 : fullSettings) {
                int response0 = runOps(ops, s0, 0);
                List<Integer> settingsAmp1 = getSublist(fullSettings, s0);
                for (int s1 : settingsAmp1) {
                    int response1 = runOps(ops, s1, response0);
                    List<Integer> settingsAmp2 = getSublist(settingsAmp1, s1); 
                    
                    for (int s2 : settingsAmp2) {
                    int response2 = runOps(ops, s2, response1);
                    List<Integer> settingsAmp3 = getSublist(settingsAmp2, s2); 
                    
                        for (int s3 : settingsAmp3) {
                        int response3 = runOps(ops, s3, response2);
                        List<Integer> settingsAmp4 = getSublist(settingsAmp3, s3); 
                    
                            for (int s4 : settingsAmp4) {
                                int finalResponse = runOps(ops, s4, response3);
                                if (finalResponse > maxResponse) {
                                    maxResponse = finalResponse;
                                    System.out.println("greater Signal found at: " +
                                            s0 + s1 + s2 +s3 +s4 + ": " + maxResponse);
                                    
                                }
                    
                    
                            }
                        }
                    
                    }
                }
            }            
            }
        
        public static List<Integer> getSublist(List<Integer> list, int setting) {
            List<Integer> smallerList = new ArrayList<Integer>();    
            smallerList.addAll(list);
            smallerList.remove(Integer.valueOf(setting));
            
            return smallerList;
        }
            
    
     
        public static int runOps(int[] ops, int arg1, int arg2) {
            
            boolean breakFound = false;
            int pos = 0;
            int jump = 4;
            int result = -1;

            int opCode;
            
            int param1 = 0; // to store the vlaue of parameter 2 depending on parametermode
            int param2 = 0; // to store the value of par 1 ... 
            
            while (breakFound == false & pos <= ops.length) {
                
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
                       ops[ops[pos + 1]] = arg1;
                       arg1 = arg2;
                    } else if (opCode == 4) { // output
                        jump = 2;
                        result = param1;
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
            
            return result;
        }
    
}
