package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private Pane action_panel;
    @FXML
    private Circle c1;

    @FXML
    protected void open_window(){
        action_panel.setVisible(true);
    }
    @FXML
    protected void set_active(){
        c1.setFill(Paint.valueOf("#3ffc32"));

    }


}