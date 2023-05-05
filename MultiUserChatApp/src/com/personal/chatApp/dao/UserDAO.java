package com.personal.chatApp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.personal.chatApp.dto.UserDTO;
import com.personal.chatApp.utils.Encryption;

public class UserDAO {

	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException,Exception {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		final String SQL="select userid from users where userid=? and password=?";
		try {
			con=CommonDAO.createConnection();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1,userDTO.getUserid());
			pstmt.setString(2,Encryption.passwordEncrypt(new String(userDTO.getPassword())));
			rs=pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
	}
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		Connection connection=null;
		Statement stmt=null;
		try {
		connection=CommonDAO.createConnection();
		stmt=connection.createStatement();
		int record=stmt.executeUpdate("insert into users(userid,password) values('"+
		userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"');");
		return record;
		}
		finally {
			if(stmt != null) {
			stmt.close();
			}
			if(connection != null) {
			connection.close();
			}
		}
		
	}
}
