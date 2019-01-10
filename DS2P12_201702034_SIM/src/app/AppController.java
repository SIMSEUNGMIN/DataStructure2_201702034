package app;

import fileHandling.FileObject;
import fileHandling.IOType;
import dataGeneration.RandomNumberGenerator;
import externalSort.FileSort;

public class AppController {
	
	private static boolean DEBUG = false;
	
	//������ν��Ͻ�����
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
	
	//������Լ�
	private void inputStringOfFilePath(){
		String filePath;
		while(true){
			filePath = AppView.inputFilePath();
			if(filePath == null){
				filePath = ".";
			}
			
			if(FileObject.filePathDoesExist(filePath)){
				AppView.DebugPrint("��θ� ����� �� �ֽ��ϴ�: " + filePath);
				break;
			}
			
			AppView.outputLine("[����] �Է��� ���� ��ΰ� �������� �ʽ��ϴ�:" + filePath);
		}
		
		this.setFilePath(filePath);
	}
	
	private void inputStringOfFileName(){
		String fileName = null;
		while(true){
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesExist(this.filePath(), fileName)){
				AppView.outputErrorLine("�ش� ������ �̹� �����մϴ�: " + fileName);
			}
			else{
				break;
			}
		}
		this.setFileName(fileName);
	}
	
	//�����Լ�
	public void inputNumberOfGenerationCount(){
		int maxCount = 0;
		while(true){
			maxCount = AppView.inputMaxSize();
			if(maxCount < 0){
				AppView.outputErrorLine("�Է� ���� 0���� �۽��ϴ�: " + maxCount);
			}
			else{
				break;
			}
		}
		this.setMaxCount(maxCount);
	}
	
	public void inputAndMakeFile(){
		AppView.outputLine("> ������ ���� ������ ���� ������ �Է��ؾ��մϴ�:");
		
		this.inputStringOfFilePath();
		this.inputStringOfInputFileName();
		this.inputStringOfOutputFileName();
		//this.inputNumberOfGenerationCount();
		AppView.output("");
		
		/*AppView.outputLine("> ������ ������ �����մϴ�:");
		//���� ��ü ���� �׸��� ����
		//�־��� ���� �̸����� ���� ��ü�� �����Ѵ�.
		this.setFileObject(new FileObject(this.filePath(), this.fileName(), IOType.Output));
		this.setRandomGen(new RandomNumberGenerator());
		this.randomGen().prepare(this.maxCount());
		
		AppView.outputLine("> ������ �����մϴ�:");
		if(this.fileObject().openFile()){
			//������ ���� ������ ���� ������ŭ�� �ݺ��Ͽ� �� ���Ͽ� ���
			for(int i =0; i < this.maxCount(); i++){
				int randomValue = this.randomGen().randomNumber();
				AppView.DebugPrint(String.format(" ���Ͽ� ���ڸ� ����մϴ�: %d\n", randomValue));
				this.fileObject().writeInteger(randomValue);
			}
		}
		else{
			//������ ������ ������ �޼����� �������� ���� ����� �ٽ� �Է¹޴´�.
			// ���� ��ΰ� �������� �ʴ� ���/
		}
		AppView.outputLine("> ������ �����Ͽ����ϴ�:");
		//������ �ݴ´�.
		this.fileObject().closeFile();
		*/
	}
	
	private void devideAndSort() {
		this.setFileSort(new FileSort(200, this.filePath()));
		
		AppView.outputLine("> 2-way ���� ������ �����մϴ�:");
		this.fileSort().sortFile(this.inputFileName(), this.outputFileName());
	}
	
	private void showSortedFile() {
		AppView.outputLine("");
		AppView.outputLine("- ���ĵ� ������ �̸��� ������ �����ϴ�:");
		AppView.outputLine("- FileName: " + this.outputFileName());
		
		AppView.outputLine("> ������ �����մϴ�.");
		AppView.outputLine("");
		this.fileSort().validation();
		
		/*AppView.outputLine("> ���ĵ� ������ �̸��� ������ �����ϴ�:");
		AppView.outputLine("-FileName:" + this.outputFileName());
		AppView.outputLine("");*/
	}
	
	private void inputStringOfOutputFileName() {
		String fileName;
		
		while(true) {
			AppView.outputLine("- ��� ������ �̸��� �Է��մϴ�");
			if(DEBUG) {
				fileName = "test.txt";
				AppView.DebugPrint("? �����̸��� ���Ƿ� �����մϴ�:" + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesExist(this.filePath(), fileName)) {
				AppView.outputErrorLine("�ش� ������ �����մϴ�: " + fileName);
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
			AppView.outputLine("- �Է� ������ �̸��� �Է��մϴ�");
			if(DEBUG) {
				fileName = "test.txt";
				AppView.DebugPrint("? �����̸��� ���Ƿ� �����մϴ�: " + fileName);
				break;
			}
			fileName = AppView.inputFileName();
			if(FileObject.fileDoesExist(this.filePath(), fileName)) {
				AppView.outputErrorLine("�ش� ������ �����մϴ�: " + fileName);
				break;
			}
			else {
				break;
			}
		}
		
		this.setInputFileName(fileName);
	}
	
	public void run(){
		AppView.outputLine("<<< �ܺ����� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		
		//����� ������ �̸� �� ��θ� �Է¹޴´�
		this.inputAndMakeFile();
		
		//2-way ������ �����մϴ�.
		this.devideAndSort();
		
		//���ĵ� ������ ����Ѵ�.
		this.showSortedFile();
		
		AppView.outputLine("<<< �ܺ����� ���α׷��� �����մϴ� >>>");
	}
	
}
