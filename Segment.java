public class Segment implements Comparable{

    enum type = {HORIZONTAL, VERTICAL};
    double[] xRange;
    double[] yRange;

    Segment(double x1, double y1, double x2, double y2){
        if (y1 == y2){
            this.type = HORIZONTAL;
        }
        else if (x1 == x2){
            this.type = VERTICAL;
        }
        this.xRange = [x1, x2];
        this.yRange = [y1, y2];
    }

    public int compareTo(Segment S){

    }
}
