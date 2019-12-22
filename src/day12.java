/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class day12 {

    public static void main(String[] args) {
        day12 day12 = new day12();
        day12.part1();
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

    class planet {

        int x_vel;
        int y_vel;
        int z_vel;
        int x_pos;
        int y_pos;
        int z_pos;

        public planet(int x, int y, int z) {
            x_pos = x;
            y_pos = y;
            z_pos = z;

            x_vel = 0;
            y_vel = 0;
            z_vel = 0;
        }

        public void printStatus() {
            System.out.print("x pos:" + x_pos + " , y_pos:" + y_pos + " , z_pos:" + z_pos);
            System.out.println("x vel:" + x_vel + " , y_vel:" + y_vel + " , z_vel:" + z_vel);
        }

        public int[] getPos() {
            return new int[]{x_pos, y_pos, z_pos};
        }

        public int[] getVels() {
            return new int[]{x_vel, y_vel, z_vel};
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
        
        public int getEnergy(){
            return ((Math.abs(this.x_pos) + Math.abs(this.y_pos) + Math.abs(this.z_pos)) *
                    (Math.abs(this.x_vel) + Math.abs(this.y_vel) + Math.abs(this.z_vel)));
        }

    }

}
