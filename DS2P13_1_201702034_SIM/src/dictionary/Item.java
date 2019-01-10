package dictionary;

public class Item {
	//Node에서 내부 카운트를 기록하기 위한 자료구조
	
	private int _count; //아이템들의 개수
	
	//getter/setter
	public int count() {
		return this._count;
	}
	
	public void setCount(int aCount) {
		this._count = aCount;
	}
	
	//생성자
	public Item() {
		this.setCount(0);
	}
	
	public Item(int givenCount) {
		this.setCount(givenCount);
	}
	
	//공개 함수
	public void incrementCount() {
		this.setCount(this.count()+1);
	}
	
}
