import java.util.*;

public class Date {
	private int day;
	private int month;
	private int year;
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public boolean isAfterThan(Date adate) { 					// this, adate den sonra m� diye kontrol eder.
		if(adate.getYear() < this.year) {
			return true;
		}
		else if(adate.getYear() == this.year){
			if(adate.getMonth() < this.month) {
				return true;
			}
			else if(adate.getMonth() == this.month) {
				if(adate.getDay() < this.day) {
					return true;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public boolean isEqualsWith(Date adate) {							//this, adate e e�it mi diye kontrol eder.
		if (adate.getYear() == this.year){
			if (adate.getMonth() == this.month){
				if (adate.getDay() == this.day){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isBeforeThan(Date adate) { 						// this adate den �nce ise true d�nd�r�r.
		if (!adate.isAfterThan(this) || adate.isEqualsWith(this))
			return false;
		return true;
	}
	
	
	
	public static Date getToday() {								//bug�n�n tarihini i�eren Date nesnesi olu�turup d�nd�r�r.
		Calendar calendar = Calendar.getInstance();
		return new Date(calendar.get(Calendar.DAY_OF_MONTH) , calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
	}
	
	
	public int getYear() {
		return this.year;
	}
	
	public int getMonth() {
		return this.month;
	}
	public int getDay() {
		return this.day;
	}
}
