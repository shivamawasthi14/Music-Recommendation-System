package core;

public class User {
	private int userId;
	private String password, userName;

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", userName=" + userName + "]";
	}

	public int getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public User(int userId, String userName, String password) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
	}

}