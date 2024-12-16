public class Rocks extends RiverObjects{

    private RandomNums randomnum;
    
    public Rocks(){
        super("R");
        randomnum = new RandomNums();
        setLocation(randomnum.getRandom());
    }
}
