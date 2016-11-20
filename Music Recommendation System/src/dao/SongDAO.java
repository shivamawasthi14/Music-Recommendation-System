package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.Artist;
import core.Song;
import core.User;

public class SongDAO extends GenericDAO<Song> {
	private Connection myConn = null;
	private UserDAO userDAO;
	private ArtistDAO artistDAO;

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		SongDAO dao = new SongDAO();
		System.out.println(dao.fetchAllSongsList());
		System.out.println(dao.fetchTopSongs());
	}

	public SongDAO() throws FileNotFoundException, IOException, SQLException {
		// TODO Auto-generated constructor stub
		super();
		myConn = getConnection();
		userDAO = new UserDAO();
		artistDAO = new ArtistDAO();
	}

	@Override
	public Song convertRowToT(ResultSet myRs) throws SQLException {

		Song s = new Song(myRs.getInt("song_id"), myRs.getString("title"), myRs.getString("artist_name"),
				myRs.getString("album"), myRs.getString("genre"), myRs.getDouble("danceability"),
				myRs.getDouble("lyrics"), myRs.getDouble("energy"), myRs.getDouble("tempo"), myRs.getInt("likes"));
		return s;
	}

	public List<Song> fetchLikedSongsList(User user) throws FileNotFoundException, IOException, SQLException {
		List<Song> songs = new ArrayList<>();
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM user_songs where user_id  = ?");
			myStmt.setInt(1, user.getUserId());
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				int userId = rs.getInt(1);
				int songId = rs.getInt(2);
				songs.add(getSong(songId));
			}
		} finally {
			close(myStmt);
		}
		return songs;
	}

	public Song getSong(int songId) throws SQLException {
		PreparedStatement myStmt = null;
		Song song = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM song where song_id  = ?");
			myStmt.setInt(1, songId);
			ResultSet rs = myStmt.executeQuery();
			if (rs.next())
				song = convertRowToT(rs);
		} finally {
			close(myStmt);
		}
		return song;
	}

	/*
	 * Add the song as liked by the user
	 */
	public void updatelikeSong(Song song, User user) throws SQLException {
		Statement stmt = myConn.createStatement();
		ResultSet s = stmt.executeQuery(
				"select * from user_songs where user_id = " + user.getUserId() + " AND song_id = " + song.getSongId());
		if (s.next()) {
			System.out.println("Song already liked");
			return;
		}
		stmt = myConn.createStatement();
		String SQL = "INSERT into user_songs VALUES(" + user.getUserId() + "," + song.getSongId() + ")";

		stmt.executeUpdate(SQL);
		SQL = "select * from artist where artist_name = '" + song.getArtistName() + "'";
		s = stmt.executeQuery(SQL);
		if (s.next()) {
			Artist artist = artistDAO.convertRowToT(s);
			artistDAO.updatelikeArtist(artist, user);
		} else {
			System.out.println("Error: Artist corresponding to user not present");
		}
		System.out.println("Song liked: " + song.getTitle());
		
		stmt = myConn.createStatement();
		SQL = "select likes from song where song_id = " + song.getSongId();
		s = stmt.executeQuery(SQL);
		if(s.next()){
			int likes = s.getInt("likes") + 1;
			String updateStmt = "update song set likes = "+ likes + " where song_id = " + song.getSongId();
			stmt.executeUpdate(updateStmt);
			System.out.println("Song Likes Incremented: " + song.getTitle());
		}
	}

	public List<Song> fetchAllSongsList() throws SQLException {
		List<Song> songs = new ArrayList<>();

		Statement stmt = myConn.createStatement();

		String SQL = "SELECT * FROM song";
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Song song = convertRowToT(rs);
			songs.add(song);
		}

		return songs;
	}

	public List<Song> fetchTopSongs() throws SQLException {
		List<Song> songs = new ArrayList<>();

		Statement stmt = myConn.createStatement();
		String SQL = "SELECT * FROM song ORDER BY likes DESC";
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Song song = convertRowToT(rs);
			songs.add(song);
		}

		return songs;
	}

}
