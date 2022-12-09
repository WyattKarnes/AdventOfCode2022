import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 extends AdventOfCodeDay{


    public Day1(File input) {
        super(input);
    }

    @Override
    public void solve() throws FileNotFoundException {

        Scanner scr = new Scanner(input);

        List<Integer> elves = new ArrayList<>();

        List<String> strings = new ArrayList<>();


        // compile a list of Strings.

        while (scr.hasNextLine()) {

            // read in a line from the scanner
            String tmp = scr.nextLine();

            // check if the line was NOT empty
            if (!tmp.isEmpty()) {

                // add non-empty strings to the list
                strings.add(tmp);

            } else {

                // if we find an empty string, we have finished finding all calories for that particular elf.
                // total them up, and create a new Elf object that stores the number of calories.
                int sum = 0;

                for (int i = 0; i < strings.size(); i++) {
                    sum += Integer.parseInt(strings.get(i));
                }

                // store the new elf
                elves.add(sum);
                // clear strings for the next run
                strings.clear();
            }

        }

        // at this point, all elves should have their sums, we just need to find the most calorie packed elf.
        int biggest = 0;

        // Part 2 of the AoC question asks us to find the total calories of the top 3 elves.
        // to accomplish this, one solution is
        List<Integer> bigBoys = new ArrayList<>();
        bigBoys.add(elves.get(biggest));

        for (int i = 1; i < elves.size(); i++) {



            if(elves.get(i) > elves.get(biggest)){

                /*
                print("Elf no."
                        + (biggest+1)
                        + " who has "
                        + elves.get(biggest)
                        + " calories on him "
                        + " has been surpassed by elf no. "
                        + (i+1)
                        + " who has "
                        + elves.get(i)
                        + " calories on him.");
                 */

                biggest = i;
                bigBoys.add(elves.get(biggest));
            }
        }

        // Answer to part 1
        print("The elf carrying the most calories is elf no. " + (biggest+1));

        // we now look at the list of topG elves, and sum up the best 3
        int top3Total = 0;

        for (int i = bigBoys.size()-3; i < bigBoys.size(); i++) {
            top3Total += bigBoys.get(i);
        }

        print("The top 3 elves are carrying " + top3Total + " calories between them.");

        scr.close();
    }

}



