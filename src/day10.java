/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author marco
 */
public class day10 {

    public static void main(String[] args) {
        List<String> input = readByLine.readAsString("day10.txt");

        int width = input.get(0).length(); // input probably is a square
        int height = input.size();

        
        // build grid
        boolean[][] origMap = new boolean[width][height];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                origMap[row][col] = input.get(row).charAt(col) == '#';
            }
        }
        printGrid(origMap);
        int maxCount = 0;
        int maxRow = 99;
        int maxCol = 99;

//    int[][] map_debug = new int[height][width];
//    for (int row = 0; row < height; row++) {
//        for (int col = 0; col < width; col++) {
//            map_debug[row][col] = 0;
//        }
//    }
//    printGrid(map_debug);
        // for each star
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (origMap[row][col]) {

                    //int row = 4;  // for debugging
                    int currentRow = row;
                    //int col = 3; // for debugging
                    int currentCol = col;

                    //System.out.println("going into loop with: " + row + "," + col);  // debugging
                    boolean[][] map = new boolean[width][height];
                    for (int rw = 0; rw < height; rw++) {
                        for (int cl = 0; cl < width; cl++) {
                            map[rw][cl] = input.get(rw).charAt(cl) == '#';
                        }
                    }

                    // determine max radius to check:
                    int max_radius = Math.max(width - currentCol - 1,
                            Math.max(currentCol,
                                    Math.max(height - currentRow - 1,
                                            currentRow))
                    );

                    int radius = 1;
                    int counts = 0;
                    while (radius <= max_radius) {
                        // which rows have to be checked:
                        int top_row = currentRow - radius;
                        int bottom_row = currentRow + radius;
                        // which cols have to be checked:
                        int left_col = currentCol - radius;
                        int right_col = currentCol + radius;

                        // go through all cols and rows
                        for (int c = left_col; c <= right_col; c++) {
                            if (c >= 0 & c < width & top_row >= 0 & top_row < height) {
                                //System.out.println("1 checking col: " + c + ", row: " + top_row);
                                if (map[top_row][c]) {
                                    //   System.out.println("Star found at" + "col: "+ c + " downrow: " + top_row);
                                    counts++;
                                    map[top_row][c] = false;
                                    deleteMultiples(map, top_row, c, currentRow, currentCol, radius, max_radius);
                                    //  map[top_row][c] = false; // avoid double counting, even possible?
                                }
                            }
                            if (c >= 0 & c < width & bottom_row >= 0 & bottom_row < height) {
                                //System.out.println("2 checking col: " + c + ", row: " + bottom_row);
                                if (map[bottom_row][c]) {
                                    //    System.out.println("Star found at" + "col: "+ c + " downrow: " + bottom_row);
                                    counts++;
                                    map[bottom_row][c] = false;
                                    deleteMultiples(map, bottom_row, c, currentRow, currentCol, radius, max_radius);
                                    //  map[bottom_row][c] = false;
                                }
                            }
                        }
                        for (int r = top_row + 1; r < bottom_row; r++) {
                            if (r >= 0 & r < height & left_col >= 0 & left_col < width) {
                                //System.out.println("3 checking col: " + left_col+ ", row: " + row);
                                if (map[r][left_col]) {
                                    counts++;
                                    map[r][left_col] = false;
                                    //    System.out.println("Star found at" + "col: "+ left_col + " row: " + rw);
                                    deleteMultiples(map, r, left_col, currentRow, currentCol, radius, max_radius);
                                }
                            }

                            if (r >= 0 & r < height & right_col >= 0 & right_col < width) {
                                //System.out.println("4 checking col: " + right_col+ ", row: " + rw);
                                if (map[r][right_col]) {
                                    //    System.out.println("Star found at" + "col: "+ right_col + " row: " + rw);
                                    counts++;
                                    map[r][right_col] = false;
                                    deleteMultiples(map, r, right_col, currentRow, currentCol, radius, max_radius);
                                }
                            }
                        }
                        // System.out.println("counts total: " + counts);
                        radius++;
                        //printGrid(map);
                    }
                    //System.out.println("found stars at" + currentRow + ", " + currentCol + ": " + counts);

                    if (counts > maxCount) {
                        maxCount = counts;
                        maxCol = currentCol;
                        maxRow = currentRow;
                    }
                    // end of loop that needs to run for eahc star    

                    // modify grid for easier debugging
                    //   map_debug[currentRow][currentCol] = counts;
                }
            }
        }
    System.out.println("solution: " + maxRow + "," + maxCol + "\nwith " + maxCount);
//        printGrid(map_debug);
    
// part2

    System.out.println("=========");
    System.out.println("part2, different approach (using angles (variables,comments still call it slope, but its angles ;)))");

