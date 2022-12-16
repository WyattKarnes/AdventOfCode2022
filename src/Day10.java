import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day10 extends AdventOfCodeDay {

    private int cycles = 1;
    private int x = 1;
    private int sigStr = 0;
    private int[] buffers = new int[4];
    private int curBuff = 2;

    public Day10(File input) {
        super(input);
    }

    @Override
    public void solve() throws FileNotFoundException {
        Scanner scr = new Scanner(input);

        while(cycles <= 5){

            print("");
            print("Cycle: " + cycles);

            String s = "";
            if(scr.hasNextLine()){
                s = scr.nextLine();
            }

            startCycle(s);
            duringCycle();
            afterCycle();


        }


    }

    private void startCycle(String s){
        // read input
        if(s.isEmpty()){
            return;
        }

        if(!s.equals("noop")){

            // grab the number to add
            int val = Integer.parseInt(s.substring(5));

            // queue up the add command
            buffers[2] = val;
            curBuff = 3;

        }

        print("Buffer Contents During: ");
        for (int i = 0; i < buffers.length; i++) {
            System.out.println(" " + i + ": " + buffers[i]);
        }

    }

    private void duringCycle(){

        print("Register During Cycle " + cycles +": " + x);

    }

    private void afterCycle(){
        buffers[0] = buffers[1];
        buffers[1] = buffers[2];
        buffers[2] = buffers[3];
        buffers[3] = 0;

        if(buffers[0] != 0){
            x += buffers[0];
            buffers[0] = 0;
        }

        print("Register After Cycle " + cycles +": " + x);

        print("Buffer Contents After: ");
        for (int i = 0; i < buffers.length; i++) {
            System.out.println(" " + i + ": " + buffers[i]);
        }
        cycles++;
    }

    private void oldIdea() throws FileNotFoundException {
        Scanner scr = new Scanner(input);

        int cycles = 1;
        int x = 1;
        int sigStr = 0;
        // programming a clock circuit
        // addX takes two cycles and then the X register is increased by the value V (which can be neg). This happens AFTER the second cycle.
        // noop takes only one cycle, and it does nothing
        // signal strength is the cycle number * value of X register, and is updated starting at cycle 20 and then every 40 cycles thereafter

        int[] buffer = new int[4];
        while (scr.hasNextLine()) {

            // cycle begins
            print("");
            print("Cycle: " + cycles);

            if (scr.hasNextLine()) {

                String s = scr.nextLine();

                // at the beginning of the cycle, an instruction "begins"
                print("Processing instructions... ");
                if (!s.equals("noop")) {
                    int val = Integer.parseInt(s.substring(5));
                    print("Begin add instruction: " + val);

                    // find an open buffer
                    buffer[2] = val;
                    print("Stored " + val + " in buffer " + 2);
                } else {
                    print("No-Op instruction.");
                }


                if (cycles == 20 || cycles % 40 == 20) {
                    sigStr += cycles * x;
                    print("Signal Strength: " + sigStr);
                }


            }

            print("During Cycle " + cycles + " Register Contains: " + x);
            cycles++;
            // cycle ends here

            print("Buffer Contents: ");
            for (int i = 0; i < buffer.length; i++) {
                System.out.println(" " + i + ": " + buffer[i]);
            }

            // after a cycle ends, instructions "finish" execution
            if (buffer[0] != 0) {
                print("Finish Add Instruction: Add " + buffer[0] + " to register.");
                x += buffer[0];
                buffer[0] = 0;
            }


            buffer[0] = buffer[1];
            buffer[1] = buffer[2];
            buffer[2] = buffer[3];
            buffer[3] = 0;

            print("After Cycle " + (cycles - 1) + " Register Contains: " + x);


            print("Buffer Contents: ");
            for (int i = 0; i < buffer.length; i++) {
                System.out.println(" " + i + ": " + buffer[i]);
            }


        }

        print(sigStr);
    }
}
