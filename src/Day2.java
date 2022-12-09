import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 extends AdventOfCodeDay{

    private HashMap<String, Integer> results = new HashMap<>();
    private HashMap<String, String> strategy = new HashMap<>();

    public Day2(File input) {
        super(input);
    }

    private void initMaps(){
        // A = rock
        // B = paper
        // C = scissors

        // X = rock
        // Y = paper
        // Z = scissors

        // for when they pick rock
        results.put("A X", 4);
        results.put("A Y", 8);
        results.put("A Z", 3);

        // for when they pick paper
        results.put("B X", 1);
        results.put("B Y", 5);
        results.put("B Z", 9);

        // for when they pick scissors
        results.put("C X", 7);
        results.put("C Y", 2);
        results.put("C Z", 6);

        // for when they pick Rock
        strategy.put("A X", "A Z");
        strategy.put("A Y", "A X");
        strategy.put("A Z", "A Y");

        // for when they pick paper
        strategy.put("B X", "B X");
        strategy.put("B Y", "B Y");
        strategy.put("B Z", "B Z");

        // for when they pick scissors
        strategy.put("C X", "C Y");
        strategy.put("C Y", "C Z");
        strategy.put("C Z", "C X");
    }

    private void part1(Scanner scr){

        int totalScore = 0;

        while(scr.hasNextLine()){

            String s = scr.nextLine();
            totalScore += results.get(s);

        }

        print(totalScore);
        scr.close();
    }

    private void part2(Scanner scr){

        int totalScore = 0;

        while(scr.hasNextLine()){

            String s = scr.nextLine();
            s = strategy.get(s);
            totalScore += results.get(s);

        }

        print(totalScore);
        scr.close();
    }

    @Override
    public void solve() throws FileNotFoundException {
        Scanner scr = new Scanner(input);
        initMaps();
        part1(scr);
        part2(scr);
    }
}
