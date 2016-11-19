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
	private JPanel Mainpanel, Songpane, Artistpane;
	private JScrollPane songScrollPane, artistScrollPane;
	private JList songList, artistList;
	private JButton likeSongButton, refreshSongsButton, likeArtistButton, refreshArtistsButton;
	DefaultListModel songListModel, artistListModel;

	public UserProfileFrame(User user, MainController mainController) {
		this.mainController = mainController;
		this.loggedUser = user;
		initSwingComponents();
	}

	public UserProfileFrame() {
		initSwingComponents();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unchecked", "serial", "unchecked" })
	private void displaySongs(JPanel panel) {
		songListModel = new DefaultListModel();
		songList = new JList(songListModel);
		songList.setCellRenderer(new SongListCellRenderer(songList));
		songScrollPane = new JScrollPane(songList);
		refreshSongsButton = new JButton("Refresh");
		likeSongButton = new JButton("Like");
		refreshSongsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshSongList();
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
					likeSong(songList.getSelectedValue());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(songScrollPane, BorderLayout.CENTER);
		panel.add(likeSongButton, BorderLayout.NORTH);
		panel.add(refreshSongsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshSongList();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private void displayArtists(JPanel panel) {
		artistScrollPane = new JScrollPane();
		artistListModel = new DefaultListModel();
		artistList = new JList(artistListModel);
		artistList.setCellRenderer(new ArtistListCellRenderer(artistList));
		artistScrollPane.setViewportView(artistList);

		refreshArtistsButton = new JButton("Refresh");
		likeArtistButton = new JButton("Like");
		refreshArtistsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					refreshArtistList();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		likeArtistButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					likeArtist(artistList.getSelectedValue());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(artistScrollPane, BorderLayout.CENTER);
		panel.add(likeArtistButton, BorderLayout.NORTH);
		panel.add(refreshArtistsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshArtistList();
		} catch (SQLException | IOException e1) {
			// TODO Auto-generated catch block
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

		Songpane = new JPanel();
		Songpane.setLayout(new BorderLayout());
		Songpane.setBackground(new Color(102, 51, 153));
		displaySongs(Songpane);

		Artistpane = new JPanel();
		Artistpane.setLayout(new BorderLayout());
		Artistpane.setBackground(new Color(102, 51, 153));
		displayArtists(Artistpane);

		ImageIcon icon = new ImageIcon("images/a.png");
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addTab("Songs", icon, Songpane, "Does nothing");
		tabbedPane.addTab("Artists", icon, Artistpane, "Does nothing");

		Mainpanel.add(tabbedPane);
		add(Mainpanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void refreshSongList() throws FileNotFoundException, SQLException, IOException {
		List<Song> songs = mainController.getRecommendedSongList(loggedUser);
		songList.setListData(songs.toArray());
	}

	void refreshArtistList() throws SQLException, FileNotFoundException, IOException {
		List<Artist> artists = mainController.getRecommendedArtistList(loggedUser);
		artistList.setListData(artists.toArray());
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
