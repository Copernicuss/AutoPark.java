

public class Subscription {
	private Date begin;
	private Date end;
	private SubscribedVehicle vehicle; 
	
	public Subscription(Date begin, Date end, String plate) {
		this.begin = begin;
		this.end = end;
		SubscribedVehicle sv = new SubscribedVehicle(plate);
		sv.setSubcription(this);  
		this.vehicle = sv;		
	}
	
	
	public boolean isValid() {								//bugünün tarihi begin ve end arasýnda mý diye kontrol eder.
		
		Date today = Date.getToday();						
		
		if (this.begin.isEqualsWith(today) ||  today.isAfterThan(this.begin)) {
			if (this.end.isEqualsWith(today) || today.isBeforeThan(this.end)){
				return true;
			}
		}
		return false;
	}
	
	public Date getBegin() {
		return this.begin;
	}
	public Date getEnd() {
		return this.end;
	}

	public SubscribedVehicle getVehicle() {
		return vehicle;
	}
	


}
	

