import java.util.*;
import java.io.*;

public class Intersection {

    public static void printIntersections(Event e, TreeSet<Double> yValues, PrintWriter out){
        NavigableSet<Double> intersections;
        if (e.yStart < e.yEnd){
            intersections = yValues.subSet(e.yStart, true, e.yEnd, true);
        }
        else{
            intersections = yValues.subSet(e.yEnd, true, e.yStart, true);
        }
        for (double currInt : intersections){
            out.println(e.xVal + " " + currInt);
        }
        return;
    }

    public static void findIntersections(TreeSet<Event> queue, PrintWriter out){
        //System.out.println(queue.toString());
        // takes tree queue, makes new tree with y values
        System.out.println(queue);
        TreeSet<Double> yValues = new TreeSet<Double>();
        for (Event curr: queue){
            Event next = queue.higher(curr);
            System.out.println("Curr is " + curr);
            System.out.println("Next is " + queue.higher(curr) + "\n");
            //curr.toString();
            // if the next event occurs at the same x value as the current event
            if (curr.xVal == next.xVal){
                //System.out.println("Two events at this x coordinate!");

                Event nextNext = queue.higher(next);
                // if the next two events occur at the same x value as current event
                if (next.xVal == nextNext.xVal){
                    NavigableSet<Double> intersections;
                    //System.out.println("Three events at this x coordinate!");
                    //System.out.println(curr.eventType + " " + next.eventType + " " + nextNext.eventType);
                    //next.toString();
                    //nextNext.toString();

                    // we can ignore the add and remove since they effectively cancel, so all we need to do is find the vertical segment
                    if (curr.eventType == 2){
                        //System.out.println("found1!");
                        printIntersections(curr, yValues, out);
                    }
                    else if (next.eventType == 2){
                        //System.out.println("found2!");
                        printIntersections(next, yValues, out);
                    }
                    else if (nextNext.eventType == 2){
                        //System.out.println("found3!");
                        printIntersections(nextNext, yValues, out);
                    }
                    //System.out.println("Skipping");
                }
                /*
                //curr == next x val, but not nextNext
                //3 options - AV, VR, AR
                // AR
                if (curr.eventType + next.eventType == 1{
                    //remove first, then add
                }
                // AV
                else if (curr.eventType + next.eventType == 2){
                    // add, then vertical
                }
                // RV
                else if (curr.eventType + next.eventType == 3){
                    // vertical, then remove
                }
                */
            }
            // this event occurs by itself and is an add
            else if (curr.eventType == 0){
                System.out.println("Point added!");
                yValues.add(curr.yStart);
            }
            // is remove
            else if (curr.eventType == 1){
                yValues.remove(curr.yStart);
                System.out.println("Point removed!");
            }
            // is vertical
            else if (curr.eventType == 2){
                System.out.println("Intersections!");
                printIntersections(curr, yValues, out);
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
                if (x1 < x2){
                    Event start = new Event(0, x1, y1, y2);
                    queue.add(start);
                    Event end = new Event(1, x2, y1, y2);
                    queue.add(end);
                }
                else if (x1 > x2){
                    Event start = new Event(0, x2, y1, y2);
                    queue.add(start);
                    Event end = new Event(1, x1, y1, y2);
                    queue.add(end);
                }
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
