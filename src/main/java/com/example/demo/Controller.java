package com.example.demo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.demo.Relation.*;


public class Controller extends Button_class {
    String green="#3ffc32";
    Rectangle current;
    public void init(){
        map=new HashMap<>();
        array1= new ArrayList<>();
        array1.add("g1");
        array1.add("g2");
        array1.add("g3");
        array1.add("g15");
        map.put("r1",array1);
        array2= new ArrayList<>();
        array2.add("g3");
        array2.add("g4");
        map.put("r2",array2);
        array3= new ArrayList<>();
        array3.add("g4");
        array3.add("g5");
        array3.add("g10");
        array3.add("g6");
        map.put("r3",array3);
        array4= new ArrayList<>();
        array4.add("g6");
        array4.add("g7");
        array4.add("g8");
        map.put("r4",array4);
        array5= new ArrayList<>();
        array5.add("g8");
        array5.add("g9");
        map.put("r5",array5);
        array6= new ArrayList<>();
        array6.add("g9");
        array6.add("g10");
        array6.add("g11");
        map.put("r6",array6);
        all_group= get_all_groups(board);
        all_rectangle= get_all_rectangle(board);
        close_window(announcement);
        open_window(choose_rectangle);
    }
    @FXML
    protected void choose_rectangle(){
        for( Rectangle r: all_rectangle.values()){
            r.setDisable(false);
            r.setStroke(Paint.valueOf(green));
            // funkcja zapisujaca wybrane pole
        }
        close_window(choose_rectangle);
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
    protected void set_active(Group g){
        g.setDisable(false);
        Circle c= get_circle(g);
        c.setFill(Paint.valueOf(green));
    }
    @FXML
    protected void active(){

        ArrayList<String> arrayList = get_groups(current);
        for(String s:arrayList){
           set_active(all_group.get(s));
       }
    }
    @FXML
    protected void a(Event e){
        Group g= (Group) e.getSource();
        System.out.println(g.getId());
    }

    @FXML
    protected void set_1(Event e){
        open_window(action_panel);
        current= (Rectangle) e.getSource();
    }




}