package huffman;

public class HuffmanTree implements Comparable<HuffmanTree> {
	
	//비공개 인스턴스 변수
	private HuffmanTreeNode _root;
	private long _frequency;
	
	//getter/setter
	private void setFrequency(long newFrequency) {
		this._frequency = newFrequency;
	}
	
	public long frequency() {
		return this._frequency;
	}
	
	protected HuffmanTreeNode root() {
		return this._root;
	}
	
	private void setRoot(HuffmanTreeNode newRoot) {
		this._root = newRoot;
	}
	
	//생성자
	protected HuffmanTree(short givenByteCode, long givenFrequency) {
		this.setFrequency(givenFrequency);
		this.setRoot(new HuffmanTreeLeafNode(givenByteCode));
	}
	
	protected HuffmanTree(HuffmanTree givenLeftSubtree, HuffmanTree givenRightSubtree) {
		this.setFrequency(givenLeftSubtree.frequency() + givenRightSubtree.frequency());
		this.setRoot(new HuffmanTreeNode(givenLeftSubtree.root(), givenRightSubtree.root()));
	}
	
	@Override
	public int compareTo(HuffmanTree otherTree) {
		if(this.frequency() < otherTree.frequency()) {
			return -1;
		}
		else if(this.frequency() > otherTree.frequency()) {
			return +1;
		}
		else {
			return 0;
		}
	}	
	

}
