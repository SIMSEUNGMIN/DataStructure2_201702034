package app;

public class AppController {
	
	//상수
	
	//인스턴스변수
	private CompressionController _compressionController;
	private DecompressionController _decompressionController;
	private ValidationController _validationController;
	
	//getter/setter
	private CompressionController compressionController() {
		if(this._compressionController == null) {
			//lazy instantiation
			this._compressionController = new CompressionController();
		}
		return this._compressionController;
	}
	
	private DecompressionController decompressionController() {
		if(this._decompressionController == null) {
			this._decompressionController = new DecompressionController();
		}
		return this._decompressionController;
	}
	
	private ValidationController validationController() {
		if(this._validationController == null) {
			this._validationController = new ValidationController();
		}
		return this._validationController;
	}
	
	//private methods
	private MainMenu selectMenu() {
		AppView.outputLine("");
		AppView.output("? 해야할 작업을 선택하시오 (압축 =1, 해제=2, 검증=3, 종료=4): ");
		try {
			int selectedMenuNumber = AppView.inputInteger();
			MainMenu selectedMenuValue = MainMenu.value(selectedMenuNumber);
			if(selectedMenuValue == MainMenu.ERROR) {
				AppView.outputLine("!오류: 작업 선택이 잘못되었습니다. (잘못된 번호: " + selectedMenuNumber + ")");
			}
			return selectedMenuValue;
		}
		catch(NumberFormatException e) {
			AppView.outputLine("!오류 : 입력된 메뉴 번호가 정수형 숫자가 아닙니다.");
			return MainMenu.ERROR;
		}
	}
	
	//public method
	public void run() {
		AppView.outputLine("<<< Huffman Code 를 이용햔 파일 압축/해제 프로그램을 시작합니다. >>>");
		
		MainMenu selectedMenuValue = this.selectMenu();
		while(selectedMenuValue != MainMenu.END) {
			switch(selectedMenuValue) {
			case COMPRESS:
				this.compressionController().run();
				break;
			case DECOMPRESS:
				this.decompressionController().run();
				break;
			case VALIDATE:
				this.validationController().run();
				break;
			default:
				break;
			}
			selectedMenuValue = this.selectMenu();
		}
		AppView.outputLine("");
		AppView.outputLine("<<< <<< Huffman Code 를 이용한 파일 압축/해제 프로그램을 종료합니다 >>>");
	}
}
