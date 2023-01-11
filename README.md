# Chess
This is my attempt to create a chess game.      
This will be my largest project ever, and will likely take quite some time to complete.    
Update on 1/8/23: The game is now completely functional! I still have many different ideas for what to add, though.    

Update on 1/10/23: I have added three other game rules: promoting, castling, and en passant.    
The game pretty much now contains all chess functionality, except for the king potentially being able to move into check.    
I have spent a lot of time working on this project over the last few days, so I want to take a break before starting on this final rule.    

Update on 1/11/23: I have added a system that keeps track of which squares are under attack and prevents the king from moving into check.    
There is an edge case where I can't currently mark a protected piece as being under "attack" from its same color    
because the gridSquaresUnderAttack() implementation relies on isMoveValid(), which will always return false if a piece attempts    
to move onto a square that is the same color. This means that a king piece could potentially take a protected piece, thereby    
moving into check. However, it would be difficult to deal with this using isMoveValid() as previously described.    
For now, I think I will just leave this issue alone and instead focus on adding a check mechanic or an undo button.

# TODO

-Add promoting and castling (this is most important) (DONE)    
-Add function to determine what squares are under attack on a board and update the boolean fields of these squares accordingly (DONE)    
-Change king section of isMoveValid to state that moving into any square under attack by an opponent is invalid (DONE)    
-Add a check mechanic that forces the player in check to deal with check    
-Stop the game and announce the winner if checkmate has occurred    
-Add function to load board states from a file or even save?    
-Add an undo button(would require me to make a deep copy of the grid array and a copy constructor for Square)    
-Fischer random chess?    

# How to run      
First, make sure you have Java installed. Put each .java file in the same directory, then open the directory in cmd.     
Type "javac (filename).java" without quotations for each file to generate a compiled .class file for each .java file.        
Finally, type "java Main" to execute the program.
