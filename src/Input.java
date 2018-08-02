import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Input {
	public static List<String> selctionsList = new ArrayList<String>(Arrays.asList(new String[] {"1", "2", "3", "h", "q"}));
	
	public static void printMenu() {
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록  ");
		System.out.println("| 2. 일정 검색 ");
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
	}

	public static void requestInput() {
		System.out.println("명령 (1, 2, 3, h, q)");
		System.out.print("> ");
	}
	
	public static String getRootSelection(Scanner sc) {
		String rootSelection = null;
		while (true) {
			rootSelection = sc.next();
			if (selctionsList.contains(rootSelection)) {
				break;
			}
			requestInput();
		}
		return rootSelection;
	}
	
	public static int[] getYearNMonth(Scanner scanner) {
		
		System.out.println("+----------------------+");
		System.out.println("| 1. 일정 등록  ");
		System.out.println("| 2. 일정 검색 ");
		System.out.println("| 3. 달력 보기");
		System.out.println("| h. 도움말 q. 종료");
		System.out.println("+----------------------+");
		System.out.println("명령 (1, 2, 3, h, q)");
		System.out.print("> ");
		
		int[] result = new int[2];
		System.out.println("년도를 입력하세요. ");
		System.out.print("> ");
		int inputYear = scanner.nextInt();
		if (inputYear == -1) {
			result[0] = inputYear;
			result[1] = inputYear;
			return result;
		}
		System.out.println("달을 입력하세요.");
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