// make list of all remaining stars, with list of slopes
// remove first 9 slopes, for identical radii use only nearest

    
    // create list of asteroids
    // create list of radians/degrees
    
    int centralRow = maxRow;  // from solution part1
    int centralCol = maxCol;  // from solution part1
    int targetDestroyed = 200;   // central asteroid is counted as well, this is corrected in the loop
    int solutionRow;
    int solutionCol;
    
    List<int[]> asteroidPos = new ArrayList<int[]>();
    List<Integer> asteroidDist = new ArrayList<>();
    List<Double> asteroidSlope = new ArrayList<>();
    
    
        for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
           if (input.get(row).charAt(col) == '#' & !(row == centralRow & col == centralCol)) {
               asteroidPos.add(new int[]{row, col});
               //System.out.println("new pos added: " + row + "," + col);
               
               // problem here: division by zero, also: 2 times negative slopes get handled incorrect
            
               int colDiff = col-centralCol;
               int rowDiff = row-centralRow;
               
               double angle = Math.toDegrees(Math.atan2((double) colDiff, (double) -rowDiff));
               if (angle < 0) angle = angle + 360;
               //System.out.println("new angle added: " + angle);
               asteroidSlope.add(angle);
               
               // distance, manhattan dist is sufficient
               asteroidDist.add(Math.abs(colDiff) + Math.abs(rowDiff));
           }
            }
        }
        

        
        double lastSlope = Collections.max(asteroidSlope); // set to max, so that loop starts with the smallers
        int destroyedCount = 0;
        int solution = 0;
        
        while (asteroidPos.size() > 0 & destroyedCount < targetDestroyed) {

            double minSlope = 999;
            // get minimum angle
            if (lastSlope >= Collections.max(asteroidSlope)) { // to determine the next minimum Slope once 1 round arrives at the currently biggest
                minSlope = Collections.min(asteroidSlope);
            } else {
                for (double slope : asteroidSlope) {
                    if (slope > (lastSlope + 0.001) & slope < minSlope) { // to determine the next slope which is bigger than the last used one
                        minSlope = slope;
                    }
                }
            }


            // get pos where this minSlope applies
            int closestDist = 99;
            int closestPos = 99999;
            List<Integer> minSlopes = new ArrayList<>();
            for (int pos = 0; pos < asteroidSlope.size(); pos++) {
                if (asteroidSlope.get(pos) == minSlope) {
                    // determine closest asteroid among those with this slope
                    if (asteroidDist.get(pos) < closestDist) {
                        closestDist = asteroidDist.get(pos);
                        closestPos = pos;
                    }
                }
            }
           
            // destroy this asteroid before next slope/asteroid is searched
            // set double lastSlope, so that the next slope is definitely greater
            lastSlope = asteroidSlope.get(closestPos);

            //System.out.println("closestPos =" + closestPos);
            solution = asteroidPos.get(closestPos)[1] * 100 + asteroidPos.get(closestPos)[0];
            
            asteroidPos.remove(closestPos);
            asteroidSlope.remove(closestPos);
            asteroidDist.remove(closestPos);
            destroyedCount++;
        }
        System.out.println("Destroyed (without centre): " + (destroyedCount));
        System.out.println("Solution day 10 part 2:" + solution);

    }

    public static void printGrid(boolean[][] grid) {
        int size = grid[0].length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (grid[row][col] == true) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
    }
    public static void printGrid(int[][] grid) {
        int size = grid[0].length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                
                    System.out.print(""+ grid[row][col] + ",");
                 
            }
            System.out.println();

        }
    }
    
    public static void deleteMultiples(boolean[][] grid, int targetRow, int targetCol, int baseRow, int baseCol, int origRadius, int max_radius) {
        
        
        // would be much esier with angles and matching of rounded angles...
        // but... 
        
        int size = grid[0].length;
        //int level = origRadius;
        int level = 1;
        int colDiff = targetCol - baseCol;
        int rowDiff = targetRow - baseRow;
        
   
 

        int divisor = Math.abs(findGCD(colDiff, rowDiff));
        
        //System.out.println("found divisor: " + divisor);
        
        colDiff = colDiff / divisor;
        rowDiff = rowDiff / divisor;
        
        // irgendwas ist hier immernoch faul
        
        //vorzeichen? gibt keine negativen diffs? muss es aber geben
        
        //System.out.println("corrected rowDiff: " + rowDiff);
        //System.out.println("corrected colDiff: " + colDiff);

        int checkCol;
        int checkRow;
        while (level <= max_radius) {
            checkCol = baseCol + (level*colDiff);
            checkRow = baseRow + (level*rowDiff);
            if (checkCol >= 0 & checkCol < size & checkRow >= 0 & checkRow < size) {
                if (grid[checkRow][checkCol]) {
                    // covered star found!!
                    //System.out.println("========\ncovered Star found!\n at col: " +
                    //        checkCol + " row: " + checkRow);
                    grid[checkRow][checkCol] = false; // delete this star
                }
            }
            level++;
        }
        
    }
            /*
     * Java method to find GCD of two number using Euclid's method
     * @return GDC of two numbers in Java
     */
    public static int findGCD(int number1, int number2) {
        //base case
        if(number2 == 0){
            return number1;
        }
        return findGCD(number2, number1%number2);
    }
    
}
    

