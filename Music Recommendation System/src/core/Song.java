package core;

public class Song {
	private String title, artistName, album, genre;
	private double danceability, lyrics, energy, tempo;
	private int likes, song_id;
	private double score;

	@Override
	public String toString() {
		return "Song [title=" + title + ", artistName=" + artistName + ", album=" + album + ", genre=" + genre
				+ ", danceability=" + danceability + ", lyrics=" + lyrics + ", energy=" + energy + ", tempo=" + tempo
				+ ", likes=" + likes + "]";
	}

	public String getTitle() {
		return title;
	}

	public int getSongId() {
		return song_id;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getAlbum() {
		return album;
	}

	public String getGenre() {
		return genre;
	}

	public double getDanceability() {
		return danceability;
	}

	public double getLyrics() {
		return lyrics;
	}

	public double getEnergy() {
		return energy;
	}

	public double getTempo() {
		return tempo;
	}

	public int getLikes() {
		return likes;
	}

	public double getScore() {
		return (danceability + tempo + energy + lyrics) / 4.0;
	}

	public Song(int song_id, String title, String artistName, String album, String genre, double danceability,
			double lyrics, double energy, double tempo, int likes) {
		this.song_id = song_id;
		this.title = title;
		this.artistName = artistName;
		this.album = album;
		this.genre = genre;
		this.danceability = danceability;
		this.lyrics = lyrics;
		this.energy = energy;
		this.tempo = tempo;
		this.likes = likes;
	}
}
