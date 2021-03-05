import java.util.*;
import java.io.*;

public class Intersection {

    public static void findIntersections(TreeSet<Event> queue, PrintWriter out){
        // takes tree queue, makes new tree with y values
        TreeSet<Double> yValues = new TreeSet<Double>();
        for (Event curr: queue){
            if (curr.eventType == 0){
                yValues.add(curr.yStart);
            }

            else if (curr.eventType == 1){
                yValues.remove(curr.yStart);
            }

            else if (curr.eventType == 2){
                NavigableSet<Double> intersections;
                if (curr.yStart < curr.yEnd){
                    intersections = yValues.subSet(curr.yStart, true, curr.yEnd, true);
                }
                else{
                    intersections = yValues.subSet(curr.yEnd, true, curr.yStart, true);
                }
                for (double currInt : intersections){
                    out.println(curr.xVal + " " + currInt);
                }
            }
        }
        return;
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

        // we use a BST to store the queue of events to be gone through
        TreeSet<Event> queue = new TreeSet<Event>();
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

            // this segment is horizontal
            // we add two events to the queue, one for the start and one for the end of the segment
            if (y1 == y2){
                Event start = new Event(0, x1, y1, y2);
                queue.add(start);
                Event end = new Event(1, x2, y1, y2);
                queue.add(end);
            }
            // this segment is vertical
            // we create only one new event in the queue, telling us to check for intersections
            else if (x1 == x2){
                Event vert = new Event(2, x2, y1, y2);
                queue.add(vert);
            }

            // example debugging print statement
            System.err.printf("(%f, %f) to (%f, %f)\n", x1, y1, x2, y2);
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
        findIntersections(queue, out);


        /* your output is finished here */
        out.flush();
        if (!stdout)
            out.close();
    }
}
