/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */

import java.util.List;
import java.util.ArrayList;

public class day6 {
    public static void main(String[] args) {
        // read by line, separate by star,
        // tree not necessary, might bee for part2

        List<String> orbits = readByLine.readAsString("day6.txt");

        int level = 1;
        int orbitCount = 0;
        List<String> nextCenters = new ArrayList<String>();
        nextCenters.add("COM"); // start with center of mass
        List<String> children = returnChildren(orbits, "COM");

        while (orbits.size() > 0) {

            orbitCount = orbitCount + (level * children.size());

            for (String centers : children) {
                nextCenters.add(centers.split("\\)")[1]);
                if (centers.split("\\)")[1].equals("YOU")) {   // to help part2
                    System.out.println("YOU found orbiting level" + level);
                }
                if (centers.split("\\)")[1].equals("SAN")) {   // to help part2
                    System.out.println("SAN found orbiting level" + level);
                }
            }

            // remove all childrens from orbits
            orbits.removeAll(children);

            // search for new children
            children.clear();
            for (String center : nextCenters) {
                children.addAll(returnChildren(orbits, center));
            }

            nextCenters.clear();
            level++;
        }
        System.out.println("result part 1: " + orbitCount);
        
        
        // part 2
        
        orbits = readByLine.readAsString("day6.txt");
         // SAN is 4 levels lower than YOU: 334
            // YOU is at level 330
            
            level = 334;
            String SANcenter = "SAN";
            String YOUcenter = "YOU";
            
            while (level > 330) { // manual adjustment, after this the cneters can be directly compared
                SANcenter = getCenter(orbits, SANcenter);
                level--;
            }
            
            while (!SANcenter.equals(YOUcenter) & level > 0) {
                SANcenter = getCenter(orbits, SANcenter);
                YOUcenter = getCenter(orbits, YOUcenter);
                level--;
                
            }
            System.out.println("day6 part 2: " + (334 - level + 330 - level - 2));
    }
    
      
    
        private static List<String> returnChildren(List<String> orbits, String center) {
            
            List<String> children = new ArrayList<String>();
            
            for (String o : orbits) {
                if (o.startsWith(center)) {
                    children.add(o);
            }
        }
            return children;
        }
        
        private static String getCenter(List<String> orbits, String outer){
            for (String o : orbits) {
                if (o.split("\\)")[1].equals(outer)) {
                    return o.split("\\)")[0];
            }
        }
            return "not found"; // should not happen
        }
 
}
