package ed.av.rpg.linearalgebra;

public class Point2Di {

    public int x;
    public int y;

    public Point2Di(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point2Di point2Di = (Point2Di) o;
        return x == point2Di.x && y == point2Di.y;
    }
}
