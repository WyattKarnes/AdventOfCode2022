import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day3 extends AdventOfCodeDay{
    private HashMap<Character, Integer> values = new HashMap<>();

    public Day3(File input) {
        super(input);
    }

    private void initValues(){
        String a = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < a.length(); i++) {
            values.put(a.charAt(i), i+1);
        }
    }

    private  int part1(String r){

        // cut the string in half to find the separate compartments
        String s1 = r.substring(0, r.length()/2);
        String s2 = r.substring(r.length()/2);

        // find the common letter(s) between the compartments
        HashMap map = new HashMap<Character, Integer>();

        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i), 0);
        }

        for (int i = 0; i < s2.length(); i++) {
            if(map.containsKey(s2.charAt(i))){
                print("The common character is " + s2.charAt(i) + " which has a value of " + values.get(s2.charAt(i)));
                // add that letter's priority value to the sum
                return values.get(s2.charAt(i));
            }
        }

        return 0;
    }
    private  int part2(String a, String b, String c){

        // find the common letter(s) between the backpacks
        HashMap map1 = new HashMap<Character, Integer>();

        HashMap map2 = new HashMap<Character, Integer>();

        for (int i = 0; i < a.length(); i++) {
            map1.put(a.charAt(i), 0);
        }

        for (int i = 0; i < b.length(); i++) {
            map2.put(b.charAt(i), 0);
        }


        for (int i = 0; i < c.length(); i++) {
            if(map1.containsKey(c.charAt(i)) && map2.containsKey(c.charAt(i))){
                print("The badge item is " + c.charAt(i) + " which has a value of " + values.get(c.charAt(i)));
                // add that letter's priority value to the sum
                return values.get(c.charAt(i));
            }
        }

        return 0;

    }

    @Override
    public void solve() throws FileNotFoundException {

        Scanner scr = new Scanner(input);
        initValues();

        // main work

        int sum = 0;

        // Part 1
        /*
        while(scr.hasNextLine()){
            sum += part1(scr.nextLine());
        }
        */


        while(scr.hasNextLine()){
            sum += part2(scr.nextLine(), scr.nextLine(), scr.nextLine());
        }



        print(sum);
        scr.close();
    }
}
