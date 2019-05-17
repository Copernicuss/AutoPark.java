
public class Time {
	private int hour;
	private int minute;
	
	public Time(int hour,int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	
	public int getDifference(Time other) {		//farkı dakika olarak döndürür. 
		if(this.hour >= other.getHour())
			return ((this.hour*60 + this.minute)  -  (other.getHour()*60 + other.getMinute()));
		else {
			return(24*60 - ((other.getHour()*60 + other.getMinute()) - (this.hour*60+ this.minute))) ;
		}
	}
	
	public int getHour() {
		return this.hour;
	}
	public int getMinute() {
		return this.minute;
	}
	
}
