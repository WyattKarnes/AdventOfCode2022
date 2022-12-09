import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 extends AdventOfCodeDay{
    public Day4(File input) {
        super(input);
    }

    @Override
    public void solve() throws FileNotFoundException {
        Scanner scr = new Scanner(input);

        // I ONLY WANT THE INTS, DANG IT!
        scr.useDelimiter(",|\n|-");

        int overlaps = 0;

        while(scr.hasNext()){

            // Range 1
            int x1 = Integer.parseInt(scr.next());
            int x2 = Integer.parseInt(scr.next().trim()); // shouldn't have to trim, but there seems to be a hidden character.

            // Range 2
            int y1 = Integer.parseInt(scr.next());
            int y2 = Integer.parseInt(scr.next().trim()); // shouldn't have to trim, but there seems to be a hidden character.

            // Part 1 Solution
            /*
            if(x1 <= y1 && x2 >= y2){
                System.out.println("The first pair fully contains the second pair.");
                overlaps++;
            } else if(y1 <= x1 && y2 >= x2){
                System.out.println("The second pair fully contains the first pair.");
                overlaps++;
            }
             */

            // Part 2 Solution
            if(x1 <= y1 && x2 >= y1){
                System.out.println("The first pair overlaps the second pair.");
                overlaps++;
            } else if(y1 <= x1 && y2 >= x1){
                System.out.println("The second pair overlaps the first pair.");
                overlaps++;
            }

        }


        print("Found " + overlaps + " complete overlaps.");
        scr.close();
    }
}
