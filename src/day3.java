
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
import java.util.ArrayList;

public class day3 {
    public static void main(String[] args) {
        
        // probably beetter using hashmap/hashset
        
        // determine dimensions
        String line1 = readByLine.readAsString("day3.txt").get(0);
        String line2 = readByLine.readAsString("day3.txt").get(1);
        
        String[] l1directions = line1.split(",");
        String[] l2directions = line2.split(",");
        
        ArrayList<int[]> locations1 = createLocationList(l1directions);
        ArrayList<int[]> locations2 = createLocationList(l2directions);
        
        ArrayList<int[]> matches = new ArrayList<int[]>();
        
        int bestDistance = 99999;
        
        // find intersections
        for (int[] coords1 : locations1) {
            int currentDistance = getDistance(coords1);
            if (currentDistance != 0 & currentDistance < bestDistance){ // probably better to remov all {0,0} from the lists
                for (int[] coords2 : locations2) {
                    if (coords1[0] == coords2[0] & coords1[1] == coords2[1]) {
                        bestDistance = currentDistance;
                    }
                }
                
            }
        }
        System.out.println("day3 part1 sol: " + bestDistance); // correct
        
    }
    
    public static int getDistance(final int[] coords) {
        return Math.abs(coords[0] + Math.abs(coords[1]));
    }
    
        public static ArrayList<int[]> createLocationList(String[] directions) {

        ArrayList<int[]> locations = new ArrayList<int[]>();

        int x = 0;
        int y = 0;

        char dir;
        int dis;

        for (String s : directions) {
            dir = s.charAt(0);
            dis = Integer.parseInt(s.substring(1));

            if (dir == 'U') {
                for (int i = 0; i < dis; i++) {
                    locations.add(new int[]{x, y});
                    x++;
                }
            }
            if (dir == 'D') {
                for (int i = 0; i < dis; i++) {
                    locations.add(new int[]{x, y});
                    x--;
                }
            }
            if (dir == 'R') {
                for (int i = 0; i < dis; i++) {
                    locations.add(new int[]{x, y});
                    y++;
                }
            }
            if (dir == 'L') {
                for (int i = 0; i < dis; i++) {
                    locations.add(new int[]{x, y});
                    y--;
                }
            }
        }
        return locations;
    }
    
}
