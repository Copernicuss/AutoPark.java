import java.util.*;
import java.util.Arrays;

public class AutoPark {
	private double hourlyFee;
	private double incomeDaily;
	private int capacity;
	private ArrayList<SubscribedVehicle> subscribedVehicles;
	private ArrayList<ParkRecord> parkRecords;
	
	
	public AutoPark(double hourlyFee, int capacity) {
		this.hourlyFee = hourlyFee;
		this.capacity = capacity;
		subscribedVehicles = new ArrayList<SubscribedVehicle>();
		parkRecords = new ArrayList<ParkRecord>();
	}
	
	public SubscribedVehicle searchVehicle(String plate) {	//verilen plakaya sahip arac�n aboneli�i var m� diye kontrol eder.
		for(SubscribedVehicle sv: subscribedVehicles) {
			if ( sv.getPlate().compareTo(plate) == 0)
				return sv;
		} 
		return null;
	}
	
	public boolean isParked(String plate) {		//verilen plakaya sahip arac otoparkta m� diye kontrol eder.
		for(ParkRecord pr: parkRecords) {
			if (pr.getVehicle().getPlate().compareTo(plate) == 0)
				return true;
		}
		return false;
	}
	
	public void enlargeVehicleArray(int x) {			
		this.capacity = this.capacity + x;
	}
	
	public boolean addVehicle(SubscribedVehicle scv) {		// yeni abonelik ekleme
		if (searchVehicle(scv.getPlate()) != null) { 
			return false;
		}
		else {
			subscribedVehicles.add(scv);
			return true;
		}
	}
	
	public boolean vehicleEnters(String plate, Time enter, boolean isOfficial){		//otoparka ara� giri�i, ara� official ise isOfficial true girilir.
		if (isParked(plate) == true) {					//ara� otoparkta m� diye kontrol edilir.
			System.out.println("the vehicle is already parked !.");
			return false;
		}
		
		if (this.capacity <= parkRecords.size()) {			// otopark dolu mu diye kontrol edilir.
			System.out.println("Capacity is full !");
			return false;
		}
			
		if (searchVehicle(plate) != null) {				//aboneli�i olan ara� giri�i
			ParkRecord pr = new ParkRecord(enter,searchVehicle(plate));	//	attention
			parkRecords.add(pr);
			System.out.println(plate + " plakal� ara� otoparka giri� yapt�.");
			return true;
		}
		else {
			if (isOfficial == true) {			//official ara� giri�i
				ParkRecord pr = new ParkRecord(enter,new OfficialVehicle(plate));				
				parkRecords.add(pr);
				System.out.println(plate + " plakal� ara� otoparka giri� yapt�.");
				return true;
			}
			else {							//regular ara� giri�i
				ParkRecord pr = new ParkRecord(enter,new RegularVehicle(plate));			
				parkRecords.add(pr);
				System.out.println(plate + " plakal� ara� otoparka giri� yapt�.");
				return true;
			}
		}
	}
	
	public boolean vehicleExit(String plate, Time exit) {
		for(ParkRecord pr: parkRecords) {
			if (pr.getVehicle().getPlate().compareTo(plate) == 0) {		//verilen plakaya sahip arac� otoparkta bulur.
				
				if (pr.getVehicle().isSpecial() == true || (searchVehicle(plate) != null)) {	//ara� abone ise veya official ise buraya girer.
					if (pr.getVehicle().isSpecial() == false && searchVehicle(plate).getSubscription().isValid() != true) {	//abone arac�n aboneli�i invalid ise
						pr.setExitTime(exit);																				// regular arac gibi �cret �der.
						this.incomeDaily = this.incomeDaily + ((pr.getParkingDuration()/60) * this.hourlyFee);
						parkRecords.remove(pr);
						System.out.println(plate + " plakal� ara� otoparktan ayr�ld�.");
						return true;
					}
					pr.setExitTime(exit);	
					parkRecords.remove(pr);
					System.out.println(plate + " plakal� ara� otoparktan ayr�ld�.");
					return true;
				}
				else {
					pr.setExitTime(exit);
					this.incomeDaily = this.incomeDaily + ((pr.getParkingDuration()/60) * this.hourlyFee);	//regular ara� ��k���.
					parkRecords.remove(pr);
					System.out.println(plate + " plakal� ara� otoparktan ayr�ld�.");
					return true;
				}
			}	
		}
		return false;
	}
	
	public String toString() {	// string
		String str1 = Arrays.toString(subscribedVehicles.toArray());
		String str2 = Arrays.toString(parkRecords.toArray());
		return "------- AutoPark------" + "\nCapacity :" + this.capacity + "\nSubscribed Vehicles : " + str1 + "\nPark Records :" + str2 + "\nDaily income : " + this.incomeDaily + "\n-----------------------";
	}
	
	
	public double getIncomeDaily() {
		return this.incomeDaily;
	}
	
}
