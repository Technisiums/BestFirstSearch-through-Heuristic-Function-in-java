Verision: 2.0
This project solves the 8-Puzzle Problem.
Flow of working. (BestFirstSearch_8Puzzle is the main class of the project)
*Create Problem and Goal states
*Searching for Goal thourgh AI technique (Heuristic)
*Display All the nodes

***************************_Format of Display_***************************
*Every Node Assigned with an ID
*In the first line of the output. There is Node ID, next there are Child IDs of that node after that there is Level number and at the end there is Heuristic value.
*on next three lines there is Puzzle box. where -1 indicates the queen.
*If child IDs=Leaf Node thats means it has no children and it is considered to be the leaf node.

*NOTE####
"The Node with the Highest Node value is your solution."

****Example::

Node ID:1 Child IDs:3 4 5  Level:0 HV:-1
2 8 3 
1 6 4 
7 -1 5 

Node ID:3 Child IDs:6 7 8 9  Level:1 HV:4
2 8 3 
1 -1 4 
7 6 5 

Node ID:6 Child IDs:13 14 15  Level:2 HV:6
2 -1 3 
1 8 4 
7 6 5 

Node ID:13 Child IDs:Leaf Node Level:3 HV:-1
2 8 3 
1 -1 4 
7 6 5 

**********************************************************************************************************************************************************************
Author: Nimra Aziz
Technisiums.
All the code on GitHub is totally free. You can use this code in your projects. You can also modify.  
This code is published by Technisiums.(10-03-2018)
**********************************************************************************************************************************************************************