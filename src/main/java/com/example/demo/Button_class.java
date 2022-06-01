package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Button_class {
    HashMap<String,Group> all_group;
    static HashMap<String, ArrayList<String>> map;
    static ArrayList<String> array1, array2;
    @FXML
    static VBox main;
    @FXML
    public Pane board;
    @FXML
    public Circle c1;
    @FXML
    public AnchorPane announcement;
    public AnchorPane action_panel;
    @FXML
    public static Group g1;
    public static Group g2;
    @FXML
    public Rectangle r1;
    @FXML
    public Button check;
    public Button kill;
    public Button close;

}
