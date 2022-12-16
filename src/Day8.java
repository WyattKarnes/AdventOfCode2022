import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 extends AdventOfCodeDay{

    public Day8(File input) {
        super(input);
    }

    @Override
    public void solve() throws FileNotFoundException {
        Scanner scr = new Scanner(input);

        List<ArrayList<Tree>> trees = new ArrayList<>();
        int visibleTrees = 0;
        int row = 0;

        while(scr.hasNextLine()){
            // check if a string has been passed from before.
            // if not, grab a new line.
            String s1 = scr.nextLine();



            // Initialize a new List of Trees
            trees.add(new ArrayList<>());

            // Loop over s1, and check for all adjacent visibilities.
            for (int i = 0; i < s1.length(); i++) {

                int t = Character.getNumericValue(s1.charAt(i));

                boolean def = ( row == 0 ||
                                !scr.hasNextLine() ||
                                i == 0 ||
                                i == s1.length()-1);


                // if a Tree is visible by default (on an edge)
                Tree tree;
                if(def) {
                    tree = new Tree(t, true, true);
                    trees.get(row).add(tree);
                } else {
                    tree = new Tree(t);
                    trees.get(row).add(tree);
                }

            }

            row++;
        }


        int treesSeen = 0;
        int max = 0;
        for (int i = 0; i < trees.size(); i++) {

            for (int j = 0; j < trees.get(i).size(); j++) {

                Tree cur = trees.get(i).get(j);

                Tree check;

                // finding an edge makes cur a visible tree.
                // finding a tree taller than or equal to cur lets us stop without making a connection
                // finding a tree shorter than cur means we add a connection to the graph.

                // search left, look for the edge, or for a blockage.

                for (int k = j - 1; k >= 0; k--) {

                    check = trees.get(i).get(k);
                    treesSeen++;

                    if (check.height >= cur.height) {
                        break;
                    } else {

                        if (check.isEdge) {
                            cur.visible = true;
                        }

                    }

                }

                cur.score *= treesSeen;
                treesSeen = 0;

                // search right, look for the edge, or for a blockage.
                for (int k = j + 1; k < trees.get(i).size(); k++) {

                    check = trees.get(i).get(k);
                    treesSeen++;

                    if (check.height >= cur.height) {
                        break;
                    } else {

                        if (check.isEdge) {
                            cur.visible = true;
                        }

                    }

                }

                cur.score *= treesSeen;
                treesSeen = 0;

                // search up, look for the edge, or for a blockage.
                for (int k = i - 1; k >= 0; k--) {

                    check = trees.get(k).get(j);
                    treesSeen++;

                    if (check.height >= cur.height) {
                        break;
                    } else {

                        if (check.isEdge) {
                            cur.visible = true;
                        }

                    }

                }

                cur.score *= treesSeen;
                treesSeen = 0;

                // search down, look for the edge, or for a blockage.
                for (int k = i + 1; k < trees.size(); k++) {

                    check = trees.get(k).get(j);
                    treesSeen++;

                    if (check.height >= cur.height) {
                        break;
                    } else {

                        if (check.isEdge) {
                            cur.visible = true;
                        }

                    }

                }

                cur.score *= treesSeen;
                treesSeen = 0;

                if (cur.visible) {
                    visibleTrees++;
                }

                // see if this tree is more scenic than the last.
                if(cur.score > max){
                    max = cur.score;
                }

            }

        }



        print(visibleTrees);
        print(max);

    }


    private class Tree {

        int height;
        int score;
        boolean visible;
        boolean isEdge;



        Tree(int height){
            this(height, false, false);
        }

        Tree(int height, boolean visible, boolean isEdge){
            this.height = height;
            this.visible = visible;
            this.isEdge = isEdge;
            this.score = 1;
        }

        @Override
        public String toString() {
            return height + "";
        }
    }

}
