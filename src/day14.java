/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author marco
 */


public class day14 {
    public static void main(String[] args) {
        day14 day14 = new day14();
        day14.part1();
    }
    
    List<String> input;
    
    public day14() { // constructor
        //input = readByLine.readAsString("day14test.txt");
    }
    
    public void part1() {
        
        reaction masterReaction = new reaction();
        
        masterReaction.addEduct("A", 9);
        masterReaction.addEduct("B", 3);
        masterReaction.addProduct("FUEL", 1);
        
        masterReaction.printReaction();
        
        
        reaction supportReaction = new reaction();
        supportReaction.addEduct("A", 7);
        supportReaction.addEduct("A", 7);
        supportReaction.addProduct("B", 1);
        
        supportReaction.printReaction();
        
        
        // detect which educts are not OREs
        
            // check if educt is a product in any of the known reactions
            
            // replace educt

    }
    
    public class reaction {

        ArrayList<String> eductNames;
        ArrayList<Integer> eductQuantities;
        ArrayList<String> productNames;
        ArrayList<Integer> productQuantities;
        
        public reaction(){
            this.eductNames = new ArrayList<String>();
            this.eductQuantities = new ArrayList<Integer>();
            this.productNames = new ArrayList<String>();
            this.productQuantities = new ArrayList<Integer>();
            
        }
        
        public void replaceEducts(reaction source){
            // double check so that product of source reaction really is an educt of this reaction
            
            int eductPos = 999;
            int eductQuantity = 0;
            for (int i = 0; i < eductNames.size(); i++) {
                if (eductNames.get(i).equals(source.productNames.get(0))) {
                    eductPos = i;
                    eductQuantity = eductQuantities.get(i);
                    break;
                }
            }
            if (eductPos == 999) {
                System.out.println("Product of source reaction is not an educt of active reaction");
            } else { // product - educt pair was found
                
            }
            
            // remove educt
            
            // add educts from 
                // multiply by eductQuantity
            
            
        }

        public void addEduct(String name, int quant) {
            
            // check if Educt is already present, then just add quantity
            
            for (int i = 0; i < eductNames.size(); i++) {
                if (eductNames.get(i).equals(name)) {
                    eductQuantities.set(i, quant + eductQuantities.get(i));
                    return;
                }
            }
            
            eductNames.add(name);
            eductQuantities.add(quant);
        }

        public void addProduct(String name, int quant) {
            productNames.add(name);
            productQuantities.add(quant);
        }
        
        public void printEducts(){
            for (int i = 0; i < eductNames.size(); i++) {
                System.out.print("" + eductQuantities.get(i) + " " + eductNames.get(i) + ", ");
            }
        }
            
        public void printProducts(){
            for (int i = 0; i < productNames.size(); i++) {
                System.out.print("" + productQuantities.get(i) + " " + productNames.get(i) + ", ");
            }
        }
        
        public void printReaction(){
            printEducts();
            System.out.print(" => ");
            printProducts();
            System.out.println();
            
        }
        
    }
}
