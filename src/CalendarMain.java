import java.util.Scanner;

public class CalendarMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] yearNMonth = Input.getYearNMonth(scanner);
		int inputYear = yearNMonth[0];
		int inputMonth = yearNMonth[1];
		int maxDate = CalendarUtils.getMaxDate(inputYear, inputMonth);
		Output.print(inputYear, inputMonth, maxDate);
	}
}
