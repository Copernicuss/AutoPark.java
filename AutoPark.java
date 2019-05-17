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
	
	public SubscribedVehicle searchVehicle(String plate) {	//verilen plakaya sahip aracýn aboneliði var mý diye kontrol eder.
		for(SubscribedVehicle sv: subscribedVehicles) {
			if ( sv.getPlate().compareTo(plate) == 0)
				return sv;
		} 
		return null;
	}
	
	public boolean isParked(String plate) {		//verilen plakaya sahip arac otoparkta mý diye kontrol eder.
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
	
	public boolean vehicleEnters(String plate, Time enter, boolean isOfficial){		//otoparka araç giriþi, araç official ise isOfficial true girilir.
		if (isParked(plate) == true) {					//araç otoparkta mý diye kontrol edilir.
			System.out.println("the vehicle is already parked !.");
			return false;
		}
		
		if (this.capacity <= parkRecords.size()) {			// otopark dolu mu diye kontrol edilir.
			System.out.println("Capacity is full !");
			return false;
		}
			
		if (searchVehicle(plate) != null) {				//aboneliði olan araç giriþi
			ParkRecord pr = new ParkRecord(enter,searchVehicle(plate));	//	attention
			parkRecords.add(pr);
			System.out.println(plate + " plakalý araç otoparka giriþ yaptý.");
			return true;
		}
		else {
			if (isOfficial == true) {			//official araç giriþi
				ParkRecord pr = new ParkRecord(enter,new OfficialVehicle(plate));				
				parkRecords.add(pr);
				System.out.println(plate + " plakalý araç otoparka giriþ yaptý.");
				return true;
			}
			else {							//regular araç giriþi
				ParkRecord pr = new ParkRecord(enter,new RegularVehicle(plate));			
				parkRecords.add(pr);
				System.out.println(plate + " plakalý araç otoparka giriþ yaptý.");
				return true;
			}
		}
	}
	
	public boolean vehicleExit(String plate, Time exit) {
		for(ParkRecord pr: parkRecords) {
			if (pr.getVehicle().getPlate().compareTo(plate) == 0) {		//verilen plakaya sahip aracý otoparkta bulur.
				
				if (pr.getVehicle().isSpecial() == true || (searchVehicle(plate) != null)) {	//araç abone ise veya official ise buraya girer.
					if (pr.getVehicle().isSpecial() == false && searchVehicle(plate).getSubscription().isValid() != true) {	//abone aracýn aboneliði invalid ise
						pr.setExitTime(exit);																				// regular arac gibi ücret öder.
						this.incomeDaily = this.incomeDaily + ((pr.getParkingDuration()/60) * this.hourlyFee);
						parkRecords.remove(pr);
						System.out.println(plate + " plakalý araç otoparktan ayrýldý.");
						return true;
					}
					pr.setExitTime(exit);	
					parkRecords.remove(pr);
					System.out.println(plate + " plakalý araç otoparktan ayrýldý.");
					return true;
				}
				else {
					pr.setExitTime(exit);
					this.incomeDaily = this.incomeDaily + ((pr.getParkingDuration()/60) * this.hourlyFee);	//regular araç çýkýþý.
					parkRecords.remove(pr);
					System.out.println(plate + " plakalý araç otoparktan ayrýldý.");
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
