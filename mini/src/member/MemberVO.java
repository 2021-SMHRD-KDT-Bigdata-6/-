package member;

public class MemberVO {
	private String id;
	private String pw;
	private String nick;
	private String phone;
	
	
	
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", nick=" + nick + ", phone=" + phone + "]";
	}



	public MemberVO(String id, String pw, String nick, String phone) {
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.phone = phone;
	}
	
	
	
	public MemberVO(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}



	public MemberVO() {
		
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
	
	

}
