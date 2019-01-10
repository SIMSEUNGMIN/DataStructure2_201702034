package externalSort;

import app.AppView;
import fileHandling.FileObject;
import fileHandling.IOType;
import fileHandling.Run;
import sort.QuickSort;
import sort.QuickSortWithInsertionSort;

public class FileSort {
	//�־��� ��ũ ������ �����Ͽ�, ���ο� ���Ϸ� �����Ѵ�
	//���� ������ ũ��� �̸� ����(QuickSortWithInsertionSort�� ���)
	//pivot�� �������� ����, ���� ������ ũ��� 20; 20������ ���� ���� ������ ����
	
	//�ʱ� run�� ����
	//������ run�� queue���� ����, ������ �� ���� 2���� run�� queue���� �����Ͽ� ��´�
	//���յ� run�� queue�� �����Ѵ�
	//������ queue�� run�� 1�� ���� ������ �����Ѵ�
	
	//����� �ν��Ͻ� ����
	private Integer[] _runMemory;
	private int _maxInternalSorting;
	private String _defaultFilePath;
	
	private String _inputFileName;
	private String _outputFileName;
	
	private FileObject _inputFileObject;
	
	private RunManager _runManager;
	
	private QuickSort<Integer> _quickSort = new QuickSortWithInsertionSort<Integer>(true);
	
	//getter/setter
	private FileObject inputFileObject() {
		return this._inputFileObject;
	}
	
	private void setInputFileObject(FileObject aInputFileObject) {
		this._inputFileObject = aInputFileObject;
	}
	
	private int maxInternalSorting() {
		return this._maxInternalSorting;
	}
	
	private String defaultFilePath() {
		return this._defaultFilePath;
	}
	
	private void setDefaultFilePath(String aDefaultFilePath) {
		this._defaultFilePath = aDefaultFilePath;
	}
	
	private RunManager runManager() {
		return this._runManager;
	}
	
	private void setRunManager(RunManager aRunManager) {
		this._runManager = aRunManager;
	}
	
	private String inputFileName() {
		return this._inputFileName;
	}
	
	private void setInputFileName(String aInputFileName) {
		this._inputFileName = aInputFileName;
	}
	
	private String outputFileName() {
		return this._outputFileName;
	}
	
	private void setOutputFileName(String aOutputFileName) {
		this._outputFileName = aOutputFileName;
	}
	
	private Integer[] runMemory() {
		return this._runMemory;
	}
	
	private void setRunMemory(Integer[] aRunMemory) {
		this._runMemory = aRunMemory;
	}
	
	private QuickSort<Integer> quickSort(){
		return this._quickSort;
	}
	
	//������
	@SuppressWarnings("unused")
	private FileSort() {
		
	}
	
	public FileSort(int givenMaxInternalSorting, String givenDefaultFilePath) {
		//���� ��ο� run�� ũ�⸦ ����
		this.setMaxInternalSorting(givenMaxInternalSorting);
		this.setDefaultFilePath(givenDefaultFilePath);
		//runMemory�� �����Ͽ� �� ���� ���� �� �ִ� run�� ������ ����
		this.setRunMemory(new Integer[givenMaxInternalSorting]);
	}
	
	//�����Լ�
	public void setMaxInternalSorting(int newMaxInternalSorting) {
		this._maxInternalSorting = newMaxInternalSorting;
	}
	
	public void setFilePath(String newFilePath) {
		this._defaultFilePath = newFilePath;
	}
	
	public void sortFile(String anInputFileName, String anOutputFileName) {
		//�Է� ���� �� ��� ���� �̸��� ���� ������ �����ϴ� �Լ�
		//Run�� �����ϰ� �����Ͽ� ��ģ��
		
		//���� �̸� ����
		this.setInputFileName(anInputFileName);
		this.setOutputFileName(anOutputFileName);
		
		//�ʱ� run���� �����Ѵ�
		this.generateInitRuns();
		
		//��� Rung���� �����Ͽ� ��ģ��
		this.mergeAllRuns();
	}
	
	private boolean readElementForInternalSort() {
		//���� �ִ� �Է� ���Ͽ��� runMemory�� ũ�⸸ŭ ���� �о���� �Լ�
		
		//���Ҹ� �о�´�
		int count = 0;
		for(int i = 0; i < this.maxInternalSorting(); i++) {
			Integer value = this.inputFileObject().readInteger();
			if(value == null || value == -1) {
				break;
			}
			this.runMemory()[i] = value.intValue();
			count++;
		}
		
		if(count == 0) {
			return false;
		}
		return true;
	}
	
