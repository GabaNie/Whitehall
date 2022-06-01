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
    Rectangle current;
    public void init(){
        map=new HashMap<>();
        array1= new ArrayList<>();
        array1.add("g1");
        array1.add("g2");
        map.put("r1",array1);
        all_group= get_all_groups(board);
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
        c.setFill(Paint.valueOf("#3ffc32"));
    }
    @FXML
    protected void active(){
        init();
        ArrayList<String> arrayList = get_groups(r1);
        for(String s:arrayList){
           //System.out.println(all_group.get(s).getId());
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