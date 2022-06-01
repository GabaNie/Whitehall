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
        array2= new ArrayList<>();
        array2.add("g3");
        array2.add("g4");
        map.put("r1",array1);
        map.put("r2",array2);
        all_group= get_all_groups(board);
        all_rectangle= get_all_rectangle(board);
        close_window(announcement);
        open_window(wybierz_pole);
    }
    @FXML
    protected void choose_rectangle(){
        for( Rectangle r: all_rectangle.values()){
            r.setDisable(false);
            r.setStroke(Paint.valueOf(green));
        }
        close_window(wybierz_pole);
    }
    @FXML
    protected void open_window(AnchorPane a){
        a.setVisible(true);
    }
    @FXML
    protected  void close_window(AnchorPane a){
        a.setVisible(false);
        current=null;
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