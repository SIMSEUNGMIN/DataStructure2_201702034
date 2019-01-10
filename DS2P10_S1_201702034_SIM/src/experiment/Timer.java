package experiment;

public final class Timer {
	
	//������ν��Ͻ��Լ�
	private static long startTime;
	private static long stopTime;
	
	//������
	private Timer(){};
	
	//���� �Լ�
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
