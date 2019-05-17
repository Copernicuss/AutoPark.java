public class Main {

	public static void main(String[] args) {
		AutoPark otopark = new AutoPark(10,10);
		Subscription sub = new Subscription(new Date(17,2,2010),new Date(12,4,2020),"34 SV 001");
		Subscription sub2 = new Subscription(new Date(25,12,2010),new Date(12,4,2018),"34 SV 002");
		
		otopark.addVehicle(sub.getVehicle());
		otopark.addVehicle(sub2.getVehicle());	
		System.out.println("Daily Income:" + otopark.getIncomeDaily());
		
		otopark.vehicleEnters("34 SV 001",new Time(2,22),false);
		otopark.vehicleEnters("34 SV 002",new Time(18,30),false);
		
		System.out.println("34 SV 001 plakalý araç park halinde mi ?  "+otopark.isParked("34 SV 001"));
		
		otopark.vehicleEnters("34 OV 001",new Time(10,25),true);
		otopark.vehicleEnters("34 RG 001",new Time(8,22),false);
		
		System.out.println(otopark);
		
		otopark.vehicleExit("34 SV 001",new Time(12,39)); 		// Subcribed Vehicle, para ödemez.
		
		otopark.vehicleExit("34 RG 001",new Time(12,51));		// 4 saat
		
		System.out.println("34 RG 001 plakalý araç park halinde mi ?  "+otopark.isParked("34 RG 001"));
		
		System.out.println("Daily Income:" + otopark.getIncomeDaily());
		
		otopark.enlargeVehicleArray(5);
		
		System.out.println(otopark);
		
		otopark.vehicleExit("34 SV 002",new Time(22,39)); //Subcribed Vehicle, aboneliði dolmuþ
															//ücret öder. 
		
		otopark.vehicleExit("34 OV 001",new Time(17,05)); 
		
		System.out.println(otopark);
	}

}
