package com.personal.chatApp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import com.personal.chatApp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textArea;
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		int PORT=Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket=new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		out=socket.getOutputStream();
		in=socket.getInputStream();
		this.textArea=textArea;
		readMessage();
		
//		System.out.println("Client arrived....");
//		System.out.println("Enter the message send to the Server....");
//		Scanner sc=new Scanner(System.in);
//		String message=sc.nextLine();
//		OutputStream out=socket.getOutputStream(); //write bytes on network
//		out.write(message.getBytes());
//		System.out.println("Message send to the Server");
//		sc.close();
//		out.close();
//		socket.close();
	}
	
	public void sendMessage(String message) throws IOException {
		message=message+"\n";
		out.write(message.getBytes());
	}
	
	public void readMessage() {
		worker=new ClientWorker(in,textArea);
		worker.start();
	}

//	public static void main(String[] args) throws UnknownHostException, IOException {
//		// TODO Auto-generated method stub
//      Client client=new Client();
//	}

}
