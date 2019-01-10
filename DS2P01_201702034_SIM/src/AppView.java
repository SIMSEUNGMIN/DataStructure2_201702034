import java.util.Scanner;

public final class AppView {

	private static Scanner scanner = new Scanner(System.in);
	
	private AppView(){
		
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
			AppView.output("? vertex 수를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfVertices = Integer.parseInt(scannedToken);
				return numberOfVertices;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) Vertex 수 입력에 오류가 있습니다 : " + scannedToken);
			}
		}
	}
	
	public static int inputNumberOfEdges(){
		int numberOfEdges;
		String scannedToken;
		
		while(true){
			AppView.output("? edge 수를 입력하시오 : ");
			scannedToken = AppView.scanner.next();
			try{
				numberOfEdges = Integer.parseInt(scannedToken);
				return numberOfEdges;
			}
			catch(NumberFormatException e){
				AppView.outputLine("(오류) Edge 수 입력에 오류가 있습니다 : " + scannedToken);
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
}
