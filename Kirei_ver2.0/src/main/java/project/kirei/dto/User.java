package project.kirei.dto;

public class User {

	private int user_No;
	private String user_Id;
	private String user_Pwd;
	private String nickname;
	private String join_Date;
	private int authority_No;

	public User() {}

	public User(int user_No, String user_Id, String user_Pwd, String nickname, String join_Date, int authority_No) {
		this.user_No = user_No;
		this.user_Id = user_Id;
		this.user_Pwd = user_Pwd;
		this.nickname = nickname;
		this.join_Date = join_Date;
		this.authority_No = authority_No;
	}

	public int getUser_No() {
		return user_No;
	}
	public void setUser_No(int user_No) {
		this.user_No = user_No;
	}

	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getUser_Pwd() {
		return user_Pwd;
	}
	public void setUser_Pwd(String user_Pwd) {
		this.user_Pwd = user_Pwd;
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getJoin_Date() {
		return join_Date;
	}
	public void setJoin_Date(String join_Date) {
		this.join_Date = join_Date;
	}

	public int getAuthority_No() {
		return authority_No;
	}
	public void setAuthority_No(int authority_No) {
		this.authority_No = authority_No;
	}

}
