package FloodIt;

public class Coord {
    public int x;
    public int y;
    public Coord(final int _x, final int _y) {
        this.x = _x;
        this.y = _y;
    }
    public boolean equals(final Object obj) {
        if(obj instanceof Coord) {
            final Coord that = (Coord) obj;
            return this.x == that.x && this.y == that.y;
        }else {
            return false;
        }
    }
    public int hashCode() {
        return this.x * 101 + this.y;
    }
    public String toString() {
        return Integer.toString(this.x) + ", " + Integer.toString(this.y);
    }
}
