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

        TreeGraph graph = new TreeGraph();
        List<ArrayList<Tree>> trees = new ArrayList<>();
        int visibleTrees = 0;

        // put all of the trees into a 2D list
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
                    visibleTrees++;
                } else {
                    tree = new Tree(t);
                    trees.get(row).add(tree);
                }


            }

            row++;
        }


    }

    private class Tree{

        int height;

        boolean visible;
        boolean isEdge;

        Tree(int height){
            this(height, false, false);
        }

        Tree(int height, boolean visible, boolean isEdge){
            this.height = height;
            this.visible = visible;
            this.isEdge = isEdge;

        }

        @Override
        public String toString() {
            return height + "";
        }
    }

    private class TreeGraph {
        // a map of Trees and their connections
        HashMap<Tree, List<Tree>> map = new HashMap<>();

        public void addNewTree(Tree t){
            map.put(t, new LinkedList<>());
        }

        // tree connections are all bidirectional
        public void addEdge(Tree source, Tree destination){

            if(!map.containsKey(source)){
                addNewTree(source);
            }

            if(!map.containsKey(destination)){
                addNewTree(destination);
            }

            map.get(source).add(destination);
            map.get(destination).add(source);

        }

        public void printVertexCount(){
            print("Total Vertices: " + map.keySet().size());
        }

        public void countEdges(){
            int count = 0;
            for (Tree t : map.keySet()) {
               count += map.get(t).size();
            }
            // since all connections are bidirectional, we have to divide the count by 2 for an accurate number
            count /= 2;

            print("Total Edges ");
        }

        public boolean containsTree(Tree t){
            if(map.containsKey(t)){
                print("The graph does contain that tree");
                return true;
            }

            print("The graph does not contain such a tree");
            return false;
        }

        public boolean containsEdge(Tree t1, Tree t2){
            if(map.get(t1).contains(t2)){
                print("There is visibility between these trees");
                return true;
            }

            print("There is no visibility between these trees.");
            return false;
        }

        public boolean isEmpty(){
            return map.isEmpty();
        }
    }
}
