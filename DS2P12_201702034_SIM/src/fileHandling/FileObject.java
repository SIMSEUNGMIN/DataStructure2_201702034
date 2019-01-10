package fileHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.AppView;


public class FileObject { //파일의 생성, 삭제, 변경을 수행
	
	private String _filePath; //파일 경로를 알려주는 변수
	private String _fileName; //파일 이름을 알려주는 변수
	private IOType _fileIOType; //파일 형태를 알려주는 변수
	
	private FileReader _fileReader; //파일을 읽어오는 변수
	private BufferedReader _reader; //파일을 버퍼에 넣는 변수
	private FileWriter _fileWriter; //파일에 데이터를 쓰는 변수
	private File _file; //파일을 객체로 보는 변수
	
	
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
	
	//생성자
	public FileObject(String givenFilePath, String givenFileName, IOType givenIOType){
		//파일 경로와 파일 이름, 파일 타입을 받아서 초기화.
		//givenFileName의 파일을 givenIOType으로 사용하는 파일 객체로 생성한다.
		//아직 파일이 open되지는 않았다.
		this.setFilePath(givenFilePath);
		this.setFileName(givenFileName);
		this.setIOType(givenIOType);
	}
	
	public FileObject(){
		//기본 생성자
		//파일 경로, 파일 이름, 파일 타입이 주어지지 않은 상태이며, 추후에 setter를 사용하여 주어질 수 있다.
	}
	
	//공개 함수
	//File 객체는 파일의 접근을 지원하여 파일 생성, 변경, 삭제를 수행.
	public static boolean filePathDoesExist(String aFilePath){
		//파일 경로를 받아서 그 파일 경로에 있는 파일을 객체로 만든다. 그 다음 그 파일이 존재하는지 확인.
		if(aFilePath == null) return false;
		
		File file = new File(aFilePath);
		AppView.DebugPrint("[DEBUG] 입력한 파일 경로 : " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	public static boolean fileDoesExist(String aFilePath, String aFileName){
		//파일 경로와 파일 이름을 받아서 그 파일 경로에 있는 같은 이름의 파일을 객체로 만든다. 그 다음 파일이 존재하는지 확인.
		if(aFilePath == null || aFileName == null){
			return false;
		}
		
		File file = new File(aFilePath + "/" + aFileName);
		AppView.DebugPrint("[DEBUG] 입력한 파일 경로 및 이름: " + file.getAbsolutePath() + "\n");
		
		return file.exists();
	}
	
	//파일의 타입에 따라서 닫는 스트림이 달라진다. 현재는  출력 파일 스트림만 사용
	public boolean closeFile(){
		//입력 파일일 경우에는 파일을 읽어오기 때문에 reader을 close 해주고
		//출력 파일일 경우에는 파일에 데이터를 쓰기 때문에 writer을 close 해준다.
		if(this.fileIOType().equals(IOType.Input)){ //입력 파일의 경우
			try {
				this.reader().close();
				this.fileReader().close();
			}
			catch(IOException e) {
				e.printStackTrace(); //어디서 문제가 생겼는지 알려주는 함수
				return false;
			}
		}
		else if (this.fileIOType().equals(IOType.Output)){ //출력 파일의 경우
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
	
	//예외처리를 통해 사용하기 위한 filPath(), fileName()이 설정되었는지 확인.
	//파일 타입에 따라서 여는 방식이 달라진다.
	//input : 파일이 존재하는지 확인 후, 파일 오픈
	//output: 파일이 존재할 경우 에러로 표시(파일이 없을 경우에 만들어서 작성하는 것)
	public boolean openFile(){
		if(this.filePath() == null || this.fileName() == null){
			// 파일 경로와 파일 이름이 없는 경우 오류 메세지 띄움
			AppView.outputErrorLine("File Object에 파일 이름이나 파일 경로가 설정되어 있지 않습니다.");
			return false;
		}
		
		File aFile = null; 
		
		if(this.fileIOType().equals(IOType.Input)){
			//파일을 입력 파일로 받을 경우 파일 경로와 파일 이름으로 파일 객체를 만든다.
			aFile = new File(this.filePath() + "/" + this.fileName());
			//openFile
			try {
				//파일을 읽어온다.
				this.setFileReader(new FileReader(aFile)); //파일을 fileReader로 읽어온다.
				this.setReader(new BufferedReader(this.fileReader())); //읽어온 fileReader의 값을 bufferedReader에 넣어준다.
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		}
		else if(this.fileIOType().equals(IOType.Output)){
			//파일을 출력 파일로 받을 경우 파일 경로와 파일 이름으로 파일 객체를 만든다.
			aFile = new File(this.filePath() + "/" + this.fileName());
			//openFile
			try{
				//파일에 데이터를 쓰기 위해 파일을 새로 생성할 수 있는지 확인한다.
				if(aFile.createNewFile() == false){
					return false;
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			
			try{
				//파일에 데이터를 쓸 수 있게 한다.
				this.setFileWriter(new FileWriter(aFile));
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		else{
			//입력도 출력도 아닌 경우
			AppView.outputErrorLine("File IOType이 알맞지 않습니다.");
			return false;
		}
		
		this.setFile(aFile);
		return true;
	}
	
	//write() 함수의 경우 String타입으로 기록
	public boolean writeInteger(Integer value){
		//파일에 데이터를 입력하는 함수
		if(this.file().canWrite() == false){ //파일에 데이터를 쓸 수 있는지 확인
			return false;
		}
		if(value == null){ //데이터가 존재하는지 확인
			return false;
		}
		
		try{ //파일에 데이터를 쓴다
			this.fileWriter().write(String.format("%d\n", value.intValue())); 
		}
		catch(IOException e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean removeFile() {
		//파일을 삭제 
		boolean state = false;
		try {
			//파일을 삭제하고 boolean 값을 return 한다
			state = this.file().delete(); 
		}
		catch(SecurityException e) {
			e.printStackTrace();
		}
		return state;
	}
	
	public boolean renameFile(String aNewFileName) {
		//파일의 이름을 바꾼다
		File newFile = new File(aNewFileName); //새로운 이름으로 새 파일을 만든다
		boolean result = this.file().renameTo(newFile); //새로운 이름을 가진 파일로 교체한다
		
		if(result == false) { //교체를 성공했는지 확인
			return false;
		}
		
		this.setFile(newFile); //교체를 성공했으면 newFile로 현재 파일을 set
		return true;
	}
	
	public Integer readInteger() { //파일에서 줄 단위로 값을 읽어오기 위한 함수
		//bufferedReader을 이용하여 파일에서 값을 읽어옴
		if(this.file().canRead() == false) { //읽을 수 있는 파일인지 확인
			return null;
		}
		Integer value = null;
		try {
			//파일을 읽어올 수 있는 경우 파일에 대해서 한줄씩 읽어온다
			String readedLine = this.reader().readLine();
			if(readedLine == null) return null;
			value = Integer.parseInt(readedLine); //한줄씩 읽어온 라인을 저장
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return value;
	}
}
