import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarHandler {
	
	private final String[] calHeader = { "SU", "MO", "TU", "WE", "TH", "FR", "SA" };
	private final int width = calHeader.length;
	
	private Calendar cal;
	private int startDayOfWeek;
	private int lastDay;
	
	public CalendarHandler(Calendar cal) {
		this.cal = cal;
		this.startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		this.lastDay = cal.getActualMaximum(Calendar.DATE);
	}

	public String[][] getDotArray() {
		String[][] dotArray = new String[6][width];
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String monthStr = (month < 10) ? "0" + month :  "" + month;
		int dayForPrint = 1;
		List<String> scheduledDateList = new ArrayList<String>();
		
		String searchStr = year + "-" + monthStr;
		System.out.println("searchStr : " + searchStr);
		for(String key : RootMenuHandler.scheduleMap.keySet()) {
			if (key.startsWith(searchStr)) {
				System.out.println("key : " + key);
				scheduledDateList.add(key.substring(8, 10));
			}
		}
		System.out.print("scheduled date in current month : ");
		System.out.println(scheduledDateList);
		int row = 0;
		for (int i = 1; dayForPrint <= lastDay; i++) {
			
			if (i < startDayOfWeek) {
				dotArray[row][i-1] = " ";
			} else {
				String dayForPrintStr = (dayForPrint < 10) ? "0" + dayForPrint : "" + dayForPrint;
				System.out.println(dayForPrintStr);
				if (scheduledDateList.contains(dayForPrintStr)) {
					dotArray[row][(i-1) % width] = "*";
				} else {
					dotArray[row][(i-1) % width] = " ";
				}
				dayForPrint++;
				if (i % width == 0) {
					row++;
				}
			}
		}
		return dotArray;
	}

	public String[][] getCalArray() {
		String[][] calArray = new String[6][width];
		int dayForPrint = 1; // 출력 날짜

		int row = 0;
		for (int i = 1; dayForPrint <= lastDay; i++) {
			// 시작 요일이 오기 전에는 공백 출력
			if (i < startDayOfWeek) {
				calArray[row][i - 1] = " ";
			} else {
				// 날짜 배열에 입력
				// System.out.println("row : " + row + " i : " + i);
				calArray[row][(i - 1) % width] = Integer.toString(dayForPrint);
				dayForPrint++;

				// 마지막 열에 오면 행 바꿈
				if (i % width == 0) {
					row++;
				}
			}
		}
		return calArray;
	}

	public void printCalc(String[][] calArray, String[][] dotArray) {
		
		// 달력 헤더 출력
		int rowP = 0;
		for (String str : calHeader) {
			System.out.print(str + "\t");
		}
		System.out.println();

		// 날짜 배열 출력
		for (int i = 1; i < lastDay + startDayOfWeek; i++) {
			System.out.print(calArray[rowP][(i - 1) % width] + "\t");
			if ((i - 1) % width == width - 1) {
				System.out.println();
				for (int j = 0; j < width; j++) {
					System.out.print(dotArray[rowP][j] + "\t");
				}
				System.out.println();
				rowP++;
			}
		}
		System.out.println();

	}
}
	