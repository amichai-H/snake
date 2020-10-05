package components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Snake {
   ArrayList<Point> body;
    int head =0;
    int tail = 0;
    Queue<Point> nextAdd = new LinkedList<>();
    Point addNow = null;
    Map map;
    public Snake(Map map){
        Point start = new Point(10,10);
        body = new ArrayList<>();
        body.add(start);
        this.map = map;
    }
    public boolean move(MoveIt moveIt) {
        for (int i = tail; i>0;i--){
            body.get(i).newlocation(body.get(i-1));
        }
        switch (moveIt){
            case Up:
                body.get(head).up();
                break;
            case Down:
                body.get(head).down();

                break;
            case Left:
                body.get(head).left();

                break;
            case Right:
                body.get(head).right();
                break;
        }
        if (!checkBorder()){
            return false;
        }

        if (addNow!=null){
            body.add(new Point(addNow));
            tail++;
            addNow = null;
        }
        if (map.thereIsFruit(body.get(head))){
            map.eatFruit(body.get(head));
            nextAdd.add( new Point(body.get(head)));
            map.addFruit();
            System.out.println("WOW");
        }
        if (!nextAdd.isEmpty()&&body.get(tail).equals(nextAdd.peek())){
            addNow = nextAdd.poll();
        }
        return true;

    }

    private boolean checkBorder() {
        int k = body.get(head).i(),j=body.get(head).j();
        if (k>20||k<0||j>20||j<0){
            return false;
        }
        for (int i = 1; i<=tail;i++){
            if(body.get(i).equals(body.get(head))){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Point> getBody() {
        return body;
    }
}
