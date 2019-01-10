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
			AppView.output("? ���� ���� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) ���� �� �Է¿� ������ �ֽ��ϴ� : " + scannedToken);
			}
		}
	}
	
	public static int inputNumberOfEdges(){
		int numberOfEdges;
		String scannedToken;
		
		while(true){
			AppView.output("? ����� ���� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfEdges = Integer.parseInt(scannedToken);
				return numberOfEdges;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) ����� �� �Է¿� ������ �ֽ��ϴ� : " + scannedToken);
			}
		}
	}
	
	public static int inputTailVertex(){
		int tailVertex;
		String scannedToken;
		
		while(true){
			AppView.output("? tail Vertex �� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				tailVertex = Integer.parseInt(scannedToken);
				return tailVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) tail Vertex �Է¿� ������ �ֽ��ϴ� : " + scannedToken);
			}
		}
	}
	
	public static int inputHeadVertex(){
		int headVertex;
		String scannedToken;
		
		while(true){
			AppView.output("? head vertex �� �Է��Ͻÿ� : ");
			scannedToken = AppView.scanner.next();
			try{
				headVertex = Integer.parseInt(scannedToken);
				return headVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(����) head Vertex �Է¿� ������ �ֽ��ϴ� : " + scannedToken);
			}
		}
	}
	
	public static int inputSourceVertex(){
		int sourceVertex;
		String scannedToken;
		while(true){
			AppView.output("? ��� vertex�� �Է��Ͻÿ�: ");
			scannedToken = AppView.scanner.next();
			try{
				sourceVertex = Integer.parseInt(scannedToken);
				return sourceVertex;
			}
			catch(NumberFormatException e){
				AppView.outputLine("[����] ��� vetex �Է¿� ������ �ֽ��ϴ�: " + scannedToken);
			}
		}
	}
}
