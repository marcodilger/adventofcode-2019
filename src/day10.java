/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author marco
 */
public class day10 {
    public static void main(String[] args) {
    List<String> input = readByLine.readAsString("day10test.txt");
    
    int width = input.get(0).length(); // input probably is a square
    int height = input.size();
    
    //System.out.println("" + width + ""+ height);
    
    // grid
    boolean[][] map = new boolean[width][height];
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
           map[j][i] = input.get(i).charAt(j) == '#';
        }
        
        }
    
    // for each star
    
    int current_right = 0;
    int current_down = 0;
            
    // create localmap from scratch
    
    // determine max radius to check:
    int max_radius = Math.max(
            width-current_right -1, 
            Math.max(current_right,
            Math.max(height - current_down -1,
            current_down))
    );
    int r = 1;
    while (r <= max_radius) {
        // which rows have to be checked:
        int top_row = current_down - r;
        int bottom_row = current_down + r;
        // which cols have to be checked:
        int left_col = current_right - r;
        int right_col = current_right + r;
        
        System.out.println(
                "tr: " + top_row +
                " br: " + bottom_row +
                " lc: " + left_col + 
                " rc: " + right_col
        );
        
        // go through all cols and rows
        for (int col = left_col; col <= right_col; col++) {
            if (col >= 0 & col < width & top_row > 0 & top_row < height) {
                if (map[col][top_row]) {
                    System.out.println("Star found at" + "col: "+ col + " downrow: " + top_row);
                }
            }
            if (col >= 0 & col < width & bottom_row > 0 & bottom_row < height) {
                if (map[col][bottom_row]) {
                    System.out.println("Star found at" + "col: "+ col + " downrow: " + bottom_row);
                }
            }
        }
        for (int row = top_row + 1; row < bottom_row; row++) {
            if (row >= 0 & row < height & left_col > 0 & left_col < width) {
                if (map[row][left_col]) {
                    System.out.println("Star found at" + "col: "+ left_col + " downrow: " + row);
                }
            }
            
            if (row >= 0 & row < height & right_col > 0 & right_col < width) {
                if (map[row][right_col]) {
                    System.out.println("Star found at" + "col: "+ right_col + " downrow: " + row);
                }
            }
        }
        
        
        r++;
    }
    }
    
}
    

