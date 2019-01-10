package dictionary;

public class Node {
	//item과 Key를 관리하기 위한 클래스
	//bucket에서 LinkedList T element에 삽입되는 원소
	//bucket의 LinkedList 안에는 key의 값과 item의 수가 들어간다
	
	//노드 안에는 키의 값과 item의 값이 들어간다
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
	
	//생성자
	public Node() {
		this.setKey(null);
		this.setItem(null);
	}
	
	public Node(Key givenKey, Item givenObject) {
		this.setKey(givenKey);
		this.setItem(givenObject);
	}

}
