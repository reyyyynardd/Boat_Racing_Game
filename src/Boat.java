public class Boat{
	
    private int playerno;
    private String playerName;
    private int location;

    private int turns;
    
    public Boat() {
    	
    }
    
    public Boat(String name,int no){
    	playerName = name;
        playerno = no;
        location = 0;
        turns = 0;
    }

    //setter/getters
    public void setPlayerNo(int no) {
    	playerno = no;
    }
    
    public int getPlayerNo() {
        return playerno;
    }

    public void move(int move) {
        location += move;
    }

    public int getLocation() {
        return location;
    }
    
    public void setPlayerName(String name) {
    	playerName = name;
    }
    
    public String getPlayerName() {
    	return playerName;
    }

    public void addTurns(int t) {
        turns += t;
    }

    public int getTurns() {
        return turns;
    }
}
