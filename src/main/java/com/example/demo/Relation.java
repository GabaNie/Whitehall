package com.example.demo;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;


public class Relation extends Button_class {

    public static HashMap<String,Group> get_all_groups(Pane board){
        ObservableList<Node> array= board.getChildren();
        HashMap<String,Group> list= new HashMap<>();
        for(Node g: array){
            if(g.getTypeSelector().equals("Group")){
                list.put(g.getId(), (Group) g);
            }
        }
        return list;
    }
    public static HashMap<String,Rectangle> get_all_rectangle(Pane board){
        ObservableList<Node> array= board.getChildren();
        HashMap<String,Rectangle> list= new HashMap<>();
        for(Node g: array){
            if(g.getTypeSelector().equals("Rectangle")){
                list.put(g.getId(), (Rectangle) g);
            }
        }
        return list;
    }

    public static ArrayList<String> get_groups(Rectangle r){
        return map.get(r.getId());
    }

    static Circle get_circle(Group g){
        for (Node node : g.getChildren()){
            if (node.getTypeSelector().equals("Circle")) {
                return (Circle) node;
            }
        }
        return null;
    }
}
