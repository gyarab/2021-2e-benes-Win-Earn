package com.example.demo4;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class HelloController extends Platformer {
    //Cela hra
    public int ticket = 4;
    public int slugCoin = 20;
    public int key = 0;
    public String name = " ";
    private final int startTime = 86400;
    private final int startTimeBox = 600;
    private int secondsBox = startTimeBox;
    private int minBox;
    private int secBox;
    private int novySecBox;
    private int secondsMine = startTime;
    private int oldSecondsBox;
    private int oldSecondsMine;
    private int randomTarget = 0;
    private int oldYear, oldMonth, oldDay, oldHour, oldMinute, oldSecond;
    private int currYear, currMonth, currDay, currHour, currMinute, currSecond;
    private double mineCoin = 0.000;
    private boolean isBuySnMapOne = false;
    private boolean isBuySnMapTwo = false;
    private boolean isBuySnMapThird = false;
    private boolean isBuySnMapFour = false;
    private boolean isFirstDate = true;
    private String firstDate;
    Alert a = new Alert(Alert.AlertType.NONE);
    Timeline timeMine = new Timeline();
    //Snake
    protected int roste = 0;
    protected int pocet = 3;
    protected int pohyb = 3;
    protected int[] osax = new int[100];
    protected int[] osay = new int[100];
    protected int[] jidlo;
    protected int[] hlava;
    protected boolean naraz = false;
    protected Rectangle papani;
    protected Rectangle[] rect = new Rectangle[100];
    protected Random random = new Random();
    //Count
    private int vysledek = 0;
    private int countskore = 0;
    private int secondsCount = 20;

    @FXML
    private StackPane stackPane_full_screen;

    @FXML
    private GridPane grid_main_snake;

    @FXML
    private Pane snake_map;

    @FXML
    private Button btn_start_snake_game;

    @FXML
    private Button btn_home_game;

    @FXML
    private Label txt_game_snake_target;

    @FXML
    private Label txt_game_snake_skore;

    @FXML
    private GridPane grid_main_count;

    @FXML
    private Pane count_map;

    @FXML
    private HBox hbox_count_txt;

    @FXML
    private Label txt_count_first_number;

    @FXML
    private Label txt_count_sign;

    @FXML
    private Label txt_count_second_number;

    @FXML
    private Button btn_count_result_1;

    @FXML
    private Button btn_count_result_2;

    @FXML
    private Button btn_count_result_3;

    @FXML
    private Button btn_count_start_game;

    @FXML
    private Button btn_home_count;

    @FXML
    private Label txt_count_time;

    @FXML
    private Label txt_count_your_skore;

    @FXML
    private GridPane grid_main_platform;

    @FXML
    private Pane platform_map;

    @FXML
    private Button btn_home_platform;

    @FXML
    private GridPane grid_main_home;

    @FXML
    private StackPane stackPane_main_bar;

    @FXML
    private GridPane grid_rules;

    @FXML
    private GridPane grid_minigames;

    @FXML
    private Button btn_start_snake;

    @FXML
    private Button btn_start_jumpgame;

    @FXML
    private Button btn_start_postreh;

    @FXML
    private GridPane grid_bonus;

    @FXML
    private TextFlow grid_bonus_img;

    @FXML
    private Button btn_collect_box;

    @FXML
    private Button btn_collect_mine;

    @FXML
    private Label txt_time_box;

    @FXML
    private Label txt_time_mine;

    @FXML
    private GridPane grid_shop;

    @FXML
    private Button btn_buy_2;

    @FXML
    private Button btn_buy_1;

    @FXML
    private Button btn_buy_3;

    @FXML
    private Button btn_buy_4;

    @FXML
    private GridPane grid_home;

    @FXML
    private Label stat_txt_name;

    @FXML
    private ComboBox<?> cmbox_names;

    @FXML
    private Label stat_txt_coin;

    @FXML
    private Label stat_txt_ticket;

    @FXML
    private Label stat_txt_key;

    @FXML
    private Label stat_txt_open;

    @FXML
    private Button btn_load_data;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_home;

    @FXML
    private Button btn_bonus;

    @FXML
    private Button btn_minigames;

    @FXML
    private Button btn_shop;

    @FXML
    private Button btn_rules;

    @FXML
    private Button btn_saveGame;

    @FXML
    private Label label_platform_konec;

    /**
     * @param event po kliknuti na toto tlacitko se nacte minuly hracuv progres, pokud tedy nejaky ma
     */
    @FXML
    void loadGameData(ActionEvent event) {
        loadData();
        stat_txt_coin.setText(String.valueOf(slugCoin));
        stat_txt_key.setText(String.valueOf(key));
        stat_txt_ticket.setText(String.valueOf(ticket));
        cmbox_names.setPromptText(name);
        stat_txt_open.setText(firstDate);
        if (isBuySnMapOne) {
            btn_buy_1.setText("Choose");
        }
        if (isBuySnMapTwo) {
            btn_buy_2.setText("Choose");
        }
        if (isBuySnMapThird) {
            btn_buy_2.setText("Choose");
        }
        if (isBuySnMapFour) {
            btn_buy_2.setDisable(true);
            koupeno = true;
        }

        int currMonthInDay = 0;
        int currMonthInSecond;
        int oldMonthInDay = 0;
        int oldMonthInSecond;
        int i;
        int j;
        int currSecForDay;
        int oldSecForDay;
        int[] arr = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        for (i = 0; i < currMonth; i++) {
            currMonthInDay = currMonthInDay + arr[i];
        }
        for (j = 0; j < oldMonth; j++) {
            oldMonthInDay = oldMonthInDay + arr[j];
        }

        currMonthInDay = currMonthInDay - (arr[i - 1] - currDay) - 1;
        oldMonthInDay = oldMonthInDay - (arr[j - 1] - oldDay) - 1;
        currMonthInSecond = currMonthInDay * 24 * 3600;
        oldMonthInSecond = oldMonthInDay * 24 * 3600;

        currSecForDay = (3600 * currHour) + (60 * currMinute) + currSecond;
        oldSecForDay = (3600 * oldHour) + (60 * oldMinute) + oldSecond;

        currMonthInSecond += currSecForDay;
        oldMonthInSecond += oldSecForDay;

        if (oldSecondsBox < 600) {
            if (oldYear == currYear) {
                int mezi = currMonthInSecond - oldMonthInSecond;
                if (mezi >= oldSecondsBox || mezi < 0) {
                    System.out.println("delsi doba");
                    btn_collect_box.setDisable(false);
                    btn_collect_box.setText("Collect");
                    txt_time_box.setText("00:" + "0" + minBox + ":" + secBox + "0");
                    secondsBox = 0;
                } else {
                    secondsBox = oldSecondsBox - mezi;
                    System.out.println("jeste eni dost casu");
                    btn_collect_box.setText("Continue");
                }
            } else {
                btn_collect_box.setDisable(false);
                btn_collect_box.setText("Collect");
                txt_time_box.setText("00:" + "0" + minBox + ":" + secBox + "0");
                secondsBox = 0;
            }
        }

        if (oldSecondsMine < 86400) {
            if (oldYear == currYear) {
                int mezi = currMonthInSecond - oldMonthInSecond;
                if (mezi >= oldSecondsMine || mezi < 0) {
                    System.out.println("delsi doba");
                    btn_collect_mine.setDisable(false);
                    btn_collect_mine.setText("Collect");
                    secondsMine = 0;
                } else {
                    secondsMine = oldSecondsMine - mezi;
                    System.out.println("jeste eni dost casu");
                    btn_collect_mine.setText("Continue");
                }
            } else {
                btn_collect_mine.setDisable(false);
                btn_collect_mine.setText("Collect");
                secondsMine = 0;
            }
        }
    }

    /**
     * @param event funkce bezi na pozadi a vzdycky aktualizuje zakkladni promene hry, aby byly aktualni
     */
    @FXML
    void SaveMe(MouseEvent event) {
        stat_txt_coin.setText(String.valueOf(slugCoin));
        stat_txt_key.setText(String.valueOf(key));
        stat_txt_ticket.setText(String.valueOf(ticket));
        cmbox_names.setPromptText(name);

        if (isFirstDate) {
            firstDate = String.valueOf(java.time.LocalDate.now());
            stat_txt_open.setText(firstDate);
            isFirstDate = false;
        }

    }

    /**
     * @param event prepinani gridu na hlavnim panelu
     */
    @FXML
    protected void onClick(ActionEvent event) {
        if (event.getSource() == btn_home || event.getSource() == btn_home_game || event.getSource() == btn_home_platform || event.getSource() == btn_home_count) {
            grid_main_home.toFront();
            grid_home.toFront();
            if (event.getSource() == btn_home_platform){
                konecHry();
            }
        } else if (event.getSource() == btn_bonus) {
            grid_bonus.toFront();
            btn_collect_mine.setDisable(key == 0 || timeMine.getStatus() == Animation.Status.RUNNING);
        } else if (event.getSource() == btn_minigames) {
            grid_minigames.toFront();
            if (ticket <= 0) {
                btn_start_postreh.setDisable(true);
                btn_start_snake.setDisable(true);
                btn_start_jumpgame.setDisable(true);
            } else {
                btn_start_postreh.setDisable(false);
                btn_start_snake.setDisable(false);
                btn_start_jumpgame.setDisable(false);
            }
        } else if (event.getSource() == btn_shop) {
            grid_shop.toFront();
        } else if (event.getSource() == btn_rules) {
            grid_rules.toFront();
        }
    }

    /**
     * funkce, ktera nacita ulozena data ze souboru
     */
    public void loadData() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("saveData.txt"));

            name = br.readLine();
            slugCoin = Integer.parseInt(br.readLine());
            ticket = Integer.parseInt(br.readLine());
            key = Integer.parseInt(br.readLine());
            isBuySnMapOne = Boolean.parseBoolean(br.readLine());
            isBuySnMapTwo = Boolean.parseBoolean(br.readLine());
            isBuySnMapThird = Boolean.parseBoolean(br.readLine());
            isBuySnMapFour = Boolean.parseBoolean(br.readLine());
            firstDate = br.readLine();
            oldSecondsBox = Integer.parseInt(br.readLine());
            oldSecondsMine = Integer.parseInt(br.readLine());
            oldYear = Integer.parseInt(br.readLine());
            oldMonth = Integer.parseInt(br.readLine());
            oldDay = Integer.parseInt(br.readLine());
            oldHour = Integer.parseInt(br.readLine());
            oldMinute = Integer.parseInt(br.readLine());
            oldSecond = Integer.parseInt(br.readLine());

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        currYear = Integer.parseInt((new SimpleDateFormat("yyyy")).format(new Date()));
        currMonth = Integer.parseInt((new SimpleDateFormat("MM")).format(new Date()));
        currDay = Integer.parseInt((new SimpleDateFormat("dd")).format(new Date()));
        currHour = Integer.parseInt((new SimpleDateFormat("HH")).format(new Date()));
        currMinute = Integer.parseInt((new SimpleDateFormat("mm")).format(new Date()));
        currSecond = Integer.parseInt((new SimpleDateFormat("ss")).format(new Date()));
    }

    /**
     * funkce, ktera ulozi hracuv progras do souboru .txt
     */
    public void saveGameData() {
        name = (String) cmbox_names.getValue();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (name == null || name.isEmpty()) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("error: you didn't choose any nickname");
            a.show();
        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("saveData.txt"));
                bw.write(name);
                bw.newLine();
                bw.write("" + slugCoin);
                bw.newLine();
                bw.write("" + ticket);
                bw.newLine();
                bw.write("" + key);
                bw.newLine();
                bw.write("" + isBuySnMapOne);
                bw.newLine();
                bw.write("" + isBuySnMapTwo);
                bw.newLine();
                bw.write("" + isBuySnMapThird);
                bw.newLine();
                bw.write("" + isBuySnMapFour);
                bw.newLine();
                bw.write(firstDate);
                bw.newLine();
                bw.write("" + secondsBox);
                bw.newLine();
                bw.write("" + secondsMine);
                bw.newLine();
                bw.write((new SimpleDateFormat("yyyy")).format(new Date()));
                bw.newLine();
                bw.write((new SimpleDateFormat("MM")).format(new Date()));
                bw.newLine();
                bw.write((new SimpleDateFormat("dd")).format(new Date()));
                bw.newLine();
                bw.write((new SimpleDateFormat("HH")).format(new Date()));
                bw.newLine();
                bw.write((new SimpleDateFormat("mm")).format(new Date()));
                bw.newLine();
                bw.write((new SimpleDateFormat("ss")).format(new Date()));


                bw.close();
                System.out.println(minBox + ":" + secBox + "           " + secondsBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param event kdyz nekdo klikne na tlacitko s touto funkci ukonci se cely program
     */
    @FXML
    protected void extClick(javafx.event.ActionEvent event) {
        if (event.getSource() == btn_exit) {
            System.exit(0);
        }
    }

    /**
     * @param event po kliknuti na tlacitko se ulozi hracuv postup
     */
    @FXML
    void saveGameDataByBtn(ActionEvent event) {
        saveGameData();
    }

    /**
     * @param event urcuje co se ma stat kdyz si nekdo koupi neco v shopu
     */
    @FXML
    void Buy_thing(MouseEvent event) {
        String btnBuy = String.valueOf(event.getSource());
        switch (btnBuy) {
            case "Button[id=btn_buy_1, styleClass=button]'Buy'":
            case "Button[id=btn_buy_1, styleClass=button]'Choose'":
                if (slugCoin < 59 && !isBuySnMapOne) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("error: you don't have enough SlugCoin");
                    a.show();
                } else if (!snake_map.getId().equals("shop_img_big_snake") && !isBuySnMapOne) {
                    slugCoin -= 59;
                    btn_buy_1.setDisable(true);
                    btn_buy_2.setDisable(false);
                    btn_buy_3.setDisable(false);
                    btn_buy_1.setText("Choose");
                    snake_map.setId("shop_img_big_snake");
                    isBuySnMapOne = true;
                } else {
                    snake_map.setId("shop_img_big_snake");
                    btn_buy_1.setDisable(true);
                    btn_buy_2.setDisable(false);
                    btn_buy_3.setDisable(false);
                }
                System.out.println("jsem tu");
                break;
            case "Button[id=btn_buy_2, styleClass=button]'Buy'":
            case "Button[id=btn_buy_2, styleClass=button]'Choose'":
                if (slugCoin < 79 && !isBuySnMapTwo) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("error: you don't have enough SlugCoin");
                    a.show();
                } else if (!snake_map.getId().equals("shop_img_boom_snake") && !isBuySnMapTwo) {
                    slugCoin -= 79;
                    btn_buy_2.setDisable(true);
                    btn_buy_1.setDisable(false);
                    btn_buy_3.setDisable(false);
                    btn_buy_2.setText("Choose");
                    snake_map.setId("shop_img_boom_snake");
                    isBuySnMapTwo = true;
                } else {
                    snake_map.setId("shop_img_boom_snake");
                    btn_buy_2.setDisable(true);
                    btn_buy_1.setDisable(false);
                    btn_buy_3.setDisable(false);
                }
                break;
            case "Button[id=btn_buy_3, styleClass=button]'Buy'":
            case "Button[id=btn_buy_3, styleClass=button]'Choose'":
                if (slugCoin < 99 & !isBuySnMapThird) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("error: you don't have enough SlugCoin");
                    a.show();
                } else if (!snake_map.getId().equals("shop_img_gold_snake") && !isBuySnMapThird) {
                    slugCoin -= 99;
                    btn_buy_3.setDisable(true);
                    btn_buy_2.setDisable(false);
                    btn_buy_1.setDisable(false);
                    btn_buy_3.setText("Choose");
                    snake_map.setId("shop_img_gold_snake");
                    isBuySnMapThird = true;
                } else {
                    snake_map.setId("shop_img_gold_snake");
                    btn_buy_2.setDisable(false);
                    btn_buy_1.setDisable(false);
                    btn_buy_3.setDisable(true);
                }
                break;
            case "Button[id=btn_buy_4, styleClass=button]'Buy'":
                if (slugCoin < 119) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("error: you don't have enough SlugCoin");
                    a.show();
                } else {
                    slugCoin -= 119;
                    btn_buy_4.setDisable(true);
                    koupeno = true;
                    isBuySnMapFour = true;
                }
                break;
            default:
                // code block
        }
    }

    /**
     * @param event funkce kteera se vyvola po jakem koliv kliknuti na tlacitko start game v hlavnim gridu a da do popredi dany grid hry
     */
    @FXML
    protected void startGame(ActionEvent event) {
        System.out.println(ticket);
        if (event.getSource() == btn_start_snake) {
            if (ticket <= 0) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("error: you don't have enough Ticket");
                a.show();
                btn_start_snake.setDisable(true);
                btn_start_postreh.setDisable(true);
                btn_start_jumpgame.setDisable(true);
            } else {
                grid_main_snake.toFront();
                snake_map.setPrefSize(1250, 700);
                btn_start_snake_game.setDisable(false);
                txt_game_snake_skore.setText(String.valueOf(pocet));
                ticket -= 1;
            }
        } else if (event.getSource() == btn_start_jumpgame) {
            if (ticket <= 0) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("error: you don't have enough Ticket");
                a.show();
                btn_start_jumpgame.setDisable(true);
                btn_start_snake.setDisable(true);
                btn_start_postreh.setDisable(true);
            } else {
                grid_main_platform.toFront();
                platform_map.setPrefSize(1280, 720);
                ticket -= 1;

                Stage primaryStage = new Stage();

                try {
                    start(primaryStage);
                    primaryStage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                timer.start();
            }
        } else if (event.getSource() == btn_start_postreh) {
            if (ticket <= 0) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("error: you don't have enough Ticket");
                a.show();
                btn_start_postreh.setDisable(true);
                btn_start_snake.setDisable(true);
                btn_start_jumpgame.setDisable(true);
            } else {
                grid_main_count.toFront();
                count_map.setPrefSize(1280, 720);
                txt_count_time.setText(String.valueOf(secondsCount));
                txt_count_your_skore.setText("0");
                btn_count_start_game.setVisible(true);
                btn_count_result_1.setVisible(false);
                btn_count_result_3.setVisible(false);
                btn_count_result_2.setVisible(false);
                hbox_count_txt.setVisible(false);
                ticket -= 1;
            }
        }
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (vyhra == true) {
                System.out.println("vyhra");
                label_platform_konec.setText("You win");
                slugCoin += 10;
                timer.stop();
            } else if (prohra == true) {
                label_platform_konec.setText("You lose");
                timer.stop();
            }
        }
    };

    @Override
    public void start(Stage primaryStage) throws Exception {
        super.start(primaryStage);
    }

    private Rectangle jednaCastHada() {
        Rectangle rea = new Rectangle(45, 45);
        rea.setFill(Color.rgb(0, 204, 0));
        rea.setStroke(Color.BLACK);
        rea.setVisible(false);
        rea.setArcHeight(30.0);
        rea.setArcWidth(30.0);
        return rea;
    }

    private void newGame() {
        roste = 0;
        pocet = 3;
        pohyb = 3;
        osax = new int[100];
        osay = new int[100];
        naraz = false;
        snake_map.getChildren().removeAll(papani);
        snake_map.getChildren().removeAll(rect);
    }

    private void move() {
        int[][] tmp = {{osax[1], osay[1]}, {}};
        osax[1] = osax[0];
        osay[1] = osay[0];
        for (int i = 2; i < pocet; i++) {
            tmp[1] = new int[]{osax[i], osay[i]};
            osax[i] = tmp[0][0];
            osay[i] = tmp[0][1];
            tmp[0] = tmp[1];
            //potom co had dosahne jidla prida had svoji velikost
            if (roste > 0 && osax[pocet - 1] == jidlo[0] && osay[pocet - 1] == jidlo[1]) {
                rect[pocet - 1].setVisible(true);
                --roste;
            }
        }
        //pohyb hada
        switch (pohyb) {
            case 0:
                if (osay[0] + 50 <= 650) {
                    osay[0] += 50;
                } else {
                    naraz = true;
                    btn_start_snake_game.setVisible(true);
                    btn_start_snake_game.setDisable(true);
                }
                break;
            case 1:
                if (osax[0] - 50 >= 0) osax[0] -= 50;
                else {
                    naraz = true;
                    btn_start_snake_game.setVisible(true);
                    btn_start_snake_game.setDisable(true);
                }

                break;
            case 2:
                if (osay[0] - 50 >= 0) osay[0] -= 50;
                    //konec hry, když se hráč dotkne zdi
                else {
                    naraz = true;
                    btn_start_snake_game.setVisible(true);
                    btn_start_snake_game.setDisable(true);
                }
                break;
            case 3:
                if (osax[0] + 50 <= 1200) osax[0] += 50;
                else {
                    naraz = true;
                    btn_start_snake_game.setVisible(true);
                    btn_start_snake_game.setDisable(true);
                }
                break;
        }
        if (intersects(osax[0], osay[0])) {
            naraz = true;
            btn_start_snake_game.setVisible(true);
            btn_start_snake_game.setDisable(true);
        } else {
            for (int i = 0; i < pocet; i++) {
                rect[i].setTranslateX(osax[i]);
                rect[i].setTranslateY(osay[i]);
            }
            if (rect[0].getBoundsInParent().intersects(papani.getBoundsInParent())) {
                boolean zmena = false;

                jidlo = new int[]{(int) papani.getTranslateX(), (int) papani.getTranslateY()};
                while (!zmena) {
                    papani.setTranslateX(random.nextInt(25) * 50);
                    papani.setTranslateY(random.nextInt(14) * 50);
                    if (intersects(jidlo[0], jidlo[1]) && (int) papani.getTranslateX() != jidlo[0] || (int) papani.getTranslateY() != jidlo[1])
                        zmena = true;
                }
                osax[pocet] = (int) papani.getTranslateX();
                osay[pocet] = (int) papani.getTranslateY();
                rect[pocet].setTranslateX(rect[pocet - 1].getX());
                rect[pocet].setTranslateY(rect[pocet - 1].getY());
                ++pocet;
                ++roste;
                if (pocet == randomTarget) {
                    slugCoin += 5;
                    naraz = true;
                    btn_start_snake_game.setVisible(true);
                    btn_start_snake_game.setDisable(true);
                }
            }
        }
    }

    public boolean intersects(int x, int y) {
        int i = 0;
        for (Rectangle part : rect) {
            if (part != rect[0] && i > 0 && part.isVisible() && x == osax[i] && y == osay[i]) {
                System.out.println(i);
                return true;
            }
            i++;
        }
        return false;
    }

    public void play() {
        hlava = new int[]{osax[0], osay[0]};
        Thread game;
        //rect[0].setFill(Color.rgb(0, 132, 0));
        System.out.println(osax[0] + " " + osax[1] + " " + osax[2] + " ");
        papani = jednaCastHada();
        papani.setStroke(Color.BLACK);
        papani.setFill(Color.RED);
        papani.setVisible(true);
        papani.setTranslateX(random.nextInt(25) * 50);
        papani.setTranslateY(random.nextInt(14) * 50);
        snake_map.getChildren().add(papani);


        for (int i = 0; i < 3; i++) {
            rect[i] = jednaCastHada();
            rect[i].setTranslateX(50 + 50 * i);
            rect[i].setTranslateY(50 + 50);
            rect[i].setVisible(true);
            snake_map.getChildren().add(rect[i]);
        }
        for (int i = 3; i < 100; i++) {
            rect[i] = jednaCastHada();
            rect[i].setTranslateX(50 + 50 * i);
            rect[i].setTranslateY(50 + 50);
            snake_map.getChildren().add(rect[i]);
        }
        rect[0].setId("snakeHead");
        game = new Thread(() -> {
            while (!naraz) {
                move();
                try {
                    Thread.sleep(100);
                } catch (Exception ignored) {
                }
            }
        });

        game.start();
        //ovládání snaka pomocí kláves WASD
        grid_main_snake.setOnKeyPressed(event -> {
            txt_game_snake_skore.setText(String.valueOf(pocet));
            KeyCode k = event.getCode();
            switch (k) {
                case D:
                    if (pohyb != 1 && ((pohyb == 2 || pohyb == 0) && hlava[1] != osay[0])) pohyb = 3;
                    break;
                case A:
                    if (pohyb != 3 && ((pohyb == 2 || pohyb == 0) && hlava[1] != osay[0])) pohyb = 1;
                    break;
                case S:
                    if (pohyb != 2 && ((pohyb == 3 || pohyb == 1) && hlava[0] != osax[0])) pohyb = 0;
                    break;
                case W:
                    if (pohyb != 0 && ((pohyb == 3 || pohyb == 1) && hlava[0] != osax[0])) pohyb = 2;
                    break;
            }
            hlava = new int[]{osax[0], osay[0]};
        });
    }

    /**
     * @param event spusti samotnou hru snake
     */
    @FXML
    void startSnakeGame(ActionEvent event) {
        randomTarget = (int) Math.floor(Math.random() * (30 - 15 + 1) + 15);
        btn_start_snake_game.setVisible(false);
        txt_game_snake_target.setText(String.valueOf(randomTarget));
        newGame();
        play();
    }

    /**
     * @param event kliknutim na toto tlacitko se spusti treti hra, a to count game
     */
    @FXML
    void startCountGame(ActionEvent event) {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondsCount--;
                int newSecondsCount = secondsCount;
                txt_count_time.setText("" + newSecondsCount);
                if (secondsCount <= 0) {
                    time.stop();
                    btn_count_start_game.setVisible(true);
                    btn_count_start_game.setDisable(true);
                    btn_count_result_1.setVisible(false);
                    btn_count_result_3.setVisible(false);
                    btn_count_result_2.setVisible(false);
                    hbox_count_txt.setVisible(false);
                    System.out.println(countskore);
                    slugCoin += countskore;
                }
            }
        });

        time.getKeyFrames().add(frame);
        time.playFromStart();
        nextExample();
    }

    /**
     * funkce, ktera vybira nove nahodne priklady a zobrazuje je na Pane
     */
    public void nextExample() {
        int firstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1);
        int secondRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1);
        int sign = (int) Math.floor(Math.random() * (4 - 1 + 1) + 1);
        int chooseBtn = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
        btn_count_start_game.setVisible(false);
        btn_count_result_1.setVisible(true);
        btn_count_result_3.setVisible(true);
        btn_count_result_2.setVisible(true);
        hbox_count_txt.setVisible(true);

        txt_count_first_number.setText(String.valueOf(firstRandomNum));
        txt_count_second_number.setText(String.valueOf(secondRandomNum));

        if (sign == 1) {
            txt_count_sign.setText("+");
            vysledek = firstRandomNum + secondRandomNum;
        } else if (sign == 2) {
            txt_count_sign.setText("-");
            vysledek = firstRandomNum - secondRandomNum;
        } else if (sign == 3) {
            txt_count_sign.setText("*");
            vysledek = firstRandomNum * secondRandomNum;
        } else if (sign == 4) {
            int delFirstRandomNum = 0;
            txt_count_sign.setText("/");
            int randoDel = (int) Math.floor(Math.random() * (9 - 1 + 1) + 1);

            switch (randoDel) {
                case 1: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 2: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 3: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 4: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 5: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 6: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 7: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 8: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                case 9: {
                    delFirstRandomNum = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1) * randoDel;
                }
                default:
                    System.out.println("Invalid number");
            }
            txt_count_first_number.setText(String.valueOf(delFirstRandomNum));
            txt_count_second_number.setText(String.valueOf(randoDel));
            vysledek = delFirstRandomNum / randoDel;

        }
        System.out.println(firstRandomNum + " " + sign + " " + secondRandomNum + " = " + vysledek);


        int randomResult = sign;
        if (chooseBtn == 1) {
            btn_count_result_1.setText(String.valueOf(vysledek));
            btn_count_result_2.setText(String.valueOf(vysledek - randomResult));
            btn_count_result_3.setText(String.valueOf(vysledek + randomResult));
        } else if (chooseBtn == 2) {
            btn_count_result_2.setText(String.valueOf(vysledek));
            btn_count_result_3.setText(String.valueOf(vysledek - randomResult));
            btn_count_result_1.setText(String.valueOf(vysledek + randomResult));
        } else if (chooseBtn == 3) {
            btn_count_result_3.setText(String.valueOf(vysledek));
            btn_count_result_1.setText(String.valueOf(vysledek - randomResult));
            btn_count_result_2.setText(String.valueOf(vysledek + randomResult));
        }
    }

    /**
     * @param event vola se, kdyz hrac klikne na jedno ze tri tlacitek v count game a zkontroluje jestli je spravne
     */
    @FXML
    void clickOnResult(ActionEvent event) {
        String vysledekDva = String.valueOf(vysledek);
        if (event.getSource() == btn_count_result_1 && btn_count_result_1.getText().equals(vysledekDva)) {
            System.out.println("spravne");
            countskore++;
            txt_count_your_skore.setText(String.valueOf(countskore));
            nextExample();
        } else if (event.getSource() == btn_count_result_2 && btn_count_result_2.getText().equals(vysledekDva)) {
            System.out.println("spravne");
            countskore++;
            txt_count_your_skore.setText(String.valueOf(countskore));
            nextExample();
        } else if (event.getSource() == btn_count_result_3 && btn_count_result_3.getText().equals(vysledekDva)) {
            System.out.println("spravne");
            countskore++;
            txt_count_your_skore.setText(String.valueOf(countskore));
            nextExample();
        } else {
            System.out.println("spatna odpoved");
            nextExample();
        }
    }

    /**
     * @param event urcuje co se ma stat, kdyz nekdo klikne na tlacitko vybrat bonus Box
     */
    @FXML
    void collectBox(ActionEvent event) {
        if (event.getSource() == btn_collect_box && secondsBox == 0) {
            int random = (int) Math.floor(Math.random() * (20 - 1 + 1) + 1);
            int randomthink = (int) Math.floor(Math.random() * (7 - 1 + 1) + 1);
            int randomTicket = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);
            System.out.println(randomthink);
            secondsBox = startTime;
            switch (randomthink) {
                case 1, 3, 5 -> {
                    System.out.println(slugCoin);
                    slugCoin += random;
                    System.out.println(random + " " + slugCoin);
                }
                case 2, 4, 6 -> {
                    ticket += randomTicket;
                    System.out.println(ticket);
                }
                case 7 -> {
                    key++;
                    System.out.println(key);
                }
                default -> System.out.println("Invalid number");
            }
        }

        txt_time_box.setText("00:10:00");
        btn_collect_box.setDisable(true);
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secondsBox--;
                novySecBox = secondsBox;
                secBox = novySecBox % 60;
                novySecBox /= 60;
                minBox = novySecBox % 60;
                //System.out.println("00:" + "0" + minBox + ":" + secBox);
                txt_time_box.setText("00:" + "0" + minBox + ":" + secBox);
                if (secondsBox <= 0) {
                    time.stop();
                    btn_collect_box.setDisable(false);
                    btn_collect_box.setText("Collect");
                    txt_time_box.setText("00:" + "0" + minBox + ":" + secBox + "0");
                }
            }
        });

        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /**
     * @param event urcuje co se ma stat, kdyz nekdo klikne na tlacitko vybrat bonus Mine
     */
    @FXML
    void collectMine(ActionEvent event) {
        if (event.getSource() == btn_collect_mine && secondsMine == 0) {
            secondsMine = startTime;
        }
        key--;
        txt_time_mine.setText("24:00:00");
        btn_collect_mine.setDisable(true);
        timeMine.setCycleCount(Timeline.INDEFINITE);
        if (timeMine != null) {
            timeMine.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NumberFormat numFormat = new DecimalFormat();

                secondsMine--;
                mineCoin += 0.000625;
                numFormat = new DecimalFormat("0.#######");
                System.out.println(numFormat.format(mineCoin));
                int novysec = secondsMine;
                int sec = novysec % 60;
                novysec /= 60;
                int min = novysec % 60;
                int hod = novysec / 60;
                System.out.println(hod + ":" + min + ":" + sec);
                txt_time_mine.setText(hod + ":" + min + ":" + sec);
                btn_collect_mine.setText(String.valueOf(numFormat.format(mineCoin)));
                if (secondsMine <= 0) {
                    timeMine.stop();
                    btn_collect_mine.setDisable(false);
                    btn_collect_mine.setText("Collect");
                }
            }
        });

        timeMine.getKeyFrames().add(frame);
        timeMine.playFromStart();
    }
}