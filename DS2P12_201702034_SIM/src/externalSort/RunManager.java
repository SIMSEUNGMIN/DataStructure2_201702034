package externalSort;

import fileHandling.Run;
import list.Queue;
import list.CircularQueue;

public class RunManager {
	//정렬에 사용될 Run을 관리하고 정렬을 수행하기 위해 사용된다
	
	//비공개 인스턴스 변수
	private Queue<Run> _queue;
	
	//getter/setter
	private Queue<Run> queue(){
		return this._queue;
	}
	
	public void setQueue(Queue<Run> q) {
		this._queue = q;
	}
	
	//생성자
	public RunManager() {
		this._queue = new CircularQueue();
	}
	
	//공개 함수
	//생성자를 통해 주어진 element를 이용하여 Run을 생성하고 큐에 삽입한다.
	public void makeRunList() {}
	
	
	//Run에 사용될 정보를 입력받는다
	public void setRunInformation(String aDirectoryPath, String aFilePrefix) {
		Run.setDirectoryPath(aDirectoryPath);
		Run.setFilePrefix(aFilePrefix);
	}
	
	//Run을 큐에 삽입하는 함수
	public void insertRunList(Integer[] aElementListForRun) {
		Run eachRun = new Run();
		eachRun.makeRun(aElementListForRun);
		//Run을 생성해 준 다음 인자로 넘긴 List를 통해 파일을 생성
		
		this.queue().add(eachRun); //생성한 Run을 큐에 삽입
	}
	
	//외부에서 생성된 run을 큐에 삽입한다
	public void insertRunList(Run aRun) {
		if(aRun == null) {
			return ;
		}
		
		this.queue().add(aRun);
	}
	
	//큐에 있는 run을 제거하여 반환한다
	public Run getRun() {
		return this.queue().remove();
	}
	
	//큐의 크기를 반환하는 것으로 run의 개수를 알려준다
	public int runListLength() {
		return this.queue().size();
	}

}
