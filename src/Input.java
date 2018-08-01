import java.util.Scanner;

public class Input {
	public static int[] getYearNMonth(Scanner scanner) {
		System.out.println("년도를 입력하세요. ");
		int inputYear = scanner.nextInt();
		System.out.println("달을 입력하세요. ");
		int inputMonth = scanner.nextInt();
		while (inputMonth < 0 || inputMonth > 12) {
			System.out.println("올바른 달을 다시 입력하세요.");
			inputMonth = scanner.nextInt();
		}
		int[] result = {inputYear, inputMonth};
		return result;
	}
}
