package com.personal.chatApp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.personal.chatApp.dao.UserDAO;
import com.personal.chatApp.dto.UserDTO;
import com.personal.chatApp.utils.UserInfo;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class UserScreen extends JFrame{
	private JTextField userTxt;
	private JPasswordField passwordField;


	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
				
	}
	UserDAO userDAO=new UserDAO();
	
	private void register() {
		String userid=userTxt.getText();
		char[] password=passwordField.getPassword();
		UserDTO userDTO=new UserDTO(userid, password);
		try {
		int result=userDAO.add(userDTO);
		if(result>0) {
//			System.out.println("Record added..");
			JOptionPane.showMessageDialog(this,"Register Succesfully");
		}
		else {
//			System.out.println("Record not added..");
			JOptionPane.showMessageDialog(this,"Register Failed");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issues...");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some Generic Exception raised...");
			ex.printStackTrace();
		}
	}
	
	private void doLogin() {
		String userid=userTxt.getText();
		char[] password=passwordField.getPassword();
		UserDTO userDTO=new UserDTO(userid, password);
		String message="";
		try {
			if(userDAO.isLogin(userDTO)) {
				message = "Welcome "+userid;
				UserInfo.USER_NAME=userid;
				JOptionPane.showMessageDialog(this,"Login Succesfully!"+message);
				setVisible(false);
				dispose();
				DashBoardScreen dashBoard=new DashBoardScreen(message);
				dashBoard.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this,"Invalid userId or Password");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public UserScreen() {
		setTitle("LOGIN");
		getContentPane().setBackground(SystemColor.activeCaption);
		getContentPane().setLayout(null);
		
		userTxt = new JTextField();
		userTxt.setBackground(Color.LIGHT_GRAY);
		userTxt.setFont(new Font("Arial", Font.ITALIC, 20));
		userTxt.setBounds(243, 177, 237, 32);
		getContentPane().add(userTxt);
		userTxt.setColumns(10);
		
		JLabel pwdLb = new JLabel("Password   ");
		pwdLb.setForeground(new Color(0, 0, 0));
		pwdLb.setHorizontalAlignment(SwingConstants.TRAILING);
		pwdLb.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		pwdLb.setBounds(128, 220, 116, 42);
		getContentPane().add(pwdLb);
		
		JLabel lblUserid = new JLabel("UserId   ");
		lblUserid.setForeground(new Color(0, 0, 0));
		lblUserid.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUserid.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblUserid.setBounds(117, 177, 116, 32);
		getContentPane().add(lblUserid);
		
		JLabel loginLb = new JLabel("LOGIN/REGISTER");
		loginLb.setForeground(new Color(0, 0, 0));
		loginLb.setHorizontalAlignment(SwingConstants.CENTER);
		loginLb.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 34));
		loginLb.setBounds(171, 22, 293, 49);
		getContentPane().add(loginLb);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setFont(new Font("Arial", Font.ITALIC, 20));
		passwordField.setBounds(243, 229, 237, 32);
		getContentPane().add(passwordField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setBackground(Color.GRAY);
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		loginBtn.setBounds(117, 310, 176, 42);
		getContentPane().add(loginBtn);
		
		JLabel lblNewLabel = new JLabel("or");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(303, 310, 42, 42);
		getContentPane().add(lblNewLabel);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.setBackground(Color.GRAY);
		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerBtn.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		registerBtn.setBounds(367, 310, 176, 42);
		getContentPane().add(registerBtn);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(UserScreen.class.getResource("/images/grp.jpg")));
		lblNewLabel_1.setBounds(10, 11, 645, 377);
		getContentPane().add(lblNewLabel_1);
		setResizable(false);
		setSize(671, 427);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
