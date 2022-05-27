package com.example.demo;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class Controller extends Button_class {
    Rectangle current;
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
        Circle c= Relation.get_circle(g);
        c.setFill(Paint.valueOf("#3ffc32"));
    }
    @FXML
    protected void active(){
       ArrayList<Group> arrayList = Relation.get_groups(board);
       for(Group g:arrayList){
           System.out.println(g.getId());
           set_active(g);
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