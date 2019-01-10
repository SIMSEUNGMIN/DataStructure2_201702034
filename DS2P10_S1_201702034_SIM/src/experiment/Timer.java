package experiment;

public final class Timer {
	
	//비공개인스턴스함수
	private static long startTime;
	private static long stopTime;
	
	//생성자
	private Timer(){};
	
	//공개 함수
	public static void start(){
		Timer.startTime = System.nanoTime();
	}
	
	public static void stop(){
		Timer.stopTime = System.nanoTime();
	}
	
	public static long duration(){
		return(Timer.stopTime - Timer.startTime) / 1000;
	}
}
