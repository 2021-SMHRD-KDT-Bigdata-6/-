package mm;

public class ProfileVO {
	private String id;
	private String pw;
	private String name;
	private int point;
	
	
	
	
	public ProfileVO(String id, String name, int point) {
		
		this.id = id;
		this.name = name;
		this.point = point;
	}
	@Override
	public String toString() {
		return " [id=" + id + ", name=" + name + ", point=" + point + "]";
	}
	public ProfileVO(String name) {
		this.name=name;
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

	public String getName() {
		return name;
	}

	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ProfileVO(String id, String pw, String name,int point) {		
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.point=point;
	}

	public ProfileVO(String id, String pw) {
		this.id =id;
		this.pw=pw;
		
	}
   
	   
	}