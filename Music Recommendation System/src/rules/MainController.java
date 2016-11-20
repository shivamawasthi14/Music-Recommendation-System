package rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import core.Artist;
import core.Song;
import core.User;
import dao.ArtistDAO;
import dao.SongDAO;
import dao.UserDAO;
import javafx.util.Pair;
import ui.LoginFrame;
import ui.SignupFrame;
import ui.UserProfileFrame;

public class MainController {
	// GUI
	private LoginFrame loginFrame;
	private SignupFrame signupFrame;
	private UserProfileFrame userProfileFrame;

	// DAO
	private UserDAO userDAO;
	private ArtistDAO artistDAO;
	private SongDAO songDAO;

	// Entities
	private User userLogged;

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		new MainController();
	}

	public MainController() throws FileNotFoundException, IOException, SQLException {
		userDAO = new UserDAO();
		artistDAO = new ArtistDAO();
		songDAO = new SongDAO();
		loginFrame = new LoginFrame(this);
	}

	public void register() {
		loginFrame.setVisible(false);
		signupFrame = new SignupFrame(this);
		System.out.print("MainController: register");
	}

	public void login(String userName, String password) throws SQLException, FileNotFoundException, IOException {
		User temp = userDAO.checkCredentials(userName, password);
		if (temp != null) {
			userLogged = temp;
			System.out.println("User Logged: Opening Profile");
			userProfileFrame = new UserProfileFrame(userLogged, this);
			loginFrame.setVisible(false);
			JOptionPane.showMessageDialog(userProfileFrame, "Welcome to Music RS,  Browse freely! Logout when done!", "Welcome",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			System.out.println("Invalid Credentials");
			JOptionPane.showMessageDialog(loginFrame, "Wrong username or password!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void createUser(String userName, String password) throws SQLException {
		// Check if all info is valid, store a reference on adding to database
		userLogged = userDAO.addUser(userName, password);
		if (userLogged != null) {
			System.out.println("User added");
			signupFrame.setVisible(false);
			loginFrame.setVisible(true);
		} else {
			System.out.println("User not added");
		}
	}

	public List<Song> getLikedSongList(User user) throws FileNotFoundException, IOException, SQLException {
		return songDAO.fetchLikedSongsList(user);
	}

	public List<Artist> getLikedArtistList(User user) throws SQLException, FileNotFoundException, IOException {
		return artistDAO.fetchLikedArtistsList(user);
	}

	public List<Song> getAllSongList() throws FileNotFoundException, IOException, SQLException {
		return songDAO.fetchAllSongsList();
	}

	public List<Artist> getAllArtistList() throws SQLException, FileNotFoundException, IOException {
		return artistDAO.fetchAllArtistsList();
	}

	public List<Song> getRecommendedSongList(User user) throws SQLException, FileNotFoundException, IOException {
		List<Song> allSongs = songDAO.fetchAllSongsList();
		List<Song> recommended = new ArrayList<>();
		if (!userDAO.isSongPreferencesSet(user))
			return songDAO.fetchTopSongs();
		List<Song> likedSongs = getLikedSongList(user);
		double userScore = 0.0;
		for (Song song : likedSongs) {
			double score = song.getScore();
			userScore += score;
		}
		userScore /= likedSongs.size();
		List<Pair<Song, Double>> deviations = new ArrayList<>();
		for (Song song : allSongs) {
			double score = song.getScore();
			double diff = Math.abs(score - userScore);
			deviations.add(new Pair<Song, Double>(song, diff));
		}
		deviations.sort(new Comparator<Pair<Song, Double>>() {
			@Override
			public int compare(Pair<Song, Double> o1, Pair<Song, Double> o2) {
				if ((o1.getValue() < o2.getValue()))
					return -1;
				return 1;
			}
		});
		for (Pair<Song, Double> pair : deviations) {
			recommended.add(pair.getKey());
		}
		return recommended.subList(0, 8);
	}

	public List<Artist> getRecommendedArtistList(User user) throws SQLException, FileNotFoundException, IOException {
		List<Artist> allArtists = artistDAO.fetchAllArtistsList();
		List<Artist> recommended = new ArrayList<>();
		if (!userDAO.isArtistPreferencesSet(user))
			return artistDAO.fetchTopArtists();
		List<Artist> likedArtists = getLikedArtistList(user);
		double userScore = 0.0;
		Map<String, Integer> genreCount = new HashMap<String, Integer>();

		double maxAge = 0, minAge = 1000;
		for (Artist artist : likedArtists) {
			Integer freq = genreCount.get(artist.getGenre());
			genreCount.put(artist.getGenre(), (freq == null) ? 1 : freq + 1);
			double age = artist.getAge();
			maxAge = Math.max(maxAge, age);
			minAge = Math.min(maxAge, age);
			userScore += age;
		}
		userScore /= likedArtists.size();
		List<Pair<Artist, Pair<Integer, Double>>> deviations = new ArrayList<>();
		for (Artist artist : allArtists) {
			double age = artist.getAge();
			double diff = Math.abs(age - userScore);
			Integer genrePoints = genreCount.get(artist.getGenre());
			Pair<Integer, Double> value = new Pair<Integer, Double>(genrePoints == null ? 0 : genrePoints, diff);
			deviations.add(new Pair<Artist, Pair<Integer, Double>>(artist, value));
		}
		deviations.sort(new Comparator<Pair<Artist, Pair<Integer, Double>>>() {
			@Override
			public int compare(Pair<Artist, Pair<Integer, Double>> arg0, Pair<Artist, Pair<Integer, Double>> arg1) {
				Pair<Integer, Double> value1 = arg0.getValue(), value2 = arg1.getValue();
				if (value1.getKey() != value2.getKey()) {
					return value1.getKey() > value2.getKey() ? -1 : 1;
				} else {
					return value1.getValue() < value2.getValue() ? -1 : 1;
				}
			}
		});
		for (Pair<Artist, Pair<Integer, Double>> pair : deviations) {
			recommended.add(pair.getKey());
		}
		return recommended.subList(0, 8);
	}

	public void updateLikeSong(Song song, User user) throws SQLException {
		songDAO.updatelikeSong(song, user);
	}

	public void updateLikeArtist(Artist artist, User user) throws SQLException {
		artistDAO.updatelikeArtist(artist, user);
	}

	public boolean isLiked(User user, Song song) throws SQLException {
		return userDAO.isLiked(user, song);
	}

	public boolean isLiked(User user, Artist artist) throws SQLException {
		return userDAO.isLiked(user, artist);
	}

	public void openLogin() {
		loginFrame = new LoginFrame(this);
	}
}
