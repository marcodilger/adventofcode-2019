/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author marco
 */
public class day24 {

    public static void main(String[] args) {
        day24 day24 = new day24();
        day24.part1();

    }

    public void part1() {
        List<String> input = readByLine.readAsString("day24.txt");
        HashSet diversityScores = new HashSet();
        grid bugs = new grid(input);
        bugs.printGrid();
        
        
        while (true) {
            bugs.evolve();
            int score = bugs.getBiodiversity();
            if (diversityScores.contains(score)) {
                System.out.println("already contains biodversity " + score);
                break;
            } else {
                diversityScores.add(score);
                System.out.println("added diversity score: " + score);
            }
        }
        bugs.printGrid();

    }

    class grid {

        int[][] grid;

        public grid(List<String> input) {
            grid = new int[5][5];

            // fill intial grid
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (input.get(i).charAt(j) == '#') {
                        grid[i][j] = 1;
                    } else if (input.get(i).charAt(j) == '.') {
                        grid[i][j] = 0;

                    } else {
                        System.out.println("parsing error");
                    }
                }
            }
        }

        public void printGrid() {
            System.out.println("======state of current grid of bugs: =======");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print("" + grid[i][j]);
                }
                System.out.println("");
            }
            System.out.println("==================");
        }
        
        private void evolve() {
            int[][] newGrid = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    newGrid[i][j] = evaluateCell(i, j);
                }
            }
            this.grid = newGrid;
        }
        
        private int evaluateCell(int row, int col) {
            if (isBug(row, col)) { 
                // current cell is a bug, check if it dies
                if (countAdjacentBugs(row, col) != 1) {
                    return 0;
                } else return 1;
            } else {
                // current cell has no bug
                if (countAdjacentBugs(row, col) == 1 | countAdjacentBugs(row, col) == 2) {
                    return 1;
                } else return 0;
            }
        }
        

        
        public int getBiodiversity(){
            int result = 0;
            int digit = 0;
            while (digit < 25) {
                int base = (int) Math.pow(2, digit);
                result = result + (grid[digit / 5][digit % 5] * base);
                digit++;
            }
            return result;
        }

        private int countAdjacentBugs(int row, int col) {
            
            int left = isBug(row, col-1)? 1 : 0;
            int right = isBug(row, col+1)? 1 : 0;
            int top = isBug(row-1, col)? 1 : 0;
            int bottom = isBug(row+1, col)? 1 : 0;            
            return left+right+top+bottom;
        }

        private boolean isBug(int row, int col) {
            if (row >= 0 & row < 5 & col >= 0 & col < 5) {
                if (this.grid[row][col] == 1) {
                   // System.out.println("is bug");
                    return true;
                }
                //System.out.println("is not bug");
            }
            return false;
        }

    }

}
