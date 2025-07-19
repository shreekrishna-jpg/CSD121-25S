1. Why can you change the type of the returned value in promptForPlayer without changing the return type in the function signature? (Your answer should refer to polymorphism.

The abstract base class Player is the return type of the promptForPlayer function.  Since the result type is given as Player, polymorphism allows this method to return any subclass of Player, including HumanPlayer, RandyPlayer, and OmolaPlayer.

A reference variable of a base class type can point to an object of any subclass type in object-oriented programming, which is why this works.  Returning an instance of a subclass is therefore allowed and doesn't require changing the defined return type.

Therefore, the actual returned object may be any subclass of Player, even though the method signature indicates that it returns a Player.  Dynamic method dispatch ensures that the right subclass implementation is applied automatically when pickNextMove() is invoked on this returned object later.


2. Explain why the error occurred initially and why adding the abstract method signature fixes the error. (HINT: What is the type of the ‘whoseTurn’ variable in TicTacToeGame.doNextTurn?

At first teh error has occurred because of the whoseTurn variable is a type of Player, and the method "pickNextMove(Board)" is not declared in player class yet. Even if we add the player like Randy and Omola in this method as subclass java does not make it call methods on a variable until we define them as variable declared type. So, java throws an compile time error because player doesnot have this method.
So, after adding an abstract method i.e. "public abstract Position pickNextMove(Board board);" in the player class it fixes the error because the java finds this has to implement every subclass of player with this method. This allows polymorphism to function properly: Java can safely call pickNextMove(...) even though whoseTurn is declared as type Player, and the appropriate subclass version (from HumanPlayer or RandyPlayer, for example) will be executed at runtime.

In summary, we allow Java to handle all players (machine or human) in a polymorphic manner while still using the correct action based on the actual object type by using pickNextMove(...) in the Player superclass. 


3. Explain in detail (using the terminology we have discussed in class) how it is possible that neither our main method nor our TicTacToeGame class need change at all when adding new Player types to our game. Your discussion must include an explanation of how the single call to pickNextMove in TicTacToeGame.doNextTurn works correctly no matter whose turn it is or which types the players are.

The reason why nither our main or TicTacToeGame class needs to change at all when adding our new players in our game and is still working because it is all possible due to polymorphism, inheritance and dynamic distpatch which we learnt in class as a core principle to perform our object oriented programming.

First of all lets talk about where polymorphism and abstraction is used:

In our code, the player class is an abstract class that clarifies the basic principles and behaviours of any players that mignt be human player or any advanced or computer player like randy or omola as it specifies the abstract method in teh code i.e. "public abstract Position pickNextMove(Board board);". This method must be used by all of the subclasses used which are HumanPlayer, RandyPlayer and OmolaPlayer. We can name the players and the player variable can hold any object that is a subclass of player and the example is: Player playerX = new HumanPlayer("Amrita");
Player playerO = new RandyPlayer("Ram"); .

Secondly lets talk about the Dynamic Dispatch that has been used in doNextTurn in TicTacToeGame class:

var whoseTurn = switch(turnToken) {
case X -> playerX;
case O -> playerO;
};
var pos = whoseTurn.pickNextMove(board);

In the above code given, we know that even if whoseTurn is declared as a player type, java use dynamic dispatch method to be sure what kind and which version of pickNextMove() has to be called at runtime on the basis of actual object which are HumanPlayer, OmolaPlayer or RandyPlayer. This means:

It may read input from the console if it's HumanPlayer.

It may choose a free cell at random if it's RandyPlayer.

It may have a plan if it's OmolaPlayer.
 
This is how the TicTacToeGame class doesn't need to worry about which type of player is playing. It just calls pickNextMove() and automatically the correct logic(move) runs.

In conclusion, our primary game logic will manage all player types in the same way since we use abstraction and polymorphism.  The TicTacToeGame just asks the player for a move using pickNextMove, and Java makes sure the correct method runs at runtime. It doesn't need to know or care how each player chooses their move.

