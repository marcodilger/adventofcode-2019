
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
public class intcode {
            int[] ops;
            int pos;
            int initialSetting;
            int lastReturnValue;
            boolean isSet;
            boolean active;
            
            public intcode(int[] ops, int setting) {
                this.pos = 0;
                this.ops = Arrays.copyOf(ops, ops.length);
                this.initialSetting = setting;
                this.isSet = false;
                this.active = true;
                this.lastReturnValue = 0;
            }
            
            public int runCode(int input) {
                
                if (input == -1) {
                    return -1;
                }
            
            //int pos = 0;
            int jump = 4;

            int opCode;
            boolean breakFound = false;
            int result = -1;
            
            int param1 = 0; // to store the vlaue of parameter 2 depending on parametermode
            int param2 = 0; // to store the value of par 1 ... 
            
                while (breakFound == false & pos < ops.length) {
                   //System.out.println("starting opcode eval"); 
                   //this.printIntcode();
                   
                    //determine opCode
                    opCode = ops[pos] % 100;
                    
                    if (opCode == 99) {
                    //    System.out.println("99 encountered");
                        active = false;
                        break;
                    }
                    //System.out.println("opcode: " + opCode);
                    
                    
                    //System.out.println("current pos: " + pos);
                    // System.out.println("current opCode: " + opCode);

                    //determine parametermodes
                    if (opCode != 99) { // 99 has no parameters/arguments
                        if (((ops[pos] / 100) % 10) == 0) { // position mode
                            param1 = ops[ops[pos + 1]];
                        } else {    // 1 = immediate mode
                            param1 = ops[pos + 1];
                        }
                    }

                    if (opCode != 3 & opCode != 4 & opCode != 99) { //opCodes 3 and 4 don't use paramter/only have 1 parameter
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
                        //breakFound = true;
                      //  active = false;
                        break; // this round is over
                    } else if (opCode == 1) { // addition
                        //Parameters that an instruction writes to will never be in immediate mode.
                        ops[ops[pos + 3]] = param1 + param2;

                    } else if (opCode == 2) { // multiplication 
                        ops[ops[pos + 3]] = param1 * param2;  //Parameters that an instruction writes to will never be in immediate mode.
                    } else if (opCode == 3) { // input
                        jump = 2;
                        if (!isSet) {
                            ops[ops[pos + 1]] = this.initialSetting;
                            isSet = true;
                           // System.out.println("amp is set");
                        } else {
                            ops[ops[pos + 1]] = input;
                           // System.out.println("" + isSet);
                           // System.out.println("using " + input + " as input");
                        }
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
                    //System.out.println("performing jump... to: " + pos);
                }
                lastReturnValue = result;
       
                
            return result;
            }
            
            public int getPos() {
                return pos;
            }
            
            public void changePos(int pos, int newValue) {
                ops[pos] = newValue;
            }
            
            public int[] getIntcode() {
                return ops;
            }
            
            public void printIntcode(){
                for (int op : ops) {
                    System.out.print(" " + op);
                }
                    System.out.println("");
            }
    

    
}
