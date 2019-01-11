package huffman;

public class HuffmanTreeNode {
	
	//private Instance variables
	private HuffmanTreeNode _left;
	private HuffmanTreeNode _right;
	
	//getter/setter
	protected HuffmanTreeNode left() {
		return this._left;
	}
	
	protected void setLeft(HuffmanTreeNode newLeft) {
		this._left = newLeft;
	}
	
	protected HuffmanTreeNode right() {
		return this._right;
	}
	
	protected void setRifht(HuffmanTreeNode newRight) {
		this._right = newRight;
	}
	
	//»ý¼ºÀÚ
	protected HuffmanTreeNode() {}
	
	protected HuffmanTreeNode(HuffmanTreeNode givenLeft, HuffmanTreeNode givenRight) {
		this.setLeft(givenLeft);
		this.setRifht(givenRight);
	}
	
	//protect method
	protected boolean isLeaf() {
		return (this.left() == null && this.right() == null);
	}

}
