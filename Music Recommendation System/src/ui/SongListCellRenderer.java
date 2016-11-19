package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import core.Song;

public class SongListCellRenderer implements ListCellRenderer {
	private JPanel panel;
	private JLabel label;

	public SongListCellRenderer(JList list) {
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

		Song song = (Song) value;
		Color hover = new Color(246, 36, 89);
		Color normal = new Color(155, 89, 182);
		label.setText(songToString(song)
				+ "                                                                                                                                                                              ");
		label.setOpaque(true);
		panel.setBackground(CellHasFocus ? hover : normal);
		return panel;
	}

	private String songToString(Song song) {
		return "<html><pre> " + song.getTitle() + "<br> " + song.getArtistName() + "<br> " + song.getAlbum();
	}
}
