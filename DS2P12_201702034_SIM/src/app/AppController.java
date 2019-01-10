package app;

import fileHandling.FileObject;
import fileHandling.IOType;
import dataGeneration.RandomNumberGenerator;
import externalSort.FileSort;

public class AppController {
	
	private static boolean DEBUG = false;
	
	//비공개인스턴스변수
	private FileObject _fileObject;
	private String _filePath;
	private String _inputFileName;
	private String _outputFileName;
	private String _fileName;
	
	private FileSort _fileSort;
	private int _maxCount;
	private RandomNumberGenerator _randomGen;
	
	//getter/setter
	private RandomNumberGenerator randomGen(){
		return this._randomGen;
	}
	
	private void setRandomGen(RandomNumberGenerator aRandomGen){
		this._randomGen = aRandomGen;
	}
	
	private FileObject fileObject(){
		return this._fileObject;
	}
	
	private void setFileObject(FileObject aFileObject){
		this._fileObject = aFileObject;
	}
	
	private String filePath(){
		return this._filePath;
	}
	
	private void setFilePath(String aFilePath){
		this._filePath = aFilePath;
	}
	
	private String fileName(){
		return this._fileName;
	}
	
	private void setFileName(String aFileName){
		this._fileName = aFileName;
	}
	
	private int maxCount(){
		return this._maxCount;
	}
	
	private void setMaxCount(int aMaxCount){
		this._maxCount = aMaxCount;
	}
	
	private FileSort fileSort() {
		return this._fileSort;
	}
	
	private void setFileSort(FileSort aFileSort) {
		this._fileSort = aFileSort;
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
	
	//비공개함수
	private void inputStringOfFilePath(){
		String filePath;
		while(true){
			filePath = AppView.inputFilePath();
			if(filePath == null){
				filePath = ".";
			}
			
			if(FileObject.filePathDoesExist(filePath)){
				AppView.DebugPrint("경로를 사용할 수 있습니다: " + filePath);
				break;
			}
			
			AppView.outputLine("[오류] 입력한 파일 경로가 존재하지 않습니다:" + filePath);
		}
		
		this.setFilePath(filePath);
	}
	
	private void inputStringOfFileName(){
		String fileName = null;
		while(true){
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesExist(this.filePath(), fileName)){
				AppView.outputErrorLine("해당 파일이 이미 존재합니다: " + fileName);
			}
			else{
				break;
			}
		}
		this.setFileName(fileName);
	}
	
	//공개함수
	public void inputNumberOfGenerationCount(){
		int maxCount = 0;
		while(true){
			maxCount = AppView.inputMaxSize();
			if(maxCount < 0){
				AppView.outputErrorLine("입력 값이 0보다 작습니다: " + maxCount);
			}
			else{
				break;
			}
		}
		this.setMaxCount(maxCount);
	}
	
	public void inputAndMakeFile(){
		AppView.outputLine("> 무작위 파일 생성을 위해 정보를 입력해야합니다:");
		
		this.inputStringOfFilePath();
		this.inputStringOfInputFileName();
		this.inputStringOfOutputFileName();
		//this.inputNumberOfGenerationCount();
		AppView.output("");
		
		/*AppView.outputLine("> 무작위 파일을 생성합니다:");
		//파일 객체 생성 그리고 오픈
		//주어진 파일 이름으로 파일 객체를 생성한다.
		this.setFileObject(new FileObject(this.filePath(), this.fileName(), IOType.Output));
		this.setRandomGen(new RandomNumberGenerator());
		this.randomGen().prepare(this.maxCount());
		
		AppView.outputLine("> 파일을 오픈합니다:");
		if(this.fileObject().openFile()){
			//무작위 수를 파일의 원소 개수만큼을 반복하여 얻어서 파일에 출력
			for(int i =0; i < this.maxCount(); i++){
				int randomValue = this.randomGen().randomNumber();
				AppView.DebugPrint(String.format(" 파일에 숫자를 기록합니다: %d\n", randomValue));
				this.fileObject().writeInteger(randomValue);
			}
		}
		else{
			//오류가 있으면 적절한 메세지를 내보내고 파일 경로의 다시 입력받는다.
			// 파일 경로가 존재하지 않는 경우/
		}
		AppView.outputLine("> 파일을 생성하였습니다:");
		//파일을 닫는다.
		this.fileObject().closeFile();
		*/
	}
	
	private void devideAndSort() {
		this.setFileSort(new FileSort(200, this.filePath()));
		
		AppView.outputLine("> 2-way 파일 정렬을 시작합니다:");
		this.fileSort().sortFile(this.inputFileName(), this.outputFileName());
	}
	
	private void showSortedFile() {
		AppView.outputLine("");
		AppView.outputLine("- 정렬된 파일의 이름은 다음과 같습니다:");
		AppView.outputLine("- FileName: " + this.outputFileName());
		
		AppView.outputLine("> 파일을 검증합니다.");
		AppView.outputLine("");
		this.fileSort().validation();
		
		/*AppView.outputLine("> 정렬된 파일의 이름은 다음과 같습니다:");
		AppView.outputLine("-FileName:" + this.outputFileName());
		AppView.outputLine("");*/
	}
	
	private void inputStringOfOutputFileName() {
		String fileName;
		
		while(true) {
			AppView.outputLine("- 출력 파일의 이름을 입력합니다");
			if(DEBUG) {
				fileName = "test.txt";
				AppView.DebugPrint("? 파일이름을 임의로 설정합니다:" + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesExist(this.filePath(), fileName)) {
				AppView.outputErrorLine("해당 파일이 존재합니다: " + fileName);
				break;
			}
			else {
				break;
			}
		}
		
		this.setOutputFileName(fileName);
	}
	
	private void inputStringOfInputFileName() {
		String fileName;
		
		while(true) {
			AppView.outputLine("- 입력 파일의 이름을 입력합니다");
			if(DEBUG) {
				fileName = "test.txt";
				AppView.DebugPrint("? 파일이름을 임의로 설정합니다: " + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesExist(this.filePath(), fileName)) {
				AppView.outputErrorLine("해당 파일이 존재합니다: " + fileName);
				break;
			}
			else {
				break;
			}
		}
		
		this.setInputFileName(fileName);
	}
	
	public void run(){
		AppView.outputLine("<<< 외부정렬 프로그램을 시작합니다 >>>");
		AppView.outputLine("");
		
		//입출력 파일의 이름 및 경로를 입력받는다
		this.inputAndMakeFile();
		
		//2-way 정렬을 수행합니다.
		this.devideAndSort();
		
		//정렬된 파일을 출력한다.
		this.showSortedFile();
		
		AppView.outputLine("<<< 외부정렬 프로그램을 종료합니다 >>>");
	}
	
}
