

public class ParkRecord {			
	private Time enterTime;
	private Time exitTime;
	private Vehicle vehicle;
	

	public ParkRecord(Time enterTime, Vehicle vehicle) {
		this.enterTime = enterTime;
		this.vehicle = vehicle;
	}
	
	public Vehicle getVehicle() {
		return this.vehicle;
	}
	
	public Time getEnterTime() {
		return this.enterTime;
	}
	public Time getExitTime() {
		return this.exitTime;
	}
	
	public int getParkingDuration() {
		return this.exitTime.getDifference(this.enterTime);
	}
	
	public void setExitTime(Time exitTime) {
		this.exitTime = exitTime;
	}	
	public String toString() { 
		return ("" + this.vehicle.getPlate() + " _ Enter Time " + this.enterTime.getHour() + ":" + this.enterTime.getMinute());
	}
}
