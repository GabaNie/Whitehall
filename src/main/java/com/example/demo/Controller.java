package com.example.demo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.demo.Relation.*;


public class Controller extends Button_class {
    String green="#3ffc32";
    Rectangle current;
    private Player player1;
    private Player player2;
    private Kuba kuba;
    private Player activeplayer;

    public void init(){
        player1= new Player();
        player1.connectToServer();
        player1.setUpGUI();
        player2= new Player();
        player2.connectToServer();
        player2.setUpGUI();
        kuba= new Kuba();
        kuba.connectToServer();

        map_group=new HashMap<>();
        set_array();
        all_group= get_all_groups(board);
        all_rectangle= get_all_rectangle(board);
        all_rectangle2=get_all_rectangle2(board2);
        close_window(announcement);
        activeplayer=player1;
        open_window(choose_rectangle);
    }
    @FXML
    protected void choose_rectangle(){
        for( Rectangle r: all_rectangle.values()){
            r.setDisable(false);
            r.setStroke(Paint.valueOf(green));
        }
        close_window(choose_rectangle);
    }
    @FXML
    protected void choose(Event e){
        Rectangle r= (Rectangle) e.getSource();
        if(activeplayer==player1){
            move(r);
            System.out.println("player1");
            r.setDisable(true);
            r.setStroke(Paint.valueOf("Black"));
            activeplayer=player2;
        }else {
            move(r);
            System.out.println("player2");
            for( Rectangle rr: all_rectangle.values()){
                rr.setDisable(true);
                rr.setStroke(Paint.valueOf("Black"));
                rr.setOnMouseClicked(event->choose1());
            }
            start_game();
        }
    }
    @FXML
    protected void start_game(){
        String s=kuba.get_position();
        Circle c= get_circle(all_group.get(s));
        c.setFill(Paint.valueOf("Red"));
        s=rect[kuba.turn];
        Rectangle r=all_rectangle2.get(s);
        r.setFill(Paint.valueOf("Red"));
        open_window(murder1);
        activeplayer=player1;
    }
    @FXML
    protected void action(){
        close_window(murder1);
        action_text.setText("Graczu "+activeplayer.playerID+"\n jaką akcje chcesz wykonać?");
        open_window(action_panel);
    }

    protected void changeplayer(){
        if(activeplayer==player1) {activeplayer=player2;
        text_turn.setText("Gracz 2");}
        else {
            kuba_move();
            activeplayer = player1;
            text_turn.setText("Gracz 1");
        }
    }
    protected void kuba_move(){
        kuba.turn++;
        String s=rect[kuba.turn];
        Rectangle r=all_rectangle2.get(s);
        r.setFill(Paint.valueOf(green));
    }
    @FXML
    protected void move(Rectangle r){
        if(activeplayer==player1){
            player1.setRectangle(r);
            spy_red.setLayoutX(r.getLayoutX()-12.5);
            spy_red.setLayoutY(r.getLayoutY()-50+28);
            spy_red.setVisible(true);
        }
        else {
            player2.setRectangle(r);
            spy_blue.setLayoutX(r.getLayoutX()-12.5);
            spy_blue.setLayoutY(r.getLayoutY()-50+28);
            spy_blue.setVisible(true);
        }
    }

    @FXML
    protected void open_window(AnchorPane a){
        a.setVisible(true);
    }
    @FXML
    protected  void close_window(AnchorPane a){
        a.setVisible(false);
    }

    @FXML
    protected void set_active_group(Group g,String color){
        g.setDisable(false);
        Circle c= get_circle(g);
        c.setFill(Paint.valueOf(color));
    }
    @FXML
    protected void check(){
        go.setDisable(true);
        ArrayList<String> arrayList = get_groups(activeplayer.rectangle);
        for(String s:arrayList){
           set_active_group(all_group.get(s),green);
       }
        close_window(action_panel);
    }
    @FXML
    protected void kill_action(){
        go.setDisable(true);
        kill.setDisable(true);
        check.setDisable(true);
    }
    @FXML
    protected void go(){

    }
    @FXML
    protected void end_action(){
        check.setDisable(false);
        kill.setDisable(false);
        close.setDisable(false);
        go.setDisable(false);
        close_window(action_panel);
        changeplayer();
        action_text.setText("Graczu "+activeplayer.playerID+"\n jaką akcje chcesz wykonać?");
        open_window(action_panel);
    }
    @FXML
    protected void a(Event e){
        Group g= (Group) e.getSource();
        open_window(kuba_check);
        g.setDisable(true);
        set_active_group(g,"White");
    }
    @FXML
    protected void checked(){
        close_window(kuba_check);
        open_window(action_panel);
    }

    @FXML
    protected void choose1(){
        //current= (Rectangle) e.getSource();
    }

    protected void set_array() {
        rect = new String[]{"t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9"};
        map_rectangle= new HashMap<>();
        map_rectangle.put("r1",new String[]{"r2"});
        array1 = new ArrayList<>();
        array1.add("g1");
        array1.add("g2");
        array1.add("g3");
        array1.add("g15");
        map_group.put("r1", array1);
        array2 = new ArrayList<>();
        array2.add("g3");
        array2.add("g4");
        map_group.put("r2", array2);
        array3 = new ArrayList<>();
        array3.add("g4");
        array3.add("g5");
        array3.add("g10");
        array3.add("g6");
        map_group.put("r3", array3);
        array4 = new ArrayList<>();
        array4.add("g6");
        array4.add("g7");
        array4.add("g8");
        map_group.put("r4", array4);
        array5 = new ArrayList<>();
        array5.add("g8");
        array5.add("g9");
        map_group.put("r5", array5);
        array6 = new ArrayList<>();
        array6.add("g9");
        array6.add("g10");
        array6.add("g11");
        map_group.put("r6", array6);

    }




}