import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Scanner input;
    private River river;
    private Menu menu;

    private Scoreboard scoreboard;

    public Game() {
        try {
            scoreboard = new Scoreboard();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        input = new Scanner(System.in);
        river = new River();
        menu = new Menu();
    }

    public void start() throws InterruptedException{
        //Display Menu
        menu.diplay();
        int choice = input.nextInt();
        //Play Game
        if (choice == 1){
            try {
                scoreboard.retrieveScore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scoreboard.printScore();
            System.out.println(" ");
        	river.player1Name();
        	river.player2Name();
            System.out.println(" ");
            System.out.println("Game has started! May the best sailor win!");
            while (river.player1.getLocation() < 100 && river.player2.getLocation() < 100) {
                displayOutline();
                river.display();
                displayOutline();
                if (river.player2.getLocation() >= 100){
                    break;
                }
                pressEnterToContinue();
                river.player1turn();
                displayOutline();
                river.display();
                displayOutline();
                if (river.player1.getLocation() >= 100){
                    break;
                }
                pressEnterToContinue();
                river.player2turn();
            }
            if (river.player1.getLocation() >= 100){
                System.out.printf("%s has won with %d turns !",river.player1.getPlayerName(), river.player1.getTurns());
                scoreboard.addScore(river.player1.getPlayerName(), river.player1.getTurns());
            }
            if (river.player2.getLocation() >= 100){
                System.out.printf("%s has won with %d turns !",river.player2.getPlayerName(), river.player2.getTurns());
                scoreboard.addScore(river.player2.getPlayerName(), river.player2.getTurns());
            }
            try {
                scoreboard.retrieveScore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                scoreboard.insertScore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            pressEnterToContinue();
            System.out.println(" ");
            menu.diplay();
        }
        //Print Instruction
        else if (choice == 2) {
            menu.instruction();
            System.out.println(" ");
            pressEnterToContinue();
            start();
        }
        //Print Top Score
        else if (choice == 3) {
            try {
                scoreboard.retrieveScore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scoreboard.printScore();
            System.out.println(" ");
            pressEnterToContinue();
            start();
        }
        //Exit Game
        else if (choice == 4) {
            menu.exit();
        }
    }

    public static void displayOutline() {
        for (int i = 0; i < 100; i++ ){
            System.out.print("=");
        }
        System.out.print("\n");
    }
 
    public static void pressEnterToContinue()
    {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
}
