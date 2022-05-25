package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PhoneDao {
	
	private String id = "phonedb";
	private String pw = "phonedb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int personInsert(PersonVo p) {
		
		int count = -1;
		getConnection();
		
		try {
			String query = "insert into person\nvalues(seq_person_id.nextval, ?, ?, ?) ";
					
			pstmt = conn.prepareStatement(query); 
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());

			count = pstmt.executeUpdate();
											
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int personDelete(int personId) {
		
		int count = -1;
		getConnection();
		
		try {
			String query = "delete from person\nwhere person_id = ?";
						
			pstmt = conn.prepareStatement(query); 
			pstmt.setInt(1, personId);
			
			count = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int personUpdate(int personId, PersonVo p) {

		int count = -1;
		getConnection();
		
		try {
			String query = "update person\nset name = ?, hp = ?, company = ?\nwhere person_id = ?";
						
			pstmt = conn.prepareStatement(query); 
			
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			pstmt.setInt(4, personId);
			
			count = pstmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int personSelect() {
		
		int count = 0;

		getConnection();
		
		try {
			String query = "select person_id, name, hp, company\nfrom person\norder by person_id asc "; 
			
			pstmt = conn.prepareStatement(query); 
		
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				int personID = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);
				
				PersonVo curr = new PersonVo(personID, name, hp, company);
				curr.print();
				count++;
			}
		
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}	
		
		close();
		
		return count;
		
	}
	

	public int personSelect(String find) {
		
		int count = 0;

		getConnection();
		
		try {
			String query = "select person_id, name, hp, company\nfrom person\nwhere name like ? or hp like ? or company like ?\norder by person_id asc "; 
			
			pstmt = conn.prepareStatement(query); 
			
			find = "%" + find + "%";
			pstmt.setString(1, find);
			pstmt.setString(2, find);
			pstmt.setString(3, find);

			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				int personID = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);
				
				PersonVo curr = new PersonVo(personID, name, hp, company);
				curr.print();
				count++;
			}
		
		} catch (SQLException e) {
			System.out.println("errer: " + e);
		}	
		
		close();
		
		return count;
	}
	

	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
}
