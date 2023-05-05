package com.personal.chatApp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread{
	private Socket clientSocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
	public ServerWorker(Socket clientSocket,Server server) throws IOException {
		this.server=server;
		this.clientSocket=clientSocket;
		in=clientSocket.getInputStream(); //Client data read
		out=clientSocket.getOutputStream(); //Client data write
		System.out.println("New client arrives....");
	}
	@Override
	public void run() {
		//read the data from the client and broadcast it to all
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		String line;
		try {
			while(true) {
				line=br.readLine();//it needs \n at the end to read line
				System.out.println("Line read "+line);
				line=line+"\n";
				if(line.equalsIgnoreCase("quit")){
					break;//client chat ends
				}
//				out.write(line.getBytes());//Single client sends
				//Broadcast to all
				for(ServerWorker serverWorker : server.workers) {
					
					serverWorker.out.write(line.getBytes());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
			if(br != null) {
				br.close();
			}
			if(in != null) {
				in.close();
			}
			if(out != null) {
				out.close();
			}
			if(clientSocket != null) {
				clientSocket.close();
			}
			}
			catch(Exception ex) {
			 ex.printStackTrace();
			}
		}
	}

}
