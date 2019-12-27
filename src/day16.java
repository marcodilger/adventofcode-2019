/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;

/**
 *
 * @author marco
 */
public class day16 {

    public static void main(String[] args) {
        day16 day16 = new day16();
        //day16.part1();
        //day16.part2();
        day16.part2restart();
        
        

    }

    public void part2restart() {
        int multiplicator = 10000;
        String origInput = "59776034095811644545367793179989602140948714406234694972894485066523525742503986771912019032922788494900655855458086979764617375580802558963587025784918882219610831940992399201782385674223284411499237619800193879768668210162176394607502218602633153772062973149533650562554942574593878073238232563649673858167635378695190356159796342204759393156294658366279922734213385144895116649768185966866202413314939692174223210484933678866478944104978890019728562001417746656699281992028356004888860103805472866615243544781377748654471750560830099048747570925902575765054898899512303917159138097375338444610809891667094051108359134017128028174230720398965960712";
        //String origInput = "03036732577212944063491565474664";
        //String origInput = "00000322577212944063491565474664";
        String[] inputString = origInput.split("");
        int offset = Integer.parseInt(origInput.substring(0, 7)); // first 7 digits, always (0, 7) is corrects
        
        System.out.println("offset: " + offset);

        int inputLength = inputString.length;

        int[] input = new int[inputLength];

        for (int i = 0; i < inputLength; i++) {
            input[i] = Integer.parseInt(inputString[i]);
        }
        System.out.println("input string length: " + inputLength);
        System.out.println("input string length after multiplicator: " + inputLength * multiplicator);
        int inputLengthExt = (multiplicator * inputLength) - offset; // length of the input of part 2 after multiplication and removal of the offset
        System.out.println("input string length after multiplicator and offset corr: " + inputLengthExt); // offset correction only helps a little

        int[] inputPart2pre = new int[inputLengthExt + offset];
        for (int i = 0; i < (inputLengthExt + offset); i++) {
            inputPart2pre[i] = input[i % inputLength];
        }

        int[] inputPart2 = Arrays.copyOfRange(inputPart2pre, offset, inputLengthExt + offset); // trimming off the first positions by length of offset  // last argumen tis exclusive

        int[] phase = inputPart2.clone();

//        for (int i = 0; i< 100; i++) {
//            System.out.print(phase[i]);2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2
//        }
//        System.out.println("");
        int step = 0;

        // testing getRowValue2
//        int testResult = getRowValue2(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 4, 6, 0); // works
//        System.out.println("testresult getRowValue" + testResult); 
        
        System.out.println("real result:" + getRowValue2(phase, inputLength, 100000, offset));
    }
    
    
    

    // idea:use a fake offset, which is the next smallest offset where offset % lenght of origIntput = 0 
    //-> then pattern rules can be implemented easier?
    // afterwards correct answer by remainder
    public static int getRowValue2(int[] input, int lengthOriginalInput, int rowAfterOffset, int offset) {

        int[] basePattern = new int[]{1, 0, -1, 0};
        int basePatternLength = basePattern.length;

        // found out by pen&paper:
        // there is a repetition after leastCommonMultiple(lengthOfOriginalInput, basePatternLength*repetitionsThisRow)
        int repetitionsThisRow = (offset + 1) + rowAfterOffset;

        int cycleLength = lcm(lengthOriginalInput, basePatternLength * repetitionsThisRow);
        System.out.println("length of repeating cycle:" + cycleLength);

        // make sure 0s at beginning are handled: rowAfterOffset-positions in the pattenr are 0-> begin multiplication at rowAfterOffset
        int[] subInput = Arrays.copyOfRange(input, rowAfterOffset, rowAfterOffset + cycleLength); // last argument is exclusive
        System.out.println("subInput length has to match cycleLength" + subInput.length);
        
        // determine the max number of repeating cycles:
        int repeatingCycles = (input.length - rowAfterOffset)/ cycleLength;
        System.out.println("number of complete repeating patterns: " + repeatingCycles);
        
        int posInSubInput = 0;
        int posInPattern = 0;
        int resultOfRepeatingCycle = 0;
        
        if (repeatingCycles >= 1) {
            
        
        // handle case where there is not even 1 cycle
        while (posInSubInput < subInput.length) { // because offset is used, the special case for step == o is not necessary
            for (int k = 0; k < repetitionsThisRow; k++) {
                if (posInSubInput < (subInput.length)) {
                    resultOfRepeatingCycle = (resultOfRepeatingCycle + basePattern[posInPattern] * subInput[posInSubInput]) % 10;
                    posInSubInput++;
                }
            }
            posInPattern++;
            if (posInPattern >= basePattern.length) {
                posInPattern = 0;
            }
        }
        }
        System.out.println("sum of 1 repeating cycle: " + resultOfRepeatingCycle);
        

        
        
        
        
        
        // remaining elements:
        // (completelength - rowAfterOffset) % cycleLength;
        int[] remainingInput = Arrays.copyOfRange(input, rowAfterOffset + repeatingCycles * cycleLength, input.length);
        System.out.println("length of remeaining input " + remainingInput.length);
        System.out.println("remeaining input " + Arrays.toString(remainingInput));
        
        // dteermine result of remaining pos
        posInSubInput = 0;
        posInPattern = 0;
        int resultOfRemaining = 0;
        while (posInSubInput < remainingInput.length) { // because offset is used, the special case for step == o is not necessary
            for (int k = 0; k < repetitionsThisRow; k++) {
                if (posInSubInput < (remainingInput.length)) {
                    resultOfRemaining = (resultOfRemaining + basePattern[posInPattern] * remainingInput[posInSubInput]);
                    posInSubInput++;
                }
            }
            posInPattern++;
            if (posInPattern >= basePattern.length) {
                posInPattern = 0;
            }
        }

        return Math.abs(resultOfRepeatingCycle * repeatingCycles + resultOfRemaining) % 10;
    }

