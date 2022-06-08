package com.example.demo;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Kuba {
    public int turn;
    private String[] turns;
    private ClientSideConnection csc;

    public Kuba(){
        turn=0;
        turns= new String[]{"g15", "g16", "g17","g18","g27","g26","g33","g32","g58","g57"};

    }
    public String get_position(){
        return turns[turn];
    }
    public boolean was_there(String g){
        for(int i=0;i<turn;i++){
            if(turns[i].equals(g)) return true;
        }
        return false;
    }
    public void connectToServer(){
        csc= new ClientSideConnection();
    }

    private class ClientSideConnection {
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;

        public ClientSideConnection() {
            System.out.println("----Kuba-----");
            try {
                socket = new Socket("localhost", 51734);
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                System.out.println("IO Exception from CSC");
            }
        }
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
