package tedtalkDB.model;

public class Review {
	private String name; // name of review
	private int rate;
	private String topic; // topic of ted talk
	private String pres; // presenter
	private String desc; // body of review
	private int profID; // ID associated with who created review
	private int revID; // review ID associated with each review
	
	// creates new review
	public void createReview(String name, int rate, String topic, String pres, String desc, int profID, int revID) {
		this.name = name;
		this.rate = rate;
		this.topic = topic;
		this.pres = pres;
		this.desc = desc;
		this.profID = profID;
		this.revID = revID;
	}
	
	// get methods
	public String getName() {
		return name;
	}
	
	public int getRate() {
		return rate;
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getPres() {
		return pres;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public int getProfID() {
		return profID;
	}
	
	public int getrevID() {
		return revID;
	}
	
	// for editing of a review
	public void updateDesc(String desc) {
		this.desc = desc;
	}
}
