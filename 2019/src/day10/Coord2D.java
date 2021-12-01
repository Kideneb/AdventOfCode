package day10;

public class Coord2D {
    int x;
    int y;

    public Coord2D(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Coord2D add(Coord2D elem) {
        return new Coord2D(this.x + elem.getX(), this.y + elem.getY());
    }

    public Coord2D dist(Coord2D elem) {return new Coord2D(elem.getX() - this.x, elem.getY() - this.y);}

    @Override
    public boolean equals(Object obj) {
        Coord2D elem = (Coord2D) obj;
        return this.getX() == elem.getX() && this.getY() == elem.getY();
    }
}
