package com.example.demo;

import java.net.*;
import java.io.*;


public class Player{
    public String rectangleID;
    //private int width;
    //private int height;
    private int playerID;
    private int otherPlayer;
    private int turnsMade;
    private boolean buttonEnable;

    private ClientSideConnection csc;

    public Player(){
        rectangleID= "";
        //width=w;
        //height=h;
        turnsMade=0;
    }
    public void setRectangle(String recID){
        rectangleID= recID;
    }
    public void setUpGUI(){

        if(playerID==1){
            //message.setText("You are player #1. You go first.");
            otherPlayer=2;
            buttonEnable=true;
        }else {
            //message.setText("You are player #2. Wait for your turn.");
            otherPlayer=1;
            buttonEnable=false;
            Thread t= new Thread(new Runnable() {
                public void run() {
                    updateTurn();
                }
            });
            t.start();
        }
        //toggleButtons();
        //this.setVisible(true);
    }
    public void connectToServer(){
        csc= new ClientSideConnection();
    }
/*
    public  void setUpButton(){
        ActionListener al=new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                JButton b= (JButton) ae.getSource();
                int bNum = Integer.parseInt(b.getText());

                message.setText("You clicked button"+ bNum);
                turnsMade++;
                System.out.println("Turns made"+turnsMade);

                buttonEnable=false;
                toggleButtons();

                myPoints+=values[bNum-1];
                System.out.println("My point"+myPoints);
                csc.sendButtonNum(bNum);

                if(playerID==2 && turnsMade==maxTurns){
                    checkWinner();
                }else{
                    Thread t= new Thread(new Runnable() {
                        public void run() {
                            updateTurn();
                        }
                    });
                    t.start();
                }}
        };
        b1.addActionListener(al);
        b2.addActionListener(al);
        b3.addActionListener(al);
        b4.addActionListener(al);
    }

    public  void toggleButtons(){
        b1.setEnabled(buttonEnable);
        b2.setEnabled(buttonEnable);
        b3.setEnabled(buttonEnable);
        b4.setEnabled(buttonEnable);
    }


    public void startRecivingButtonNums(){
        Thread t= new Thread(new Runnable() {
            public void run() {
                while (true){
                    csc.receiveButtonNum();
                }
            }
        });
        t.start();
    }
 */
    public void updateTurn(){
        //int n= csc.receiveButtonNum();
        //message.setText("Your enemy click b #"+n);
        //enemyPoints+=values[n-1];
        //System.out.println("Your enemy has"+ enemyPoints);
        buttonEnable= true;
        if(playerID==1 ){
            //checkWinner();
        }else {
            buttonEnable= true;
        }
        //toggleButtons();
    }
    /*
    private void checkWinner(){
        buttonEnable= false;
        if(myPoints>enemyPoints){
            message.setText("You WON!"+ myPoints);
        }else if (myPoints<enemyPoints){
            message.setText("You LOST!"+ myPoints);
        }else {
            message.setText("It's a tie!");
        }
        csc.closeConnection();
    }
*/
    private class ClientSideConnection{
        private Socket socket;
        private  DataInputStream dataIn;
        private DataOutputStream dataOut;

        public ClientSideConnection(){
            System.out.println("----Client-----");
            try{
                socket= new Socket("localhost", 51734);
                dataIn= new DataInputStream(socket.getInputStream());
                dataOut= new DataOutputStream(socket.getOutputStream());
                playerID= dataIn.readInt();
                System.out.println("Connected to server as Player #"+playerID);
                /*
                maxTurns= dataIn.readInt()/2;
                values[0]= dataIn.readInt();
                values[1]= dataIn.readInt();
                values[2]= dataIn.readInt();
                values[3]= dataIn.readInt();
                System.out.println("maxTurns"+maxTurns);
                System.out.println("Value #1 is "+values[0]);
                System.out.println("Value #2 is "+values[1]);
                System.out.println("Value #3 is "+values[2]);
                System.out.println("Value #4 is "+values[3]);*/
            }catch (IOException ex){
                System.out.println("IO Exception from CSC");
            }
        }
        /*
        public void sendButtonNum(int n){
            try{
                dataOut.writeInt(n);
                dataOut.flush();
            }catch (IOException ex){
                System.out.println("IOExcetion from sendButtonNum()");
            }
        }

        public int receiveButtonNum(){
            int n=-1;
            try{
                n= dataIn.readInt();
                System.out.println("Player click"+otherPlayer);
            }catch (IOException ex){
                System.out.println("IOException from reciveButtonNum");
            }
            return n;
        }*/
        public void closeConnection(){
            try{
                socket.close();
                System.out.println("----Connenction closed-----");
            }catch (IOException ex){
                System.out.println("IOException connection close");
            }
        }
    }

}

