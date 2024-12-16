import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Scanner;

public class River{
    private ArrayList<RiverObjects> objectlist;
    Scanner input = new Scanner(System.in);
    Boat player1 = new Boat();
    Boat player2 = new Boat();
    private Dice dice;
 
    public River() {
        objectlist = new ArrayList<RiverObjects>();
        dice = new Dice();
        Random rand = new Random();
        int no_current = rand.nextInt(10);
        int no_traps = rand.nextInt(10);
        int no_rocks = rand.nextInt(7);

        for (int i=0; i<no_current; i++) {
            objectlist.add(new Current());
        }
        for (int i=0; i<no_traps; i++) {
            objectlist.add(new Trap());
        }
        for (int i=0; i<no_rocks; i++) {
            objectlist.add(new Rocks());
        }
    }
    
    public void player1Name() {
    	player1.setPlayerNo(1);
    	System.out.print("Enter Player 1's Name: ");
        String pname1 = input.nextLine();
        String result = pname1.replaceAll("\\s", "");
        player1.setPlayerName(result);
    }

    public void player2Name() {
    	player2.setPlayerNo(2);
    	System.out.print("Enter Player 2's Name: ");
        String pname2 = input.nextLine();
        String result = pname2.replaceAll("\\s", "");
        player2.setPlayerName(result);
    }

    public void player1turn() throws InterruptedException {
        int rollnum = dice.rolldice();
        player1.move(rollnum);
        player1.addTurns(1);
        System.out.println(player1.getPlayerName() + " rolled a " + rollnum);
        for(RiverObjects r: objectlist) {
            if (r.getLocation() == player1.getLocation()) {
                if (r instanceof Current) {
                    int move = ((Current)r).getStrength();
                    player1.move(move);
                    System.out.println(player1.getPlayerName() + " received a boost forward by " + move + " from a current!");
                }
                if (r instanceof Trap) {
                    int move = ((Trap)r).getStrength();
                    if (move > player1.getLocation()){
                        move = player1.getLocation();
                    }
                    player1.move(-move);
                    System.out.println(player1.getPlayerName() + " got stuck in a trap and is set back by " + move);
                }
                if (r instanceof Rocks) {
                	System.out.printf("%s is currently at position %d\n", player1.getPlayerName(), player1.getLocation());
                    System.out.println(player1.getPlayerName() + " got stuck in a rock and will skip the next turn");
                    System.out.println(" ");
                    Game.displayOutline();
                    display();
                    Game.displayOutline();                   
                    System.out.println(" ");
                    Game.pressEnterToContinue();
                    player2turn();
                    System.out.println(" ");
                    Game.displayOutline();
                    display();
                    Game.displayOutline();
                    System.out.println(" ");
                    pause();
                    System.out.println(player1.getPlayerName() + "'s turn is skipped");
                    break;
                }
            }
        }
        if (player1.getLocation()<=100){
            System.out.printf("%s is currently at position %d\n", player1.getPlayerName(), player1.getLocation());
            System.out.println(" ");
        }
    }

    public void player2turn() throws InterruptedException {
        int rollnum = dice.rolldice();
        player2.move(rollnum);
        player2.addTurns(1);
        System.out.println(player2.getPlayerName() + " rolled a " +rollnum);
        for(RiverObjects r: objectlist) {
            if (r.getLocation() == player2.getLocation()) {
                if (r instanceof Current) {
                    int move = ((Current)r).getStrength();
                    player2.move(move);
                    System.out.println(player2.getPlayerName() + " received a boost forward by " + move + " from a current!");
                }
                if (r instanceof Trap) {
                    int move = ((Trap)r).getStrength();
                    if (move > player2.getLocation()){
                        move = player2.getLocation();
                    }
                    player2.move(-move);
                    System.out.println(player2.getPlayerName() + " got stuck in a trap and is set back by " + move);
                }
                if (r instanceof Rocks) {
                	System.out.printf("%s is currently at position %d\n", player2.getPlayerName(), player2.getLocation());
                	System.out.println(player2.getPlayerName() + " got stuck in a rock and will skip their next turn");
                	System.out.println(" ");
                    Game.displayOutline();
                    display();
                    Game.displayOutline(); 
                    pause();
                    System.out.println(" ");
                    player1turn();
                    System.out.println(" ");
                    Game.displayOutline();
                    display();
                    Game.displayOutline();
                    System.out.println(" ");
                    pause();
                    System.out.println(player2.getPlayerName() + "'s turn is skipped");
                    break;
                }
            }
        }
        if (player2.getLocation()<=100){
            System.out.printf("%s is currently at position %d\n", player2.getPlayerName(), player2.getLocation());
            System.out.println(" ");
        }
    }

    public void display() {
        for(int i=0; i<100; i++) {
            boolean isOccupied = false;

            if (player1.getLocation() == player2.getLocation()) {
                if (player1.getLocation() == i) {
                    isOccupied = true;
                    System.out.print("½");
                }
            }
            else {
                if (player1.getLocation() == i) {
                    isOccupied = true;
                    System.out.print(player1.getPlayerNo());
                }
                if (player2.getLocation() == i) {
                    isOccupied = true;
                    System.out.print(player2.getPlayerNo());
                }
            }

            for(RiverObjects r: objectlist) {
                if (r.getLocation() == i && r.getLocation() != player1.getLocation() && r.getLocation() != player2.getLocation()) {
                    isOccupied = true;
                    System.out.print(r.getSymbol());
                }
            }
            if (!isOccupied) {
                System.out.print(" ");
            }
        }
        System.out.print("\n");
    }
    
    public static void pause() throws InterruptedException {
        System.out.println("Skipping turn...");
    	TimeUnit.SECONDS.sleep(3);
        for (int i = 1; i <= 16; i++){
            System.out.print("\b");
        }
    }
}
