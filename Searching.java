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

public class Searching {
    
    Queue<Node>frontier1 = new LinkedList<>(); //  Queue of Nodes used in BFS as we put the children in it and we use FIFO as first in first out
    Stack<Node>frontier2 = new Stack<Node>(); //  stack of Nodes used in DFS as we put the children in it and we use LIFO as last in first out
    LinkedList<Node> explored = new LinkedList<>(); //  used to put in it the the explored nodes and mark them as they're exploerd
    LinkedList <Node> path = new LinkedList<>(); // used to put in it the nodes of the shortest path
    Node initialState; // the initial state puzzle
    Node theLastNode; // the last node puzzle will be stored in this node to get find the shortest path
    
    public Searching(Node int_state){
        initialState = int_state;
    }
    
    // the BFS code 
    public boolean BFS(){
        frontier1.add(initialState);
        
        while(!(frontier1.isEmpty())){
            
            Node state = frontier1.remove();
            explored.add(state);
            
            if (state.IsTheGoal()) {
                theLastNode = state;
                System.out.println();
                System.out.println("GOAL IS FOUND!!!!");
                state.PrintThePuzzle();
                
                System.out.println("---------------------");
                System.out.println("And the Shortest Path from Start is:");     
                
                ShowTheShortestPath();           
                
                return true;
                
            }else{
            state.PrintThePuzzle();
            }
              
            state.ExpandMoves();
            
            for (int i = 0; i < state.TreeChildren.size(); i++) {
                
                Node child = state.TreeChildren.get(i);
                if (!Contains(explored, child)) {
                    
                    frontier1.add(child);
                }
            }          
        }
        
        return false;
        
    }
    
    // the DFS code
    public boolean DFS(){
        frontier2.push(initialState);
        
        while(!(frontier2.empty())){
            
        Node state = frontier2.pop();
        explored.add(state);
        
            if (state.IsTheGoal()) {
            
            
                theLastNode = state;
                System.out.println();
                System.out.println("GOAL IS FOUND!!!!");
                state.PrintThePuzzle();
                
                System.out.println("------------------------");
                System.out.println("And the Shortest Path from Start is:");
               
               ShowTheShortestPath();
                
                return true;
                
            }else{
            state.PrintThePuzzle();
            }
            state.ExpandMoves();
            
            for (int i = 0; i < state.TreeChildren.size(); i++) {
                
                Node child = state.TreeChildren.get(i);
                
                if (!(Contains(explored, child))) {
                    frontier2.push(child);
                }
            }
        }
        
        return false;
        
    }
    
    // used to determine if this node is already exist in the explored or not if not then add this node in the explored list 
    // else don't put this node in the list and continue 
    public boolean Contains(LinkedList <Node> explored, Node child) {
        
        boolean contains = false;
        for (int i = 0; i < explored.size(); i++) {
            if (explored.get(i).IsTheSamePuzzle(child.PuzzleBoard)) {
                contains = true;
            }
        }
        return contains;
    }
    
    // this function is used to get the shortest path from the initial state to the goal state and print them 
    public void ShowTheShortestPath(){

        while(path.peekLast() != initialState){
            path.add(theLastNode);
            theLastNode = theLastNode.parent;
        }
        for (int i = path.size()-1 ; i >= 0; i--) {
            Node node = path.get(i);
            node.PrintThePuzzle();
        }
    }
    
    
    
}
