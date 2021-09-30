package mm;

public class NoteVO {
	
	private String id;
	private String answer;
	private String word;
	
	
	public NoteVO(String id) {
		this.id=id;
	}
	
	public NoteVO(String word, String id, String answer) {
		this.id = id;
		this.answer = answer;
		this.word = word;
	}
	
	

	public NoteVO(String word, String answer) {
		this.word = word;
		this.answer = answer;
		
	}



	@Override
	public String toString() {
		return word;
	}
	
	
	
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
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

