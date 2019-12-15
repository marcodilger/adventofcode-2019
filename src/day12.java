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
        day12 day12part1 = new day12();
        day12part1.part1();
    }
    
    public void part1(){
        ArrayList<planet> planets = new ArrayList<>();
        
        // add planets manually, quicker than specifying parser
        
//<x=-3, y=15, z=-11>
//<x=3, y=13, z=-19>
//<x=-13, y=18, z=-2>
//<x=6, y=0, z=-1>

        
        planets.add(new planet(-3,15,-11));
        planets.add(new planet(3,13,-19));
        planets.add(new planet(-13,18,-2));
        planets.add(new planet(6,0,-1));
        
        System.out.println(planets.get(0).getVels()[0]);
        System.out.println(planets.get(0).getPos()[0]);
        
        
        
    }
    
   public static void applyGravity(planet planet1, planet planet2){
       // for 1, 2, 3
       // compare Pos x/1  y/2 z/3  
       //        depending on outcome increase/decrease velicoty x, y, z
   }
    
   class planet{
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
        
        public int[] getPos(){
            return new int[]{x_pos, y_pos, z_pos};
        }
        public int[] getVels(){
            return new int[]{x_vel, y_vel, z_vel};
        }
    
    
}
    
    
}




