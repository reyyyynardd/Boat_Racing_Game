import java.util.Random;

public class Dice {
    private Random rand;

    public Dice(){
        rand = new Random();
    }
    public int rolldice(){
        return 1+(rand.nextInt(6));
    }
}
