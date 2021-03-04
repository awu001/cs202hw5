public class Event implements Comparable{

    private enum eventType = {START, END, VERT};
    private double xVal, yStart, yEnd;

    public Event(int type, double x, double y1, double y2){
        this.eventType = eventType.values()[type];
        this.xVal = x;
        this.yStart = y1;
        this.yEnd = y2;
    }

    public int compareTo(Event e){
        // this event occurs before event e
        if (this.xVal < e.xVal){
            return -1;
        }
        // this event occurs after event e
        else if (this.xVal > e.xVal){
            return 1;
        }
        // this event and event e occur simultaneously
        else if (this.xVal == e.xVal){
            return 0;
        }
    }
}
