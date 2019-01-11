package app;

public class AppController {
	
	//���
	
	//�ν��Ͻ�����
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
		AppView.output("? �ؾ��� �۾��� �����Ͻÿ� (���� =1, ����=2, ����=3, ����=4): ");
		try {
			int selectedMenuNumber = AppView.inputInteger();
			MainMenu selectedMenuValue = MainMenu.value(selectedMenuNumber);
			if(selectedMenuValue == MainMenu.ERROR) {
				AppView.outputLine("!����: �۾� ������ �߸��Ǿ����ϴ�. (�߸��� ��ȣ: " + selectedMenuNumber + ")");
			}
			return selectedMenuValue;
		}
		catch(NumberFormatException e) {
			AppView.outputLine("!���� : �Էµ� �޴� ��ȣ�� ������ ���ڰ� �ƴմϴ�.");
			return MainMenu.ERROR;
		}
	}
	
	//public method
	public void run() {
		AppView.outputLine("<<< Huffman Code �� �̿��h ���� ����/���� ���α׷��� �����մϴ�. >>>");
		
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
		AppView.outputLine("<<< <<< Huffman Code �� �̿��� ���� ����/���� ���α׷��� �����մϴ� >>>");
	}
}
