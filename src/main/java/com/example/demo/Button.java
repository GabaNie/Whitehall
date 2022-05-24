package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;


public class Button {
    HashSet<Node> buttons;
    @FXML
    static
    VBox main;
    @FXML
    public Pane action_panel;
    public Pane board;
    @FXML
    public Circle c1;
    @FXML
    public AnchorPane announcement;
    @FXML
    public Group g1;
    public Group g2;
    @FXML
    public Rectangle r1;

    public Button() {
        buttons_start();
        display();
    }

    public void buttons_start(){
        //this.buttons= new HashSet<Node>();
        getChild();
    }
    public void display(){
        System.out.println("Display "+ buttons.size());
        for(Node node: this.buttons){
            System.out.println("-"+node.getTypeSelector());
        }
    }
    public void getChild(){
        ObservableList<Node> ob = board.getChildren();
        for( Node node : ob){
            System.out.println(node.getTypeSelector());
        }
        //System.out.println(ob.size());
        /*try {
            for (Node node : n.getChildrenUnmodifiable()) {
                n.
                buttons.add(node);
                //if (node != null) getChild((Parent) node);
            }
        }catch (NullPointerException e) {}
        */

    }

    public Node pressed(){
        for(Node node: main.getChildrenUnmodifiable() ){
            //if(node.isPressed()) return node;
            System.out.println(node.getId());
        }
        return null;
    }


}
