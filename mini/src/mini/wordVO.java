package mini;

public class wordVO {
	private int no;
	private String answer;
	private String word;
	
	
	
	public wordVO(int no, String answer, String word) {
		
		this.no = no;
		this.answer = answer;
		this.word = word;
	}

	@Override
	public String toString() {
		return word;
	}
	
	public wordVO(String word) {		
		this.word = word;
		
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
