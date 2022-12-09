import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        String[] paths = new String[25];
        File[] files = new File[25];


        for (int i = 0; i < 25; i++) {
            paths[i] = "res/day"+(i+1)+".txt";
            files[i] = new File(paths[i]);
        }

        AdventOfCodeDay[] days = {
                new Day1(files[0]),
                new Day2(files[1]),
                new Day3(files[2]),
                new Day4(files[3]),
                new Day5(files[4]),
                new Day6(files[5]),
                new Day7(files[6]),
                new Day8(files[7])
        };

        try {
            days[7].solve();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        /*
        for (AdventOfCodeDay day : days) {
            try {
                day.solve();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

         */
    }

}
