import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileIO {
	@SuppressWarnings("finally")
	public static boolean writeSch(Map<String, List<String>> scheduleMap) {
		Boolean result = false;
		//인자로 받은 맵의 내용을 파일로 쓸 수 있도록 텍스트로 변환
		StringBuilder strForWrite = new StringBuilder();
		
		for (String date : scheduleMap.keySet()) {
			List<String> schedules = scheduleMap.get(date);
			String prefixStr = "";
			strForWrite.append(date + "||");
			for (String schedule : schedules) {
				strForWrite.append(prefixStr + schedule);
				prefixStr = "&&";
			}
			strForWrite.append("\n");
		}
		
		File file = new File("D:/test1.txt");
		FileWriter fWriter = null;
		BufferedWriter bWriter = null;
				
		try {
			fWriter = new FileWriter(file, false);
			bWriter = new BufferedWriter(fWriter);
			bWriter.write(strForWrite.toString());
			bWriter.flush();
			System.out.println("DONE!");
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bWriter != null) bWriter.close();
				if(fWriter != null) fWriter.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return result;
		}
		
	}
	public static Map<String, List<String>> readSavedSch() {
		FileReader reader = null;
		BufferedReader bReader = null;
		try {
			String readLine = null;
			File file = new File("D:/test1.txt");
			reader = new FileReader(file);
			bReader = new BufferedReader(reader);
			
			String[] dateSchPair = null;
			String[] schedules = null;
			List<String> scheduleList = null;
			while((readLine = bReader.readLine()) != null) {
				System.out.println("readLine : " + readLine);
				//TO-DO:written format validation
				dateSchPair = readLine.split("\\|\\|");
				schedules = dateSchPair[1].split("&&");
				scheduleList = Arrays.asList(schedules);
				RootMenuHandler.scheduleMap.put(dateSchPair[0], scheduleList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bReader != null) bReader.close();
				if(reader != null) reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new HashMap();
	}
}
