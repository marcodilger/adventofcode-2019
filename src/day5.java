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
        
        intcode aircon = new intcode(ops, 1);
        int solution_p1 = aircon.runCode(9999); // passed parameter never gets used
        System.out.println("day 5 part1: " + solution_p1);

        intcode aircon_part2 = new intcode(ops, 5);
        int solution_p2 = aircon_part2.runCode(9999); // passed parameter never gets used
        System.out.println("day 5 part2: " + solution_p2);

    }
    
}
