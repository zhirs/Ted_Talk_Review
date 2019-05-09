package tedtalkDB.model;

public class keywords{
	private int reviewID;
	private int keywordID;
	private String keyword;
	
	public keywords() {
		
	}
	
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	
	public void setkeyWord(String keyword) {
		this.keyword = keyword;
	}
	
	public void setkeywordID(int keywordID) {
		this.keywordID = keywordID;
	}
	
	public int getReviewID() {
		return reviewID;
	}
	
	public String getKeyWord() {
		return keyword;
	}
	
	public int getkeywordID() {
		return keywordID;
	}
}
