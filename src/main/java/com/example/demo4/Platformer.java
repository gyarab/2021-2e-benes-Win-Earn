package com.example.demo4;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.HashMap;

public class Platformer extends Application {
    private HashMap<KeyCode, Boolean> klice1 = new HashMap<KeyCode, Boolean>();
    protected Scene scene;

    private ArrayList<Node> platforma = new ArrayList<Node>();
    private ArrayList<Node> konec = new ArrayList<Node>();
    private ArrayList<Node> lava = new ArrayList<Node>();


    protected Pane podklad = new Pane();
    private Pane herniPole = new Pane();
    private Pane rozhrani = new Pane();

    private Node hrac;
    private Point2D hracRychlost = new Point2D(0, 0);
    private Rectangle bg = new Rectangle(1280, 720);
    private boolean muzeSkakat = true;

    private int sirkaLevel;
    protected boolean vyhra = false;
    protected boolean prohra = false;
    protected boolean koupeno = false;
    protected boolean prohraPomoc = false;
    protected boolean vyhraPomoc = false;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            aktual();
        }
    };


    private void vykreslitObsah() {

        sirkaLevel = Level.LEVEL1[0].length() * 60;

        for (int i = 0; i < Level.LEVEL1.length; i++) {
            String line = Level.LEVEL1[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Node platform = vytvoritHrace(j * 60, i * 60, 60, 60, Color.rgb(79, 46, 0));
                        platforma.add(platform);
                        break;
                    case '2':
                        Node end = vytvoritHrace(j * 60, i * 60, 60, 60, Color.rgb(77, 43, 171));
                        konec.add(end);
                        break;
                    case '3':
                        Node lava = vytvoritHrace(j * 60, i * 60, 60, 60, Color.rgb(173, 53, 2));
                        this.lava.add(lava);
                        break;
                }
            }
        }
        if (!koupeno) {
            hrac = vytvoritHrace(0, 500, 40, 40, Color.rgb(146, 217, 65));
        } else {
            hrac = vytvoritHrace(0, 500, 40, 40, Color.rgb(255, 215, 0));
        }

        hrac.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < sirkaLevel - 640) {
                herniPole.setLayoutX(-(offset - 640));
                System.out.println("hybu se");
            }
        });
        bg.setFill(Color.AQUA);
            podklad.getChildren().addAll(bg, herniPole, rozhrani);
        System.out.println("Filip");


    }

    private void aktual() {
        if (jeZmacknuty(KeyCode.W) && hrac.getTranslateY() >= 5) {
            skakatHrac();
        }
        if (jeZmacknuty(KeyCode.A) && hrac.getTranslateX() >= 5) {
            hybatHracX(-5);
        }
        if (jeZmacknuty(KeyCode.D) && hrac.getTranslateX() + 40 <= sirkaLevel - 5) {
            hybatHracX(5);
        }
        if (hracRychlost.getY() < 10) {
            hracRychlost = hracRychlost.add(0, 1);
        }

        hybatHracY((int) hracRychlost.getY());
    }

    private void hybatHracX(int value) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforma) {
                if (hrac.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (hrac.getTranslateX() + 40 == platform.getTranslateX()) {
                            return;
                        }
                    } else {
                        if (hrac.getTranslateX() == platform.getTranslateX() + 60) {
                            return;
                        }
                    }
                }
            }
            for (Node end : konec) {
                if (hrac.getBoundsInParent().intersects(end.getBoundsInParent())) {
                    end.getProperties().put("nazivu", false);
                    System.out.println("Jsem tam");
                    vyhral();
                }
            }
            hrac.setTranslateX(hrac.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void hybatHracY(int value) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforma) {
                if (hrac.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (hrac.getTranslateY() + 40 == platform.getTranslateY()) {
                            hrac.setTranslateY(hrac.getTranslateY() - 1);
                            muzeSkakat = true;
                            return;
                        }
                    } else {
                        if (hrac.getTranslateY() == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            for (Node end : konec) {
                if (hrac.getBoundsInParent().intersects(end.getBoundsInParent())) {
                    end.getProperties().put("nazivu", false);
                    System.out.println("Jsem tam");
                    vyhral();
                }
            }
            for (Node lava : lava) {
                if (hrac.getBoundsInParent().intersects(lava.getBoundsInParent())) {
                    hrac.setTranslateY(500);
                    hrac.setTranslateX(0);
                    herniPole.setLayoutX(0);
                    konecHry();
                }
            }
            hrac.setTranslateY(hrac.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    private void skakatHrac() {
        if (muzeSkakat) {
            hracRychlost = hracRychlost.add(0, -30);
            muzeSkakat = false;
        }
    }

    public void konecHry() {
        Pane pomoc = new Pane();
        timer.stop();
        podklad.getChildren().removeAll(bg, herniPole, rozhrani);
        scene.setRoot(pomoc);

        prohra = true;
    }

    protected Node vytvoritHrace(int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);
        entity.getProperties().put("nazivu", true);

        herniPole.getChildren().add(entity);
        return entity;
    }

    private boolean jeZmacknuty(KeyCode key) {
        //System.out.println(key);
        return klice1.getOrDefault(key, false);
    }

    private void vyhral() {
        Pane pomoc = new Pane();
        timer.stop();
        podklad.getChildren().removeAll(bg, herniPole, rozhrani);
        scene.setRoot(pomoc);
        vyhra = true;

    }

    AnimationTimer timerVyhPro;

    public void start(Stage primaryStage) throws Exception {
        vyhraPomoc = vyhra;
        prohraPomoc = prohra;
        prohra = false;
        vyhra = false;
        vykreslitObsah();

        scene = new Scene(podklad);
        scene.setOnKeyPressed(event -> {
            klice1.put(event.getCode(), true);
            System.out.println("holaa fungujeme");
        });
        scene.setOnKeyReleased(event -> {
            klice1.put(event.getCode(), false);
            System.out.println(event.getCode());
        });


        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Platformer");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setY(270);
        primaryStage.setX(340);
        primaryStage.setScene(scene);
        primaryStage.show();
        animace();

        timerVyhPro = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (vyhra == true || prohra == true) {
                    primaryStage.close();
                    konecTimer();
                    //System.exit(0);
                }
            }
        };
        timerVyhPro.start();

    }

    public void konecTimer() {
        timerVyhPro.stop();
    }

    public void animace() {
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

