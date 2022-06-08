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
    AnchorPane active;
    String[] activeList;

    public void init(){
        player1= new Player();
        player1.connectToServer();
        player1.setUpGUI();
        player2= new Player();
        player2.connectToServer();
        player2.setUpGUI();
        kuba= new Kuba();
        kuba.connectToServer();
        active=choosed;

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
        choosed_text.setText("Gracz "+ activeplayer.playerID+"wybrał pole startowe.");
    }
    @FXML
    protected void choose(Event e){
        close_window(choosed);
        Rectangle r= (Rectangle) e.getSource();
        move(r);
        for( Rectangle rr: all_rectangle.values()){
            rr.setDisable(true);
            rr.setStroke(Paint.valueOf("Black"));
        }
        open_window(active);
    }
    @FXML
    protected void choosed1(){
        close_window(choosed);

        for( Rectangle r: all_rectangle.values()){
            r.setDisable(false);
            r.setStroke(Paint.valueOf(green));
        }
        changeplayer();
        choosed_text.setText("Gracz "+ activeplayer.playerID+"wybrał pole startowe.");
        player1.rectangle.setDisable(true);
        player1.rectangle.setStroke(Paint.valueOf("Black"));
        ok2.setOnMouseClicked(mouseEvent -> start_game());
    }
    @FXML
    protected void start_game(){
        close_window(choosed);
        active=choosed;
        ok2.setOnMouseClicked(mouseEvent -> checked());
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
        activeList = get_groups(activeplayer.rectangle);
    }

    protected void changeplayer(){
        if(activeplayer==player1) {activeplayer=player2;
        text_turn.setText("Gracz 2");}
        else {
            kuba_move();
            if(kuba.turn==9) {
                close_window(action_panel);
                open_window(end);
            }
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
        activeList = get_groups(activeplayer.rectangle);
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
    protected void set_active_rectangle(String rect,String color){
        Rectangle r= all_rectangle.get(rect);
        r.setDisable(false);
        r.setStroke(Paint.valueOf(color));
    }
    @FXML
    protected void check(){
        go.setDisable(true);
        for(String s:activeList){
           set_active_group(all_group.get(s),green);
       }
        close_window(action_panel);
    }
    @FXML
    protected void kill_action(){
        go.setDisable(true);
        kill.setDisable(true);
        check.setDisable(true);
        for(String s:activeList){
            set_active_group(all_group.get(s),green);
        }
        close_window(action_panel);
    }
    @FXML
    protected void go(){
        go.setDisable(true);
        close_window(action_panel);
        set_active_rectangle(activeplayer.rectangle.getId(),green);
        set_move(activeplayer.rectangle.getId(),1);
    }

    protected void set_move(String rect, int n){
        if (n<=3){
        String[] array=map_rectangle.get(rect);
            System.out.println(array);
        for(String r:array){
            if(all_rectangle.get(r).isDisable()){
                set_active_rectangle(r,green);
                set_move(r,n++);
            }
        }}
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
        activeList = get_groups(activeplayer.rectangle);
    }
    @FXML
    protected void a(Event e){
        Group g= (Group) e.getSource();
        if(kuba.was_there(g.getId())){
            set_active_group(g,"Orange");
            kuba_text.setText("Kuba przechodził przez to pole");
            g.setDisable(true);
        }else {
            set_active_group(g,"White");
            kuba_text.setText("Kuba nie przechodził tą drogą");
            g.setDisable(true);
        }
        table_delete(g.getId());
        open_window(kuba_check);


    }
    protected void table_delete(String s){
        String[] n= new String[activeList.length];
        int i=0;
        for(String a:activeList){
            if(a.equals(s)) continue;
            n[i]=a;
            i++;
        }
        activeList=n;
    }
    @FXML
    protected void checked(){
        close_window(kuba_check);
        close_window(choosed);
        open_window(action_panel);
    }


    protected void set_array() {
        rect = new String[]{"t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9"};
        map_rectangle= new HashMap<>();

        map_rectangle.put("r1",new String[]{"r2","r9"});
        map_group.put("r1", new String[]{"g1","g2","g3","g15"});
        map_rectangle.put("r2",new String[]{"r1","r3"});
        map_group.put("r2", new String[]{"g3","g4"});
        map_rectangle.put("r3",new String[]{"r2","r4"});
        map_group.put("r3", new String[]{"g4","g5","g10","g6"});
        map_rectangle.put("r4",new String[]{"r3","r5"});
        map_group.put("r4", new String[]{"g6","g7","g8"});
        map_rectangle.put("r5",new String[]{"r4","r6"});
        map_group.put("r5", new String[]{"g8","g9"});
        map_rectangle.put("r6",new String[]{"r3","r5","r7","r15"});
        map_group.put("r6", new String[]{"g9","g10","g11"});
        map_rectangle.put("r7",new String[]{"r6","r13"});
        map_group.put("r7", new String[]{"g12","g13",});
        map_rectangle.put("r8",new String[]{"r7","r9"});
        map_group.put("r8", new String[]{"g13","g14"});
        map_rectangle.put("r9",new String[]{"r1","r8"});
        map_group.put("r9", new String[]{"g15","g14","g16"});
        map_rectangle.put("r10",new String[]{"r9","r11","r12"});
        map_group.put("r10", new String[]{"g16","g17","g29"});
        map_rectangle.put("r11",new String[]{"r10","r13"});
        map_group.put("r11", new String[]{"g17","g18"});
        map_rectangle.put("r12",new String[]{"r10","r14","r20"});
        map_group.put("r12", new String[]{"g29","g30","g28","g31"});
        map_rectangle.put("r13",new String[]{"r11","r7"});
        map_group.put("r13", new String[]{"g12","g18","g27","g19"});
        map_rectangle.put("r14",new String[]{"r13","r12"});
        map_group.put("r14", new String[]{"g27","g28","g26"});
        map_rectangle.put("r15",new String[]{"r13","r6","r16","r17"});
        map_group.put("r15", new String[]{"g11","g19","g20","g22"});
        map_rectangle.put("r16",new String[]{"r15"});
        map_group.put("r16", new String[]{"g20","g21"});
        map_rectangle.put("r17",new String[]{"r15","r18","r22"});
        map_group.put("r17", new String[]{"g22","g24","g23","g37"});
        map_rectangle.put("r18",new String[]{"r17","r19","r21"});
        map_group.put("r18", new String[]{"g24","g35","g25"});
        map_rectangle.put("r19",new String[]{"r18","r14","r20"});
        map_group.put("r19", new String[]{"g26","g33","g25"});
        map_rectangle.put("r20",new String[]{"r19","r12","r21","r35"});
        map_group.put("r20", new String[]{"g33","g31","g34"});
        map_rectangle.put("r21",new String[]{"r20","r18","r22"});
        map_group.put("r21", new String[]{"g34","g35","g36","g41"});
        map_rectangle.put("r22",new String[]{"r21","r14"});
        map_group.put("r22", new String[]{"g37","g36","g38"});
        map_rectangle.put("r23",new String[]{"r22","r24"});
        map_group.put("r23", new String[]{"g38","g39","g40"});
        map_rectangle.put("r24",new String[]{"r23","r21","r25","r37"});
        map_group.put("r24", new String[]{"g40","g41","g42","g43"});
        map_rectangle.put("r25",new String[]{"r24","r26"});
        map_group.put("r25", new String[]{"g43","g44"});
        map_rectangle.put("r26",new String[]{"r25","r27"});
        map_group.put("r26", new String[]{"g44","g45"});
        map_rectangle.put("r27",new String[]{"r26","r28"});
        map_group.put("r27", new String[]{"g45","g46"});
        map_rectangle.put("r28",new String[]{"r27","r29"});
        map_group.put("r28", new String[]{"g46","g47","g49","g48"});
        map_rectangle.put("r29",new String[]{"r28","r30","r31"});
        map_group.put("r29", new String[]{"g49","g55","g50"});
        map_rectangle.put("r30",new String[]{"r29","r32"});
        map_group.put("r30", new String[]{"g50","g51","g52"});
        map_rectangle.put("r31",new String[]{"r29","r32","r36"});
        map_group.put("r31", new String[]{"g56","g55","g54"});
        map_rectangle.put("r32",new String[]{"r31","r30","r36"});
        map_group.put("r32", new String[]{"g54","g52","g53","g61"});
        map_rectangle.put("r33",new String[]{"r32","r34"});
        map_group.put("r33", new String[]{"g60","g61"});
        map_rectangle.put("r34",new String[]{"r33","r35"});
        map_group.put("r34", new String[]{"g60","g61"});
        map_rectangle.put("r35",new String[]{"r34","r20","r36"});
        map_group.put("r36", new String[]{"g32","g59","g58"});
        map_rectangle.put("r36",new String[]{"r35","r31","r37"});
        map_group.put("r36", new String[]{"g58","g56","g57"});
        map_rectangle.put("r37",new String[]{"r36","r24"});
        map_group.put("r37", new String[]{"g42","g57"});
    }




}