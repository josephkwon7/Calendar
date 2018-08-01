import java.util.Scanner;

public class CalendarMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int[] yearNMonth = new int[2];
		int inputYear = 0;
		int inputMonth = 0;
		while (true) {
			yearNMonth = Input.getYearNMonth(scanner);
			inputYear = yearNMonth[0];
			inputMonth = yearNMonth[1];
			if (inputYear == -1 || inputMonth == -1) break;
			int maxDate = CalendarUtils.getMaxDate(inputYear, inputMonth);
			Output.print(inputYear, inputMonth, maxDate);
		}
		System.out.println("Have a nice day!");
		scanner.close();
	}
}
