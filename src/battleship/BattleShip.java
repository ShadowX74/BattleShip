/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author ShadowX
 */
public class BattleShip extends Application {
/*10 points for graphics
10 points objects
10 points polymorphism
10 points abstract classes
10 points linked lists
10 points 2D array
10 points >2D array (or arraylist)
10 points - maps & keys
20 points playable, worthwhile game*/
    int turn = 0;
    int action = 2;
    int ship = 1;
    int placeholder = 1;
    boolean reset = true;
    Location loc;
    Ship current;
    Map<Location, String> coordinates1 = new HashMap();
    Map<Location, String> coordinates2 = new HashMap();
    Location[][] board1 = new Location[10][10];
    Location[][] board2 = new Location[10][10];
    Button[][] board1button = new Button[10][10];
    Button[][] board2button = new Button[10][10];
    Map<Location, Ship> ships1 = new HashMap();
    Map<Location, Ship> ships2 = new HashMap();
    ArrayList<String> input = new ArrayList();

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();

        Scene scene = new Scene(root, 896, 500);
        primaryStage.setTitle("Battleship!");
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(896, 500);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board1[i][j] = new Location((i * 40) + 63, (j * 40) + 23);
                board2[i][j] = new Location((i * 40) + 63, (j * 40) + 473);

                board1button[i][j] = new Button();
                board1button[i][j].setLayoutX((j * 40) + 23);
                board1button[i][j].setLayoutY((i * 40) + 63);
                board1button[i][j].setText("  ");
                board1button[i][j].setPrefSize(40, 40);
                board1button[i][j].setBackground(Background.EMPTY);
                root.getChildren().add(board1button[i][j]);

                board2button[i][j] = new Button();
                board2button[i][j].setLayoutX((j * 40) + 473);
                board2button[i][j].setLayoutY((i * 40) + 63);
                board2button[i][j].setText("  ");
                board2button[i][j].setPrefSize(40, 40);
                board2button[i][j].setBackground(Background.EMPTY);
                root.getChildren().add(board2button[i][j]);

                board1button[i][j].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (turn % 2 == 0 && action % 2 == 0) {
                            boolean hit = false;
                            for (Location l : ships2.keySet()) {
                                int h, v;
                                if (ships2.get(l).dir.equals("vert")) {
                                    h = 1;
                                    v = ships2.get(l).size;
                                } else {
                                    v = 1;
                                    h = ships2.get(l).size;
                                }
                                if ((((Button) event.getSource()).getLayoutX() >= l.x)
                                        && (((Button) event.getSource()).getLayoutX() < l.x + h * 40)) {
                                    if ((((Button) event.getSource()).getLayoutY() >= l.y)
                                            && (((Button) event.getSource()).getLayoutY() < l.y + v * 40)) {
                                        hit = true;
                                        coordinates1.put(new Location(((Button) event.getSource()).getLayoutX(), ((Button) event.getSource()).getLayoutX()), "hit");
                                        ships2.get(l).health -= 1;
                                    }
                                }
                            }
                            if (!hit) {
                                coordinates1.put(new Location(((Button) event.getSource()).getLayoutX(), ((Button) event.getSource()).getLayoutX()), "miss");
                            }
                            action++;
                        }
                    }
                });
                board2button[i][j].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        if (turn % 2 == 1 && action % 2 == 1) {
                            boolean hit = false;
                            for (Location l : ships1.keySet()) {
                                int h, v;
                                if (ships1.get(l).dir.equals("vert")) {
                                    h = 1;
                                    v = ships1.get(l).size;
                                } else {
                                    v = 1;
                                    h = ships1.get(l).size;
                                }
                                if ((((Button) event.getSource()).getLayoutX() >= l.x)
                                        && (((Button) event.getSource()).getLayoutX() < l.x + h * 40)) {
                                    if ((((Button) event.getSource()).getLayoutY() >= l.y)
                                            && (((Button) event.getSource()).getLayoutY() < l.y + v * 40)) {
                                        hit = true;
                                        coordinates1.put(new Location(((Button) event.getSource()).getLayoutX(), ((Button) event.getSource()).getLayoutX()), "hit");
                                        ships1.get(l).health -= 1;
                                    }
                                }
                            }
                            if (!hit) {
                                coordinates1.put(new Location(((Button) event.getSource()).getLayoutX(), ((Button) event.getSource()).getLayoutX()), "miss");
                            }
                            action++;
                        }
                    }
                });
            }
        }

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                // only add once... prevent duplicates
                if (!input.contains(code)) {
                    System.out.println(code);
                    input.add(code);
                }
                System.out.println(input);
            }
        });

