
public class SubscribedVehicle implements Vehicle{
	private Subscription subscription;			// ??
	private String plate;
													
	
	public SubscribedVehicle(String plate) {					
		this.plate = plate;
	}
	
	public String getPlate() {
		return this.plate;
	}
	
	public Subscription getSubscription() {
		return this.subscription;
	}
	public void setSubcription(Subscription sbc) {
		this.subscription = sbc;
	}
	
	public boolean isSpecial() {
		return false;
	}
	
	public String toString() {
		return "" + this.plate;
	}
	

}