    public static int lcm(int number1, int number2) {
        if (number1 == 0 || number2 == 0) {
            return 0;
        }
        int absNumber1 = Math.abs(number1);
        int absNumber2 = Math.abs(number2);
        int absHigherNumber = Math.max(absNumber1, absNumber2);
        int absLowerNumber = Math.min(absNumber1, absNumber2);
        int lcm = absHigherNumber;
        while (lcm % absLowerNumber != 0) {
            lcm += absHigherNumber;
        }
        return lcm;
    }

    public static int getRowValue(int[] input, int step, int offset) {
        int[] basePattern = new int[]{1, 0, -1, 0};
        int pos = step;
        System.out.println("pos count before getRowValue: " + pos);
        int posInPattern = 0;
        int currentResult = 0;
        for (int i = step; i < input.length; i++) { // because offset is used, the special case for step == o is not necessary
            for (int k = 0; k <= step + offset; k++) {
                if (pos < (input.length)) {
                    currentResult = (currentResult + basePattern[posInPattern] * input[pos]) % 10;
                    //  System.out.println("basePattern[posInPattern]: " + basePattern[posInPattern]);
                    //  System.out.println("input[pos]: " + input[pos]);
                    pos++;
                }
            }
            posInPattern++;
            if (posInPattern >= basePattern.length) {
                posInPattern = 0;
            }
        }
        System.out.println("pos count after getRowValue: " + pos);
        System.out.println("result before %10: " + currentResult);
        return currentResult;
    }

    public void part2() {

        // runs out of heap space quickly,
        // even with increased heap memory by command line arguments
        // needs workaround...
        //String origInput = "59776034095811644545367793179989602140948714406234694972894485066523525742503986771912019032922788494900655855458086979764617375580802558963587025784918882219610831940992399201782385674223284411499237619800193879768668210162176394607502218602633153772062973149533650562554942574593878073238232563649673858167635378695190356159796342204759393156294658366279922734213385144895116649768185966866202413314939692174223210484933678866478944104978890019728562001417746656699281992028356004888860103805472866615243544781377748654471750560830099048747570925902575765054898899512303917159138097375338444610809891667094051108359134017128028174230720398965960712";
        String origInput = "03036732577212944063491565474664";
        String[] inputString = origInput.split("");

        int offset = Integer.parseInt(origInput.substring(0, 6));

        int part2multiplicator = 10000;  // short input, 1000 multiplicator: 3 sec
        // after update of createPattern(), getValue(): 1 sec
        // still: for 10000 with short input: > 136 sec for 1 iteration
        // after introducing offset: for 10000 with short input: 102 sec for 1 iteration
        int inputLength = inputString.length;

        System.out.println("input string length: " + inputLength);
        System.out.println("input string length after multiplicator: " + inputLength * part2multiplicator);
        System.out.println("input string length after multiplicator and offset corr: " + ((inputLength * part2multiplicator) - offset)); // offset correction only helps a little

        int[] input = new int[inputLength];

        for (int i = 0; i < inputLength; i++) {
            input[i] = Integer.parseInt(inputString[i]);
        }

        int inputPart2Length = part2multiplicator * inputLength;

        int[] inputPart2 = new int[inputPart2Length];
        for (int i = offset; i < inputPart2Length; i++) {
            inputPart2[i] = input[i % inputLength];
        }
        int[] nextInputPart2 = new int[inputPart2Length];

        //todo: the digits before the offset never matter, i.e. the whole thing can be shortened by the offset, which is considerable
        //int limit = 2;
        lowMemPhase row = new lowMemPhase(inputPart2Length, 0); // speed up by re-assigning pattern instead of recreating instance?
        // marginal, if any

        // for (int outer = 1; outer <= limit; outer++) {
        int currentPos = row.getRowValue(inputPart2, 0); // updated: everything before i is irrelevant
        nextInputPart2[0] = currentPos;
        for (int i = offset; i < inputPart2Length; i++) { //only works from i = 1 on, because of createPattern
            row.createPattern(i); // updated: everything until i is 0
            currentPos = row.getRowValue(inputPart2, i); // updated: everything before i is irrelevant
            nextInputPart2[i] = currentPos;
            //if (i < 100) System.out.println(currentPos);
        }
        inputPart2 = nextInputPart2;
        //}

//        System.out.print("result after" + limit + " phases ");
//        for (int i = offset; i < offset + 8; i++) {
//            System.out.print(inputPart2[i] + "");
//        }
//        System.out.println("");
//        
//        lowMemPhase part2_0 = new lowMemPhase(inputPart2Length, 0);
//        int pos0 = part2_0.getRowValue(inputPart2);
//        System.out.println("row0, pos0: " + pos0);
//        System.out.println("patternLength: " + inputPart2Length);
//        System.out.println("patternLength: " + inputLength);
    }

