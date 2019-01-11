package huffman;

public class HuffmanCode {
	
	//비공개 인스턴스 변수
	private int _length;
	private boolean[] _code = new boolean[Huffman_CONST.MAX_CODE_LENGTH];
	
	//비공개 함수
	private void setLength(int newLength) {
		this._length = newLength;
	}
	
	private boolean[] code() {
		return this._code;
	}
	
	private void setBitAtIndex(int index, boolean bitValue) {
		this.code()[index] = bitValue;
	}
	
	//생성자
	protected HuffmanCode() {
		this.reset();
	}
	
	//object copy method
	protected HuffmanCode clone() {
		HuffmanCode clone = new HuffmanCode();
		clone.setLength(this.length());
		for(int i = 0; i < this.length(); i++) {
			clone.setBitAtIndex(i, this.bitAtIndex(i));
		}
		return clone;
	}
	
	//protected method
	protected void reset() {
		this.setLength(0);
	}
	
	protected void appendBit(boolean bitValue) {
		if(this.length() < Huffman_CONST.MAX_CODE_LENGTH) {
			this.setBitAtIndex(this.length(), bitValue);
			this.setLength(this.length()+1);
		}
	}
	
	protected void removeLastBit() {
		if(this.length() > 0) {
			this.setLength(this.length()-1);
		}
	}
	
	//public method
	public int length() {
		return this._length;
	}
	
	public boolean bitAtIndex(int index) {
		if(index >= 0 && index < this.length()) {
			return this.code()[index];
		}
		else {
			throw new IllegalStateException("Illegl Stzte Exception : Given Index is out of range");
		}
	}
}
