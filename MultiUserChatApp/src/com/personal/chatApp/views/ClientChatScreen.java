package com.personal.chatApp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.personal.chatApp.network.Client;
import com.personal.chatApp.utils.UserInfo;
import java.awt.Color;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//					try {
//						ClientChatScreen frame = new ClientChatScreen();
//					} catch (UnknownHostException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(Color.WHITE);
		client=new Client(textArea);
		setTitle("Chit Chat with "+UserInfo.USER_NAME);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 651, 261);
		contentPane.add(scrollPane);
		
		
		textArea.setFont(new Font("Arial", Font.PLAIN, 16));
		textArea.setBounds(10, 24, 713, 280);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setBounds(10, 278, 554, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMsg();
			}
		});
		send.setBounds(574, 278, 95, 27);
		contentPane.add(send);
		setVisible(true);
	}

	
	private void sendMsg() {
		String message=textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+" - "+message);
			textField.setText("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
