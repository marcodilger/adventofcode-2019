
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
            day7 day7 = new day7();
            day7.part2();
            
            }
        
        public void part2() {
        String input = readByLine.readAsString("day7test.txt").get(0);
            String[] opsString = input.split(",");
            int[] ops = new int[opsString.length];
            int i = 0;
            for (String s : opsString) {
                ops[i] = Integer.parseInt(s);
                i++;
            }
            
            List<Integer> fullSettings = new ArrayList<>();
            fullSettings.addAll(Arrays.asList(5, 6, 7, 8, 9));
            
           
            //create settings
            
            List<Integer[]> settingsList = createSettingsList(fullSettings);
            
            // go through settings
            
            // for now only first
            //Integer[] s = settingsList.get(0);
            
            int[] s = new int[]{9,8,7,6,5};
      
            //     9,8,7,6,5
            
        
            Amplifier amp0 = new Amplifier(ops, s[0]);
            Amplifier amp1 = new Amplifier(ops, s[1]);
            Amplifier amp2 = new Amplifier(ops, s[2]);
            Amplifier amp3 = new Amplifier(ops, s[3]);
            Amplifier amp4 = new Amplifier(ops, s[4]);
            
            System.out.println("return after init: " + amp0.runCode(s[0]));
            amp1.runCode(s[1]);
            amp2.runCode(s[2]);
            amp3.runCode(s[3]);
            amp4.runCode(s[4]);
            
  
            int ampOut = amp0.runCode(0);
            while (ampOut != -1) {
                System.out.println("running amp1");
                ampOut = amp1.runCode(ampOut);
                System.out.println("running amp2");
                ampOut = amp2.runCode(ampOut);
                System.out.println("running amp3");
                ampOut = amp3.runCode(ampOut);
                System.out.println("running amp4");
                ampOut = amp4.runCode(ampOut);
                System.out.println("running amp0");
            }
            System.out.println("" + ampOut);
            
            
            // end loop when ? when all are inactive?
            
            System.out.println(amp4.lastReturnValue);
            
        }
        
        public class Amplifier {
            int[] ops;
            int pos;
            int initialSetting;
            int lastReturnValue;
            boolean isSet;
            boolean active;
            
            public Amplifier(int[] ops, int setting) {
                this.pos = 0;
                this.ops = ops;
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
            
                while (breakFound == false & pos <= ops.length) {

                    //determine opCode
                    opCode = ops[pos] % 100;
                    
                    // System.out.println("current pos: " + pos);
                    // System.out.println("current opCode: " + opCode);

                    //determine parametermodes
                    if (opCode != 99) {
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
                        breakFound = true;
                        active = false;
                        System.out.println("99 encountered");
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
                }
                lastReturnValue = result;
       
                
            return result;
            }
            
            public int getPos() {
                return pos;
            }
    
}

        
        public static List<Integer> getSublist(List<Integer> list, int setting) {
            List<Integer> smallerList = new ArrayList<Integer>();    
            smallerList.addAll(list);
            smallerList.remove(Integer.valueOf(setting));
            
            return smallerList;
        }
        
        public static List<Integer[]> createSettingsList(List<Integer> fullSettings){
            List<Integer[]> settingsList = new ArrayList<Integer[]>();
            Integer[] currentSettings = new Integer[5];
            
            for (int s0 : fullSettings) {
                List<Integer> settingsAmp1 = getSublist(fullSettings, s0);
                for (int s1 : settingsAmp1) {
                    List<Integer> settingsAmp2 = getSublist(settingsAmp1, s1);
                    for (int s2 : settingsAmp2) {
                    List<Integer> settingsAmp3 = getSublist(settingsAmp2, s2);
                        for (int s3 : settingsAmp3) {
                        List<Integer> settingsAmp4 = getSublist(settingsAmp3, s3);
                            for (int s4 : settingsAmp4) {
                                currentSettings[0] = s0;
                                currentSettings[1] = s1;
                                currentSettings[2] = s2;
                                currentSettings[3] = s3;
                                currentSettings[4] = s4;
                                
                                settingsList.add(currentSettings);
                                    
                                }
                    
                    
                            }
                        }
                    
                    }
                }
            return settingsList;
            }
        
    
}
