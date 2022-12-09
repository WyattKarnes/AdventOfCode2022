import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 extends AdventOfCodeDay{

    List<Directory> directories = new ArrayList<>();

    public Day7(File input) {
        super(input);
    }

    private void part1(){
        // total up the directories to see who to delete
        int sum = 0;

        for (Directory dir: directories) {
            if(dir.getSize() < 100000){
                sum += dir.getSize();
            }
        }

        print("By deleting the chosen directories you can save " + sum + " space.");
    }

    private void part2(){

        // part 2
        int freeSpace = 70000000 - directories.get(0).getSize();
        int spaceNeeded = 30000000 - freeSpace;

        // we have 25,640,133 gb free space
        // we need to free up 4,359,867 gb
        print("You have " + freeSpace + " free space.");
        print("You need to free up " + spaceNeeded + " to perform the update.");

        int smallest = directories.get(0).getSize();
        for (Directory dir: directories) {
            if(dir.getSize() <= smallest && dir.getSize() >= spaceNeeded){
                smallest = dir.getSize();
            }
        }

        print("The smallest directory you can get away with deleting has a size of " + smallest);
    }

    @Override
    public void solve() throws FileNotFoundException {
        Scanner scr = new Scanner(input);

        // add the root
        Directory root = new Directory("/", null);
        Directory cur = root;
        directories.add(cur);

        // skip the first two lines of input, they don't matter.
        scr.nextLine();
        scr.nextLine();

        // build the file system
        while(scr.hasNext()){

            String s = scr.next();

            // handle the finding of a new directory
            if(s.equals("dir")){
                String dirName = scr.next();
                //print("New Directory found in " + cur.name + " called " + dirName);
                Directory temp = new Directory(dirName, cur);
                cur.subDirs.add(temp);
                directories.add(temp);
            }

            // handle the changing of a directory
            if(s.equals("cd")){

                String next = scr.next();

                // handle going out
                if(next.equals("..")){
                    //print("moving up to directory " + cur.parent.name);
                    cur = cur.parent;
                } else {
                    // handle going in
                    //print("moving in to directory " + next);
                    cur = cur.subDirs.get(cur.subDirs.indexOf(new Directory(next, cur)));
                }

            }

            // handle the finding of a file
            if(Character.isDigit(s.charAt(0))){
                String fileSize = s;

                //print("found a file of size " + fileSize + ", which will be added to directory " + cur.name);
                cur.files.add(Integer.parseInt(fileSize));
            }

        }

        part1();
        part2();
    }

    private class Directory {

        String name;

        Directory parent;
        List<Integer> files;
        List<Directory> subDirs;


        Directory(String name, Directory parent) {
            this.name = name;
            this.parent = parent;

            files = new ArrayList<>();
            subDirs = new ArrayList<>();
        }

        int getSize(){

            int total = 0;

            for (int i = 0; i < files.size(); i++) {
                total += files.get(i);
            }

            for (int i = 0; i < subDirs.size(); i++) {
                total += subDirs.get(i).getSize();
            }

            return total;
        }

        @Override
        public String toString(){
            return this.name;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Directory && ((Directory) obj).name.equals(this.name)){
                return true;
            }

            return false;
        }
    }
}
