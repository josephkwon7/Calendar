import java.util.Calendar;

public class CalendarUtils {
	public static int getMaxDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		
		return cal.getActualMaximum(Calendar.DATE);
	}
}
