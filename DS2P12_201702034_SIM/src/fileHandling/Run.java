package fileHandling;

import app.AppView;

/* class FileObject �� ��ӹ޴´�.
 * Run file ��ü�� �����ϰ� ���� ���� �Ѵ�.
 * �����Ǹ� ��ü ID�� �ο��ȴ�. �� ID�� �Ϸ� ��ȣ�� �����Ǹ�, ���� �̸��� �� �κ��� �����Ѵ�
 */

public class Run extends FileObject{	
	
	//public static method
	// �� Ŭ������ ��� ������ �����ϴ� ���丮 ��θ� �����Ѵ�.
	private static String _DIRECTORY_PATH = null;
	
	public static void setDirectoryPath(String aDirectoryPath) {
		_DIRECTORY_PATH = aDirectoryPath;
	}
	
	//�� Ŭ������ ��� ���� �̸� �պκп� �ٰ� �Ǵ� ���ڿ��� �����Ѵ�
	private static String _FILE_PREFIX = null;
	
	public static void setFilePrefix(String aFilePrefix) {
		_FILE_PREFIX = aFilePrefix;
	}
	
	//run id �ʱ� ���� �����Ѵ�. �� ���� �־����� ������ �⺻������ 0���� �����Ѵ�
	private static int RUN_ID = 0;
	
	public static void setRunId(int newRunId) {
		RUN_ID = newRunId;
	}
	
	//����� �ν��Ͻ� ����
	private int _runID;
	
	//getter/setter
	private int runID() {
		return this._runID;
	}
	
	private void setID(int aRunID) {
		this._runID= aRunID;
	}
	
	//������
	//��ü�� �����ϰ� �ʱ�ȭ �Ѵ�.
	//������ ��ü�� ���ο� runID�� �����Ǿ� �ο��ȴ�
	public Run() { //���Ͽ� ������ ����� ���� ���̱� ������ ��� ���Ϸ� ����. (?)
		this.setID(Run.RUN_ID);
		Run.setRunId(this.runID()+1); //��ü �����ø��� ���̵��� ���� �ø���.
		String aFileName = String.format("%s_%d.tmp", Run._FILE_PREFIX, this.runID());
		//���� �̸��� �����ϰ� ��ο� tmp/�� ���� ���� ���ϰ� �и�
		this.setFilePath(Run._DIRECTORY_PATH + "/tmp/"); //���� ��� ����
		this.setFileName(aFileName); // ���� �̸� ����
		this.setIOType(IOType.Output); //��� ���Ϸ� ����
	}
	
	public void makeRun(Integer[] elementList) {
		//���ڷ� �־��� ����Ʈ�� ����Ͽ� run���Ͽ� "�ۼ�"�ϴ� �Լ�
		
		if(elementList.length == 0) {
			AppView.outputErrorLine("Make Run�� �����Ͽ����ϴ�: ����Ʈ ����");
			return;
		}
		
		if(this.openFile() == false) {
			AppView.outputErrorLine("������ ���� ���� �����Ͽ����ϴ�:" + this.fileName());
		}
		
		for(int i = 0; i < elementList.length; i++) {
			if(this.writeInteger(elementList[i]) == false) {
				AppView.outputErrorLine("�� �̻� �� �� �����ϴ�:");
				break;
			}
		}
		
		if(this.closeFile() == false) {
			AppView.outputErrorLine("������ �ݴ� �Ϳ� �����Ͽ����ϴ�:" + this.fileName());
			return;
		}
	}
}
