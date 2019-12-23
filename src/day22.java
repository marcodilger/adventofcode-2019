
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
/**
 *
 * @author marco
 */
public class day22 {
    public static void main(String[] args) {
        day22 day22 = new day22();
        day22.part1();
        
    }
    
    public void part1(){
      
        cardDeck d = new cardDeck(10007);
        
        // parse input
        List<String> input = readByLine.readAsString("day22.txt");
        
        for (String evaluated : input) {
        if (evaluated.contains("deal into")) {
            d.dealIntoNew();
        } else if (evaluated.contains("cut")) {
            d.cut(Integer.parseInt(evaluated.split(" ")[1]));
        } else if (evaluated.contains("deal with increment")) {
            d.dealIncrement(Integer.parseInt(evaluated.split(" ")[3]));
        }
        }
        
        //System.out.println(d);
        System.out.println(d.getCard(2019));
    }
    
    class cardDeck {

       
        int[] deck;
        int deckSize;
        
        public cardDeck(int size) {
            this.deckSize = size;
            this.deck = new int[deckSize];
            for (int i = 0; i < deckSize; i++) {
                this.deck[i] = i;
            }
        }
        
        // methods to implement:
        public void dealIntoNew() {
            // reverse the deck
            // never has arguments
            
            int dealt = 0;
            int[] workingDeck = new int[deckSize];
            while (dealt < deckSize) {
                workingDeck[(deckSize - dealt) - 1] = this.deck[dealt];
                dealt++;
            }
            this.deck = workingDeck;
        }
        
        public void cut(int cut) {
            // cut away until pos
            // and put on new stack; fill stack up with remaining
            
            // handle negative (can be converted to std case)
            if (cut < 0) {
                cut = deckSize + cut;
            }
            
            
            int dealt = 0;
            int[] workingDeck = new int[deckSize];
            
            // first put the cut away part at the end of the array
            while (dealt < cut) {
                workingDeck[(deckSize - cut) + dealt] = this.deck[dealt];
                dealt++;
            }
            
            while (dealt < deckSize) {
                workingDeck[(dealt - cut)] = this.deck[dealt];
                dealt++;
            }
            
            
            
            this.deck = workingDeck;
            
        }
        
        public void dealIncrement(int increment) {
            int dealt = 0;
            int[] workingDeck = new int[deckSize];
            while (dealt < deckSize) {
                workingDeck[(dealt*increment) % deckSize] = this.deck[dealt];
                
                dealt++;
            }
            this.deck = workingDeck;
        }
        
        public int getPos(int pos) {
            return this.deck[pos];
        }
        
        public int getCard(int card) {
            for (int i = 0; i < deckSize; i++) {
                if (this.deck[i] == card) {
                    return i;
                }
            }
            return -1;
        }
        
         @Override
        public String toString() {
            return "cardDeck{" + "deck=" + Arrays.toString(deck) + '}';
        }
        
    }
    
}
