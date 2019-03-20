package tedtalk.model;

public class RequestModel {
	private String Tag;
	private String URL;
	private String Description;
	private int profID;
	private int reqID;
	private double avgRating;
	
	public RequestModel() {
	}
	
	public String getTag() {
		return Tag;
	}
	
	public String getURL() {
		return URL;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public int getProfID() {
		return profID;
	}
	
	public int getReqID() {
		return reqID;
	}
	
	public double getAvgRating(){
		return avgRating;
	}
	
	public void setTag(String Tag) {
		this.Tag = Tag;
	}
	
	public void setURL(String URL) {
		this.URL = URL;
	}
	
	public void setDescription(String Description) {
		this.Description = Description;
	}
	
	public void setProfID(int profID) {
		this.profID = profID;
	}
	
	public void setReqID(int reqID) {
		this.reqID = reqID;
	}
	
	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}
}