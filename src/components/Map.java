package components;

public class Map {
    private int arr[][];
    public Map(int n,int m){
        arr = new int[n][m];
    }

    public int getFrom(Point p) {
        return arr[p.i()][p.j()];
    }
    public boolean thereIsFruit(Point p){
        return  arr[p.i()][p.j()] == 1;
    }
    public void eatFruit(Point p){

        this.arr[p.i()][p.j()] = 0;
    }
    public void addFruit() {
        int i  = (int)( Math.random()*19);
        int j  = (int)( Math.random()*19);
        Point p =new Point(i,j);
        this. arr[p.i()][p.j()] = 1;
    }

    public int[][] getMap() {
        return arr;
    }
}
