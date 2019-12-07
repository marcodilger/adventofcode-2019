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
 
}
