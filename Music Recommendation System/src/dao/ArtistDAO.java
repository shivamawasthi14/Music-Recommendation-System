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
import core.User;

public class ArtistDAO extends GenericDAO<Artist> {
	private Connection myConn = null;

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		ArtistDAO dao = new ArtistDAO();
		System.out.println(dao.fetchAllArtistsList());
		System.out.println(dao.fetchTopArtists());
	}

	public ArtistDAO() throws FileNotFoundException, IOException, SQLException {
		super();
		myConn = getConnection();
	}

	@Override
	public Artist convertRowToT(ResultSet myRs) throws SQLException {
		Artist a = new Artist(myRs.getInt("artist_id"), myRs.getString("genre"), myRs.getString("artist_name"),
				myRs.getInt("likes"), myRs.getInt("age"), myRs.getString("gender"));
		return a;
	}

	public List<Artist> fetchLikedArtistsList(User user) throws FileNotFoundException, IOException, SQLException {
		List<Artist> artists = new ArrayList<>();
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM user_artists where user_id  = ?");
			myStmt.setInt(1, user.getUserId());
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				int us = rs.getInt(1);
				int artistId = rs.getInt(2);
				artists.add(getArtist(artistId));
			}
		} finally {
			close(myStmt);
		}
		return artists;
	}

	public Artist getArtist(int artistId) throws SQLException {
		PreparedStatement myStmt = null;
		Artist artist = null;
		try {
			myStmt = myConn.prepareStatement("SELECT * FROM artist where artist_id  = ?");
			myStmt.setInt(1, artistId);
			ResultSet rs = myStmt.executeQuery();
			if (rs.next())
				artist = convertRowToT(rs);
		} finally {
			close(myStmt);
		}
		return artist;
	}

	public List<Artist> fetchAllArtistsList() throws SQLException {
		List<Artist> artists = new ArrayList<>();

		Statement stmt = myConn.createStatement();

		String SQL = "SELECT * FROM artist";
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Artist artist = convertRowToT(rs);
			artists.add(artist);
		}

		return artists;
	}

	public List<Artist> fetchTopArtists() throws SQLException {
		List<Artist> artists = new ArrayList<>();

		Statement stmt = myConn.createStatement();

		String SQL = "SELECT * FROM artist ORDER BY likes DESC";
		ResultSet rs = stmt.executeQuery(SQL);

		while (rs.next()) {
			Artist artist = convertRowToT(rs);
			artists.add(artist);
		}

		return artists;
	}

	/*
	 * Add the song as liked by the user
	 */
	public void updatelikeArtist(Artist artist, User user) throws SQLException {
		Statement stmt = myConn.createStatement();
		ResultSet s = stmt.executeQuery("select * from user_artists where user_id = " + user.getUserId()
				+ " AND artist_id = " + artist.getArtistId());
		if (s.next()) {
			System.out.println("Artist already liked.");
			return;
		}
		stmt = myConn.createStatement();
		String SQL = "INSERT into user_artists VALUES(" + user.getUserId() + "," + artist.getArtistId() + ")";
		stmt.executeUpdate(SQL);
		System.out.println("Artist Liked: " + artist.getArtistName());
		
		stmt = myConn.createStatement();
		SQL = "select likes from artist where artist_id = " + artist.getArtistId();
		s = stmt.executeQuery(SQL);
		if(s.next()){
			int likes = s.getInt("likes") + 1;
			String updateStmt = "update artist set likes = "+ likes + " where artist_id = " + artist.getArtistId();
			stmt.executeUpdate(updateStmt);
			System.out.println("Artist Likes Incremented: " + artist.getArtistName());
		}
	}

}
