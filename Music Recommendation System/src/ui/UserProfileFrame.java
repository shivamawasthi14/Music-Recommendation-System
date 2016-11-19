package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import core.Artist;
import core.Song;
import core.User;
import rules.MainController;

public class UserProfileFrame extends JFrame {
	private MainController mainController;
	private User loggedUser;
	private JTabbedPane tabbedPane;
	private JPanel Mainpanel, recommendedSongPane, recommendedArtistPane, allSongPane, allArtistPane, likedSongPane,
			likedArtistPane;
	private JScrollPane rSongScrollPane, rArtistScrollPane, lSongScrollPane, lArtistScrollPane, aSongScrollPane,
			aArtistScrollPane;
	private JList rSongList, rArtistList, lSongList, lArtistList, aSongList, aArtistList;
	private JButton likeSongButton, refreshSongsButton, likeArtistButton, refreshArtistsButton;
	DefaultListModel rSongListModel, rArtistListModel, lSongListModel, lArtistListModel, aSongListModel,
			aArtistListModel;

	public UserProfileFrame(User user, MainController mainController) {
		this.mainController = mainController;
		this.loggedUser = user;
		initSwingComponents();
	}

	public UserProfileFrame() {
		initSwingComponents();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unchecked", "serial", "unchecked" })
	private void displayRecommendedSongs(JPanel panel) {
		rSongListModel = new DefaultListModel();
		rSongList = new JList(rSongListModel);
		rSongList.setCellRenderer(new SongListCellRenderer(rSongList));
		rSongScrollPane = new JScrollPane(rSongList);
		refreshSongsButton = new JButton("Refresh");
		likeSongButton = new JButton("Like");
		refreshSongsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshRecommendedSongList();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		likeSongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					likeSong(rSongList.getSelectedValue());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(rSongScrollPane, BorderLayout.CENTER);
		panel.add(likeSongButton, BorderLayout.NORTH);
		panel.add(refreshSongsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshRecommendedSongList();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void displayRecommendedArtists(JPanel panel) {
		rArtistScrollPane = new JScrollPane();
		rArtistListModel = new DefaultListModel();
		rArtistList = new JList(rArtistListModel);
		rArtistList.setCellRenderer(new ArtistListCellRenderer(rArtistList));
		rArtistScrollPane.setViewportView(rArtistList);

		refreshArtistsButton = new JButton("Refresh");
		likeArtistButton = new JButton("Like");
		refreshArtistsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshRecommendedArtistList();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		likeArtistButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					likeArtist(rArtistList.getSelectedValue());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(rArtistScrollPane, BorderLayout.CENTER);
		panel.add(likeArtistButton, BorderLayout.NORTH);
		panel.add(refreshArtistsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshRecommendedArtistList();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void displayAllSongs(JPanel panel) {
		aSongListModel = new DefaultListModel();
		aSongList = new JList(aSongListModel);
		aSongList.setCellRenderer(new SongListCellRenderer(aSongList));
		aSongScrollPane = new JScrollPane(aSongList);
		List<Song> songs;
		try {
			songs = mainController.getAllSongList();
			aSongList.setListData(songs.toArray());
		} catch (IOException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		likeSongButton = new JButton("Like");
		likeSongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					likeSong(aSongList.getSelectedValue());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(aSongScrollPane, BorderLayout.CENTER);
		panel.add(likeSongButton, BorderLayout.NORTH);
	}

	private void displayAllArtists(JPanel panel) {
		aArtistScrollPane = new JScrollPane();
		aArtistListModel = new DefaultListModel();
		aArtistList = new JList(aArtistListModel);
		aArtistList.setCellRenderer(new ArtistListCellRenderer(aArtistList));
		aArtistScrollPane.setViewportView(aArtistList);
		List<Artist> artists;
		try {
			artists = mainController.getAllArtistList();
			aArtistList.setListData(artists.toArray());
		} catch (SQLException | IOException e2) {
			e2.printStackTrace();
		}

		likeArtistButton = new JButton("Like");
		likeArtistButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					likeArtist(aArtistList.getSelectedValue());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(aArtistScrollPane, BorderLayout.CENTER);
		panel.add(likeArtistButton, BorderLayout.NORTH);
	}

	private void displayLikedSongs(JPanel panel) {
		lSongListModel = new DefaultListModel();
		lSongList = new JList(lSongListModel);
		lSongList.setCellRenderer(new SongListCellRenderer(lSongList));
		lSongScrollPane = new JScrollPane(lSongList);
		refreshSongsButton = new JButton("Refresh");
		refreshSongsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshLikedSongList();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(lSongScrollPane, BorderLayout.CENTER);
		panel.add(refreshSongsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshLikedSongList();
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private void displayLikedArtists(JPanel panel) {
		lArtistScrollPane = new JScrollPane();
		lArtistListModel = new DefaultListModel();
		lArtistList = new JList(lArtistListModel);
		lArtistList.setCellRenderer(new ArtistListCellRenderer(lArtistList));
		lArtistScrollPane.setViewportView(lArtistList);

		refreshArtistsButton = new JButton("Refresh");
		refreshArtistsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshLikedArtistList();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(lArtistScrollPane, BorderLayout.CENTER);
		panel.add(refreshArtistsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshLikedArtistList();
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private void initSwingComponents() {
		setTitle("UserFrame");
		setBounds(20, 20, 1300, 700);
		setLayout(null);
		setResizable(true);

		Mainpanel = new JPanel();
		Mainpanel.setBounds(0, 0, getWidth(), getHeight());
		Mainpanel.setLayout(null);
		Mainpanel.setBackground(new Color(154, 18, 179));

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(20, 20, getWidth() - 50, getHeight() - 70);
		tabbedPane.setBackground(new Color(154, 18, 179));

		recommendedSongPane = new JPanel();
		recommendedSongPane.setLayout(new BorderLayout());
		recommendedSongPane.setBackground(new Color(102, 51, 153));
		displayRecommendedSongs(recommendedSongPane);

		recommendedArtistPane = new JPanel();
		recommendedArtistPane.setLayout(new BorderLayout());
		recommendedArtistPane.setBackground(new Color(102, 51, 153));
		displayRecommendedArtists(recommendedArtistPane);

		allSongPane = new JPanel();
		allSongPane.setLayout(new BorderLayout());
		allSongPane.setBackground(new Color(102, 51, 153));
		displayAllSongs(allSongPane);

		allArtistPane = new JPanel();
		allArtistPane.setLayout(new BorderLayout());
		allArtistPane.setBackground(new Color(102, 51, 153));
		displayAllArtists(allArtistPane);

		likedSongPane = new JPanel();
		likedSongPane.setLayout(new BorderLayout());
		likedSongPane.setBackground(new Color(102, 51, 153));
		displayLikedSongs(likedSongPane);
		
		likedArtistPane = new JPanel();
		likedArtistPane.setLayout(new BorderLayout());
		likedArtistPane.setBackground(new Color(102, 51, 153));
		displayLikedArtists(likedArtistPane);
		
		ImageIcon icon = new ImageIcon("images/a.png");
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addTab("Recommended Songs", icon, recommendedSongPane, "Does nothing");
		tabbedPane.addTab("Recommended Artists", icon, recommendedArtistPane, "Does nothing");
		tabbedPane.addTab("All Songs", icon, allSongPane, "Does nothing");
		tabbedPane.addTab("All Artists", icon, allArtistPane, "Does nothing");
		tabbedPane.addTab("Liked Songs", icon, likedSongPane, "Does nothing");
		tabbedPane.addTab("Liked Artists", icon, likedArtistPane, "Does nothing");

		Mainpanel.add(tabbedPane);
		add(Mainpanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void refreshRecommendedSongList() throws FileNotFoundException, SQLException, IOException {
		List<Song> songs = mainController.getRecommendedSongList(loggedUser);
		rSongList.setListData(songs.toArray());
	}

	void refreshRecommendedArtistList() throws SQLException, FileNotFoundException, IOException {
		List<Artist> artists = mainController.getRecommendedArtistList(loggedUser);
		rArtistList.setListData(artists.toArray());
	}

	void refreshLikedSongList() throws FileNotFoundException, SQLException, IOException {
		List<Song> songs = mainController.getLikedSongList(loggedUser);
		lSongList.setListData(songs.toArray());
	}

	void refreshLikedArtistList() throws SQLException, FileNotFoundException, IOException {
		List<Artist> artists = mainController.getLikedArtistList(loggedUser);
		lArtistList.setListData(artists.toArray());
	}

	public void likeSong(Object object) throws SQLException {
		mainController.updateLikeSong((Song) object, loggedUser);
	}

	public void likeArtist(Object object) throws SQLException {
		mainController.updateLikeArtist((Artist) object, loggedUser);
	}

	public static void main(String args[]) {
		new UserProfileFrame();
	}
}
