import java.util.Scanner;

public class Input {
	public static int[] getYearNMonth(Scanner scanner) {
		int[] result = new int[2];
		System.out.println("년도를 입력하세요. ");
		System.out.print("> ");
		int inputYear = scanner.nextInt();
		if (inputYear == -1) {
			result[0] = inputYear;
			result[1] = inputYear;
			return result;
		}
		System.out.println("달을 입력하세요. ");
		System.out.print("> ");
		int inputMonth = scanner.nextInt();
		if (inputMonth == -1) {
			result[0] = inputMonth;
			result[1] = inputMonth;
			return result;
		}
		while (inputMonth < 1 || inputMonth > 12) {
			System.out.println("올바른 달을 다시 입력하세요.");
			inputMonth = scanner.nextInt();
		}
		result[0] = inputYear;
		result[1] = inputMonth;
		return result;
	}
}
