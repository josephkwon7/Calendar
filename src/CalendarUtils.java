import java.util.Calendar;

public class CalendarUtils {
	public static int getMaxDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		
		return cal.getActualMaximum(Calendar.DATE);
	}
	
	public static void printCalc(int year, int month) {
		Calendar cal = Calendar.getInstance();
		String[] calHeader = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
		String[][] calDate = new String[6][7];
		
		int width = calHeader.length;
		int startDayOfWeek; //월 시작 요일
		int lastDay; //월 마지막 날짜
		int dateForPrint = 1; //출력 날짜
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		
		startDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
//		System.out.println("startDayOfWeek : " + startDayOfWeek);
		lastDay = cal.getActualMaximum(Calendar.DATE);
//		System.out.println("lastDay : " + lastDay);
		
		int row = 0;
		for (int i=1; dateForPrint <= lastDay; i++ ) {
			
			//시작 요일이 오기 전에는 공백 출력
			if (i < startDayOfWeek) {
				calDate[row][i - 1] = " ";
			} else {
				//날짜 배열에 입력
//				System.out.println("row : " + row + " i : " + i);
				calDate[row][(i-1) % width] = Integer.toString(dateForPrint);
				dateForPrint++;
				
				//마지막 열에 오면 행 바꿈 
				if (i % width == 0) {
					row++;
				}
			}
		}
		
//		for (String[] str : calDate) {
//			for (String str2 : str) {
//				System.out.println(str2);
//			}
//		}
		
		//달력 헤더 출력
		int rowP = 0;
		for (String str : calHeader) {
			System.out.print(str + "\t");
		}
		System.out.println();
		
		//날짜 배열 출력
		for (int i = 1; i < lastDay + startDayOfWeek; i++) {
			System.out.print(calDate[rowP][(i - 1) % width] + "\t"); 
			if ((i - 1) % width == width - 1) {
				System.out.println();
				rowP++;
			}
		}
		System.out.println();
		
		
	}
}
