# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Anwesa Basu

**Email:** basu.anw@northeastern.edu

**Preferred Name:** Anwesa

**Name:** Swapnendu Majumdar

**Email:** majumdar.s@northeastern.edu

**Preferred Name:** Swapnendu



### About/Overview

We have created a graphical representation of world in java swing which can have multiple rooms,
a target character can move into all the rooms in a 0 ordered index.The targer character will have  a name and damage. 
There will be atleast 20 rooms and each room can have some items. A world will have atleast 20 items. 
A room who shares a wall with another room will be called as neighbor of the another room and each room can see into their neighbor's rooms.
A human player and a computer player will be added into the game. The behaviour of the computer player will be random.
Both the players can move,,pick items or lookarund in the world and they try to attack the target.
Ultimately the player that kills the target wins and the game will be over.



### List of Features

1) Launch:- By clicking on the launch button user will have a JMenu from where user can select any of the below 3 operations.
            a)Start game(Default config):- It will start the game with the default config provided in the command line arguments.
            b)Start game(New config):- It will start the game with the new file chosen by the user.
            c)Quit:- It will help the user to quit the game gracefully.


1) PickItem:- User(Human player) can press p or P to pick up an item from a room in the world.

2) Add Human Player:- Add Human Player button will help to add a new human player in the game. It will ask for the playername
and room num to add the player. Following is the format to add a player:- Anwesa 0

3) Add Computer Player:- Add Computer Player button will help to add a new human player in the game. It will ask for the playername
and room num to add the player. Following is the format to add a player:- Anwesa 0

4) AttackTarget:- User(Human player) can press a or A to attack the target.

5) MovePlayer:- User(Human player) can move to a neighbor room by clicking on that room in the graph.

6) LookAround:- User(Human player) can press l or L to lookaround.

7) ComputerAction:- A computer action can be taken by pressing c or C.

8) PokeIntheEye:- User(Human player) can press k or K to poke in the eye of the target.



### How to Run

To run the jar file we have to pass a file path and max no of turns as a command line arguement. I have used
C:\CS5010Project\cs5010-project-Anwesa2311\cs5010-project\res\mansion.txt path and 10 as max number of turns to send as an
arguement in the driver class run configuration.

Ex:- C:\\Users\\anwes\\Downloads\\mansion.txt 10

The arguements should be follwed by a space. Here max turns are follwed by a space after the file path.



### How to Use the Program

The program can be used for the below functionalities:-


1) Fist in driver run config add the parameters mentioned above.

2) After that run the program and it will prompt a screen where by pressing Launch button
you can start the game.



### Example Runs





### Design/Model Changes

The design has been changed while doing the implementation. Below are the changes:-

1) Adding a method in world to restrict the number of players to be added.
2) Changing the controller from a text based to a feature based to interact with both
the view and model.
3) Adding a method in the world to move a player based on row and column.
4) Adding view classes and interfaces to implement the view of the game.
5) Adding a facade class for view to interact with the controller.


### Assumptions

1) User can take the actions for computer by pressing c or C
2) For human player maximum items to carry will be decided randomly.
3) The game will continue until it reaches its max turn or if someone wins.
4) The player can not move to any other room by clicking if it does not have it as its neighbor.
5) for poking in the eye of the target user has to press k or K.
6) The user has to maintain the format "Anwesa 0" to add a new player in the game.
7) The world with new file spec will have the same number of max turns as of the default file spec.


### Limitations



1) There are recudndancies in certain places which can be reduced by using extra helper methods.

2) Could have added a reset game functionality.

3) Could have used a facade layer in the model class.


### Citations

References:-
1)https://www.geeksforgeeks.org/split-string-java-examples/
2)https://stackoverflow.com/questions/306316/determine-if-two-rectangles-overlap-each-other
3)https://www.geeksforgeeks.org/iterative-depth-first-traversal/




