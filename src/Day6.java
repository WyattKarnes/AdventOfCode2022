import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class Day6 extends AdventOfCodeDay{
    public Day6(File input) {
        super(input);
    }

    private void part1(String s){

        TreeSet<Character> chars = new TreeSet<>();

        boolean markerFound = false;

        // ptr keeps track of the starting position for the next set of four.
        int ptr = 0;

        while(!markerFound){
            // attempt to add four characters to the tree

            for (int i = 0; i < 4; i++) {

                // if a duplicate is found, clear the tree
                if(!chars.add(s.charAt(ptr + i))){
                    chars.clear();
                    break;
                }

            }

            // if we made it this far, and there are four chars in the tree, we win!
            if(chars.size() >= 4){
                markerFound = true;
                print("Marker found at " + (ptr+4));
            }

            // start over on next set of four (increment once)
            ptr++;
        }
    }

    private void part2(String s){

        TreeSet<Character> chars = new TreeSet<>();

        boolean markerFound = false;

        // ptr keeps track of the starting position for the next set of four.
        int ptr = 0;

        while(!markerFound){
            // attempt to add four characters to the tree

            for (int i = 0; i < 14; i++) {

                // if a duplicate is found, clear the tree
                if(!chars.add(s.charAt(ptr + i))){
                    chars.clear();
                    break;
                }

            }

            // if we made it this far, and there are four chars in the tree, we win!
            if(chars.size() >= 14){
                markerFound = true;
                print("Message found at " + (ptr+14));
            }

            // start over on next set of four (increment once)
            ptr++;
        }
    }

    @Override
    public void solve() throws FileNotFoundException {

        Scanner scr = new Scanner(input);
        String s = scr.nextLine();

        part1(s);
        part2(s);

    }
}
