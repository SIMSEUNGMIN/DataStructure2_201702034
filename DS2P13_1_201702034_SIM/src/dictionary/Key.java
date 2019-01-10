package dictionary;

public class Key {
	//Node���� ��ū�� ���� ���� �����ϱ� ���Ͽ� ���(bucket�� �����Ѵ�, Key������)
	
	private String _value; //Key�� ��(hash�� ���� ��, ���ڿ�)
	
	//getter/setter
	public String value() {
		return this._value;
	}
	
	public void setValue(String aValue) {
		this._value = aValue;
	}
	
	//������
	public Key() {
		this.setValue(null);
	}
	
	public Key(String givenKey) {
		this.setValue(givenKey);
	}
	
	//key�� �Լ�
	public int compareTo(Object arg0) {
		Key aKey = (Key)arg0;
		int index = 0;
		
		//�� ���ھ� ���Ѵ�
		while(this.value().charAt(index) == aKey.value().charAt(index)) {
			if(this.value().charAt(index) == '\0') { //���� ������ ���̶�� return
				//�� ���ڰ� ���� ���
				return 0;
			}
			index++;
		}
		//���� ���� ��� ���̸� return
		return (this.value().charAt(index) - aKey.value().charAt(index));
	}

}
