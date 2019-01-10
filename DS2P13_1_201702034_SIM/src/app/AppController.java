package app;

import java.io.IOException;

import dictionary.Dictionary;
import dictionary.HashTable;
import dictionary.Item;
import dictionary.Key;
import dictionary.Node;
import lexicalScan.LexicalScanner;
import lexicalScan.Token;
import lexicalScan.Token.TokenType;
import list.Iterator;

public class AppController {
	
	//private static boolean DEBUG = true;
	
	private String _fileName;
	private LexicalScanner _lexScanner;
	private Dictionary _dictionary = new HashTable();
	
	//getter/setter
	private String fileName() {
		return this._fileName;
	}
	
	private void setFileName(String aFileName) {
		this._fileName = aFileName;
	}
	
	private LexicalScanner lexSanner() {
		return this._lexScanner;
	}
	
	private void setLexicalScanner(LexicalScanner aLexScanner) {
		this._lexScanner = aLexScanner;
	}
	
	private Dictionary dictionary() {
		return this._dictionary;
	}

	//생성자
	public AppController() {}
	
	//비공개 함수
	private void inputAndMakeDictionary() {
		
		//스캔할 파일을 입력받는다.
		this.inputFileName();
		
		AppView.outputLine("> 파일을 스캔하여 사전을 구성합니다:");
		//LexicalScanner을 생성하고 파일을 오픈한다
		this.setLexicalScanner(new LexicalScanner(this.fileName()));
		
		//파일에서 토큰을 가져온다.
		Token token = null;
		Key aKey = null;
		Item anItem = null;
		
		try {
			while((token = this.lexSanner().nextToken()) != null) {
				if(token.tokenType() == TokenType.TOKEN_ID) {
					//input Dictionary
					aKey = Token.KeyFromToken(token);
					//해쉬에 키가 있는지 확인
					if(this.dictionary().KeyDoesExist(aKey)) {
						anItem = (Item)this.dictionary().objectForKey(aKey);
						this.dictionary().replaceObjectForKey(anItem, aKey);
					}
					else {
						//해쉬에 해당 키가 없음
						anItem = new Item(1); //first count
						this.dictionary().addObjectForKey(anItem, aKey);
					}
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		AppView.outputLine("");
	}
	
	private void showAllKeyAndItems() {
		Item item = null;
		String keyValue = "";
		
		//목록을 순회
		Iterator<Object> dicKeyIterator = this.dictionary().iterator();
		int itemCount = 0;
		
		while(dicKeyIterator.hasNext()) {
			Node aNode = (Node)dicKeyIterator.next();
			Key key = aNode.key();
			keyValue = key.value();
			item = (Item)this.dictionary().objectForKey(key);
			AppView.outputLine(" * count= " + itemCount + ",    Key \"" + keyValue + "\",   frequency=" + item.count());
			itemCount++;
		}
	}
	
	private void showHashResult() {
		AppView.outputLine("> 사전 정보를 출력합니다 ");
		this.showAllKeyAndItems();
		float loadingFactor = ((HashTable)this.dictionary()).loadingFactor();
		AppView.outputLine("");
		
		AppView.outputLine("- Loading Factor: " + loadingFactor);
		AppView.outputLine("");
		
		float averageSynonymListLength = ((HashTable)this.dictionary()).averageSynonymListLength();
		AppView.outputLine(" - Average Synonym List Length: " + averageSynonymListLength);
		AppView.outputLine("");
		AppView.outputLine("");
	}

	private void inputFileName() {
			String fileName;
			
			AppView.outputLine("> 스캔할 파일의 이름을 입력해 주세요.");
			fileName = AppView.fileName();
			
			this.setFileName(fileName);
	}
	
	public void run() {
		AppView.outputLine("<<< 해쉬를 이용한 사전 프로그램을 시작합니다 >>>");
		
		this.inputAndMakeDictionary();
		this.showHashResult();
		
		AppView.outputLine("<<< 해쉬를 이용한 사전 프로그램을 종료합니다 >>>");
	}
}
