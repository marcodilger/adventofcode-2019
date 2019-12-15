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
        input = readByLine.readAsString("day14test.txt");
    }
    
 

    public void part1() {
        

        List<reaction> knownReactions = new ArrayList<>();
        String master = "";
        
        // read in reactions from input
        
        for (String line : input) {
            System.out.println(line);
            if (line.endsWith("FUEL")) {
                master = line;
            } else {
                knownReactions.add(new reaction(line));
            }
        }
        
        // find the reaction that generates FUEL
        
        reaction masterReaction = new reaction(master);
        /*        
        
9 ORE => 2 A
8 ORE => 3 B
7 ORE => 5 C
3 A, 4 B => 1 AB
5 B, 7 C => 1 BC
4 C, 1 A => 1 CA
2 AB, 3 BC, 4 CA => 1 FUEL
        
  
*/
        
        // sequentially buidl parser, should then implemented in constructor of reaction
//        String reactionInput = "5 B, 7 C => 1 BC";
//        String eductString = reactionInput.split(" => ")[0];
//
//        
//        String[] educts = eductString.split(", ");
//        for (String s : educts) {
//            int eductQuant = Integer.parseInt(s.split(" ")[0]);
//            String educt = s.split(" ")[1];
//            System.out.println("educt parsed: " + eductQuant + " " + educt);
//        }
//        
//        String productString = reactionInput.split(" => ")[1];
//        int productQuant = Integer.parseInt(productString.split(" ")[0]);
//        String product = productString.split(" ")[1];
//        System.out.println("product parsed: " + productQuant + " " + product);
        

//        reaction r1 = new reaction("9 ORE => 2 A");
//        knownReactions.add(r1);
//        
// //       reaction r2 = new reaction();
//        knownReactions.add(new reaction("8 ORE => 3 B"));
////        r2.addEduct("ORE", 8);
////        r2.addProduct("B", 3);
//        
//        reaction r3 = new reaction();
//        knownReactions.add(r3);
//        r3.addEduct("ORE", 7);
//        r3.addProduct("C", 5);
//        
//        reaction r4 = new reaction();
//        knownReactions.add(r4);
//        r4.addEduct("A", 3);
//        r4.addEduct("B", 4);
//        r4.addProduct("AB", 1);
//        
//        reaction r5 = new reaction();
//        knownReactions.add(r5);
//        r5.addEduct("C", 7);
//        r5.addEduct("B", 5);
//        r5.addProduct("BC", 1);
//        
//        reaction r6 = new reaction("4 C, 1 A => 1 CA");
//        knownReactions.add(r6);

        
    
        
        
//        reaction masterReaction = new reaction();
//
//        masterReaction.addEduct("AB", 2);
//        masterReaction.addEduct("BC", 3);
//        masterReaction.addEduct("CA", 4);
//        masterReaction.addProduct("FUEL", 1);

        masterReaction.printReaction();

//        reaction supportReaction = new reaction();
//        knownReactions.add(supportReaction);
//        supportReaction.addEduct("A", 7);
//        supportReaction.addEduct("A", 7);
//        supportReaction.addEduct("C", 1);
//        supportReaction.addProduct("CA", 2);
//        supportReaction.printReaction();
//
//        reaction supportReaction2 = new reaction();
//        knownReactions.add(supportReaction2);
//        supportReaction2.addEduct("ORE", 113);
//        supportReaction2.addProduct("C", 1);
//
//        reaction supportReaction3 = new reaction();
//        knownReactions.add(supportReaction3);
//        supportReaction3.addEduct("ORE", 23);
//        supportReaction3.addProduct("A", 2);
//        


        System.out.println("known reactions:");
        for (reaction r : knownReactions) {
            r.printReaction();
        }
        System.out.println("end of known reactions");

        masterReaction.printReaction();

        // while resolvableEductsFound = true do:
        boolean resolvableEductsFound = true;
        while (resolvableEductsFound) {
            // detect which educts are not OREs
            resolvableEductsFound = false;
            for (int i = 0; i < masterReaction.eductNames.size(); i++) {
                String currentEduct = masterReaction.eductNames.get(i);

                if (!currentEduct.equals("ORE")) { // educt at pos i is not an element
                    resolvableEductsFound = true;
                    System.out.println("currently checked educt : " + currentEduct + " is not an elementary educt (ORE)");
                    // check if educt is a product in any of the known reactions
                    for (reaction r : knownReactions) {
                        System.out.print("checking: ");
                        r.printReaction();
                        if (r.productNames.get(0).equals(currentEduct)) {
                            // replace educt
                            masterReaction.replaceEducts(r);
                            masterReaction.printReaction();
                            break;

                        }
                    }
                    break;
                }
            }
        } // end of while (resolvableEductsFound)
        
        System.out.println("result: ");
        masterReaction.printReaction();

        //masterReaction.replaceEducts(supportReaction2);
        //masterReaction.printReaction();
        
 
    }
    
       

    public class reaction {

        ArrayList<String> eductNames;
        ArrayList<Integer> eductQuantities;
        ArrayList<String> productNames;
        ArrayList<Integer> productQuantities;

        public reaction() {
            this.eductNames = new ArrayList<String>();
            this.eductQuantities = new ArrayList<Integer>();
            this.productNames = new ArrayList<String>();
            this.productQuantities = new ArrayList<Integer>();

        }
        
        public reaction(String reactionInput) {
        this(); // reuse constructor without argument
        String eductString = reactionInput.split(" => ")[0];
       
        String[] educts = eductString.split(", ");
        for (String s : educts) {
            int eductQuant = Integer.parseInt(s.split(" ")[0]);
            String educt = s.split(" ")[1];
            System.out.println("educt parsed: " + eductQuant + " " + educt);
            this.addEduct(educt, eductQuant);
        }
        
        String productString = reactionInput.split(" => ")[1];
        int productQuant = Integer.parseInt(productString.split(" ")[0]);
        String product = productString.split(" ")[1];
        System.out.println("product parsed: " + productQuant + " " + product);
        this.addProduct(product, productQuant);
        }
        
        
        
        
        
        
        
        

        public void replaceEducts(reaction source) {
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
                // remove educt
                eductNames.remove(eductPos);
                eductQuantities.remove(eductPos);

                // find out how often the source reaction has to be applied to satisfy eductQuantities
                int sourceMultiplier = eductQuantity;
                while ((sourceMultiplier - 1) * source.productQuantities.get(0) >= eductQuantity) {
                    sourceMultiplier--;
                }

                // add educts from source reaction
                for (int i = 0; i < source.eductNames.size(); i++) {
                    addEduct(source.eductNames.get(i), (source.eductQuantities.get(i) * sourceMultiplier));
                }
            } // end of handling product/educt pair
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

        public void printEducts() {
            for (int i = 0; i < eductNames.size(); i++) {
                System.out.print("" + eductQuantities.get(i) + " " + eductNames.get(i) + ", ");
            }
        }

        public void printProducts() {
            for (int i = 0; i < productNames.size(); i++) {
                System.out.print("" + productQuantities.get(i) + " " + productNames.get(i) + ", ");
            }
        }

        public void printReaction() {
            printEducts();
            System.out.print(" => ");
            printProducts();
            System.out.println();

        }

    }
   
}


