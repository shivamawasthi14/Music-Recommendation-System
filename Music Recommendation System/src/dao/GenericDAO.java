package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class GenericDAO<T> {
	private Connection connection = null;

	public Connection getConnection() {
		return connection;
	}

	public GenericDAO() throws FileNotFoundException, IOException, SQLException {
		Properties props = new Properties();
		props.load(new FileInputStream("database.properties"));
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		connection = DriverManager.getConnection(dburl, user, password);
		System.out.println("DB connection successful to: " + dburl);
	}

	public List<T> getAllColumns(PreparedStatement myStmt) throws SQLException {
		List<T> list = new ArrayList<>();
		ResultSet myRs = null;
		try {
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				T temp = convertRowToT(myRs);
				list.add(temp);
			}
		} finally {
			close(myStmt, myRs);
		}
		return list;
	}

	public List<T> getAllColumns(String sqlQuery) throws SQLException {
		List<T> list = new ArrayList<>();
		ResultSet myRs = null;
		Statement myStmt = null;
		try {
			myStmt = connection.createStatement();
			myRs = myStmt.executeQuery(sqlQuery);
			while (myRs.next()) {
				T temp = convertRowToT(myRs);
				list.add(temp);
			}
		} finally {
			close(myStmt, myRs);
		}
		return list;
	}

	public abstract T convertRowToT(ResultSet myRs) throws SQLException;

	public void close(Statement stmt, Connection conn, ResultSet rs) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
		if (rs != null) {
			rs.close();
		}
	}

	public void close(Statement stmt, ResultSet rs) throws SQLException {
		close(stmt, null, rs);
	}

	public void close(Statement stmt) throws SQLException {
		close(stmt, null, null);
	}
}
