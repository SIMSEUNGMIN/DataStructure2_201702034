package externalSort;

import fileHandling.Run;
import list.Queue;
import list.CircularQueue;

public class RunManager {
	//���Ŀ� ���� Run�� �����ϰ� ������ �����ϱ� ���� ���ȴ�
	
	//����� �ν��Ͻ� ����
	private Queue<Run> _queue;
	
	//getter/setter
	private Queue<Run> queue(){
		return this._queue;
	}
	
	public void setQueue(Queue<Run> q) {
		this._queue = q;
	}
	
	//������
	public RunManager() {
		this._queue = new CircularQueue();
	}
	
	//���� �Լ�
	//�����ڸ� ���� �־��� element�� �̿��Ͽ� Run�� �����ϰ� ť�� �����Ѵ�.
	public void makeRunList() {}
	
	
	//Run�� ���� ������ �Է¹޴´�
	public void setRunInformation(String aDirectoryPath, String aFilePrefix) {
		Run.setDirectoryPath(aDirectoryPath);
		Run.setFilePrefix(aFilePrefix);
	}
	
	//Run�� ť�� �����ϴ� �Լ�
	public void insertRunList(Integer[] aElementListForRun) {
		Run eachRun = new Run();
		eachRun.makeRun(aElementListForRun);
		//Run�� ������ �� ���� ���ڷ� �ѱ� List�� ���� ������ ����
		
		this.queue().add(eachRun); //������ Run�� ť�� ����
	}
	
	//�ܺο��� ������ run�� ť�� �����Ѵ�
	public void insertRunList(Run aRun) {
		if(aRun == null) {
			return ;
		}
		
		this.queue().add(aRun);
	}
	
	//ť�� �ִ� run�� �����Ͽ� ��ȯ�Ѵ�
	public Run getRun() {
		return this.queue().remove();
	}
	
	//ť�� ũ�⸦ ��ȯ�ϴ� ������ run�� ������ �˷��ش�
	public int runListLength() {
		return this.queue().size();
	}

}
