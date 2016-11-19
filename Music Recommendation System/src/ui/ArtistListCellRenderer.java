package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

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
		ImageIcon music = new ImageIcon("images/b.png");
		label.setIcon(music);
		label.setBackground(new Color(174, 168, 211));
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
		Color normal = new Color(155, 89, 182);
		label.setText(artistToString(artist)
				+ "                                                                                                                                                              ");
		label.setOpaque(true);
		panel.setBackground(CellHasFocus ? hover : normal);
		return panel;
	}

	private String artistToString(Artist artist) {
		return "<html><pre> " + artist.getArtistName() + "<br> " + artist.getGenre() + "<br> " + artist.getAge();
	}
}
