

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marco
 */
public class day8 {
    public static void main(String[] args) {
        
        int width = 25;
        int height = 6;

        String[] stringDigits = readByLine.readAsString("day8").get(0).split("");
        
        int[] digits = new int[stringDigits.length];
        
        for (int i = 0; i < stringDigits.length; i++) {
            digits[i] = Integer.parseInt(stringDigits[i]);
        }
        
        int layerWithFewest = getLayerWithFewest(digits, 0, width*height);
        
        System.out.println("layer with fewest 0: " + layerWithFewest);
        
        System.out.println("result day 8 part1: " + (
                countInLayer(digits, layerWithFewest, 1, width*height)* 
                countInLayer(digits, layerWithFewest, 2, width*height)) );
    }
    
    public static int getLayerWithFewest(int[] digits, int fewest, int layerSize) {
        int countsCurrent = 0;
        int countsFewest = 99999;
        int layerWithFewest = 0;
        int layer = 0;
        
        int i = 0;
        for (int digit : digits) {
            
            if (digit == fewest) countsCurrent++;
            i++;
            
            // a layer is full
            if (i >= layerSize) {
                if(countsCurrent < countsFewest) {
                    countsFewest = countsCurrent;
                    layerWithFewest = layer;
                }
                layer++;
                i = 0;
                countsCurrent = 0;
            }
            
        }
        return layerWithFewest;
    }
    
    public static int countInLayer(int[] digits, int layer, int digit, int layerSize) {
        int count = 0;
        int startDigit = layerSize * layer;
        
        for (int i = startDigit; i < (startDigit + layerSize); i++) {
            if (digits[i] == digit) count++;
        }
        
        return count;
    }
}

