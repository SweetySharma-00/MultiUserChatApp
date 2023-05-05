package com.personal.chatApp.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.personal.chatApp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers=new ArrayList<>();//contain all the client socket
	public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
		System.out.println("Server starts and waiting for the Clients to join....");
		handleClientRequest();
	}
	//Multiple Client Handshaking
	public void handleClientRequest() throws IOException {
		while(true) {
			 Socket clientSocket=serverSocket.accept(); //handshaking process
			 //Per Client Per Thread (per thread per stack)
			 ServerWorker serverWorker=new ServerWorker(clientSocket,this); //creating a new worker/thread
			 workers.add(serverWorker);
			 serverWorker.start();
			}
	}
	
	
	//Code for Single Client HandShaking
	/*public Server() throws IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket=new ServerSocket(PORT);
        System.out.println("Server started and waiting for the Client Connection.....");
        Socket socket=serverSocket.accept(); //handshaking process
        System.out.println("Client joins the Server...");
        InputStream in=socket.getInputStream(); //reads bytes from the network
        byte[] arr=in.readAllBytes();
        String str=new String(arr); //  converts byte into string
        System.out.println("Message received from client "+str);
        in.close();
        socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
       Server server=new Server();
	}

}
