package huffman;

public class HuffmanTreeLeafNode
	extends HuffmanTreeNode {
	
	//����� �ν��Ͻ� ����
	private short _byteCode;
	
	//getter/setter
	protected short byteCode() {
		return this._byteCode;
	}
	
	protected void setByteCode(short newByteCode) {
		this._byteCode = newByteCode;
	}
	
	//������
	protected HuffmanTreeLeafNode(short givenByteCode) {
		this.setLeft(null);
		this.setRifht(null);
		this.setByteCode(givenByteCode);
	}
}
