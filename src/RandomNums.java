import java.util.HashSet;
import java.util.Set;
import java.util.Random;

class RandomNums {
    private Set<Integer> usedNums;

    public RandomNums(){
        usedNums = new HashSet<Integer>();
    }

    public int getRandom()
    {
        Random rand = new Random();
        int num = 1 + rand.nextInt(99);
        while(usedNums.contains(num)) {
            num = 1 + rand.nextInt(99);
        }
        usedNums.add(num);
        return num;
    }

    public void reset()
    {
        usedNums.clear();
    }
}