
public class OfficialVehicle implements Vehicle {
	private String plate;
	
	public OfficialVehicle(String plate) {
		this.plate = plate;
		
	}
	
	public String getPlate() {
		return this.plate;
	}
	
	public Subscription getSubscription() {
		return null;
	}
	
	public boolean isSpecial() {
		return true;
	}
	
}
