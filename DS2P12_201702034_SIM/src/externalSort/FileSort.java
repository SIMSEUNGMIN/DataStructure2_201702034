package externalSort;

import app.AppView;
import fileHandling.FileObject;
import fileHandling.IOType;
import fileHandling.Run;
import sort.QuickSort;
import sort.QuickSortWithInsertionSort;

public class FileSort {
	//주어진 디스크 파일을 정렬하여, 새로운 파일로 저장한다
	//내부 정렬의 크기는 미리 설정(QuickSortWithInsertionSort를 사용)
	//pivot는 무작위로 선택, 삽입 정렬의 크기는 20; 20이하일 때는 삽입 정렬을 적용
	
	//초기 run을 생성
	//생성된 run은 queue에서 관리, 병합을 할 때는 2개의 run을 queue에서 삭제하여 얻는다
	//병합된 run은 queue에 삽입한다
	//병합은 queue에 run이 1개 남을 때까지 실행한다
	
	//비공개 인스턴스 변수
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
	
	//생성자
	@SuppressWarnings("unused")
	private FileSort() {
		
	}
	
	public FileSort(int givenMaxInternalSorting, String givenDefaultFilePath) {
		//파일 경로와 run의 크기를 지정
		this.setMaxInternalSorting(givenMaxInternalSorting);
		this.setDefaultFilePath(givenDefaultFilePath);
		//runMemory를 생성하여 한 번에 읽을 수 있는 run의 개수를 지정
		this.setRunMemory(new Integer[givenMaxInternalSorting]);
	}
	
	//공개함수
	public void setMaxInternalSorting(int newMaxInternalSorting) {
		this._maxInternalSorting = newMaxInternalSorting;
	}
	
	public void setFilePath(String newFilePath) {
		this._defaultFilePath = newFilePath;
	}
	
	public void sortFile(String anInputFileName, String anOutputFileName) {
		//입력 파일 및 출력 파일 이름을 통해 파일을 정렬하는 함수
		//Run을 생성하고 정렬하여 합친다
		
		//파일 이름 설정
		this.setInputFileName(anInputFileName);
		this.setOutputFileName(anOutputFileName);
		
		//초기 run들을 생성한다
		this.generateInitRuns();
		
		//모든 Rung들을 정렬하여 합친다
		this.mergeAllRuns();
	}
	
	private boolean readElementForInternalSort() {
		//열려 있는 입력 파일에서 runMemory의 크기만큼 값을 읽어오는 함수
		
		//원소를 읽어온다
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
		//입력 파일로부터 초기 run을 생성한다
		
		this.setRunManager(new RunManager());
		this.runManager().setRunInformation(this.defaultFilePath(), "runList");
		
		this.setInputFileObject(new FileObject(this.defaultFilePath(), this.inputFileName(), IOType.Input));
		
		if(this.inputFileObject().openFile() == false) {
			AppView.outputErrorLine("초기 run 생성을 위한 파일 열기에 실패했습니다: " + this.inputFileName());
			return;
		}
		
		while(this.readElementForInternalSort()) {
			if((this.quickSort().sort(this.runMemory())) == false) {
				AppView.outputErrorLine("Run 정렬에 실패하였습니다.");
				break;
			}
			
			this.runManager().insertRunList(this.runMemory());
		}
		
		if(this.inputFileObject().closeFile() == false) {
			AppView.outputErrorLine(" 초기 run 생성을 위한 파일 닫기에 실패했습니다: " + this.inputFileName());
			return;
		}
	}

	
	private void mergeAllRuns() {
		//모든 run을 2 way 병합하여 하나의 정렬된 파일을 만든다
		
		while(true) {
			this.mergeTwoRuns();
			if(this.runManager().runListLength() == 1) break;
		}
		this.renameFinalRun();
	}
	
	//큐에 남게된 마지막 run을 삭제하여 얻는다
	//run의 임시 파일 이름을 최종 출력 파일이름으로 변경 (removeFile())
	public void renameFinalRun() {
		Run lastRun = this.runManager().getRun();
		lastRun.renameFile(this.outputFileName());
	}
	
	public void mergeTwoRuns() {
		//merge
		Run firstRun = this.runManager().getRun();
		Run secondRun = this.runManager().getRun();
		
		//Run을 읽기 위한 설정
		firstRun.setIOType(IOType.Input);
		secondRun.setIOType(IOType.Input);
		
		if(firstRun.openFile() == false || secondRun.openFile() == false) {
			AppView.outputErrorLine("런 파일을 열 수 없습니다.");
			return;
		}
		
		//병합된 Run을 담을 새로운 Run 클래스를 생성
		Run outputRun = new Run();
		outputRun.openFile();
		
		//각 Run에서 1개의 값을 각각 읽어온다
		Integer firstRunValue = firstRun.readInteger();
		Integer secondRunValue = secondRun.readInteger();
		
		while(true) {
			
			if(firstRunValue == null && secondRunValue == null) {
				break;
			}
			
			if(firstRunValue == null && secondRunValue != null) {
				//첫 번째 Run파일에서 모든 값을 불러왔을 경우
				outputRun.writeInteger(secondRunValue);
				secondRunValue = secondRun.readInteger();
				continue;
			}
			else if(firstRunValue != null && secondRunValue == null) {
				//두 번째 Run파일에서 모든 값을 불러왔을 경우
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
		//입력 및 출력에 사용한 모든 파일을 닫아준다.
		
		this.runManager().insertRunList(outputRun); //새로운 Run을 큐에 삽입한다.
		
		if(firstRun.removeFile() == false) {
			AppView.outputErrorLine("Run을 삭제하는데 실패했습니다");
		}
		if(secondRun.removeFile() == false) {
			AppView.outputErrorLine("Run을 삭제하는데 실패했습니다");
		}
	}
	
	public void validation() {
		FileObject validFile = new FileObject(this.defaultFilePath(), this.outputFileName(), IOType.Input);
		if(validFile.openFile() == false) {
			AppView.outputErrorLine("- Vaildation: 파일을 열 수 없습니다 " + this.outputFileName());
			return;
		}
		Integer value = validFile.readInteger();
		boolean state = true;
		if(value == null) {
			AppView.outputErrorLine("- Validation : 파일을 읽을 수 업습니다 " + this.outputFileName());
			return;
		}
		while(true) {
			Integer newValue = validFile.readInteger();
			if(newValue == null) {
				break;
			}
			
			if(value.compareTo(newValue) > 0) {
				AppView.outputErrorLine("- 파일이 정렬되어 있지 않습니다");
				state = false;
				break;
			}
		}
		
		if(validFile.closeFile() == false) {
			AppView.outputErrorLine("- Validation: 파일을 닫을 수 없습니다 " + this.outputFileName());
		}
		
		if(state) {
			AppView.outputLine("> 파일이 성공적으로 정렬되어 있습니다:" + this.outputFileName());
		}
	}

}
