package ui;

import core.Song;
import core.User;

public class SongListElement {
	private Song song;
	private boolean isLiked;
	public SongListElement(Song song, boolean isLiked) {
		this.song = song;
		this.isLiked = isLiked;
	}
	public Song getSong() {
		return song;
	}
	public boolean isLiked() {
		return isLiked;
	}
}
