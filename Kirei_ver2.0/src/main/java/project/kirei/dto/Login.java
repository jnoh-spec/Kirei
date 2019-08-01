package project.kirei.dto;

public class Login {

	private String user_Id;
	private String user_Pwd;

	public Login() {}

	public Login(String user_id, String user_pwd) {
		super();
		user_Id = user_id;
		user_Pwd = user_pwd;
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

}
