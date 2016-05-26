package Util;

import java.util.Calendar;

public class TimeModel {
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	
	public TimeModel() {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH)+1;
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
	}
	
	public String getTimeStr() {
		String timeString = year + "-" + month + "-" + day + " " + hour + ":" + minute;
		return timeString;
	}
}