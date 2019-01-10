package dictionary;

public class Key {
	//Node에서 토큰에 대한 값을 저장하기 위하여 사용(bucket를 구분한다, Key값으로)
	
	private String _value; //Key의 값(hash에 들어가는 값, 문자열)
	
	//getter/setter
	public String value() {
		return this._value;
	}
	
	public void setValue(String aValue) {
		this._value = aValue;
	}
	
	//생성자
	public Key() {
		this.setValue(null);
	}
	
	public Key(String givenKey) {
		this.setValue(givenKey);
	}
	
	//key비교 함수
	public int compareTo(Object arg0) {
		Key aKey = (Key)arg0;
		int index = 0;
		
		//한 글자씩 비교한다
		while(this.value().charAt(index) == aKey.value().charAt(index)) {
			if(this.value().charAt(index) == '\0') { //만약 글자의 끝이라면 return
				//두 글자가 같은 경우
				return 0;
			}
			index++;
		}
		//같지 않은 경우 차이를 return
		return (this.value().charAt(index) - aKey.value().charAt(index));
	}

}