//        scene.setOnKeyReleased(
//                new EventHandler<KeyEvent>() {
//            public void handle(KeyEvent e) {
//                String code = e.getCode().toString();
//                input.remove(code);
//            }
//        });
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                if (turn == -1) {
                    gc.clearRect(0, 0, 800, 50);
                    String text = "Player " + checkWinner() + " wins!";
                    gc.fillText(text, 40, 36);
                    gc.strokeText(text, 40, 36);
                    showBoard(gc);
                } else {
                    //System.out.println("update");
                    update(gc, canvas);
                    if (turn < 2) {
                        //System.out.println("setup");
                        setup(gc);
                    } else {
//                        System.out.println("move");
//                        move(gc);
                        System.out.println("checkdeath");
                        checkDeath();
                    }
                }
            }
        }.start();
        primaryStage.show();
    }

    private void update(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (turn % 2 == 0) {
            gc.setFill(Color.DARKTURQUOISE);
            for (Location[] arr : board1) {
                for (Location l : arr) {
                    gc.fillRect(l.x, l.y, 40, 40);
                    gc.strokeRect(l.x, l.y, 40, 40);
                }
            }
            gc.setFill(Color.DARKCYAN);
            for (Location[] arr : board2) {
                for (Location l : arr) {
                    gc.fillRect(l.x, l.y, 40, 40);
                    gc.strokeRect(l.x, l.y, 40, 40);
                }
            }

            for (Location l : coordinates1.keySet()) {
                if (coordinates1.get(l).equals("hit")) {
                    gc.setFill(Color.RED);
                    gc.fillRect(l.x, l.y, 40, 40);
                }
                if (coordinates1.get(l).equals("miss")) {
                    gc.setFill(Color.DARKBLUE);
                    gc.fillRect(l.x, l.y, 40, 40);
                }
            }
            for (Location l : ships1.keySet()) {
                Ship current = ships1.get(l);
                if (current instanceof Carrier) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof Bship) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof Submarine) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof Frigate) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof PTBoat) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
            }
        } else {
            gc.setFill(Color.DARKCYAN);
            for (Location[] arr : board1) {
                for (Location l : arr) {
                    gc.fillRect(l.x, l.y, 40, 40);
                    gc.strokeRect(l.x, l.y, 40, 40);
                }
            }
            gc.setFill(Color.DARKTURQUOISE);
            for (Location[] arr : board2) {
                for (Location l : arr) {
                    gc.fillRect(l.x, l.y, 40, 40);
                    gc.strokeRect(l.x, l.y, 40, 40);
                }
            }
            gc.setFill(Color.RED);
            for (Location l : coordinates2.keySet()) {
                if (coordinates2.get(l).equals("hit")) {
                    gc.fillRect(l.x, l.y, 40, 40);
                }
                if (coordinates1.get(l).equals("miss")) {
                    gc.setFill(Color.DARKBLUE);
                    gc.fillRect(l.x, l.y, 40, 40);
                }
            }

            for (Location l : ships2.keySet()) {
                Ship current = ships2.get(l);
                if (current instanceof Carrier) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof Bship) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof Submarine) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof Frigate) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
                if (current instanceof PTBoat) {
                    if (current.dir.equals("vert")) {
                        gc.drawImage(current.imgvert, l.x, l.y);
                    } else {
                        gc.drawImage(current.img, l.x, l.y);
                    }
                }
            }
        }
    }

    private void setup(GraphicsContext gc) {
        if (turn == 0) {
            String text = "Welcome to Battleship!";
            gc.fillText(text, 40, 36);
            gc.strokeText(text, 40, 36);

            text = "Player 1 please use the arrow keys to position your ships, R to rotate, and Enter to confirm.";
            gc.fillText(text, 40, 50);
            gc.strokeText(text, 40, 50);

            if (reset) {
                loc = new Location(63, 23);
                reset = false;
            }
            if (ship == 1) {
                current = new Carrier("not");
                ship = 0;
            } else if (ship == 2) {
                current = new Bship("not");
                ship = 0;
            } else if (ship == 3) {
                current = new Submarine("not");
                ship = 0;
            } else if (ship == 4) {
                current = new Frigate("not");
                ship = 0;
            } else if (ship == 5) {
                current = new PTBoat("not");
                ship = 0;
            }

            if (current.dir.equals("vert")) {
                gc.drawImage(current.imgvert, loc.x, loc.y);
            } else {
                gc.drawImage(current.img, loc.x, loc.y);
            }
            System.out.println(input);
            if (input.contains("LEFT")) {
                if (loc.x > 23) {
                    loc.x -= 40;
                }
                input.remove("LEFT");
            } else if (input.contains("RIGHT")) {
                if (current.dir.equals("vert")) {
                    if (loc.x <= 423 - 80) {
                        loc.x += 40;
                    }
                } else if (loc.x <= 423 - (current.size + 1) * 40) {
                    loc.x += 40;
                }
                input.remove("RIGHT");
            } else if (input.contains("UP")) {
                if (loc.y > 63) {
                    loc.y -= 40;
                }
                input.remove("UP");
            } else if (input.contains("DOWN")) {
                if (current.dir.equals("vert")) {
                    if (loc.y <= 463 - (current.size + 1) * 40) {
                        loc.y += 40;
                    }
                } else if (loc.y <= 463 - 80) {
                    loc.y += 40;
                }
                input.remove("DOWN");
            } else if (input.contains("R")) {
                if (current.dir.equals("vert")) {
                    current.dir = "not";
                    if (loc.x > 423 - current.size * 40) {
                        loc.x = 423 - current.size * 40;
                    }
                } else {
                    current.dir = "vert";
                    if (loc.y > 463 - current.size * 40) {
                        loc.y = 463 - current.size * 40;
                    }
                }
                input.remove("R");
            }
            if (input.contains("ENTER")) {
                ships1.put(loc, current);
                System.out.println("placed");
                reset = true;
                input.remove("ENTER");
                if (placeholder == 5) {
                    ship = 1;
                    placeholder = 1;
                    turn++;
                } else {
                    placeholder++;
                    ship = placeholder;
                }
            }
        } else if (turn == 1) {
            gc.clearRect(0, 0, 800, 63);

            String text = "Player 2 please use the arrow keys to position your ships, R to rotate, and Enter to confirm.";
            gc.fillText(text, 40, 36);
            gc.strokeText(text, 40, 36);

            if (reset) {
                loc = new Location(63, 473);
                reset = false;
            }
            if (ship == 1) {
                current = new Carrier("not");
                ship = 0;
            } else if (ship == 2) {
                current = new Bship("not");
                ship = 0;
            } else if (ship == 3) {
                current = new Submarine("not");
                ship = 0;
            } else if (ship == 4) {
                current = new Frigate("not");
                ship = 0;
            } else if (ship == 5) {
                current = new PTBoat("not");
                ship = 0;
            }

            if (current.dir.equals("vert")) {
                gc.drawImage(current.imgvert, loc.x, loc.y);
            } else {
                gc.drawImage(current.img, loc.x, loc.y);
            }
            if (input.contains("LEFT")) {
                if (loc.x > 473) {
                    loc.x -= 40;
                }
                input.remove("LEFT");
            } else if (input.contains("RIGHT")) {
                if (current.dir.equals("vert")) {
                    if (loc.x <= 873 - 80) {
                        loc.x += 40;
                    }
                } else if (loc.x <= 873 - (current.size + 1) * 40) {
                    loc.x += 40;
                }
                input.remove("RIGHT");
            } else if (input.contains("UP")) {
                if (loc.y > 63) {
                    loc.y -= 40;
                }
                input.remove("UP");
            } else if (input.contains("DOWN")) {
                if (current.dir.equals("vert")) {
                    if (loc.y <= 463 - (current.size + 1) * 40) {
                        loc.y += 40;
                    }
                } else if (loc.y <= 463 - 80) {
                    loc.y += 40;
                }
                input.remove("DOWN");
            } else if (input.contains("R")) {
                if (current.dir.equals("vert")) {
                    System.out.println("checked");
                    current.dir = "not";
                    if (loc.x > 873 - current.size * 40) {
                        loc.x = 873 - current.size * 40;
                    }
                } else {
                    current.dir = "vert";
                    if (loc.y > 463 - current.size * 40) {
                        loc.y = 463 - current.size * 40;
                    }
                }
                input.remove("R");
            }
            if (input.contains("ENTER")) {
                System.out.println("placed");
                ships2.put(loc, current);
                reset = true;
                input.remove("ENTER");

                if (placeholder == 5) {
                    ship = 1;
                    placeholder = 1;
                    turn++;
                } else {
                    placeholder++;
                    ship = placeholder;
                }
            }

        }
    }

    private void move(GraphicsContext gc) {
        boolean notmove = true;
        while (notmove) {
            if (turn % 2 == action % 2 && turn % 2 == 0) {
                gc.clearRect(0, 0, 800, 50);
                String text = "Player " + (turn % 2 + 1) + " please choose where to fire.";
                gc.fillText(text, 40, 36);
                gc.strokeText(text, 40, 36);
            } else {
                turn++;
                notmove = false;
            }
        }
    }

    private void checkDeath() {
        for (Location l : ships1.keySet()) {
            if (ships1.get(l).health <= 0) {
                ships1.remove(l);
            }
        }
        for (Location l : ships2.keySet()) {
            if (ships2.get(l).health <= 0) {
                ships2.remove(l);
            }
        }
        if (ships1.isEmpty() || ships2.isEmpty()) {
            turn = -1;
        }
    }

    private int checkWinner() {
        if (ships1.isEmpty()) {
            return 2;
        } else if (ships2.isEmpty()) {
            return 1;
        }
        return 0;
    }

    private void showBoard(GraphicsContext gc) {
        gc.setFill(Color.DARKTURQUOISE);
        for (Location[] arr : board1) {
            for (Location l : arr) {
                gc.fillRect(l.x, l.y, 40, 40);
                gc.strokeRect(l.x, l.y, 40, 40);
            }
        }
        for (Location[] arr : board2) {
            for (Location l : arr) {
                gc.fillRect(l.x, l.y, 40, 40);
                gc.strokeRect(l.x, l.y, 40, 40);
            }
        }

        for (Location l : coordinates1.keySet()) {
            if (coordinates1.get(l).equals("hit")) {
                gc.setFill(Color.RED);
                gc.fillRect(l.x, l.y, 40, 40);
            }
            if (coordinates1.get(l).equals("miss")) {
                gc.setFill(Color.DARKBLUE);
                gc.fillRect(l.x, l.y, 40, 40);
            }
        }
        for (Location l : coordinates2.keySet()) {
            if (coordinates2.get(l).equals("hit")) {
                gc.setFill(Color.RED);
                gc.fillRect(l.x, l.y, 40, 40);
            }

            if (coordinates1.get(l).equals("miss")) {
                gc.setFill(Color.DARKBLUE);
                gc.fillRect(l.x, l.y, 40, 40);
            }
        }

        for (Location l : ships1.keySet()) {
            Ship current = ships1.get(l);
            if (current instanceof Carrier) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof Bship) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof Submarine) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof Frigate) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof PTBoat) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
        }

        for (Location l : ships2.keySet()) {
            Ship current = ships2.get(l);
            if (current instanceof Carrier) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof Bship) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof Submarine) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof Frigate) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
            if (current instanceof PTBoat) {
                if (current.dir.equals("vert")) {
                    gc.drawImage(current.imgvert, l.x, l.y);
                } else {
                    gc.drawImage(current.img, l.x, l.y);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}