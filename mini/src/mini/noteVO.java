package mini;

public class noteVO {

	private String wrong_word;
	private String id;
	private String answer;
	public String getWrong_word() {
		return wrong_word;
	}
	public void setWrong_word(String wrong_word) {
		this.wrong_word = wrong_word;
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
	public noteVO(String wrong_word, String id, String answer) {
		this.wrong_word = wrong_word;
		this.id = id;
		this.answer = answer;
	}
	
	
	
	
	
}
