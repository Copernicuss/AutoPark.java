import junit.framework.TestCase;

public class TestSubscription extends TestCase { 
	private Subscription sub;
	private Date baslangic,bitis;
	private Date today;
	
	protected void setUp() {
		baslangic = new Date(17,3,1998);
		bitis = new Date(17,5,2019);
		sub = new Subscription(baslangic,bitis,"34 aa 123");
		today = Date.getToday();
		
	}
	
	public void testGetSubscriptionVehiclePlate() {
		String sonuc = sub.getVehicle().getPlate();
		assertEquals("34 aa 123",sonuc);
	}
	
	public void testIsValid() {
		boolean sonuc = sub.isValid();
		assertEquals(true,sonuc);
	}
	
	public void testIsAfterThan() {
		boolean sonuc = sub.getEnd().isAfterThan(today); //today after than mi?
		assertEquals(false,sonuc);
	}
	
	public void testIsBeforeThan() {
		boolean sonuc = sub.getEnd().isBeforeThan(today);
		assertEquals(false,sonuc);
	}

}
