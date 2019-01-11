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
	
	public static int inputInteger() throws NumberFormatException{
		//������ �ƴ� ����� ���� ó���� ������ �� : exception throws
		return Integer.parseInt(AppView.scanner.next());
	}
	
	public static String inputString() {
		return AppView.scanner.next();
	}
	
	public static String inputFileName() {
		AppView.output("? ���� �̸��� �Է��Ͻÿ� : ");
		return AppView.scanner.next();
	}
	
	public static String inputFilePath() {
		AppView.output("? ���� ��θ� �Է��Ͻÿ� (���� ������ ����Ϸ��� '.' �� �Է��Ͻÿ�) : ");
		return AppView.scanner.next();
	}
	
	public static boolean inputAnswerForUsingSamePath() {
		AppView.outputLine("?  �ι�° ������ ��θ� �R��° ������ ��ο� ������ ���� ����ϰڽ��ϱ�? ");
		AppView.output(" ������ ��θ� ����Ϸ��� 'Y'�� 'y'�� ġ��, �ٸ� ��θ� ����Ϸ��� ������ �ƴ� �ٸ� �ƹ� Ű�� ġ�ÿ�: ");
		char answer = AppView.scanner.next().charAt(0);
		return (answer == 'Y' || answer == 'y');
	}
	
}
