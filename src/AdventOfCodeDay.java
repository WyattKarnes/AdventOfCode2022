import java.io.File;
import java.io.FileNotFoundException;

public abstract class AdventOfCodeDay {

    File input;

    public AdventOfCodeDay(File input){
        this.input = input;
    }

    public abstract void solve() throws FileNotFoundException;

    public void print(String s){
        System.out.println(s);
    }

    public void print(int s){
        System.out.println(s);
    }

}
