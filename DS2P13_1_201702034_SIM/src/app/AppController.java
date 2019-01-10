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

	//������
	public AppController() {}
	
	//����� �Լ�
	private void inputAndMakeDictionary() {
		
		//��ĵ�� ������ �Է¹޴´�.
		this.inputFileName();
		
		AppView.outputLine("> ������ ��ĵ�Ͽ� ������ �����մϴ�:");
		//LexicalScanner�� �����ϰ� ������ �����Ѵ�
		this.setLexicalScanner(new LexicalScanner(this.fileName()));
		
		//���Ͽ��� ��ū�� �����´�.
		Token token = null;
		Key aKey = null;
		Item anItem = null;
		
		try {
			while((token = this.lexSanner().nextToken()) != null) {
				if(token.tokenType() == TokenType.TOKEN_ID) {
					//input Dictionary
					aKey = Token.KeyFromToken(token);
					//�ؽ��� Ű�� �ִ��� Ȯ��
					if(this.dictionary().KeyDoesExist(aKey)) {
						anItem = (Item)this.dictionary().objectForKey(aKey);
						this.dictionary().replaceObjectForKey(anItem, aKey);
					}
					else {
						//�ؽ��� �ش� Ű�� ����
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
		
		//����� ��ȸ
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
		AppView.outputLine("> ���� ������ ����մϴ� ");
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
			
			AppView.outputLine("> ��ĵ�� ������ �̸��� �Է��� �ּ���.");
			fileName = AppView.fileName();
			
			this.setFileName(fileName);
	}
	
	public void run() {
		AppView.outputLine("<<< �ؽ��� �̿��� ���� ���α׷��� �����մϴ� >>>");
		
		this.inputAndMakeDictionary();
		this.showHashResult();
		
		AppView.outputLine("<<< �ؽ��� �̿��� ���� ���α׷��� �����մϴ� >>>");
	}
}
