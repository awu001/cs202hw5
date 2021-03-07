import java.util.*;
import java.io.*;

public class Intersection {

    // finds all intersections, provided a BST queue of start/end points and vertical lines
    public static void findIntersections(TreeSet<Event> queue, PrintWriter out){
        // takes tree queue, makes new tree with y values
        TreeSet<Double> yValues = new TreeSet<Double>();
        Event curr;
        while (!queue.isEmpty()){
            curr = queue.pollFirst();
            if (!queue.isEmpty() && curr.xVal == queue.first().xVal){
                doubleX(queue, yValues, curr, out);
            }
            else if (curr.eventType == 0){
                yValues.add(curr.yStart);
            }

            else if (curr.eventType == 1){
                yValues.remove(curr.yStart);
            }

            else if (curr.eventType == 2){
                NavigableSet<Double> intersections;
                printIntersections(curr, yValues, out);
            }
        }
    }
    
    // helper for when a vertical line is found to print out any intersections
    public static void printIntersections(Event e, TreeSet<Double> yValues, PrintWriter out){
        NavigableSet<Double> intersections = yValues.subSet(e.yStart, true, e.yEnd, true);
        for (double currInt : intersections){
            out.println(e.xVal + " " + currInt);
        }
    }

    // helper function for if the current and next events occur at the same x Value
    public static void doubleX(TreeSet<Event> queue, TreeSet<Double> yValues, Event curr, PrintWriter out){
        Event next = queue.pollFirst();
        if (!queue.isEmpty() && next.xVal == queue.first().xVal){
            tripleX(queue, yValues, curr, next, out);
        }
        else{
            if (curr.eventType == 0){
                if (next.eventType == 0){
                    yValues.add(curr.yStart);
                    yValues.add(next.yStart);
                }
                else if (next.eventType == 2){
                    yValues.add(curr.yStart);
                    printIntersections(next, yValues, out);
                }
            }
            else if (curr.eventType == 1){
                if (next.eventType == 0){
                    yValues.remove(curr.yStart);
                    yValues.add(next.yStart);
                    // horizontal line connection
                    if (curr.yStart == next.yStart){
                        out.println(curr.xVal + " " + curr.yStart);
                    }
                }
                else if (next.eventType == 1){
                    yValues.remove(curr.yStart);
                    yValues.remove(next.yStart);
                }
                else if (next.eventType == 2){
                    printIntersections(next, yValues, out);
                    yValues.remove(curr.yStart);
                }
            }
            else if (curr.eventType == 2){
                if (next.eventType == 2){
                    yValues.add(curr.yStart);
                    yValues.add(curr.yEnd);
                    printIntersections(next, yValues, out);
                    yValues.remove(curr.yStart);
                    yValues.remove(curr.yEnd);
                }
            }
        }
    }

    // helper for if curr, next, and nextNext are all occurring at the same x value
    public static void tripleX(TreeSet<Event> queue, TreeSet<Double> yValues, Event curr, Event next, PrintWriter out){
        // we know the order is by default remove, add, vertical, if remove and add are at different y gotta add, vert, remove
        Event nextNext = queue.pollFirst();
        // if all 3 are vertical lines
        if (curr.eventType == 2 && next.eventType == 2 && nextNext.eventType == 2){
            yValues.add(curr.yEnd);
            yValues.add(nextNext.yStart);
            printIntersections(next, yValues, out);
            yValues.remove(curr.yEnd);
            yValues.remove(nextNext.yStart);
        }
        // if remove and add occur at different y values, switch the order to add, then vertical, then remove
        if (curr.yEnd != next.yStart){
            yValues.add(next.yStart);
            printIntersections(nextNext, yValues, out);
            yValues.remove(curr.yEnd);
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

        //loop to read input; we use a BST to store the queue of events to be gone through
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