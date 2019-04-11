package tedtalkDB.model;

import java.sql.Date;

public class Review {
	private String name; // name of review
	private int rate; // rating of tED talk
	private String topic; // topic of ted talk
	private String pres; // presenter
	private String desc; // body of review
	private int profID; // ID associated with who created review
	private int revID; // review ID associated with each review
	private int status; // status of review (needs review = 0, approved = 1, denied = 2), defaults status to 0
	
	public Review() {	
		status = 0;
	}
	// creates new review
	public Review(String name, int rate, String topic, String pres, String desc, int profID, int revID, int status) {
		this.name = name;
		this.rate = rate;
		this.topic = topic;
		this.pres = pres;
		this.desc = desc;
		this.profID = profID;
		this.revID = revID;
		this.status = status;
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
	
	public int getStatus() {
		return status;
	}
	
	// for editing of a review
	public void updateDesc(String desc) {
		this.desc = desc;
	}
	
	// for updating review status
	public void updateStatus(int update) {
		status = update;
	}
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}
}
