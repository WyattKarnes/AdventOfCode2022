import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Day5 extends AdventOfCodeDay {

    private static Stack<Character>[] stacks = new Stack[9];

    public Day5(File input) {
        super(input);
    }
    private Stack reverseStack(Stack in) {
        Stack out = new Stack<>();

        while (!in.isEmpty()) {
            out.push(in.pop());
        }

        /*
        while(out.isEmpty()){
            in.push(out.pop());
        }


         */
        return out;
    }
    private void buildStacks(String s) {
        System.out.println(s);
        // this indicates that we reached the bottom of the stack list

        int stackNum = 0;

        for (int i = 1; i < s.length(); i += 4) {

            if (s.charAt(i) != ' ') {
                // put the characters on the correct stacks
                stacks[stackNum].push(s.charAt(i));
            }

            stackNum++;

        }
    }

    private void part1(String s) {
        // read a line of input and break it into useable info
        String[] commands = s.split(" ");

        int amount = Integer.parseInt(commands[1]);
        int from = Integer.parseInt(commands[3]);
        ;
        int to = Integer.parseInt(commands[5]);
        ;

        // perform the line of input
        for (int i = 0; i < amount; i++) {

            if (stacks[from - 1].isEmpty()) {
                break;
            }

            stacks[to - 1].push(stacks[from - 1].pop());
        }
    }
    private void part2(String s) {
        // read a line of input
        String[] commands = s.split(" ");

        int amount = Integer.parseInt(commands[1]);
        int from = Integer.parseInt(commands[3]);
        ;
        int to = Integer.parseInt(commands[5]);
        ;

        Stack<Character> move = new Stack<>();

        // perform the line of input

        // put all of the crates to move into the arms of the CM 9001
        for (int i = 0; i < amount; i++) {

            if (stacks[from - 1].isEmpty()) {
                System.out.println("Stack number " + from + " is empty.");
                break;
            }

            System.out.println("adding " + stacks[from - 1].peek() + " to move");
            move.push(stacks[from - 1].pop());
        }

        // drop the crates from the CM 9001 onto the correct stack
        for (int i = 0; i < amount; i++) {

            if (move.isEmpty()) {
                break;
            }

            System.out.println("adding " + move.peek() + " to stack no. " + to);
            stacks[to - 1].push(move.pop());
        }
    }

    @Override
    public void solve() throws FileNotFoundException {

        Scanner scr = new Scanner(input);

        // initialize Stack array
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new Stack<>();
        }


        // Build stacks with input
        while (scr.hasNextLine()) {
            String s = scr.nextLine();

            if (s.charAt(1) == '1') {
                break;
            }

            buildStacks(s);
        }

        // reverse the stacks for correct ordering.
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = reverseStack(stacks[i]);
        }

        // once all stacks are populated:

        // skip a line
        scr.nextLine();

        // do the commands from input file
        while (scr.hasNextLine()) {
            String s = scr.nextLine();

            if (s.isEmpty()) {
                break;
            }

            // swap with part1(s) to get other answer
            part2(s);
        }


        // output the top of each stack to get the answer.
        for (int i = 0; i < stacks.length; i++) {

            if (stacks[i].isEmpty()) {
                System.out.print("");
            } else {
                print(stacks[i].pop());
            }

        }

        scr.close();
    }
}