	private void generateInitRuns() {
		//�Է� ���Ϸκ��� �ʱ� run�� �����Ѵ�
		
		this.setRunManager(new RunManager());
		this.runManager().setRunInformation(this.defaultFilePath(), "runList");
		
		this.setInputFileObject(new FileObject(this.defaultFilePath(), this.inputFileName(), IOType.Input));
		
		if(this.inputFileObject().openFile() == false) {
			AppView.outputErrorLine("�ʱ� run ������ ���� ���� ���⿡ �����߽��ϴ�: " + this.inputFileName());
			return;
		}
		
		while(this.readElementForInternalSort()) {
			if((this.quickSort().sort(this.runMemory())) == false) {
				AppView.outputErrorLine("Run ���Ŀ� �����Ͽ����ϴ�.");
				break;
			}
			
			this.runManager().insertRunList(this.runMemory());
		}
		
		if(this.inputFileObject().closeFile() == false) {
			AppView.outputErrorLine(" �ʱ� run ������ ���� ���� �ݱ⿡ �����߽��ϴ�: " + this.inputFileName());
			return;
		}
	}

	
	private void mergeAllRuns() {
		//��� run�� 2 way �����Ͽ� �ϳ��� ���ĵ� ������ �����
		
		while(true) {
			this.mergeTwoRuns();
			if(this.runManager().runListLength() == 1) break;
		}
		this.renameFinalRun();
	}
	
	//ť�� ���Ե� ������ run�� �����Ͽ� ��´�
	//run�� �ӽ� ���� �̸��� ���� ��� �����̸����� ���� (removeFile())
	public void renameFinalRun() {
		Run lastRun = this.runManager().getRun();
		lastRun.renameFile(this.outputFileName());
	}
	
	public void mergeTwoRuns() {
		//merge
		Run firstRun = this.runManager().getRun();
		Run secondRun = this.runManager().getRun();
		
		//Run�� �б� ���� ����
		firstRun.setIOType(IOType.Input);
		secondRun.setIOType(IOType.Input);
		
		if(firstRun.openFile() == false || secondRun.openFile() == false) {
			AppView.outputErrorLine("�� ������ �� �� �����ϴ�.");
			return;
		}
		
		//���յ� Run�� ���� ���ο� Run Ŭ������ ����
		Run outputRun = new Run();
		outputRun.openFile();
		
		//�� Run���� 1���� ���� ���� �о�´�
		Integer firstRunValue = firstRun.readInteger();
		Integer secondRunValue = secondRun.readInteger();
		
		while(true) {
			
			if(firstRunValue == null && secondRunValue == null) {
				break;
			}
			
			if(firstRunValue == null && secondRunValue != null) {
				//ù ��° Run���Ͽ��� ��� ���� �ҷ����� ���
				outputRun.writeInteger(secondRunValue);
				secondRunValue = secondRun.readInteger();
				continue;
			}
			else if(firstRunValue != null && secondRunValue == null) {
				//�� ��° Run���Ͽ��� ��� ���� �ҷ����� ���
				outputRun.writeInteger(firstRunValue);
				firstRunValue = firstRun.readInteger();
				continue;
			}
			else if(firstRunValue.compareTo(secondRunValue) < 0) { // a < b
				outputRun.writeInteger(firstRunValue);
				firstRunValue = firstRun.readInteger();
			}
			else { // a > b  
				outputRun.writeInteger(secondRunValue);
				secondRunValue = secondRun.readInteger();
			}
		}
		
		firstRun.closeFile();
		secondRun.closeFile();
		outputRun.closeFile();
		//�Է� �� ��¿� ����� ��� ������ �ݾ��ش�.
		
		this.runManager().insertRunList(outputRun); //���ο� Run�� ť�� �����Ѵ�.
		
		if(firstRun.removeFile() == false) {
			AppView.outputErrorLine("Run�� �����ϴµ� �����߽��ϴ�");
		}
		if(secondRun.removeFile() == false) {
			AppView.outputErrorLine("Run�� �����ϴµ� �����߽��ϴ�");
		}
	}
	
	public void validation() {
		FileObject validFile = new FileObject(this.defaultFilePath(), this.outputFileName(), IOType.Input);
		if(validFile.openFile() == false) {
			AppView.outputErrorLine("- Vaildation: ������ �� �� �����ϴ� " + this.outputFileName());
			return;
		}
		Integer value = validFile.readInteger();
		boolean state = true;
		if(value == null) {
			AppView.outputErrorLine("- Validation : ������ ���� �� �����ϴ� " + this.outputFileName());
			return;
		}
		while(true) {
			Integer newValue = validFile.readInteger();
			if(newValue == null) {
				break;
			}
			
			if(value.compareTo(newValue) > 0) {
				AppView.outputErrorLine("- ������ ���ĵǾ� ���� �ʽ��ϴ�");
				state = false;
				break;
			}
		}
		
		if(validFile.closeFile() == false) {
			AppView.outputErrorLine("- Validation: ������ ���� �� �����ϴ� " + this.outputFileName());
		}
		
		if(state) {
			AppView.outputLine("> ������ ���������� ���ĵǾ� �ֽ��ϴ�:" + this.outputFileName());
		}
	}

}
