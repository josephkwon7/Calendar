public class Calculator {
	public static int calculate(String inputStr) {
		String[] splittedStr = Utils.getSplittedString(inputStr);

//		for(String val : splittedStr) {
//			System.out.println(val);
//		}
		
		return Integer.parseInt(splittedStr[0]) + Integer.parseInt(splittedStr[1]);
	}
}
