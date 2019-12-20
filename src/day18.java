/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import java.util.ArrayList;
import java.lang.Character;

/**
 *
 * @author marco
 */
public class day18 {

    public static void main(String[] args) {
        day18 day18 = new day18();

        List<String> mazeString = readByLine.readAsString("day18test.txt");
        day18.part1(mazeString);

    }

    public void part1(List<String> mazeString) {
        maze maze = new maze(mazeString);

        maze.printMaze();
        maze.printKeys();
    }

    class maze {

        int[][] maze;
        int mazeWidth;
        int mazeHeight;
        tile startPos;
        List<Integer[]> keys;
        List<Character> keyIdents;
        List<Integer[]> doors;
        List<Character> doorIdents;

        public maze(List<String> mazeString) {
            mazeWidth = mazeString.get(0).length();
            mazeHeight = mazeString.size();

            this.maze = new int[mazeHeight][mazeWidth];
            this.keys = new ArrayList<Integer[]>();
            this.keyIdents = new ArrayList<Character>();
            this.doors = new ArrayList<Integer[]>();
            this.doorIdents = new ArrayList<Character>();

            for (int row = 0; row < mazeHeight; row++) {
                String[] currentRowString = mazeString.get(row).split("");
                for (int col = 0; col < mazeWidth; col++) {
                    System.out.println("checking " + currentRowString[col]);
                    if (mazeString.get(row).charAt(col) == '#') { // wall
                        maze[row][col] = 0;
                    } else { // any tile that can be reached
                        maze[row][col] = 1;
                        if (mazeString.get(row).charAt(col) == '@') { // startpos found
                            startPos = new tile(row, col, 0);
                        } else if (Character.isLowerCase(mazeString.get(row).charAt(col))) { // found a key
                            System.out.println("found a key");
                            maze[row][col] = 2; // use 2 for a key
                            keys.add(new Integer[]{row, col});
                            keyIdents.add(Character.toLowerCase(mazeString.get(row).charAt(col)));

                        } else if (Character.isUpperCase(mazeString.get(row).charAt(col))) { // found a door
                            System.out.println("found a door");
                            maze[row][col] = 3; // use 3 for a door
                            doors.add(new Integer[]{row, col});
                            doorIdents.add(Character.toLowerCase(mazeString.get(row).charAt(col)));

                        }
                    }
                }
            }

        }

        public void shortestDistance(tile baseTile, int targetRow, int targetCol) { // problem with this approach is that not always the closest key/door is the best one in the long run

            // create visited matrix
            int[][] visited = new int[mazeHeight][mazeWidth];
            
            // create a list of tiles to be visited
            
            List<tile> queue = new ArrayList<tile>();
            queue.add(baseTile);
            
            // while queue is not empty do:
            
            //mark as visited
            visited[baseTile.row][baseTile.col] = 1;
            
            int min_dist = 999999; // initialize minimum distance wiht high number
            
            // get an element from queue and remove it
            tile currentTile = queue.get(0);
            queue.remove(0);
            
            // if currentTile is target
            if (currentTile.row == targetRow & currentTile.col == targetCol) {
                // set distance
                if (currentTile.distance < min_dist) {
                    min_dist = currentTile.distance;
                }
            } else {
                

            // try every 4 directions from startPos;
            
            // todo imprement visitedCheck
            
            if (directionIsPossible(baseTile.row - 1, baseTile.col)) { // try to go up
                queue.add(new tile(baseTile.row - 1, baseTile.col, baseTile.distance + 1));
            } else if (directionIsPossible(baseTile.row + 1, baseTile.col)) { //try to go down
                queue.add(new tile(baseTile.row - 1, baseTile.col, baseTile.distance + 1));
            } else if (directionIsPossible(baseTile.row, baseTile.col + 1)) { //try to go right
                queue.add(new tile(baseTile.row, baseTile.col + 1, baseTile.distance + 1));
            } else if (directionIsPossible(baseTile.row, baseTile.col - 1)) { //try to go left
                queue.add(new tile(baseTile.row, baseTile.col - 1, baseTile.distance + 1));
            }
            }
                
        }

        // check if gooing to pos is possible
        private boolean directionIsPossible(int row, int col) {
            if (row <= mazeHeight & col <= mazeWidth) {
            switch (maze[row][col]) {
                case 1:
                    return true;
                case 2:
                    // key; for now a key is not handled differently
                    return true;
                case 3:
                    // door; for now, a door is not handled differently
                    return true;
                default:
                    return false;
            }
            } else {
                return false;
            }
        }

        public void printMaze() {
            for (int row = 0; row < mazeHeight; row++) {
                for (int col = 0; col < mazeWidth; col++) {
                    System.out.print("" + maze[row][col]);
                }
                System.out.println("");
            }

        }

        public void printKeys() {
            for (int i = 0; i < keys.size(); i++) {
                System.out.println("" + keyIdents.get(i) + " at pos " + keys.get(i)[0] + "," + keys.get(i)[1]);
            }
        }
    }

    class tile {

        public int row;
        public int col;
        public int distance;

        public tile(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
}
