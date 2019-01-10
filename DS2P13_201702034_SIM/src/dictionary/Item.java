package dictionary;

public class Item {
	//Node���� ���� ī��Ʈ�� ����ϱ� ���� �ڷᱸ��
	
	private int _count; //�����۵��� ����
	
	//getter/setter
	public int count() {
		return this._count;
	}
	
	public void setCount(int aCount) {
		this._count = aCount;
	}
	
	//������
	public Item() {
		this.setCount(0);
	}
	
	public Item(int givenCount) {
		this.setCount(givenCount);
	}
	
	//���� �Լ�
	public void incrementCount() {
		this.setCount(this.count()+1);
	}
	
}