    public void part1() {
        String origInput = "59776034095811644545367793179989602140948714406234694972894485066523525742503986771912019032922788494900655855458086979764617375580802558963587025784918882219610831940992399201782385674223284411499237619800193879768668210162176394607502218602633153772062973149533650562554942574593878073238232563649673858167635378695190356159796342204759393156294658366279922734213385144895116649768185966866202413314939692174223210484933678866478944104978890019728562001417746656699281992028356004888860103805472866615243544781377748654471750560830099048747570925902575765054898899512303917159138097375338444610809891667094051108359134017128028174230720398965960712";
        String[] inputString = origInput.split("");

        int[] input = new int[inputString.length];

        for (int i = 0; i < inputString.length; i++) {
            input[i] = Integer.parseInt(inputString[i]);
        }

        phase part1 = new phase(input);

        int limit = 100;
        for (int i = 1; i <= limit; i++) {
            input = part1.execPhase(input);
        }

        System.out.print("result after" + limit + " phases ");
        for (int i = 0; i < 8; i++) {
            System.out.print(input[i] + "");
        }
        System.out.println("");

    }

    class lowMemPhase {

        int[] phase;
        //int[] input;
        int patternLength;

        public lowMemPhase(int patternLength, int step) {
            // System.out.println("initialized with size: " + length);
            //this.phase = new int[length];
            //initialize();
            this.patternLength = patternLength;
            this.phase = createPattern(step);
        }

        public int getRowValue(int[] input, int step) {  // probably improved if input (part2 input - long one) is
            // constantly generated; not stored <- does not improve speed?!
            int result = 0;
            for (int i = step; i < patternLength; i++) {
                result = result + phase[i] * input[i];
            }
            return Math.abs(result % 10);
        }

        public int[] createPattern(int step) {
            int[] basePattern = new int[]{1, 0, -1, 0};
            int[] result = new int[patternLength];
            int posToFill = step; // if it starts at step, it will always start with 1, except for 0step
            int posInPattern = 0;
            while (posToFill < result.length) {
                for (int i = 0; i <= step; i++) {
                    if (posToFill < result.length) {
                        result[posToFill] = basePattern[posInPattern];
                        posToFill++;
                    }
                }
                posInPattern++;
                if (posInPattern >= basePattern.length) {
                    posInPattern = 0;
                }
            }
//            // process to correct length, taking away first
//            int[] result = new int[patternLength];
//            result = Arrays.copyOfRange(preResult, 1, (preResult.length));
            return result;

        }

    }

    class phase {

        int[][] phase;
        int[] input;
        int length;

        public phase(int[] input) {
            length = input.length;
            // System.out.println("initialized with size: " + length);
            this.phase = new int[length][length];
            initialize();
        }

        private void initialize() {

            // create pattern
            for (int i = 0; i < phase.length; i++) {
                this.phase[i] = createPattern(i);

            }
        }

        public int[] execPhase(int[] newInput) {
            this.input = newInput;

            int[] result = new int[length];
            for (int i = 0; i < length; i++) {
                //result[i] = 1;
                result[i] = getRowValue(i);
            }
            return result;
        }

        private int getRowValue(int row) {
            int result = 0;
            for (int i = 0; i < length; i++) {
                result = result + phase[row][i] * input[i];
            }
            return Math.abs(result % 10);
        }

        public void printPhase() {
            for (int row = 0; row < this.length; row++) {
                for (int col = 0; col < this.length; col++) {
                    System.out.print(phase[row][col] + ", ");
                }
                System.out.println();
            }
        }

        public int[] createPattern(int step) {
            int[] basePattern = new int[]{0, 1, 0, -1};
            int[] preResult = new int[length + 1];
            int posToFill = 0;
            int posInPattern = 0;
            while (posToFill < preResult.length) {
                for (int i = 0; i <= step; i++) {
                    if (posToFill < preResult.length) {
                        preResult[posToFill] = basePattern[posInPattern];
                        posToFill++;
                    }
                }
                posInPattern++;
                if (posInPattern >= basePattern.length) {
                    posInPattern = 0;
                }
            }
            // process to correct length, taking away first
            int[] result = new int[length];
            result = Arrays.copyOfRange(preResult, 1, (preResult.length));
            return result;

        }
    }
}
