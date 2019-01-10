package app;

import java.util.Scanner;

public final class AppView {

	private static Scanner scanner = new Scanner(System.in);
	
	private AppView(){
		
	}
	
	//only for controlling debugging message
	private static final boolean DEBUG_MODE = true;
	public static void outputDebugMessage(String aString){
		if(DEBUG_MODE){
			System.out.print(aString);
		}
	}
	
	public static void outputLine(String aString){
		System.out.println(aString);
	}
	
	public static void output(String aString){
		System.out.print(aString);
	}
	
	public static int inputNumberOfVertices(){
		int numberOfVertices;
		String scannedToken;
		
		while(true){
			AppView.output("? 원소 수를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) 원소 수 입력에 오류가 있습니다 : " + scannedToken);
			}
		}
	}
	
	public static int inputNumberOfEdges(){
		int numberOfEdges;
		String scannedToken;
		
		while(true){
			AppView.output("? 관계쌍 수를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfEdges = Integer.parseInt(scannedToken);
				return numberOfEdges;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) 관계쌍 수 입력에 오류가 있습니다 : " + scannedToken);
			}
		}
	}
	
	public static int inputTailVertex(){
		int tailVertex;
		String scannedToken;
		
		while(true){
			AppView.output("? tail Vertex 를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) tail Vertex 입력에 오류가 있습니다 : " + scannedToken);
			}
		}
	}
	
	public static int inputHeadVertex(){
		int headVertex;
		String scannedToken;
		
		while(true){
			AppView.output("? head vertex 를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) head Vertex 입력에 오류가 있습니다 : " + scannedToken);
			}
		}
	}
	
	public static int inputSourceVertex(){
		int sourceVertex;
		String scannedToken;
		while(true){
			AppView.output("? 출발 vertex를 입력하시오: ");
			scannedToken = AppView.scanner.next();
			try{
				sourceVertex = Integer.parseInt(scannedToken);
				return sourceVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[오류] 출발 vetex 입력에 오류가 있습니다: " + scannedToken);
			}
		}
	}
}
