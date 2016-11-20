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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.Artist;
import core.Song;
import core.User;
import dao.UserDAO;
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
	private JButton logOut, rLikeSongButton, rRefreshSongsButton, rLikeArtistButton, rRefreshArtistsButton;
	private JButton lRefreshSongsButton, lRefreshArtistsButton;
	private JButton aLikeSongButton, aLikeArtistButton;

	DefaultListModel rSongListModel, rArtistListModel, lSongListModel, lArtistListModel, aSongListModel,
			aArtistListModel;
	private JLabel name;

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
		rRefreshSongsButton = new JButton("Refresh");
		rLikeSongButton = new JButton("Like");
		rRefreshSongsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(UserProfileFrame.this, "Entries Refreshed!", "Operation Successful",
							JOptionPane.INFORMATION_MESSAGE);
					refreshRecommendedSongList();
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		rLikeSongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (rSongList.getSelectedValue() != null) {
						if (mainController.isLiked(loggedUser, (Song) rSongList.getSelectedValue()))
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry previously liked!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						else {
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry liked!", "Operation Successful",
									JOptionPane.INFORMATION_MESSAGE);
						}
						likeSong(rSongList.getSelectedValue());
					} else {
						JOptionPane.showMessageDialog(UserProfileFrame.this, "Select an entry, then press like!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(rSongScrollPane, BorderLayout.CENTER);
		panel.add(rLikeSongButton, BorderLayout.NORTH);
		panel.add(rRefreshSongsButton, BorderLayout.AFTER_LAST_LINE);
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

		rRefreshArtistsButton = new JButton("Refresh");
		rLikeArtistButton = new JButton("Like");
		rRefreshArtistsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(UserProfileFrame.this, "Entries Refreshed!", "Operation Successful",
							JOptionPane.INFORMATION_MESSAGE);
					refreshRecommendedArtistList();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		rLikeArtistButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (rArtistList.getSelectedValue() != null) {
						if (mainController.isLiked(loggedUser, (Artist) rArtistList.getSelectedValue()))
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry previously liked!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						else {
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry liked!", "Operation Successful",
									JOptionPane.INFORMATION_MESSAGE);
						}
						likeArtist(rArtistList.getSelectedValue());
					} else {
						JOptionPane.showMessageDialog(UserProfileFrame.this, "Select an entry, then press like!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(rArtistScrollPane, BorderLayout.CENTER);
		panel.add(rLikeArtistButton, BorderLayout.NORTH);
		panel.add(rRefreshArtistsButton, BorderLayout.AFTER_LAST_LINE);
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

		aLikeSongButton = new JButton("Like");
		aLikeSongButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (aSongList.getSelectedValue() != null) {
						if (mainController.isLiked(loggedUser, (Song) aSongList.getSelectedValue()))
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry previously liked!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						else {
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry liked!", "Operation Successful",
									JOptionPane.INFORMATION_MESSAGE);
						}
						likeSong(aSongList.getSelectedValue());
					} else {
						JOptionPane.showMessageDialog(UserProfileFrame.this, "Select an entry, then press like!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(aSongScrollPane, BorderLayout.CENTER);
		panel.add(aLikeSongButton, BorderLayout.NORTH);
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

		aLikeArtistButton = new JButton("Like");
		aLikeArtistButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (aArtistList.getSelectedValue() != null) {
						if (mainController.isLiked(loggedUser, (Artist) aArtistList.getSelectedValue()))
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry previously liked!", "Message",
									JOptionPane.INFORMATION_MESSAGE);
						else {
							JOptionPane.showMessageDialog(UserProfileFrame.this, "Entry liked!", "Operation Successful",
									JOptionPane.INFORMATION_MESSAGE);
						}
						likeArtist(aArtistList.getSelectedValue());
					} else {
						JOptionPane.showMessageDialog(UserProfileFrame.this, "Select an entry, then press like!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(aArtistScrollPane, BorderLayout.CENTER);
		panel.add(aLikeArtistButton, BorderLayout.NORTH);
	}

	private void displayLikedSongs(JPanel panel) {
		lSongListModel = new DefaultListModel();
		lSongList = new JList(lSongListModel);
		lSongList.setCellRenderer(new SongListCellRenderer(lSongList));
		lSongScrollPane = new JScrollPane(lSongList);
		lRefreshSongsButton = new JButton("Refresh");
		lRefreshSongsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(UserProfileFrame.this, "Entries Refreshed!", "Operation Successful",
							JOptionPane.INFORMATION_MESSAGE);
					refreshLikedSongList();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(lSongScrollPane, BorderLayout.CENTER);
		panel.add(lRefreshSongsButton, BorderLayout.AFTER_LAST_LINE);
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

		lRefreshArtistsButton = new JButton("Refresh");
		lRefreshArtistsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(UserProfileFrame.this, "Entries Refreshed!", "Operation Successful",
							JOptionPane.INFORMATION_MESSAGE);
					refreshLikedArtistList();
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(lArtistScrollPane, BorderLayout.CENTER);
		panel.add(lRefreshArtistsButton, BorderLayout.AFTER_LAST_LINE);
		try {
			refreshLikedArtistList();
		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private void initSwingComponents() {
		setTitle(
				"                                                                                                                                                                                            "
						+ "Logged User: " + loggedUser.getUserName().toUpperCase());
		setBounds(20, 20, 1300, 700);
		setLayout(null);
		setResizable(false);

		Mainpanel = new JPanel();
		Mainpanel.setBounds(0, 0, getWidth(), getHeight());
		Mainpanel.setLayout(null);
		Mainpanel.setBackground(Color.decode("#000000"));
		Color tabColor = Color.decode("#A5D6A7");
		Color tabBackgroundColor = Color.decode("#E0E0E0");
		logOut = new JButton("Logout");
		logOut.setBounds(1150, 25, 100, 30);
		logOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainController.openLogin();
				setVisible(false);
			}
		});

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(20, 20, getWidth() - 50, getHeight() - 70);
		tabbedPane.setBackground(tabColor);

		recommendedSongPane = new JPanel();
		recommendedSongPane.setLayout(new BorderLayout());
		recommendedSongPane.setBackground(tabBackgroundColor);
		displayRecommendedSongs(recommendedSongPane);

		recommendedArtistPane = new JPanel();
		recommendedArtistPane.setLayout(new BorderLayout());
		recommendedArtistPane.setBackground(tabBackgroundColor);
		displayRecommendedArtists(recommendedArtistPane);

		allSongPane = new JPanel();
		allSongPane.setLayout(new BorderLayout());
		allSongPane.setBackground(tabBackgroundColor);
		displayAllSongs(allSongPane);

		allArtistPane = new JPanel();
		allArtistPane.setLayout(new BorderLayout());
		allArtistPane.setBackground(tabBackgroundColor);
		displayAllArtists(allArtistPane);

		likedSongPane = new JPanel();
		likedSongPane.setLayout(new BorderLayout());
		likedSongPane.setBackground(tabBackgroundColor);
		displayLikedSongs(likedSongPane);

		likedArtistPane = new JPanel();
		likedArtistPane.setLayout(new BorderLayout());
		likedArtistPane.setBackground(tabBackgroundColor);
		// likedArtistPane.setBackground(Color.decode("#000000"));
		displayLikedArtists(likedArtistPane);

		ImageIcon icon = new ImageIcon("images/a.png");
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addTab("Recommended Songs", icon, recommendedSongPane,
				"Default : Shows Top Songs,Else Recommended Songs based on User Preferences");
		tabbedPane.addTab("Recommended Artists", icon, recommendedArtistPane,
				"Default : Shows Top Artists,Else Recommended Artists based on User Preferences");
		tabbedPane.addTab("All Songs", icon, allSongPane, "Shows All Songs in Knowledge Base");
		tabbedPane.addTab("All Artists", icon, allArtistPane, "Shows All Artists in Knowledge Base");
		tabbedPane.addTab("Liked Songs", icon, likedSongPane, "Shows All Liked Songs (Facts Base)");
		tabbedPane.addTab("Liked Artists", icon, likedArtistPane, "Shows All Liked Artists (Facts Base)");
		ChangeListener changeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent changeEvent) {
				// JTabbedPane source = (JTabbedPane) changeEvent.getSource();
				try {
					refresh();
				} catch (SQLException | IOException e) {
					System.out.println("Error during Refresh");
				}
			}
		};
		tabbedPane.addChangeListener(changeListener);
		Mainpanel.add(logOut);
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

	public void refresh() throws FileNotFoundException, SQLException, IOException {
		refreshLikedArtistList();
		refreshLikedSongList();
		refreshRecommendedArtistList();
		refreshRecommendedSongList();
	}

	public static void main(String args[]) {
		new UserProfileFrame();
	}
}
