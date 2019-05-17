
public class RegularVehicle implements Vehicle{
	private String plate;
	
	public RegularVehicle(String plate) {
		this.plate = plate;
	}
	
	public String getPlate() {
		return this.plate;
	}
	
	public Subscription getSubscription() {
		return null;
	}
	
	public boolean isSpecial() {
		return false;
	}
	
}
