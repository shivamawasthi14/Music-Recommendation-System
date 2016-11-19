package core;

public class Artist {
	private String genre, artistName;

	public String getGenre() {
		return genre;
	}

	public String getArtistName() {
		return artistName;
	}

	public int getLikes() {
		return likes;
	}

	public int getAge() {
		return age;
	}

	public boolean isGender() {
		return gender;
	}

	@Override
	public String toString() {
		return "Artist [genre=" + genre + ", artistName=" + artistName + ", likes=" + likes + ", age=" + age
				+ ", gender=" + gender + "]";
	}

	public Artist(int artistId, String genre, String artistName, int likes, int age, String gender) {
		this.artistId = artistId;
		this.genre = genre;
		this.artistName = artistName;
		this.likes = likes;
		this.age = age;
		this.gender = gender.equalsIgnoreCase("male")?true:false;
	}

	private int likes, age, artistId;
	private boolean gender;

	public double getScore() {
		// TODO Incomplete Score
		return age;
	}

	public int getArtistId() {
		return artistId;
	}
}
