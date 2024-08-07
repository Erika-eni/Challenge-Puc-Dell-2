# Challenge-Puc-Dell-2
---Description
This is a competition game between teams where teams accumulate points through different actions during matches. Matches can end in a team's victory or a draw, resulting in a "Grusht" to decide the winner. This project includes functionality to save, load, and delete saved games.
---Authors
Érila Fedrigo
---Project Structure
Team: Represents a participating team in the game.
Match: Represents a match between two teams.
Phase: Represents a tournament phase, containing several matches.
Game: Contains the main logic of the game, including the main menu and the management of phases and matches.
SaveGame: Responsible for saving, loading, and deleting saved games.
GameData: Auxiliary class to store team and match data during saving/loading.
---How to Play
Clone the repository to your local machine.
Compile and run the Game class.
Follow the instructions on the terminal to:
Enter the number of teams (must be an even number between 8 and 16).
Enter the name, war cry, and foundation year of each team.
In the main menu, you can:
Select a match to play.
Save the current game state.
Load a saved game.
Delete a saved game.
---Game Rules
-Team Actions
Blot: Adds 5 points to the team.
Plif: Subtracts 1 point from the team.
Advrungh: Subtracts 10 points from the team.
-Grusht
When a match ends in a draw, a "Grusht" occurs, where teams compete in an additional challenge to decide the winner. The winner of the "Grusht" receives an additional 3 points.
-End of Match
A match ends when one team's points reach zero. The team with remaining points is the winner. In case of a draw, a "Grusht" occurs.
---How to Save, Load, and Delete Games
-Save
In the main menu, select [S] Save game.
Enter the name of the file to save the game.
-Load
In the main menu, select [L] Load game.
Enter the name of the saved file to load the game.
-Delete
In the main menu, select [D] Delete game.
Enter the name of the saved file to delete the game.
---Requirements
Java 8 or higher
Java Development Environment (JDK)
