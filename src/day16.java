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
        day16.part1();

    }

    public void part1() {
        String testInput = "59776034095811644545367793179989602140948714406234694972894485066523525742503986771912019032922788494900655855458086979764617375580802558963587025784918882219610831940992399201782385674223284411499237619800193879768668210162176394607502218602633153772062973149533650562554942574593878073238232563649673858167635378695190356159796342204759393156294658366279922734213385144895116649768185966866202413314939692174223210484933678866478944104978890019728562001417746656699281992028356004888860103805472866615243544781377748654471750560830099048747570925902575765054898899512303917159138097375338444610809891667094051108359134017128028174230720398965960712";
        String[] inputString = testInput.split("");

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
