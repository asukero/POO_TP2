package ca.uqac.poo.tp2.utils;

public class Position {
    int x;
    int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int x, int y) {
        if(x < 0){
            throw new IndexOutOfBoundsException();
        }
        this.x = x;
        if(y < 0){
            throw new IndexOutOfBoundsException();
        }
        this.y = y;
    }

    public Position(Position other){
        this.x = other.getX();
        this.y = other.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x < 0){
            throw new IndexOutOfBoundsException();
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if(y < 0){
            throw new IndexOutOfBoundsException();
        }
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (getX() != position.getX()) return false;
        return getY() == position.getY();

    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ')';
    }
}