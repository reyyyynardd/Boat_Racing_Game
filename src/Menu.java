public class Menu {
    public void diplay(){
        System.out.print("Welcome to Boat Racing Game\n" +
                "=============================================================================\n" +
                "[1] Play the Game\n" +
                "[2] Read the Instructions\n" +
                "[3] View Top Scores\n" +
                "[4] Exit\n" +
                "=============================================================================\n" +
                "Enter option: ");
    }
    public void instruction(){
        System.out.print("\n=============================================================================\n" +
                "This Boat Racing Game is a two player game, each player will take turns to\n" +
                "roll a dice to determine the number of steps their boats will move.\n" +
                "The river contains various obstacles and power-ups that each player\n" +
                "can encounter if landed on, traps (#) will set the player back by a\n" +
                "random number of steps, currents (≈) will push the player forward by a\n" +
                "random number of steps and rocks (R) will skip one round for the player.\n" +
                "=============================================================================\n");
    }
    
    public void exit(){
        System.out.print("\nThanks for playing!");
    }
}
