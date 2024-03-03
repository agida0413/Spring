package com.sist.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

public class MainClass {

	public static void main(String[] args) {
	
		connection();
	}
	
	
	public static void connection() {
		String url="jdbc:mysql://localhost:3306/mydb?serverTimeZone=UTC";
		String driver="com.mysql.cj.jdbc.Driver";//username,password = >root 
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url,"root","root");
			String sql="SELECT goods_name,goods_price FROM goods_all_data "
						+"ORDER BY no ASC "
						+"LIMIT 0,20";//페이지
			PreparedStatement ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString(1)+rs.getString(2));
				}
				
						} catch (Exception e) {
							e.printStackTrace();
			// TODO: handle exception
		}
	}
}
