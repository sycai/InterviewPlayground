package Moderate;

/**
 * Created by sycai on 6/10/2017.
 * Given two straight line segments (represented as a start point and an end point), compute the point of intersection,
 * if any.
 */
public class Intersection {
    private static boolean intersect(Segment s1, Segment s2) {
        if (s1.slope == s2.slope) {
            if (s1.slope == Double.POSITIVE_INFINITY)   return s1.start.x == s2.start.x;
            else                                        return s1.intercept == s2.intercept;
        }

        Double interX;
        if (s1.slope == Double.POSITIVE_INFINITY) {
            interX = s1.start.x;
        } else if (s2.slope  == Double.POSITIVE_INFINITY) {
            interX = s2.start.x;
        } else {
            interX = (s1.intercept - s2.intercept) / (s1.slope - s2.slope);
        }

        double interY = s1.intercept + s1.slope * interX;
        Point p = new Point(interX, interY);

        return isBetween(s1, p) && isBetween(s2, p);
    }

    private static boolean isBetween(Segment s, Point mid) {
        Point start = s.start;
        Point end = s.end;
        if (s.slope == Double.POSITIVE_INFINITY) {
            return mid.x == start.x && start.y <= mid.y && mid.y <= end.y;
        } else if (s.slope < 0) {
            return start.x <= mid.x && start.y >= mid.y && mid.x <= end.x && mid.y >= end.y;
        } else {
            return start.x <= mid.x && start.y <= mid.y && mid.x <= end.x && mid.y <= end.y;
        }
    }

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 3);
        Point p3 = new Point(-1,-1);
        Point p4 = new Point(0,5);

        boolean res = Intersection.intersect(new Segment(p1, p2), new Segment(p3, p4));
        System.out.println(res);
    }
}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Segment {
    Point start;
    Point end;
    double slope;
    double intercept;

    public Segment(Point s, Point e) {
        // Start point should be on the left. If this is a vertical line, then start point should be the lower one.
        if (s.x < e.x) {
            this.start = s;
            this.end = e;
            slope = (s.y - e.y) / (s.x - e.x);
            intercept = s.y - s.x * slope;
        } else if (s.x > e.x){
            this.end = s;
            this.start = e;
            slope = (s.y - e.y) / (s.x - e.x);
            intercept = s.y - s.x * slope;
        } else {
            this.start = s.y <= e.y ? s : e;
            this.end = s.y > e.y ? s : e;
            slope = Double.POSITIVE_INFINITY;
            intercept = Double.POSITIVE_INFINITY;
        }
    }
}