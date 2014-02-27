package com.xalero.dominion.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BasicDAO implements AutoCloseable {

	private static Logger log = LogManager.getLogManager().getLogger(BasicDAO.class.getName());
	
	protected Connection con;
	
	public BasicDAO() {
		try {
			con = DriverManager.getConnection("jdbc:hsqldb:file:dominion_production", "SA", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws Exception {
		if (con != null) {
			con.close();
		}
	}
	
	public String getName() {
		PreparedStatement ps = null;
		String name = null;
		try {
			ps = con.prepareStatement("SELECT * FROM player LIMIT 1");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
				System.out.println("has string");
			} else {
				System.out.println("doesn't have string");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
