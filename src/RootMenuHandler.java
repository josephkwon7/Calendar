import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RootMenuHandler {
	public static Map<String, List<String>> scheduleMap = new HashMap<String, List<String>>();
	
	public static void init() {
		FileIO.readSavedSch();
	}
	
	public static void handle1(Scanner sc) {
		System.out.println("[일정 등록] 날짜를 입력하세요.(YYYY-MM-DD 형식)");
		System.out.println("> ");
		String date = sc.next().trim();
		System.out.println("[일정 등록] 일정을 입력하세요.");
		System.out.println("> ");
		String text = sc.next();
		// System.out.println("text : " + text);
		if (!scheduleMap.containsKey(date)) {
			List<String> textList = new ArrayList<String>();
			textList.add(text);
			scheduleMap.put(date, textList);
		} else {
			// 기존 일정이 있는 경우.
			List<String> existingArray = scheduleMap.get(date);
			existingArray.add(text);
		}
		boolean SaveResult = FileIO.writeSch(scheduleMap);
		if (SaveResult) System.out.println("일정이 등록되었습니다.");
		System.out.println(scheduleMap);
	}

	public static void handle2(Scanner sc) {
		System.out.println("[일정 검색] 날짜를 입력하세요.(YYYY-MM-DD 형식)");
		System.out.println("> ");
		String date = sc.next().trim();
		if (scheduleMap.containsKey(date)) {
			int schCount = scheduleMap.get(date).size();
			System.out.println(schCount + "개의 일정이 있습니다.");
			int counter = 0;
			for (String text : scheduleMap.get(date)) {
				counter++;
				System.out.println(counter + ". " + text);
			}
		}
	}

	public static void handle3(Scanner sc) {
		System.out.println("[달력 보기] 보기를 원하는 년도와 달을 입력하세요.(YYYY-MM 형식)");
		System.out.println("> ");
		String[] calYearMonth = sc.next().trim().split("-");
		
		int year = Integer.parseInt(calYearMonth[0]);
		int month = Integer.parseInt(calYearMonth[1]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
				
		CalendarHandler calHndler = new CalendarHandler(cal);
		String[][] calArray = calHndler.getCalArray();
		String[][] dotArray = calHndler.getDotArray();
		
		/*
		//calArray 확인
		System.out.println("calArray 확인");
		int cnt1 = 0;
		int cnt2 = 0;
		for (String[] strArr : calArray) {
			for (String str : strArr) {
				System.out.println("calArray[" + cnt1 + "][" + cnt2 % 7 + "] = " + str);
				cnt2++;
			}
			cnt1++;
		}
		//dotArray 확인
		System.out.println("dotArray 확인");
		cnt1 = 0;
		cnt2 = 0;
		for (String[] strArr : dotArray) {
			for (String str : strArr) {
				System.out.println("dotArray[" + cnt1 + "][" + cnt2 % 7 + "] = " + str);
				cnt2++;
			}
			cnt1++;
		}
		*/
		
		//출력
		calHndler.printCalc(calArray, dotArray);

	}

}
