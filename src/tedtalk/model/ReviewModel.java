package tedtalk.model;
import java.util.Date;
public class ReviewModel {

//MEMBERs:
	private Date reviewDate; //MIGHT CONSIDER USING JAVA.UTIL.TIME??
	private int rate; //WHOLE NUBMERS ONLY
	private int revID;
	private int profID;
	private String desc;
	private String pres;
	private String topic;
	private String name;
	
//CONSTRUCTORS:
	public ReviewModel(){//BASE CONSTRUCTOR
		
	}
	
	public int getReviewRating(){
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getReviewID(){
		return revID;
	}
	public void setRevID(int revID) {
		this.revID = revID;
	}
	public String getDesc(){
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getReviewDate(){
		return reviewDate;
	}
	public int getProfID() {
		return profID;
	}
	public void setProfID(int profID) {
		this.profID = profID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getPresenter() {
		return pres;
	}
	public void setPres(String pres) {
		this.pres = pres;
	}
}