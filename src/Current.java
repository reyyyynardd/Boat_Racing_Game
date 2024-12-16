import java.util.Random;

public class Current extends RiverObjects{
    private int strength;

    private RandomNums randomnum;

    public Current() {
        super("≈");
        Random rand = new Random();
        randomnum = new RandomNums();
        strength = 1 + rand.nextInt(5);
        setLocation(randomnum.getRandom());
    }

    public int getStrength() {
        return strength;
    }
}
