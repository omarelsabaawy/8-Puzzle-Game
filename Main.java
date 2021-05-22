/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzlegameaiproject;


/*
 *
 * @author Omar Mohamed Adel Elsabaawy
 */

import java.util.Scanner;
import java.time.ZonedDateTime;
public class Main {

static Scanner s =new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int[] puzzleBoard = new int [9]; // puzzleBoard playing the role of initial state
        
        System.out.println("Enter the Puzzle in its sequance:");
        FillThePuzzle(puzzleBoard); // function to fill the array which is the puzzle board
        System.out.println("-------------");
        
        Node rootNode = new Node(puzzleBoard);
        Searching s1 = new Searching(rootNode);
        
        System.out.println("enter 1) to use the BFS");
        System.out.println("enter 2) to use the DFS");
        
        // switch to choose what to use BFS or DFS
        
        int swtch = s.nextInt();
        if (swtch == 1 || swtch == 2) {
            System.out.println("The order of Nodes is: ");
        }
        switch(swtch){
            case 1 : s1.BFS();
                break;
            case 2 : s1.DFS();
                break;
            default: System.out.println("Bad Entry Try Again!!");
        }
        
    }
    
    // function of filling the array
    public static void FillThePuzzle(int [] ar){
        for (int i = 0; i < ar.length; i++) {
            ar[i] = s.nextInt();
        }
    }
    
    
}
