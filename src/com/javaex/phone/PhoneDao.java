package com.javaex.phone;

import java.sql.*;

public class PhoneDao {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String id = "phonedb";
	private String pw = "phonedb";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	
	public void PersonSelect() {
		int count = 0;
		getConnection();
		
		try {
			String query = "select person_id, name, hp, company\nfrom person ";

			pstmt = conn.prepareStatement(query); 
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int personId = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);
				
				PersonVo curr = new PersonVo(personId, name, hp, company);
				curr.print();
				count++;
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		if (count == 0) {
			System.out.println("[조회된 정보가 없습니다.]");
		}
		
		close();
	}
	
	
	public int PersonInsert(PersonVo p) {
		int count = -1;
		getConnection();
		
		try {
			String query = "insert into person\nvalues(seq_person_id.nextval, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
		
			count = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int PersonDelete(int n) {
		
		int count = -1;
		getConnection();
		
		try {
			String query = "delete from person\nwhere person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, n);
			
			count = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	
	public int PersonUpdate(int personId, PersonVo p) {
		int count = -1;
		getConnection();
		
		try {
			String query = "update person\nset name = ?, hp = ?, company = ?\nwhere person_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getHp());
			pstmt.setString(3, p.getCompany());
			pstmt.setInt(4, personId);
			
			count = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
		return count;
	}
	
	
	
	public void PersonSearch(String find) {
		int count = 0;
		find = '%' + find + '%';
		getConnection();
		
		try {			
			String query = "select person_id, name, hp, company\nfrom person\nwhere name = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, find);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PersonVo person = new PersonVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				person.print();
				count++;
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		if (count == 0) {
			System.out.println("[조회된 정보가 없습니다.]");
		} 
		close();
	}
	
	public void PersonSearch(int find) {
		String f = "%" + String.valueOf(find) + "%";
		getConnection();
		
		try {			
			String query = "select person_id, name, hp, company\nfrom person\nwhere hp = ? or company = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, f);
			pstmt.setString(2, f);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PersonVo person = new PersonVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				person.print();
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		}
		
		close();
	}
	
	
	
	private void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("errer: 드라이버 로딩 실패 - " + e);
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
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
