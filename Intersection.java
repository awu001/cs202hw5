import java.util.*;
import java.io.*;

public class Intersection {

    public static boolean useSegment(TreeMap<double, double> tree, double x1, double y1, double x2, double y2){
        // this is a horizontal segment, so we want to add it to the tree
        if (y1 == y2){
            tree.put(y1, x2);
            return tree;
        }
        // this is a vertical segment, so we want to check it to see its intersection with horizontal segments
        else if (x1 == x2){

        }
    }

    public static void main(String[] args) {

        /* parses command-line arguments for input filename */
        Scanner in;
        boolean stdin = true;

        if (args.length < 1) {
            in = new Scanner(System.in);
        } else {
            if (args[0].equals("-")) {
                in = new Scanner(System.in);
            } else {
                try {
                    in = new Scanner(new File(args[0]));
                    stdin = false;
                } catch (FileNotFoundException e) {
                    System.err.printf("error: filename %s could not be opened or may not exist\n", args[0]);
                    return;
                }
            }
        }

        /* loop to read input;
           you'll need to modify this to do something with the values read in */
        TreeMap<
        while (in.hasNextLine()) {
            double x1, y1, x2, y2;
            try {
                x1 = in.nextDouble();
                y1 = in.nextDouble();
                x2 = in.nextDouble();
                y2 = in.nextDouble();
            } catch (NoSuchElementException e) {
                break;
            }
            /* process/save segment's coordinates here */
            useSegment(x1, y1, x2, y2);
            removeOld()

            // example debugging print statement
            System.err.printf("(%f, %f) to (%f, %f)\n", curr[0], curr[1], curr[2], curr[3]);
        }

        /* input complete; close Scanner's input file */
        if (!stdin)
            in.close();

        /* parses command-line arguments for output filename */
        PrintWriter out;
        boolean stdout = true;
        if (args.length < 2) {
            out = new PrintWriter(System.out);
        } else {
            if (args[1].equals("-")) {
                out = new PrintWriter(System.out);
            } else {
                try {
                    out = new PrintWriter(args[1]);
                    stdout = false;
                } catch (FileNotFoundException e) {
                    System.err.printf("error: filename %s could not be opened for writing\n", args[1]);
                    return;
                }
            }
        }

        /* your output would go here */



        /* your output is finished here */
        out.flush();
        if (!stdout)
            out.close();
    }
}
