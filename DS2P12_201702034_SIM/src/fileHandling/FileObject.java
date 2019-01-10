package fileHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.AppView;


public class FileObject { //������ ����, ����, ������ ����
	
	private String _filePath; //���� ��θ� �˷��ִ� ����
	private String _fileName; //���� �̸��� �˷��ִ� ����
	private IOType _fileIOType; //���� ���¸� �˷��ִ� ����
	
	private FileReader _fileReader; //������ �о���� ����
	private BufferedReader _reader; //������ ���ۿ� �ִ� ����
	private FileWriter _fileWriter; //���Ͽ� �����͸� ���� ����
	private File _file; //������ ��ü�� ���� ����
	
	
	//getter/setter
	private BufferedReader reader(){
		return this._reader;
	}
	
	private void setReader (BufferedReader aReader){
		this._reader = aReader;
	}
	
	private FileWriter fileWriter(){
		return this._fileWriter;
	}
	
	private void setFileWriter(FileWriter aFileWriter){
		this._fileWriter = aFileWriter;
	}
	
	private FileReader fileReader(){
		return this._fileReader;
	}
	
	private void setFileReader(FileReader aFileReader){
		this._fileReader = aFileReader;
	}
	
	private File file(){
		return this._file;
	}
	
	private void setFile(File aFile){
		this._file = aFile;
	}
	
	private String filePath(){
		return this._filePath;
	}
	
	public void setFilePath(String newFilePath){
		this._filePath = newFilePath;
	}
	
	protected String fileName(){
		return this._fileName;
	}
	
	public void setFileName(String newFileName){
		this._fileName = newFileName;
	}
	
	private IOType fileIOType(){
		return this._fileIOType;
	}
	
	public void setIOType(IOType newIOType){
		this._fileIOType = newIOType;
	}
	
	//������
	public FileObject(String givenFilePath, String givenFileName, IOType givenIOType){
		//���� ��ο� ���� �̸�, ���� Ÿ���� �޾Ƽ� �ʱ�ȭ.
		//givenFileName�� ������ givenIOType���� ����ϴ� ���� ��ü�� �����Ѵ�.
		//���� ������ open������ �ʾҴ�.
		this.setFilePath(givenFilePath);
		this.setFileName(givenFileName);
		this.setIOType(givenIOType);
	}
	
	public FileObject(){
		//�⺻ ������
		//���� ���, ���� �̸�, ���� Ÿ���� �־����� ���� �����̸�, ���Ŀ� setter�� ����Ͽ� �־��� �� �ִ�.
	}
	
