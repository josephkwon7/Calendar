import java.util.Scanner;

public class CalendarMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inputStr = Input.getInputStr(scanner);
		int result = Calculator.calculate(inputStr);
		Output.printResult(result);
	}
}
