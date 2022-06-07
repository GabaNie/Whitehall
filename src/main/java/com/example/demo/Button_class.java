package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class Button_class {
    HashMap<String,Group> all_group;
    HashMap<String,Rectangle> all_rectangle;
    static HashMap<String, String[]> map_group;
    String[] rect;
    HashMap<String,Rectangle> all_rectangle2;
    static HashMap<String, String[]> map_rectangle;
    @FXML
    static VBox main;
    @FXML
    public Pane board;
    public Pane board2;
    @FXML
    public Circle c1;
    @FXML
    public AnchorPane announcement;
    public AnchorPane action_panel;
    public AnchorPane choose_rectangle;
    public AnchorPane murder1;
    public AnchorPane kuba_check;
    public AnchorPane choosed;
    @FXML
    public static Group g1;
    public static Group g2;
    @FXML
    public Button check;
    public Button kill;
    public Button close;
    public Button go;
    public Button kuba_ok;
    public Button ok2;
    @FXML
    public ImageView spy_red;
    public ImageView spy_blue;
    @FXML
    public Text action_text;
    public Text text_turn;
    public Text kuba_text;

}
