package pathnorm;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main2 {


    public static void main(String[] args) {

        // create object of Path
        Path path = Paths.get("usr", "local", "bin");

        // print Path
        System.out.println(path);

    }
}
