/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8puzzlegameaiproject;

/**
 *
 * @author Omar Mohamed Adel Elsabaawy
 */
import java.util.*;

public class Node {
    
   protected int [] PuzzleBoard = new int [9]; // puzzle board numbers
   protected Node parent ; // to make the node that are added in every branch a parent
   protected LinkedList <Node> TreeChildren = new LinkedList <> (); // the tree 
   protected int ZeroIndex = 0; // put the index that contains zero in this variable 
    
    public Node(int [] ar){
        this.PuzzleBoard = ar;
    }
    
    //this function is used to print the puzzle
    public void PrintThePuzzle(){
        System.out.println("------------------------");
        System.out.println(" "+PuzzleBoard[0]+"  "+PuzzleBoard[1]+"  "+PuzzleBoard[2]+" ");
        System.out.println(" "+PuzzleBoard[3]+"  "+PuzzleBoard[4]+"  "+PuzzleBoard[5]+" ");
        System.out.println(" "+PuzzleBoard[6]+"  "+PuzzleBoard[7]+"  "+PuzzleBoard[8]+" ");
        
    }

    //this function is used to check if this board is the goal or not    
    public boolean IsTheGoal(){
          if (PuzzleBoard[0]<PuzzleBoard[1]) {
            if (PuzzleBoard[1]<PuzzleBoard[2]) {
                if (PuzzleBoard[2]<PuzzleBoard[3]) {
                    if (PuzzleBoard[3]<PuzzleBoard[4]) {
                        if (PuzzleBoard[4]<PuzzleBoard[5]){
                            if (PuzzleBoard[5]<PuzzleBoard[6]){
                                if (PuzzleBoard[6]<PuzzleBoard[7]) {
                                    if (PuzzleBoard[7]<PuzzleBoard[8]) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false; 
    }
    
    //this function is used to copy the board and put it in another array used in the moves
    //to create another nodes 
    public void CopyThePuzzle(int [] originalPuzzle , int [] copyedPuzzle){
        for (int i = 0; i < copyedPuzzle.length; i++) {
           copyedPuzzle[i] = originalPuzzle[i];
        }
    }
    
    //this function is used to to check if it's the same puzzle or not and used in bfs 
    //and dfs as it checks if this node is already exist in the explored list or 
    //frontier list or not
    public boolean IsTheSamePuzzle(int [] ar){
        for (int i = 0; i < ar.length; i++) {
            if (PuzzleBoard[i] != ar[i]) {
                return false;
            }
        }
        return true;
    }
    
    //this function is used to make a node after moving moving the zero to left and
    //moving the number to right
    public void MoveToLeft(int [] OriginalPuzzleBoard , int zeroIndex){
        if (zeroIndex - 1 == 0 || zeroIndex - 1 == 1 || zeroIndex - 1 == 3 || zeroIndex - 1 == 4 || zeroIndex - 1 == 6 || zeroIndex - 1 == 7) {
            
            int [] copiedPuzzleBoard = new int [9];
            CopyThePuzzle(OriginalPuzzleBoard, copiedPuzzleBoard);
              
                int temp = copiedPuzzleBoard[zeroIndex - 1];
                copiedPuzzleBoard[zeroIndex - 1] = copiedPuzzleBoard[zeroIndex];
                copiedPuzzleBoard[zeroIndex] = temp;
               
                Node NewTreeChild = new Node(copiedPuzzleBoard);
                TreeChildren.add(NewTreeChild);
                NewTreeChild.parent = this ;
        }
        
    }
    
    //this function is used to make a node after moving moving the zero to right and
    //moving the number to left    
    public void MoveToRight(int [] OriginalPuzzleBoard , int zeroIndex){
        if (zeroIndex + 1 == 1 || zeroIndex + 1 == 2 || zeroIndex + 1 == 4 || zeroIndex + 1 == 5 || zeroIndex + 1 == 7 || zeroIndex + 1 == 8) {
           
            int [] copiedPuzzleBoard = new int [9];
            CopyThePuzzle(OriginalPuzzleBoard, copiedPuzzleBoard);
                
                int temp = copiedPuzzleBoard[zeroIndex + 1];
                copiedPuzzleBoard[zeroIndex + 1] = copiedPuzzleBoard[zeroIndex];
                copiedPuzzleBoard[zeroIndex] = temp;
                
                Node NewTreeChild = new Node(copiedPuzzleBoard);
                TreeChildren.add(NewTreeChild);
                NewTreeChild.parent = this;
        }
        
    }
    //this function is used to make a node after moving moving the zero to up and
    //moving the number to down
    public void MoveToUp(int [] OriginalPuzzleBoard , int zeroIndex){
        if (zeroIndex - 3 == 0 || zeroIndex - 3 == 1 || zeroIndex - 3 == 2 || zeroIndex - 3 == 3 || zeroIndex - 3 == 4 || zeroIndex - 3 == 5 ) {
            
            int [] copiedPuzzleBoard = new int [9];
            CopyThePuzzle(OriginalPuzzleBoard, copiedPuzzleBoard);
            
                int temp = copiedPuzzleBoard[zeroIndex - 3];
                copiedPuzzleBoard[zeroIndex - 3] = copiedPuzzleBoard[zeroIndex];
                copiedPuzzleBoard[zeroIndex] = temp;
                
                Node NewTreeChild = new Node(copiedPuzzleBoard);
                TreeChildren.add(NewTreeChild);
                NewTreeChild.parent = this;
        }
        
    }
    //this function is used to make a node after moving moving the zero to down and
    //moving the number to up
    public void MoveToDown(int [] OriginalPuzzleBoard , int zeroIndex){
        if (zeroIndex + 3 == 3 || zeroIndex + 3 == 4 || zeroIndex + 3 == 5 || zeroIndex + 3 == 6 || zeroIndex + 3 == 7 || zeroIndex + 3 == 8) {
            
            int [] copiedPuzzleBoard = new int [9];
            CopyThePuzzle(OriginalPuzzleBoard, copiedPuzzleBoard);
            
                int temp = copiedPuzzleBoard[zeroIndex + 3];
                copiedPuzzleBoard[zeroIndex + 3] = copiedPuzzleBoard[zeroIndex];
                copiedPuzzleBoard[zeroIndex] = temp;
                
                Node NewTreeChild = new Node(copiedPuzzleBoard);
                TreeChildren.add(NewTreeChild);
                NewTreeChild.parent = this;
                
        }
        
    }
    
    //this function is used to expand nodes and make new nodes for this parent and
    //put them in the tree as tree Children
    public void ExpandMoves(){
        for (int i = 0; i < PuzzleBoard.length; i++) {
            if (PuzzleBoard[i] == 0) {
                ZeroIndex = i;
            }
            
        }
        
        MoveToLeft(PuzzleBoard, ZeroIndex);
        MoveToRight(PuzzleBoard, ZeroIndex);
        MoveToUp(PuzzleBoard, ZeroIndex);
        MoveToDown(PuzzleBoard, ZeroIndex);
        
    }
    
    
}
