# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Spring 2022 Edition!

**Name:** Rakshika Raju

**Email:** raju.r@northeastern.edu

**Preferred Name:** Rakshika



### About/Overview

This project demonstrates the use of MVC dessign pattern to build a board game which is inspired from the Kill Doctor Lucky game.
The First part of the milestone contains the Model which has the follow Components: The World, the spaces within the world, the items in the spaces of the world, and the target character in the game. The Target character moves between rooms sequentially.
The Second part of the milestone has the updated model to include the players in the game. The players can be computer controlled or human controlled.
There is a text based synchronous controller which facilitates playing the game from the command line.
The Third part of the milesone has additional character - Target's Pet which brings changes to the Model and the Gameplay is implemented. At the end of the game, the Target is either killed and a winner is declared or the Target escapes to live another day!



### List of Features

1. Create the world using the given parameters in the input file. It can return the parsed details.
2. Create a pictorial representation of the world.
3. The game has a target character which moved in order of the space indexes. Once the target reaches the last space, its position resets to the space with index 0.
4. The program displays a list of neighbors of every space. 
5. The program accepts a text file in a specified format(examples of the format are available in /res folder) and alerts by throwing specific exceptions if the provided content is not correct.
6. Gives the details of which items are present in the room and which players are present in the room along with details if the Pet or Target are present in the room.
7. Allows to add human controlled players and computer controlled players to the game.
8. The computer controlled player plays the game independently.
9. Player is allowed to move to neighboring spaces.
10. Player is allowed to pick Items from current space. They have a limit on the number of items that can be picked. This limit is defined while creating the player.
11. Correct turn of the players are used in the order they are added to the game.
12. Players can look around the space they are current in, to get details of the neighbors and the items present in them.
13. Players are provided with basic details of their present space and the Target's location.
14. Players can attack the target of they are in the same space. When attacked with an item successfully, the target's health is reduced by the item's damage amount.
15. The Attack on target is successful if no other player sees the attack. The target dies if its health becomes 0 or lesser.
16. If player has no item, they can choose attack target by poking his eye for a damage of 1. The computer player attacks the target when they are in the same room and not seen by another player with the item containing highest damage.
17. When the pet is present in the room, that room becomes invisible to others. Hence lookaround will not contain this room in its neighbor's list.
18. Pet can be moved from its current location to any other room by the players. The Pet also automatically moves between the spaces by DFS traversal.


### How to Run

Download the jar file present in the /res folder.
In the command prompt run the jar file by giving two arguments. The first argument is the path to the inputfile, and the second argument is the maximum number of turns till which the game can be played.
Example Command: java -jar Project-KillDrLucky-M3.jar C:/Users/raksh/PDP-5010-workspace-eclipse/cs5010-project-rakshika-raju/res/mansion-rakshika.txt 15



### How to Use the Program

You can run the program with the JAR file present in the /res folder. Instructions for how to run the JAR file in present in the previous "How to Run" section.
If you clone the repository, the program runs using the main class as the Driver class. You can run the "Main.java" file by passing two command line arguments containing the path to the text file which has the specifications for creating the world and the max turns value.



### Example Runs

The file /res/Milestone1/test.txt contains the output from one example test run from Milestone1. Below are the tests run:
1. Display the Neighbors with just their Room numbers, and Object references.
2. Display items in a Space.
3. Display all the details of a Room including the items in the Room and neighbors of the room.
4. Display all the items in the World.
5. Display Target Details. Move the target to make sure its location is updated.

#### Example runs from Milestone 2:
1. /res/Milestone2/mileStone2ExampleRun.txt has the updated model requirements outputs displayed.
2. /res/Milestone2/MileStone2-gameExampleRun has the example run of playing the game with below cases:
- Create the computer controlled player.
- Create two human controller players.
- Show the correct turn of the players.
- Allow the player to Move to a Neighboring space. If a Space is not a neighbor and player attempts to move to that space, they lose their turn.
- Allow the player to Pick up and item from the space they are curerntly in. If they pick the wrong item, they are allowed to try again.
- Allow the player to look around so that they can decide what their next action should be. They can also see the target details with this command.
- Display the player details to show which space they are in and what items they have picked.
- Does not allow a player to pick more items than the max allowed value.
- Save the graphical representation of the world in PNG format in the /res folder of current directory.
- Display information of the Space in which the player is currently in, so that they can see what items are available.

