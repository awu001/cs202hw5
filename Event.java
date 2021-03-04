public class Event implements Comparable{

    enum eventType = {START, END, VERT};
    double xVal, yVal;

    public Event(int type, double x, double y){
        this.eventType = eventType.values()[type];
        this.xVal = x;
        this.yVal = y;
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
