package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Controller extends Button{

    @FXML
    protected void open_window(){
        //Node n= pressed();
        //System.out.println(n.getId());
        action_panel.setVisible(true);
    }
    @FXML
    protected void set_active(){
        //Node n= pressed();
        //System.out.println(n.getId());
        g1.setOnMouseClicked(mouseEvent -> {open_announcement();});
        Circle c= Relation.get_circle(g1);
        c.setFill(Paint.valueOf("#3ffc32"));
    }
    @FXML
    protected void nothing(){}

    @FXML
    protected void open_announcement(){
        announcement.setVisible(true);
    }
    @FXML
    protected void close_announcement(){
        announcement.setVisible(false);
    }



}