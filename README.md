# Chess
This is my attempt to create a chess game.      
This will be my largest project ever, and will likely take quite some time to complete.    
Update on 1/8/23: The game is now completely functional! I still have many different ideas for what to add, though.    
Update on 1/10/23: I have added three other game rules: promoting, castling, and en passant.    
The game pretty much now contains all chess functionality, except for the king potentially being able to move into check.    
I have spent a lot of time working on this project over the last few days, so I want to take a break before starting on this final rule.

# TODO

-Add promoting and castling (this is most important)(DONE)    
-Add function to determine what squares are under attack on a board and update the boolean fields of these squares accordingly    
-Change king section of isMoveValid to state that moving into any square under attack by an opponent is invalid    
-Add function to load board states from a file or even save?    
-Add an undo button(would require me to make a deep copy of the grid array and a copy constructor for Square)    
-Fischer random chess?    

# How to run      
First, make sure you have Java installed. Put each .java file in the same directory, then open the directory in cmd.     
Type "javac (filename).java" without quotations for each file.        
Finally, type "java Main" to execute the program.
