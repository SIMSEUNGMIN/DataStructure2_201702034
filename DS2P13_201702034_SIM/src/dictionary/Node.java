package dictionary;

public class Node {
	//item�� Key�� �����ϱ� ���� Ŭ����
	//bucket���� LinkedList T element�� ���ԵǴ� ����
	//bucket�� LinkedList �ȿ��� key�� ���� item�� ���� ����
	
	//��� �ȿ��� Ű�� ���� item�� ���� ����
	private Key _key;
	private Item _item; //object
	
	//getter/setter
	public Key key() {
		return this._key;
	}
	
	public void setKey(Key aKey) {
		this._key = aKey;
	}
	
	public Item item() {
		return this._item;
	}
	
	public void setItem(Item aItem) {
		this._item = aItem;
	}
	
	//������
	public Node() {
		this.setKey(null);
		this.setItem(null);
	}
	
	public Node(Key givenKey, Item givenObject) {
		this.setKey(givenKey);
		this.setItem(givenObject);
	}

}
