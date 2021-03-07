import java.lang.Math;

public class Event implements Comparable<Event>{

    private double xVal, yStart, yEnd;
    private int eventType;

    Event(int type, double x, double y1, double y2){
        this.eventType = type;
        this.xVal = x;
        this.yStart = Math.min(y1, y2);
        this.yEnd = Math.max(y1, y2);
    }

    public double getXVal(){
        return this.xVal;
    }
    
    public double getYStart(){
        return this.yStart;
    }

    public double getYEnd(){
        return this.yEnd;
    }

    public int getEventType(){
        return this.eventType;
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
        // need some way to distinguish whether there is a line segment ending where another is starting
        // if that number is in the tree already
        // this event and event e occur simultaneously
        else if (this.xVal == e.xVal){
            // order - remove, add, vertical
            // if this is a remove, it happens first
            if (this.eventType == 1){
                return -1;
            }
            // if e is a remove, it happens first
            else if (e.eventType == 1){
                return 1;
            }
            // now, we know neither is 1 so this event is 0 vs 2
            else if (this.eventType == 0){
                return -1;
            }
            // this event is 2 vs 0, and that should account for all cases
            else if (e.eventType == 0){
                return 1;
            }
            // two vertical lines
            else if (this.eventType == 2 && e.eventType == 2){
                // if this starts higher, it happens after
                if (this.yStart >= e.yEnd){
                    return 1;
                }
                // if this starts lower, it happens
                else if (e.yEnd >= this.yStart){
                    return -1;
                }
                return -1;
            }
        }
        return 0;
    }

    public String toString(){
        if (this.eventType == 0){
            return ("S(" + this.xVal + ", " + this.yStart + ")");
        }
        else if (this.eventType == 1){
            return ("E(" + this.xVal + ", " + this.yStart + ")");
        }
        else if (this.eventType == 2){
            return ("V(" + this.xVal + ", " + this.yStart + ") to (" + this.xVal + ", " + this.yEnd + ")");
        }
        return " ";
    }
}
