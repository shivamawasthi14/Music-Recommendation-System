package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import core.Artist;

public class ArtistListCellRenderer implements ListCellRenderer {
	private JPanel panel;
	private JLabel label;

	public ArtistListCellRenderer(JList list) {
		panel = new JPanel();
		label = new JLabel();
		label.setBackground(Color.decode("#F9FBE7"));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(label);
		// list.add(panel);
	}

	public static void main(String[] args) {

	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean CellHasFocus) {

		Artist artist = (Artist) value;
		Color hover = new Color(246, 36, 89);
		Color normal = Color.decode("#004D40");
		label.setFont(new Font("Courier New", Font.BOLD, 16));
		label.setText(artistToString(artist)
				+ "                                                                                                                                                              ");
		label.setOpaque(true);
		ImageIcon music = new ImageIcon("images/artists/" + artist.getArtistName() + ".jpg");
		label.setIcon(music);
		panel.setBackground(CellHasFocus ? hover : normal);
		panel.setToolTipText("Likes : " + artist.getLikes());
		return panel;
	}

	private String artistToString(Artist artist) {
		return "<html><pre> " + " Artist Name: " + artist.getArtistName() + "<br> " + " Genre: " + artist.getGenre()
				+ "<br> " + " Age: " + artist.getAge();
	}
}