#### Example runs from Milestone 3:
1. /res/HumanWinnerExampleRun.txt has cases where the computer and human players have attacked the Target with available items or by poking the target in the eye. The Target is killed by the attack made by the human Player. Hence human player has won.
2. /res/ComputerWinnerExampleRun.txt has case where the computer player makes the final attack which kills the target hence making computer player the winner.
3. /res/PetVisibility_TargetEscape.txt demonstrates how the pet makes a room invisible. We can see that Room 6 has neighbors 1, 4, 5, 7. But since Pet is present in Room 1 initially, room 1 is not seen as a neighbor. Once the pet moves to a different room, room 1 is seen as a neighbor of room 6 again. At the end no one is able to kill the target. Since Max number of turns is reached, the game ends with no winner and Target escapes.
4. /res/PetDFStraversal demonstrates that the Pet travels around the rooms in DFS traversal order. Example: Traversal for the pet if starting room is 1 is: 1 5 6 4 3 7. The Pet is moved by player to room 15, so the traversal starts from 15 and goes in the order: 15 7 3 4.




### Design/Model Changes

Changes from UML1 to UML2 in Milestone 1:
1. In the initial design, (UML1) the constructor of world accepted a File as input, but that has been changed to String.
2. Added private helper methods in World for code segregation.
3. Added setNeighbors method to World which sets the neighbors list of all spaces after running the function to find all neighbors of all Spaces.
4. Have not implemented the changeHealth() method of Target since the requirements for this are not clear yet. Hence it has been removed from the UML as well.

Changes from UML_TEST_PLAN_MILESTONE2_Rakshika to MileStone2-UML-After-Implementation in milestone 2:
1. In the modified design, the World Interface has only those methods which are needed for the command controller to work.
2. The controller only interacts with the world interface and not with any other interfaces in the model.
3. Added RandomNumber class which the controller uses to generate random numbers for the computer controlled player.
4. Modified the command controller classes to gave only the go() method, which calls the relevent methods in the model via the world interface.

Model is modified to include Target character's pet.
To support game play, additional public methods are added to attack the target and move the Pet.
Controller has additional command patterns to support kill attempt and move pet.
Changes from Milestone3_prelimDesign-rakshika.pdf to Milestone3-revisedUML in milestone3:
1. The Random class is being used by the model now instead of the controlled.
2. The computer controlled player's action is determined by the model and not the controller.
3. For the DFS traversal of the Pet, a new Graph Interface and its implementation class is added.


### Assumptions

1. The World dimensions can only be positive integers.
2. Initial Health of Target must be a Positive value.
3. There should atleast be two spaces present.
4. The coordinates for the room are expected to be correctly given in the input file.
5. Room Column or Row value cannot be negative. They can also not be greater then the room dimensions.
6. There should atleast be one 1 item.
7. All Intergers in the input file are whole numbers.
8. Computer controlled and human controller players are the same class and created in the same way. It is left to the conroller to handle the difference between these two types of players.
9. This game can have only one computer controlled player.
10. Player numbers can only be integers.
11. The controller accepts indexes for the spaces they want to move to and the items they want to pick. Users are expected to use relevent commands displayed on the screen to look for integer equivalents for the operations they want to do. This is not difficult to understand since index and the corresponding names are displayed on the screen.
12. If computer has no items to attack, it attacks by poke in the eye.
13. User is aware of DFS traveral and can keep track of the Pet's location on their own.




### Limitations
 
1. If there is an error during the execution of the program, the game is stopped and needs to be restarted manually.
2. If the number of rooms is high, it could take a few seconds for the program to run.



### Citations

Reference for creating buferred image and graphics:
"How to usedrawRectmethodinjava.awt.Graphics". Available: https://www.tabnine.com/code/java/methods/java.awt.Graphics/drawRect [Accessed Feb 9th 2022].

Reference for generating random numbers in a given range:
"https://www.baeldung.com/java-generating-random-numbers-in-range" [Accessed March 1st 2022].

Referemce for generating dfs traversal:
"https://www.softwaretestinghelp.com/java-graph-tutorial/" [Accessed March 29th 2022].


