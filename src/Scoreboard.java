import java.io.*;
import java.util.*;

public class Scoreboard {
    private StringTokenizer token;
    private HashMap<String, Integer> scores;

    public Scoreboard() throws IOException {
        scores = new HashMap<>();
    }

    public void retrieveScore() throws IOException {

        FileReader reader = new FileReader("TopScore.txt");
        BufferedReader bf = new BufferedReader(reader);

        String st = bf.readLine();

        while((st=bf.readLine())!= null){
            token = new StringTokenizer(st);
            String name = token.nextToken();
            int score = Integer.parseInt(token.nextToken());

            scores.put(name, score);
        }
        reader.close();
    }

    public void addScore(String name, int score){
        scores.put(name, score);
    }

    public void printScore(){

        HashMap<String, Integer> dupScores = new HashMap<>();

        dupScores = scores;

        ArrayList<Integer> temp = new ArrayList<>();
        for (int n: scores.values()){
            temp.add(n);
        }
        Collections.sort(temp);

        System.out.println("\nScoreboard: Top 5 Players\n=========================\nName\t\tScore\n=========================");
        int counter = 0;
        for ( int n: temp){
            for(Map.Entry<String, Integer> entry: dupScores.entrySet()) {
                if(entry.getValue() == n) {
                    System.out.println(entry.getKey() + "\t\t" + n);
                    dupScores.remove(entry.getKey(), n);
                    counter +=1;
                    break;
                }
            }
            if (counter == 5){
                break;
            }
        }
    }

    public void insertScore() throws IOException {

        FileWriter writer = new FileWriter("TopScore.txt");
        BufferedWriter bw = new BufferedWriter(writer);

        ArrayList<Integer> temp = new ArrayList<>();
        for (int n: scores.values()){
            temp.add(n);
        }
        Collections.sort(temp);

        bw.write("Player\tScores\n");
        int counter = 0;
        for ( int n: temp){
            for(Map.Entry<String, Integer> entry: scores.entrySet()) {
                if(entry.getValue() == n) {
                    bw.write(entry.getKey() + "\t" + n + "\n");
                    scores.remove(entry.getKey(), n);
                    counter += 1;
                    break;
                }
            }
            if (counter == 5){
                break;
            }
        }
        bw.close();
    }
}
