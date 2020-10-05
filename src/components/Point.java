package components;

import java.util.Objects;

public class Point {
    private int i;
    private int j;
    public Point(int i,int j){
        this.i = i;
        this.j = j;
    } public Point(Point p){
        this.i = p.i;
        this.j = p.j;
    }

    public int i() {
        return i;
    }

    public int j() {
        return j;
    }
    public void up(){
        j++;
    }
    public void down(){
        j--;
    }
    public void left(){
        i--;
    }
    public void right(){
        i++;
    }
    public void newlocation(Point p){
        this.i = p.i;
        this.j = p.j;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return i == point.i &&
                j == point.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }
}
