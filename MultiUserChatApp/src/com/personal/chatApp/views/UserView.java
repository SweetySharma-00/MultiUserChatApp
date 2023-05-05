package com.personal.chatApp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{

	static int count=0;
	public UserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Login");
		
		
		JLabel welcome=new JLabel("Login");
		welcome.setFont(new Font("Arial",Font.BOLD,40));
		Container container=this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100,70,400,60);
		container.add(welcome);
		
		JButton button=new JButton("Count");
		button.setBounds(100,200,200,60);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				welcome.setText("Count is: "+count);
				
			}
			
		});
		container.add(button);
		setVisible(true);
	}
	public static void main(String []args) {
		UserView userView=new UserView();
	}
}
