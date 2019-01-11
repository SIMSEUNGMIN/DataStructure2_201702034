package huffman;

public class HuffmanTreeLeafNode
	extends HuffmanTreeNode {
	
	//비공개 인스턴스 변수
	private short _byteCode;
	
	//getter/setter
	protected short byteCode() {
		return this._byteCode;
	}
	
	protected void setByteCode(short newByteCode) {
		this._byteCode = newByteCode;
	}
	
	//생성자
	protected HuffmanTreeLeafNode(short givenByteCode) {
		this.setLeft(null);
		this.setRifht(null);
		this.setByteCode(givenByteCode);
	}
}
