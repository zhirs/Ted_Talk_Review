package tedtalkDB.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
	private String name; // name of review
	private int rate; // rating of tED talk
	private String pres; // presenter
	private String desc; // body of review
	private int profID; // ID associated with who created review
	private int revID; // review ID associated with each review
	private int status; // status of review (needs review = 0, approved = 1, denied = 2), defaults status to 0
	private Date date; // time when review was created
	private Tags tag;
	
	public Review() {	
		status = 0;
	}
	// creates new review
	public Review(String name, int rate, String pres, String desc, int profID, int revID, Tags tag) {
		this.name = name;
		this.rate = rate;
		this.pres = pres;
		this.desc = desc;
		this.profID = profID;
		this.revID = revID;
		// newly created tag class, only one tag per review right now
		this.tag = tag;
		// sets status automatically to 0, needs review
		status = 0;
		// sets date as current time when review is created
		date = new Date();
	}
		
	// get methods
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
	
	// for editing of a review
	public void updateDesc(String desc) {
		this.desc = desc;
	}
	
	// for updating review status
	public void updateStatus(int update) {
		status = update;
	}
	
	// prints out time stamp of review creation
	// not sure how it works, pulled from stack overflow
	public void printTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		System.out.println(dateFormat.format(date));
	}
}
