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
            //order - remove, add, vertical
            // if this is a remove, it happens first
            if (this.eventType == 2){
                return -1;
            }
            // if e is a remove, it happens first
            else if (e.eventType == 2){
                return 1;
            }
            // now, we know neither is 1 so this event is 0 vs 2
            else if (this.eventType == 1){
                return -1;
            }
            // this event is 2 vs 0, and that should account for all cases
            else if (e.eventType == 1){
                return 1;
            }
            /* a few situations to deal with:
                0 1 - this is add, e removes
                0 2 - this is add, e is vertical - can there be a THIRD here that is also removing?
                1 0 - this is remove, e adds
                1 2 - this is remove, e is vertical - again, can there be a third in the same spot also adding?
                2 0 - this is vertical, e is add
                2 1 - this is vertical, e is remove
            */
        }
        return 0;
    }
}
