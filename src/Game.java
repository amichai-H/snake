import components.Map;
import components.MoveIt;
import components.Point;
import components.Snake;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;


public class Game {
    Snake snake;
    Map map;
    static MoveIt key;

    public void Draw() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(10,21,"Snake By Amichai Hadad");
        StdDraw.text(16,21,"score: "+snake.getBody().size());
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.line(0,20,20,20);
        StdDraw.line(0,0,0,20);
        StdDraw.line(20,0,20,20);
        StdDraw.line(0,0,20,0);
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.03);
        for (Point p : snake.getBody()) {
            StdDraw.point(p.i(), p.j());
        }
        StdDraw.setPenColor(StdDraw.GREEN);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (map.thereIsFruit(new Point(i, j))) {
                    StdDraw.point(i, j);
                }
            }
        }
        StdDraw.show();
    }

    public Game() throws InterruptedException {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(-2, 22);
        StdDraw.setYscale(-2, 22);
        map = new Map(21, 21);
        snake = new Snake(map);
        map.addFruit();
        Draw();
        key = MoveIt.Right;
        try {
            Thread thread = new Thread(() -> {
                try {
                    while (true) {
                        if (StdDraw.isKeyPressed('W')||StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                            key = MoveIt.Up;
                        } else if (StdDraw.isKeyPressed('S')||StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                            key = MoveIt.Down;
                        }
                        if (StdDraw.isKeyPressed('D')||StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                            key = MoveIt.Right;
                        } else if (StdDraw.isKeyPressed('A')||StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                            key = MoveIt.Left;
                        }
                    }


                } catch (Exception ignored) {

                }
            });
            thread.start();
        }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("ERROR");
            }


        }
    public static void main(String[] args) throws InterruptedException {
        Game game =  new Game();
        boolean win = false;
        while (true) {
            sleep(200);
                if (game.snake.move(Game.key)) {
                    game.Draw();
                }
                else break;
                if (game.snake.getBody().size()==20*20){
                    win = true;
                    break;
                }
        }
        Font font = new Font("TimesRoman", Font.BOLD+Font.ITALIC, 50);
        StdDraw.setFont(font);
        if (win){
            StdDraw.clear(StdDraw.GREEN);
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.text(10,10,"WINNER!!!!");
            StdDraw.show();
        }
        else {
            StdDraw.clear(StdDraw.BLACK);
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.text(10, 10, "Loser!!!!");
            StdDraw.text(10, 5, "Yore score is: " + game.snake.getBody().size());
            StdDraw.show();
        }
    }
}
