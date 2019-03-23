package tedtalk.model;
import java.util.Date;
public class ReviewModel {

//MEMBERs:
	protected Date reviewDate = new Date();//MIGHT CONSIDER USING JAVA.UTIL.TIME??
	protected String reviewStatus = " ";//PENDING, APPROVED ,DENIED
	protected String reviewMessage = "null";
	protected int reviewRating = 0;//WHOLE NUBMERS ONLY
	private int reviewID = 0;
	private int requestID = 0;
	ProfileModel profileModel = new ProfileModel();
	private int profID = profileModel.getProfID();
//CONSTRUCTORS:
	public ReviewModel(){//BASE CONSTRUCTOR
		
	}
	public ReviewModel(String userReview, int userRating, Date reviewDate){//DEMO CONSTRUCTOR
		this();
		this.reviewMessage = userReview;
		this.setReviewRating(userRating);
		this.reviewDate = reviewDate;
	}
//SETTERS:
	public void setReviewDate(Date currentDate){
		this.reviewDate = currentDate;
	}
	public void setReviewMessage(String userMessage){
		this.reviewMessage = userMessage;
	}
	public void setReviewStatus(String status){
		if(status.equals("pending") || status.equals("approved") || status.equals("denied"))
			this.reviewStatus = status;
	}
	public void setReviewID(int reviewID){
		this.reviewID = reviewID;
	}
	public void setRequestID(int requestID){
		this.requestID = requestID;
	}
	public void setReviewRating(int userRating){
		//NOT A DOUBLE USER MIGHT ENTER 1.2 STARS
		if(userRating > 0 && userRating < 6)
			this.reviewRating = userRating;
	}
//GETTERS:
		public int getReviewRating(){
			return reviewRating;
		}
		public int getRequestID(){
			return requestID;
		}
		public int getReviewID(){
			return reviewID;
		}
		public String getReviewStatus(){
			return reviewStatus;
		}
		public String getReviewMessage(){
			return reviewMessage;
		}
		public Date getReviewDate(){
			return reviewDate;
		}
		public int getProfID() {
			return profID;
		}
}