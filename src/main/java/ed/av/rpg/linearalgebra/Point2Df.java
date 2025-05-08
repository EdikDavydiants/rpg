package ed.av.rpg.linearalgebra;

/**
 *  2-dimensional float point.
 */
public class Point2Df {

    public float x;
    public float y;

    public Point2Df() {
        x = 0;
        y = 0;
    }

    public Point2Df(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point2Df(Point2Df p) {
        this.x = p.x;
        this.y = p.y;
    }

    /**
     * Method returns sum of two points.
     * @return Returns a new point as NEW(!) object.
     */
    public Point2Df sum(Point2Df p) {
        return new Point2Df(x + p.x, y + p.y);
    }

    /**
     * The method multiplies current point by a number k.
     * @return Returns a new point as NEW(!) object.
     */
    public Point2Df prod(float k) {
        return new Point2Df(x * k, y * k);
    }

    /**
     * Method returns length^2 of this point, or x^2 + y^2 in other words.
     */
    public float length2() {
        return x*x + y*y;
    }

    /**
     * Method returns length of this point.
     */
    public float length() {
        return (float) Math.sqrt(length2());
    }
}
