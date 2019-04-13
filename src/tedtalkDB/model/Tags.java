package tedtalkDB.model;

public enum Tags {
	// used for tag selection when creating reviews
	// used to limit input to a select few topics
	// taken from CS201 assignment 4
	environmental("environmental"),
	space("space"),
	industry("industry"),
	Civilengineering("civil engineering"),
	food("food"),
	culture("culture"),
	politics("politics"),
	MechanicalEngineering("mechanical engineering"),
	computers("computers"),
	coding("coding"),
	architecture("architecture"),
	technology("technology"),
	health("health");
	
private final String topic;
	
	private Tags(String topic) {
		this.topic = topic;
	}
	
	/**
	 * @return the topic representing this input
	 */
	public String getTopic() {
		return topic;
	}
	
	@Override
	public String toString() {
		return topic;
	}
	
	/**
	 * @return the enumeration member name as a string
	 */
	public String getMemberName() {
		return super.toString();
	}
}
