/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestfirstsearch_8puzzle;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 *
 * @author Nimra Aziz
 */
//This is the main class
public class BestFirstSearch_8Puzzle {

    private Deque<Node> open;
    private Deque<Node> close;
    static Node puzzle;
    static Node goal;
    private List<Node> path_to_goal;
    int goal_node_id;

    BestFirstSearch_8Puzzle(int goal_v[][], int puzzle_v[][]) {
        open = new ArrayDeque<>();
        close = new ArrayDeque<>();
        puzzle = new Node(puzzle_v);
        goal = new Node(goal_v);
        path_to_goal = new ArrayList<>();
        goal_node_id=-1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //****Please Read the ReadMe.txt file first to understand this code*******//
        
        int values[][] = {{2, 8, 3}, 
                          {1, 6, 4}, 
                          {7, -1, 5}}; //problem
        
        int goal_v[][] = {{1, 2, 3}, 
                          {8, -1, 4}, 
                          {7, 6, 5}};//goal
        
        /* 1,-1,3
         2,4,5
         6,7,8
         this puzzle box is unsolveable through dry-run, it takes 21 levels, and 19438 nodes
         */
        BestFirstSearch_8Puzzle myPuzzle = new BestFirstSearch_8Puzzle(goal_v, values);
        
        
        myPuzzle.Best_First_search();//actual work 
        
        /****NOTE****** 
        there are two commented lines below
            First line is used to display all the tree
            Second line is used to display only those nodes thourgh which our algoritham reached to the goal
            
            un-comment these lines to show result
        */
        
        myPuzzle.Traverse(puzzle);
        //myPuzzle.Display_path();
        
        System.out.println("Goal Node ID:"+myPuzzle.goal_node_id);
        
    }

    public void Display_path() {
        for (Node temp : path_to_goal) {
            temp.display();
        }
    }

    public void Best_First_search() {
        open.add(puzzle);
        while (!open.isEmpty()) {
            Node x = open.poll();
            path_to_goal.add(x); //to collect path data, from root to goal
            if (Arrays.deepEquals(x.puzzel_box, goal.puzzel_box)) {
                System.out.println("Success");
                goal_node_id=x.nodeID; 
                return;
            }
            String child_Moves = x.get_possible_moves(); //get all possible moves by x
            
            for (int i = 0; i < child_Moves.length(); i++) {//for each move
                
                Node newChild = x.move_queen(child_Moves.charAt(i));//create a child
                newChild.set_level(x.get_level() + 1);//set it's level
                x.add_child(newChild); //add child to x, (as x is the parent of child)
                Node is_Exist_in_open = is_Exist(newChild, open); //check if node exists in open or not?
                Node is_Exist_in_close = is_Exist(newChild, close); //check if node exists in close or not?
                if (is_Exist_in_open == null && is_Exist_in_close == null) {//case-1 (if child is not present in both i.e open and close
                    newChild.set_heuristic_value(Calculate_HV(newChild));
                    open.add(newChild);
                }
                //i have no proper understanding of case 2 and case 3.
                //without these cases, code also run, and gives the proper output.
//                if (is_Exist_in_open != null) {//case-2
//                    if (is_Exist_in_open.get_heuristic_value() >= newChild.get_heuristic_value()) {
//                        open.add(newChild);
//                    }
//                }
//                if (is_Exist_in_close != null) {//case-3
//                    if (is_Exist_in_close.get_heuristic_value() >= newChild.get_heuristic_value()) {
//                        close.remove(is_Exist_in_close);
//                        open.add(is_Exist_in_close);
//                    }
//                }
            }
            close.add(x); 
            open = sort(open);//re-arrange
        }
        System.out.println("Goal Not Found");
        return;
    }

    public static Node is_Exist(Node key, Deque<Node> values) {
        //**** This function checks weather the key exists in values or not?
        //if key exists then it will return the node (which is equal to our key)
        //other wise it will return null
        for (Node temp : values) {
            if (Arrays.deepEquals(temp.puzzel_box, key.puzzel_box)) {
                return temp;
            }
        }
        return null;
    }

    public static int Calculate_HV(Node node) {
        int hv = 0;
        for (int i = 0; i < node.puzzel_box.length; i++) {
            for (int j = 0; j < node.puzzel_box.length; j++) {
                if (node.puzzel_box[i][j] != goal.puzzel_box[i][j]) {
                    hv++;
                }
            }
        }
        hv += node.get_level();//as f(x)=g(x)+h(x)
        return hv;
    }

    public static Deque<Node> sort(Deque<Node> queue) {
        //Soring of Deque is difficult so here i convert Deque to ArrayList and then i sort ArrayList and again i covert ArrayList to Deque and returned it.
        ArrayList<Node> nodes = new ArrayList<>(queue); //Deque to ArrayList
        //Sorting..
        for (int i = 0; i < nodes.size(); i++) {

            for (int j = nodes.size() - 1; j > i; j--) {
                if (nodes.get(i).heuristic_value > nodes.get(j).heuristic_value) {

                    Node tmp = nodes.get(i);
                    nodes.set(i, nodes.get(j));
                    nodes.set(j, tmp);
                }
            }

        }
        //Arraylist to Deque
        Deque<Node> sorted = new ArrayDeque<>(nodes);
        return sorted;
    }

    public void Traverse(Node temp) {
        if (temp == null) {
            return;
        }
        temp.display();
        for (Node temp1 : temp.get_child()) {
            Traverse(temp1);
        }
    }
}