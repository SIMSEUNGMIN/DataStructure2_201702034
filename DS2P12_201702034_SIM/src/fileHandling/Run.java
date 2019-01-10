package fileHandling;

import app.AppView;

/* class FileObject 를 상속받는다.
 * Run file 객체를 생성하고 유지 관리 한다.
 * 생성되면 객체 ID가 부여된다. 이 ID는 일련 번호로 관리되며, 파일 이름의 뒷 부분을 구성한다
 */

public class Run extends FileObject{	
	
	//public static method
	// 이 클래스의 모든 파일이 존재하는 디렉토리 경로를 설정한다.
	private static String _DIRECTORY_PATH = null;
	
	public static void setDirectoryPath(String aDirectoryPath) {
		_DIRECTORY_PATH = aDirectoryPath;
	}
	
	//이 클래스의 모든 파일 이름 앞부분에 붙게 되는 문자열을 설정한다
	private static String _FILE_PREFIX = null;
	
	public static void setFilePrefix(String aFilePrefix) {
		_FILE_PREFIX = aFilePrefix;
	}
	
	//run id 초기 값을 설정한다. 이 값이 주어지지 않으면 기본적으로 0부터 시작한다
	private static int RUN_ID = 0;
	
	public static void setRunId(int newRunId) {
		RUN_ID = newRunId;
	}
	
	//비공개 인스턴스 변수
	private int _runID;
	
	//getter/setter
	private int runID() {
		return this._runID;
	}
	
	private void setID(int aRunID) {
		this._runID= aRunID;
	}
	
	//생성자
	//객체를 생성하고 초기화 한다.
	//생성된 객체에 새로운 runID가 생성되어 부여된다
	public Run() { //파일에 정렬한 결과를 적을 것이기 때문에 출력 파일로 지정. (?)
		this.setID(Run.RUN_ID);
		Run.setRunId(this.runID()+1); //객체 생성시마다 아이디의 값을 늘린다.
		String aFileName = String.format("%s_%d.tmp", Run._FILE_PREFIX, this.runID());
		//파일 이름을 지정하고 경로에 tmp/를 통해 기존 파일과 분리
		this.setFilePath(Run._DIRECTORY_PATH + "/tmp/"); //파일 경로 지정
		this.setFileName(aFileName); // 파일 이름 지정
		this.setIOType(IOType.Output); //출력 파일로 지정
	}
	
	public void makeRun(Integer[] elementList) {
		//인자로 주어진 리스트를 사용하여 run파일에 "작성"하는 함수
		
		if(elementList.length == 0) {
			AppView.outputErrorLine("Make Run이 실패하였습니다: 리스트 없음");
			return;
		}
		
		if(this.openFile() == false) {
			AppView.outputErrorLine("파일을 여는 것이 실패하였습니다:" + this.fileName());
		}
		
		for(int i = 0; i < elementList.length; i++) {
			if(this.writeInteger(elementList[i]) == false) {
				AppView.outputErrorLine("더 이상 쓸 수 없습니다:");
				break;
			}
		}
		
		if(this.closeFile() == false) {
			AppView.outputErrorLine("파일을 닫는 것에 실패하였습니다:" + this.fileName());
			return;
		}
	}
}
