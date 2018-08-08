import java.util.Scanner;

public class CalendarMain {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Input.printMenu();
		RootMenuHandler.init();

		boolean quitFlag = false;
		while (!quitFlag) {
			Input.requestInput();
			String selection = Input.getRootSelection(sc);
			
			switch (selection) {
			case "1" : //일정등록
				RootMenuHandler.handle1(sc);
				break;
			case "2" : //일정검색
				RootMenuHandler.handle2(sc);
				break;
			case "3" : //달력보기
				RootMenuHandler.handle3(sc);
				break;
			case "h" : //도움말
				//
				break;
			default : //나가기(q)
				quitFlag = true;
			}
		}
		System.out.println("Bye~");
		sc.close();
	}
}
