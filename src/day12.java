/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author marco
 */
public class day12 {

    public static void main(String[] args) {
        day12 day12 = new day12();
        //     day12.part1();
        day12.part2();
    }

    public void part1() {
        ArrayList<planet> planets = new ArrayList<>();
        int steps = 1;
        int limit = 1000;

        // add planets manually, quicker than specifying parser
//<x=-3, y=15, z=-11>
//<x=3, y=13, z=-19>
//<x=-13, y=18, z=-2>
//<x=6, y=0, z=-1>
        planets.add(new planet(-3, 15, -11));
        planets.add(new planet(3, 13, -19));
        planets.add(new planet(-13, 18, -2));
        planets.add(new planet(6, 0, -1));

// testinput
//        planets.add(new planet(-1, 0, 2));
//        planets.add(new planet(2, -10, -7));
//        planets.add(new planet(4, -8, 8));
//        planets.add(new planet(3, 5, -1));
        while (steps <= limit) {

//            System.out.println("before applying gravity");
//
//            for (planet p : planets) {
//                p.printStatus();
//            }
            // applyGravity for each combination
            // generation of combinations to be improved..
            for (int i = 0; i < planets.size(); i++) {
                for (int k = i + 1; k < planets.size(); k++) {
                    //System.out.println("" + i + "," + k);
                    applyGravity(planets.get(i), planets.get(k));

                }
            }

//            System.out.println("before applying velocity");
//
//            for (planet p : planets) {
//                p.printStatus();
//            }
            for (planet p : planets) {
                p.applyVelocity();
            }

//            System.out.println("after applying velocity, steps " + steps);
//            for (planet p : planets) {
//                p.printStatus();
//            }
            steps++;
        }
        int totalEnergy = 0;
        for (planet p : planets) {
            totalEnergy = totalEnergy + p.getEnergy();
        }
        System.out.println("resutl day12 part1: " + totalEnergy);

    }

    public void part2() {
        ArrayList<planet> planets = new ArrayList<>();
        HashSet stateX = new HashSet();
        HashSet stateY = new HashSet();
        HashSet stateZ = new HashSet();
        long steps = 1;
        long limit = 19999999999999L; // to update
        boolean found = false;

        planets.add(new planet(-3, 15, -11));
        planets.add(new planet(3, 13, -19));
        planets.add(new planet(-13, 18, -2));
        planets.add(new planet(6, 0, -1));
//    
// testinput
//        planets.add(new planet(-1, 0, 2));
//        planets.add(new planet(2, -10, -7));
//        planets.add(new planet(4, -8, 8));
//        planets.add(new planet(3, 5, -1));

        while (!found & steps <= limit) {

            // applyGravity for each combination
            // generation of combinations to be improved..
            for (int i = 0; i < planets.size(); i++) {
                for (int k = i + 1; k < planets.size(); k++) {
                    //System.out.println("" + i + "," + k);
                    applyGravity(planets.get(i), planets.get(k));

                }
            }

            for (planet p : planets) {
                p.applyVelocity();
            }

            //System.out.println("steps: " + steps);
            

            planetaryState status = new planetaryState(planets.get(0), planets.get(1), planets.get(2), planets.get(3));
            
            //System.out.println("" + status.hashCode());

            // add hashed planetary states
//            if (stateHash.contains(status.hashCode())) {
//                
//                found = true;
//                System.out.println("identical state found after: " + (steps -1));  // this does not work
//            } else {
//                stateHash.add(status.hashCode());
//            }
            
            steps++;

        }

    }

    public static void applyGravity(planet planet1, planet planet2) {
        // for 1, 2, 3
        for (int i = 0; i < 3; i++) {
            if (planet1.getPos()[i] < planet2.getPos()[i]) {
                // planet1 pos +1
                planet1.changeVels(i, +1);
                // planet2 pos -1
                planet2.changeVels(i, -1);
            } else if (planet1.getPos()[i] > planet2.getPos()[i]) {
                // planet1 pos -1
                planet1.changeVels(i, -1);
                // planet2 pos +1
                planet2.changeVels(i, +1);
            }
        }
    }

    class planetaryState {

        int[][] planetState;

        public planetaryState(planet p0, planet p1, planet p2, planet p3) {

            planetState = new int[4][6];
            
            planetState[0] = p0.getState();
            planetState[1] = p1.getState();
            planetState[2] = p2.getState();
            planetState[3] = p3.getState();

        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Arrays.deepHashCode(this.planetState);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final planetaryState other = (planetaryState) obj;
            if (!Arrays.deepEquals(this.planetState, other.planetState)) {
                return false;
            }
            return true;
        }

    }

    class planet {

        @Override
        public String toString() {
            return "planet{" + "x_vel=" + x_vel + ", y_vel=" + y_vel + ", z_vel=" + z_vel + ", x_pos=" + x_pos + ", y_pos=" + y_pos + ", z_pos=" + z_pos + '}';
        }

        int x_vel;
        int y_vel;
        int z_vel;
        int x_pos;
        int y_pos;
        int z_pos;
        
        final int x_init;
        final int y_init;
        final int z_init;

        public planet(int x, int y, int z) {
            x_pos = x;
            y_pos = y;
            z_pos = z;
            
            x_init = x;
            y_init = y;
            z_init = z;

            x_vel = 0;
            y_vel = 0;
            z_vel = 0;
        }

        public void printStatus() {
            System.out.print("x pos:" + x_pos + " , y_pos:" + y_pos + " , z_pos:" + z_pos);
            System.out.println("x vel:" + x_vel + " , y_vel:" + y_vel + " , z_vel:" + z_vel);
        }
        
        public boolean isInitial(int axis) {
            switch (axis) {
                case 0: 
                    return (this.x_pos == this.x_init & this.x_vel == 0);
                case 1: 
                    return (this.y_pos == this.y_init & this.y_vel == 0);
                case 2: 
                    return (this.z_pos == this.z_init & this.z_vel == 0);
                default:
                    System.out.println("invalid axis argument");
                    break;
            }
            return false;
        }

        public int[] getPos() {
            return new int[]{x_pos, y_pos, z_pos};
        }

        public int[] getVels() {
            return new int[]{x_vel, y_vel, z_vel};
        }

        public int[] getState() {
            return new int[]{x_pos, y_pos, z_pos, x_vel, y_vel, z_vel};
        }

        public void changeVels(int pos, int change) {
            if (pos == 0) {
                x_vel = x_vel + change;
            }
            if (pos == 1) {
                y_vel = y_vel + change;
            }
            if (pos == 2) {
                z_vel = z_vel + change;
            }
        }

        public void applyVelocity() {
            this.x_pos = this.x_pos + this.x_vel;
            this.y_pos = this.y_pos + this.y_vel;
            this.z_pos = this.z_pos + this.z_vel;
        }

        public int getEnergy() {
            return ((Math.abs(this.x_pos) + Math.abs(this.y_pos) + Math.abs(this.z_pos))
                    * (Math.abs(this.x_vel) + Math.abs(this.y_vel) + Math.abs(this.z_vel)));
        }

    }

}
