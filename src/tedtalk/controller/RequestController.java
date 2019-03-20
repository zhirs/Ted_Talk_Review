package tedtalk.controller;
import tedtalk.model.RequestModel;

public class RequestController {
	private RequestModel model;
	public void setModel(RequestModel model) {
		this.model = model;
	}
	
	public double avgRating() {
		System.out.println("done");
		return model.getAvgRating();
	}
}
