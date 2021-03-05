public class Event implements Comparable<Event>{

    double xVal, yStart, yEnd;
    int eventType;

    Event(int type, double x, double y1, double y2){
        this.eventType = type;
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
            // if this event is a vertical line and they have the same x, deal with it first
            if (this.eventType == 2){
                return -1;
            }
            // if the other event is a vertical line, deal with it first
            if (e.eventType == 2){
                return 1;
            }
            // if the event is an endpoint, the other 
            else if (this.eventType == 1 && e.eventType == 0){
                
            }
        }
        return 0;
    }
}
