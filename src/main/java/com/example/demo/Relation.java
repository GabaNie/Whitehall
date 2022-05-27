package com.example.demo;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Relation extends Button_class {
    @FXML
    public Group g1;
    Map<String, ArrayList<Group>> map=new HashMap<>();
    ArrayList<Group> array1, array2;

    public Relation() {
        System.out.println("relation");
        array1= new ArrayList<>();
        if(g1==null) System.out.println("*****");
        array1.add(g1);
        array1.add(g2);
        map.put("r1",array1);
        for (Group b : array1)
        {
            System.out.println("-" +b.getId());
        }

    }
    public static ArrayList<Group> get_groups(Pane board){
        ObservableList<Node> array= board.getChildren();
        ArrayList<Group> arrayList = new ArrayList<>();
        for(Node g: array){
            if(g.getTypeSelector().equals("Group")){
                arrayList.add((Group) g);
            }
        }
        return arrayList;
    }

    public ArrayList<Group> get_groups(Rectangle r){
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