	//���� �Լ�
	//File ��ü�� ������ ������ �����Ͽ� ���� ����, ����, ������ ����.
	public static boolean filePathDoesExist(String aFilePath){
		//���� ��θ� �޾Ƽ� �� ���� ��ο� �ִ� ������ ��ü�� �����. �� ���� �� ������ �����ϴ��� Ȯ��.
		if(aFilePath == null) return false;
		
		File file = new File(aFilePath);
		AppView.DebugPrint("[DEBUG] �Է��� ���� ��� : " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	public static boolean fileDoesExist(String aFilePath, String aFileName){
		//���� ��ο� ���� �̸��� �޾Ƽ� �� ���� ��ο� �ִ� ���� �̸��� ������ ��ü�� �����. �� ���� ������ �����ϴ��� Ȯ��.
		if(aFilePath == null || aFileName == null){
			return false;
		}
		
		File file = new File(aFilePath + "/" + aFileName);
		AppView.DebugPrint("[DEBUG] �Է��� ���� ��� �� �̸�: " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	//������ Ÿ�Կ� ���� �ݴ� ��Ʈ���� �޶�����. �����  ��� ���� ��Ʈ���� ���
	public boolean closeFile(){
		//�Է� ������ ��쿡�� ������ �о���� ������ reader�� close ���ְ�
		//��� ������ ��쿡�� ���Ͽ� �����͸� ���� ������ writer�� close ���ش�.
		if(this.fileIOType().equals(IOType.Input)){ //�Է� ������ ���
			try {
				this.reader().close();
				this.fileReader().close();
			}
			catch(IOException e) {
				e.printStackTrace(); //��� ������ ������� �˷��ִ� �Լ�
				return false;
			}
		}
		else if (this.fileIOType().equals(IOType.Output)){ //��� ������ ���
			try{
				this.fileWriter().close();
			}
			catch(IOException e){
				e.printStackTrace();
				return false;
			}
		}
		else{
			return false;
		}
		
		return true;
	}
	
	//����ó���� ���� ����ϱ� ���� filPath(), fileName()�� �����Ǿ����� Ȯ��.
	//���� Ÿ�Կ� ���� ���� ����� �޶�����.
	//input : ������ �����ϴ��� Ȯ�� ��, ���� ����
	//output: ������ ������ ��� ������ ǥ��(������ ���� ��쿡 ���� �ۼ��ϴ� ��)
	public boolean openFile(){
		if(this.filePath() == null || this.fileName() == null){
			// ���� ��ο� ���� �̸��� ���� ��� ���� �޼��� ���
			AppView.outputErrorLine("File Object�� ���� �̸��̳� ���� ��ΰ� �����Ǿ� ���� �ʽ��ϴ�.");
			return false;
		}
		
		File aFile = null; 
		
		if(this.fileIOType().equals(IOType.Input)){
			//������ �Է� ���Ϸ� ���� ��� ���� ��ο� ���� �̸����� ���� ��ü�� �����.
			aFile = new File(this.filePath() + "/" + this.fileName());
			//openFile
			try {
				//������ �о�´�.
				this.setFileReader(new FileReader(aFile)); //������ fileReader�� �о�´�.
				this.setReader(new BufferedReader(this.fileReader())); //�о�� fileReader�� ���� bufferedReader�� �־��ش�.
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		}
		else if(this.fileIOType().equals(IOType.Output)){
			//������ ��� ���Ϸ� ���� ��� ���� ��ο� ���� �̸����� ���� ��ü�� �����.
			aFile = new File(this.filePath() + "/" + this.fileName());
			//openFile
			try{
				//���Ͽ� �����͸� ���� ���� ������ ���� ������ �� �ִ��� Ȯ���Ѵ�.
				if(aFile.createNewFile() == false){
					return false;
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
			try{
				//���Ͽ� �����͸� �� �� �ְ� �Ѵ�.
				this.setFileWriter(new FileWriter(aFile));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else{
			//�Էµ� ��µ� �ƴ� ���
			AppView.outputErrorLine("File IOType�� �˸��� �ʽ��ϴ�.");
			return false;
		}
		
		this.setFile(aFile);
		return true;
	}
	
	//write() �Լ��� ��� StringŸ������ ���
	public boolean writeInteger(Integer value){
		//���Ͽ� �����͸� �Է��ϴ� �Լ�
		if(this.file().canWrite() == false){ //���Ͽ� �����͸� �� �� �ִ��� Ȯ��
			return false;
		}
		if(value == null){ //�����Ͱ� �����ϴ��� Ȯ��
			return false;
		}
		
		try{ //���Ͽ� �����͸� ����
			this.fileWriter().write(String.format("%d\n", value.intValue())); 
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean removeFile() {
		//������ ���� 
		boolean state = false;
		try {
			//������ �����ϰ� boolean ���� return �Ѵ�
			state = this.file().delete(); 
		}
		catch(SecurityException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public boolean renameFile(String aNewFileName) {
		//������ �̸��� �ٲ۴�
		File newFile = new File(aNewFileName); //���ο� �̸����� �� ������ �����
		boolean result = this.file().renameTo(newFile); //���ο� �̸��� ���� ���Ϸ� ��ü�Ѵ�
		
		if(result == false) { //��ü�� �����ߴ��� Ȯ��
			return false;
		}
		
		this.setFile(newFile); //��ü�� ���������� newFile�� ���� ������ set
		return true;
	}
	
	public Integer readInteger() { //���Ͽ��� �� ������ ���� �о���� ���� �Լ�
		//bufferedReader�� �̿��Ͽ� ���Ͽ��� ���� �о��
		if(this.file().canRead() == false) { //���� �� �ִ� �������� Ȯ��
			return null;
		}
		Integer value = null;
		try {
			//������ �о�� �� �ִ� ��� ���Ͽ� ���ؼ� ���پ� �о�´�
			String readedLine = this.reader().readLine();
			if(readedLine == null) return null;
			value = Integer.parseInt(readedLine); //���پ� �о�� ������ ����
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return value;
	}
}
