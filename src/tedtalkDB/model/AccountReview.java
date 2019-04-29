package tedtalkDB.model;

public class AccountReview {

	private int profId;
	private int revId;
	
	public AccountReview() {
		
	}
	
	public void setProfId(int profId) {
		this.profId = profId;
	}
	
	public int getProfId() {
		return profId;
	}
	
	public void setRevId(int revId) {
		this.revId = revId;
	}
	
	public int getRevId() {
		return revId;
	}
}