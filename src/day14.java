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
        //day14.part1();
        day14.part2();
        
        // stopped at 1/10 the max ore: 133006
        // 1330067 was too high
    }

    List<String> input;

    public day14() { // constructor
        input = readByLine.readAsString("day14.txt");
    }
    
 

    public void part1() {
        

        List<reaction> knownReactions = new ArrayList<>();
        String master = "";
        
        // read in reactions from input
        
        //System.out.println("reading lines");
        for (String line : input) {
            System.out.println(line);
            if (line.endsWith("FUEL")) {
                master = line;
            } else {
                knownReactions.add(new reaction(line));
            }
        }
        //System.out.println("end of reading lines");
        
        // create the reaction that generates FUEL
        
        reaction masterReaction = new reaction(master);
        
        System.out.println("known reactions:");
        for (reaction r : knownReactions) {
            r.printReaction();
        }
        System.out.println("end of known reactions");

        System.out.print("master reaction: ");
        masterReaction.printReaction();

        // while resolvableEductsFound = true do:
        boolean resolvableEductsFound = true;
        while (resolvableEductsFound) {
            
            // todo: prioritize reaction to merge which have common educts
            
            // detect which educts are not OREs
            resolvableEductsFound = false;
            for (int i = 0; i < masterReaction.eductNames.size(); i++) {
                String currentEduct = masterReaction.eductNames.get(i);
                int currentEductQuant = masterReaction.eductQuantities.get(i);

                if (!currentEduct.equals("ORE") & currentEductQuant > 0) { // educt at pos i is not an element
                    resolvableEductsFound = true;
                    //System.out.println("currently checked educt : " + currentEduct + " is not an elementary educt (ORE)");
                    // check if educt is a product in any of the known reactions
                    for (reaction r : knownReactions) {
                        //System.out.print("checking: ");
                        //r.printReaction();
                        if (r.productNames.get(0).equals(currentEduct)) {
                            // replace educt
                            masterReaction.replaceEducts(r);
                            //masterReaction.printReaction();
                            break;

                        }
                    }
                    break;
                }
            }
        } // end of while (resolvableEductsFound)
        
        System.out.println("result: ");
        masterReaction.printReaction();
 
    }
    
    public void part2() {
        

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
        System.out.println("end of reading in lines");
        
        reaction lastReaction = new reaction(); // to store the leftovers of the last reaction
        
        long oreLimit =   1000000000000L;
        long oreCount = 0;
        long fuelCount = 0;
        
        while (oreCount <= oreLimit) {
        
        // create the reaction that generates FUEL
        reaction masterReaction = new reaction(master);
        // correct this by the educts in lsatReaction
        for (int i = 0; i < lastReaction.eductNames.size(); i++) {
            masterReaction.addEduct(lastReaction.eductNames.get(i), lastReaction.eductQuantities.get(i));
        }
        

        boolean resolvableEductsFound = true;
        while (resolvableEductsFound) {
            
            // todo: prioritize reaction to merge which have common educts
            
            // detect which educts are not OREs
            resolvableEductsFound = false;
            for (int i = 0; i < masterReaction.eductNames.size(); i++) {
                String currentEduct = masterReaction.eductNames.get(i);
                int currentEductQuant = masterReaction.eductQuantities.get(i);

                if (!currentEduct.equals("ORE") & currentEductQuant > 0) { // educt at pos i is not an element
                    resolvableEductsFound = true;
                    //System.out.println("currently checked educt : " + currentEduct + " is not an elementary educt (ORE)");
                    // check if educt is a product in any of the known reactions
                    for (reaction r : knownReactions) {
                        //System.out.print("checking: ");
                        //r.printReaction();
                        if (r.productNames.get(0).equals(currentEduct)) {
                            // replace educt
                            masterReaction.replaceEducts(r);
                            //masterReaction.printReaction();
                            break;

                        }
                    }
                    break;
                }
            }
        } // end of while (resolvableEductsFound)
        
        // handle masterReaction, i.e.
            // delete ORE , add it to oreCount
            // put rest to lastMasterReaction
        //oreCount = oreCount + masterReaction.getEductQuant("ORE");
        int currentOre = 0;
        for (int i = 0; i < masterReaction.eductNames.size(); i++) {
            if (masterReaction.eductNames.get(i).equals("ORE")) {
                currentOre = masterReaction.eductQuantities.get(i);
            }
        }
        //oreCount = oreCount + masterReaction.getEductQuant("ORE");
        oreCount = oreCount + currentOre;
        masterReaction.deleteEduct("ORE");
           
        lastReaction = new reaction();
        for (int i = 0; i < masterReaction.eductNames.size(); i++) {
            lastReaction.addEduct(masterReaction.eductNames.get(i), masterReaction.eductQuantities.get(i));
        }
        
        // increase fuel count
        fuelCount++;        
        //System.out.println("ore count after this round " + oreCount);
        }
        System.out.println("solution day14 part 2: " + (fuelCount-1));
            
        
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
            //System.out.println("educt parsed: " + eductQuant + " " + educt);
            this.addEduct(educt, eductQuant);
        }
        
        String productString = reactionInput.split(" => ")[1];
        int productQuant = Integer.parseInt(productString.split(" ")[0]);
        String product = productString.split(" ")[1];
        //System.out.println("product parsed: " + productQuant + " " + product);
        this.addProduct(product, productQuant);
        }
        
        public void deleteEduct(String toDelete) {
            for (int i = 0; i < this.eductNames.size(); i++) {
                if (this.eductNames.get(i).equals(toDelete)) {
                    this.eductNames.remove(i);
                    this.eductQuantities.remove(i);
                    break;
                }
            }
        }
        
//        public int getEductQuant(String educt){ // problem, not used for now
//            for (int i = 0; i < this.eductNames.size(); i++) {
//                if (this.eductNames.get(i).equals(educt)) {
//                    return this.eductQuantities.get(i);
//                } else {
//                    return -1;
//                }
//        }
        
        
        

        public void replaceEducts(reaction source) {
            
            // todo: needs to handle leftovers
            // idea: add leftovers as negative educts, should then cancel out
            
            // double check so that product of source reaction really is an educt of this reaction

            int eductPos = 999;
            int eductQuantity = 0;
            for (int i = 0; i < eductNames.size(); i++) {
                if (eductNames.get(i).equals(source.productNames.get(0))) {
                    eductPos = i;
                    eductQuantity = eductQuantities.get(i);
                    //System.out.println("replacing " + eductNames.get(i));
                    break;
                }
            }
            if (eductPos == 999) {
                //System.out.println("Product of source reaction is not an educt of active reaction");
            } else { // product - educt pair was found
                // remove educt
                eductNames.remove(eductPos);
                eductQuantities.remove(eductPos);

                // find out how often the source reaction has to be applied to satisfy eductQuantities
                int sourceMultiplier = eductQuantity; // maximum possible times the source reaction has to be called
                while ((sourceMultiplier - 1) * source.productQuantities.get(0) >= eductQuantity) {
                    sourceMultiplier--;
                }

                // add educts from source reaction
                for (int i = 0; i < source.eductNames.size(); i++) {
                    addEduct(source.eductNames.get(i), (source.eductQuantities.get(i) * sourceMultiplier));
                }
                
                // leftovers?
                int eductDiff = eductQuantity - (sourceMultiplier * source.productQuantities.get(0));
                if (eductDiff != 0) {
                    //System.out.println("leftovers: " + eductDiff + " " + source.productNames.get(0));
                    addEduct(source.productNames.get(0), eductDiff); // add the leftovers (a negative number) to master Reaction                    
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


