package tedtalkDB.model;


import java.sql.Date;

public class Review {
	private String name; // name of review
	private int rate; // rating of tED talk
	private String pres; // presenter
	private String desc; // body of review
	private int profID; // ID associated with who created review
	private int revID; // review ID associated with each review
	private int status; // status of review (needs review = 0, approved = 1, denied = 2), defaults status to 0
	private Date date; // time when review was created
	private String tag; // topic of review
	private String url;
	public int setrevID;
	
	public Review() {	
		status = 0;
	}
	// creates new review
	public Review(String url, String name, int rate, String pres, String desc, int profID, int revID, String tag, int status, Date date) {
		this.url = url;
		this.name = name;
		this.rate = rate;
		this.pres = pres;
		this.desc = desc;
		this.profID = profID;
		this.revID = revID;
		// newly created tag class, only one tag per review right now
		this.tag = tag;
		// sets status automatically to 0, needs review
		this.status = status;
		// sets date as current time when review is created
		this.date = date;
	}
		
	// getter methods
	public String getName() {
		return name;
	}
	
	public int getRate() {
		return rate;
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
	
	public String getURL() {
		return url;
	}
	
	public String getTag() {
		return tag;
	}
	public int getRevID() {
		return revID;
	}
	
	// setter methods
	public void setName(String name) {
		this.name = name;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}
	public void setRevID(int revID) {
		this.revID = revID;
	}
	
	public void setPres(String pres) {
		this.pres = pres;
	}
	 
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	// for editing of a review
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	// for updating review status
	public void setStatus(int update) {
		status = update;
	}
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setProfID(int profID) {
		// TODO Auto-generated method stub
		this.profID = profID;
	}
}
