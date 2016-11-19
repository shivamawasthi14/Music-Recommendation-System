package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.Artist;
import core.Song;
import core.User;

public class UserDAO extends GenericDAO<User> {
	private Connection myConn = null;

	public UserDAO() throws FileNotFoundException, IOException, SQLException {
		super();
		myConn = getConnection();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		UserDAO dao = new UserDAO();
		dao.addUser("tara073", "iiita123");
	}

	public User addUser(String userName, String password) throws SQLException {
		System.out.println("addUser: " + userName + " " + password);
		PreparedStatement myStmt = null;
		User newUser = null;
		try {
			myStmt = myConn.prepareStatement(
					"insert into user (`password`,`user_name`,`is_preferences_set`) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			myStmt.setString(1, password);
			myStmt.setString(2, userName);
			myStmt.setBoolean(3, false);
			System.out.println(myStmt.toString());
			try {
				int rowsInserted = myStmt.executeUpdate();
				System.out.println("Rows Inserted = " + rowsInserted);
				ResultSet rs = myStmt.getGeneratedKeys();
				rs.next();
				int autoId = rs.getInt(1);
				newUser = new User(autoId, userName, password);
				System.out.println(newUser);
			} catch (Exception e) {
				System.out.println("User already present.");
			}
		} finally {
			close(myStmt);
		}
		return newUser;
	}

	public void updateUserPreferences(User user) throws SQLException {
		Statement stmt = myConn.createStatement();
		String SQL = "UPDATE user SET is_preferences_set = true where user_id = " + user.getUserId();
		stmt.executeUpdate(SQL);
	}

	@Override
	public User convertRowToT(ResultSet myRs) throws SQLException {
		return new User(myRs.getInt("user_id"), myRs.getString("user_name"), myRs.getString("password"));
	}

	public User checkCredentials(String user_name, String password) throws SQLException {
		PreparedStatement myStmt = null;
		User newUser = null;
		try {
			myStmt = myConn.prepareStatement("select * from user where user_name = ? AND password = ?");
			myStmt.setString(2, password);
			myStmt.setString(1, user_name);
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				newUser = convertRowToT(rs);
			}
		} finally {
			close(myStmt);
		}
		return newUser;
	}

	public boolean isSongPreferencesSet(User user) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("select * from user_songs where user_id = ?");
			myStmt.setInt(1, user.getUserId());
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				System.out.println("Song preferences set for user.");
				return true;
			}
		} finally {
			close(myStmt);
		}
		System.out.println("Song preferences not set for user.");
		return false;
	}

	public boolean isArtistPreferencesSet(User user) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("select * from user_artists where user_id = ?");
			myStmt.setInt(1, user.getUserId());
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				System.out.println("Artist preferences set for user.");
				return true;
			}
		} finally {
			close(myStmt);
		}
		System.out.println("Artist preferences not set for user.");
		return false;
	}

	public boolean isLiked(User user, Song song) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("select * from user_songs where user_id = ? AND song_id = ?");
			myStmt.setInt(1, user.getUserId());
			myStmt.setInt(2, song.getSongId());
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} finally {
			close(myStmt);
		}
		return false;
	}

	public boolean isLiked(User user, Artist artist) throws SQLException {
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("select * from user_artists where user_id = ? AND artist_id = ?");
			myStmt.setInt(1, user.getUserId());
			myStmt.setInt(2, artist.getArtistId());
			ResultSet rs = myStmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} finally {
			close(myStmt);
		}
		return false;
	}

}
