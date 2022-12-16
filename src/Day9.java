import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day9 extends AdventOfCodeDay {

    Knot[] knots;

    public Day9(File input) {
        super(input);
    }

    @Override
    public void solve() throws FileNotFoundException {

        Scanner scr = new Scanner(input);

        knots = new Knot[10];

        for (int i = 0; i < knots.length; i++) {
            knots[i] = new Knot();
        }

        while (scr.hasNextLine()) {

            String s = scr.nextLine();

            char dir = s.charAt(0);
            int mov = Integer.parseInt(s.substring(2));

            while (mov > 0) {
                moveHead(dir);


                for (int i = 1; i < knots.length; i++) {
                    updateKnot(knots[i], knots[i - 1]);
                }

                mov--;
            }

        }

        print(knots[9].visitedPos.keySet().size());

    }

    private void moveHead(char dir) {
        // move the head in the given direction
        switch (dir) {
            case 'R' -> knots[0].moveRight();

            case 'L' -> knots[0].moveLeft();

            case 'U' -> knots[0].moveUp();

            case 'D' -> knots[0].moveDown();
        }
    }

    private void updateKnot(Knot tail, Knot head) {
        int colDiff = head.curPos[0] - tail.curPos[0];
        int rowDiff = head.curPos[1] - tail.curPos[1];

        if (Math.abs(colDiff) > 1 || Math.abs(rowDiff) > 1) {

            tail.curPos[0] += (int) Math.signum(colDiff);
            tail.curPos[1] += (int) Math.signum(rowDiff);
        }

        tail.addPos();
    }

    private class Knot {

        HashMap<String, Integer> visitedPos = new HashMap();

        Integer[] curPos = {0, 0};

        Knot() {
            addPos();
        }

        void addPos() {
            if (visitedPos.containsKey(curPos[0] + " " + curPos[1])) {
                return;
            }

            visitedPos.put(curPos[0] + " " + curPos[1], 1);
        }

        void moveRight() {
            curPos[0]++;
        }

        void moveLeft() {
            curPos[0]--;
        }

        void moveUp() {
            curPos[1]--;
        }

        void moveDown() {
            curPos[1]++;
        }

    }
}
