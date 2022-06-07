package com.example.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket ss;
    private int numPlayers;
    private ServerSideConnection player1;
    private ServerSideConnection player2;

    public Server(){
        System.out.println("----Game Server-----");
        try{
            ss= new ServerSocket(51734);
        }catch (IOException ex){
            System.out.println("IOExeception from Server");
        }
    }

    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");
            while (numPlayers<2){
                Socket s=ss.accept();
                numPlayers++;
                System.out.println("Player #"+ numPlayers);
                ServerSideConnection ssc= new ServerSideConnection(s,numPlayers);
                if(numPlayers==1){
                    player1=ssc;
                }else {
                    player2=ssc;
                }
                Thread t= new Thread(ssc);
                t.start();
            }
            System.out.println("we now have 2 players.");
        }catch (IOException ex){
            System.out.println("IOException");
        }
    }

    private class ServerSideConnection implements Runnable{

        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private int playerID;

        public ServerSideConnection(Socket s, int id){
            socket=s;
            playerID=id;
            try{
                dataIn= new DataInputStream(socket.getInputStream());
                dataOut= new DataOutputStream(socket.getOutputStream());
            }catch (IOException ex){
                System.out.println("IOException from constructor");
            }
        }
        public void run(){
            try{
                dataOut.writeInt(playerID);
                /*dataOut.writeInt(maxTurns);
                dataOut.writeInt(values[0]);
                dataOut.writeInt(values[1]);
                dataOut.writeInt(values[2]);
                dataOut.writeInt(values[3]);*/
                dataOut.flush();
                while (true){
                    if(playerID==1){
                        //player1ButtonNum=dataIn.readInt();
                        System.out.println("P1 clicked #");
                        //player2.sendButtonNum(player1ButtonNum);
                    }else {
                        //player2ButtonNum=dataIn.readInt();
                        System.out.println("P2 clicked #");
                        //player1.sendButtonNum(player2ButtonNum);
                    }
                }
                //player1.closeConnection();
                //player2.closeConnection();
            }catch (IOException ex){
                System.out.println("IOException from run()");
            }
        }
        public void sendButtonNum(int n){
            try{
                dataOut.writeInt(n);
                dataOut.flush();
            }catch (IOException ex){
                System.out.println("IOException from sendButtonNum()");
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

    public static void main(String[]args){
        Server gs= new Server();
        gs.acceptConnections();
    }



}




