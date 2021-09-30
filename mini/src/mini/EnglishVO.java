package mini;

public class EnglishVO {
	private String id;
	private String pw;
	private String name;
	private String point;	
	private int no;
	private String answer;
	private String word;
	

	

	@Override
	public String toString() {
		return word;
	}
	
	

	public EnglishVO(String name, String point) {		
		this.name = name;
		this.point = point;
	}


	public EnglishVO(String word) {		
		this.word = word;
		
	}

	public EnglishVO(String id, String pw, String name,String point) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this. point= point;
	}
		
	public EnglishVO() {
		
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



	public void setName(String name) {
		this.name = name;
	}



	public String getPoint() {
		return  point;
	}



	public void setScore(String  point) {
		this. point =  point;
	}



	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
	}



	public String getAnswer() {
		return answer;
	}



	public void setAnswer(String answer) {
		this.answer = answer;
	}



	public String getWord() {
		return word;
	}



	public void setWord(String word) {
		this.word = word;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
