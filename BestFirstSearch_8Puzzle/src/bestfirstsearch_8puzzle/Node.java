/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestfirstsearch_8puzzle;

import java.util.ArrayList;

/**
 *
 * @author Nimra Aziz
 */
//
public class Node {

    public int heuristic_value;//every node has it's own heuristic value
    public int[][] puzzel_box; // A 2-D array contain the actual content of puzzle
    public int[] queen_position; // 1-D array with 2 values, [0]=RowNumber, [1]=ColumNumber
    public ArrayList<Node> children; //to save the childs
    public int nodeID;//iD assigned to every node
    public static int id = 1;//static variable to count down for Node IDs
    private int level; //as we know every node has a level

    Node(int[][] values) {
        nodeID = id;
        id++;
        level = 0;
        puzzel_box = values;
        heuristic_value = -1;//in start assign hv=-1 to all node
        queen_position = new int[2];
        children = new ArrayList<Node>();
        //-----finding the queen_position 
        for (int i = 0; i < puzzel_box.length; i++) {
            for (int j = 0; j < puzzel_box[0].length; j++) {
                if (puzzel_box[i][j] == -1) {
                    queen_position[0] = i;
                    queen_position[1] = j;

                }
            }
        }
    }
    public void set_level(int level){
        this.level=level;
    }
    public int get_level(){
        return this.level;
    }
    public String get_possible_moves() {
        String moves = "";
        switch (queen_position[0]) { //checking the RowNumber of queen
            case 0:
                moves += "d"; //queen can only go down
                break;
            case 1:
                moves += "ud"; //queen can go up and down
                break;
            case 2:
                moves += "u";//queen can only go up
                break;
        }
        switch (queen_position[1]) {//checking the column Number of Queen
            case 0:
                moves += "r";//queen can only go Right
                break;
            case 1:
                moves += "lr";//queen can go left or right
                break;
            case 2:
                moves += "l";//queen can go only left
                break;
        }
        return moves;
    }

    public void add_child(Node child) {
        children.add(child);
    }

    public ArrayList<Node> get_child() {
        return children;
    }

    public void set_heuristic_value(int hv) {
        this.heuristic_value = hv;
    }

    public int get_heuristic_value() {
        return this.heuristic_value;
    }

    public Node move_queen(char ch) {//parameters must be a charecter from  ( u d l r ) 
        int si = 0; //to save the row number of the value to which queen is going to be swapped
        int sj = 0; //to save the column number of the value to which queen is going to be swapped

        switch (ch) {
            case 'u':
                si = queen_position[0] - 1;
                sj = queen_position[1];
                break;
            case 'd':
                si = queen_position[0] + 1;
                sj = queen_position[1];
                break;
            case 'l':
                si = queen_position[0];
                sj = queen_position[1] - 1;
                break;
            case 'r':
                si = queen_position[0];
                sj = queen_position[1] + 1;
                break;

        }

        return swap(queen_position[0], queen_position[1], si, sj);

    }

    public Node swap(int qi, int qj, int si, int sj) { 
        //This function will create child similar to its parent and then it will swap queen (in child) and at then end it will return that newly created child
        //System.out.println(qi+", "+qj+", "+si+" ,"+sj);
        //creating a 2-D array (3x3) and copying puzzle box to it.
        int[][] newValues = new int[3][3];
        for (int i = 0; i < puzzel_box.length; i++) {
            for (int j = 0; j < puzzel_box.length; j++) {
                newValues[i][j] = puzzel_box[i][j];
            }
        }
        
        //creating a new child with newValues
        Node child = new Node(newValues);
        
        //Swapping Queen
        //System.out.println(child.puzzel_box[qi][qj]+" ,"+child.puzzel_box[si][sj]);
        child.puzzel_box[qi][qj] = child.puzzel_box[si][sj];
        child.puzzel_box[si][sj] = -1;
        //Assiging new Queen Position
        child.queen_position[0] = si;
        child.queen_position[1] = sj;
        return child;
    }

    public void display() {
        //Please Read The Readme.txt file to understand the format of output.

        String a = "";
        for (Node temp : children) {
            a += temp.nodeID + " ";
        }
        if (a.isEmpty()) {
            a = "Leaf Node";
        }
        System.out.println("Node ID:" + nodeID + " Child IDs:" + a+" Level:"+get_level()+" HV:"+get_heuristic_value());
        for (int i = 0; i < puzzel_box.length; i++) {
            for (int j = 0; j < puzzel_box.length; j++) {
                System.out.print(puzzel_box[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
