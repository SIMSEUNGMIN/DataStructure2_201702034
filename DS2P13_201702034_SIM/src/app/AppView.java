package app;


import java.util.Scanner;

public final class AppView {

	private static Scanner scanner = new Scanner(System.in);
	
	private AppView(){
		
	}
	
	//only for controlling debugging message
	private static final boolean DEBUG_MODE = false;
	public static void DebugPrint(String aString){
		if(DEBUG_MODE){
			System.out.print(aString);
		}
	}
	
	public static void outputErrorLine(String aString){
		System.out.println(aString);
	}
	
	public static void outputLine(String aString){
		System.out.println(aString);
	}
	
	public static void output(String aString){
		System.out.print(aString);
	}
	
	public static String fileName(){
		String fileName;
		String scannedToken;
		while(true){
			AppView.output("?사용할 파일 이름을 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			fileName = scannedToken;
			return fileName;
		}
	}
	
}
