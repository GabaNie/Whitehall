package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;

public class Relation extends Button{
    HashMap map=new HashMap<Rectangle,ArrayList>();
    ArrayList array1, array2;

    public Relation() {
        map.put(r1,array1);

        array1=new ArrayList<Group>();
        array1.add(g1);
        array1.add(g2);

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
