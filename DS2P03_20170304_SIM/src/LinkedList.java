
public class LinkedList<T> {
	
	//비공개 인스턴스 변수
	private LinkedNode<T> _head;
	private int _size;
	
	//비공개 인스턴스 변수 getter/setter
	private LinkedNode<T> head(){
		return this._head;
	}
	
	private void setHead(LinkedNode<T> newHead){
		this._head = newHead;
	}
	
	public int size(){
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	//생성자
	public LinkedList(){
		this.setSize(0);
		this.setHead(null);
	}
	
	//공개함수
	public boolean isEmpty(){
		return (this.size() == 0);
	}
	
	public boolean isFulle(){
		return false;
	}
	
	public boolean add(T anElement){
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement, this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	
	public T removeAny(){
		if(this.isEmpty()){
			return null;
		}
		else{
			T removeElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size()-1);
			return removeElement;
		}
	}
	
	public IteratorForLinkedList iterator(){
		return new IteratorForLinkedList();
	}
	
	public class IteratorForLinkedList implements Iterator<T>{
		
		//비공개 인스턴스 변수
		LinkedNode<T> _nextNode;
		
		private LinkedNode<T> nextNode(){
			return this._nextNode;
		}
		
		private void setNextNode(LinkedNode<T> newLinkedNode){
			this._nextNode = newLinkedNode;
		}
		
		//생성자
		private IteratorForLinkedList(){
			this.setNextNode(LinkedList.this.head());
		} 
		
		//공개 함수 구현
		@Override
		public boolean hasNext() {
			return (this.nextNode() != null);
		}

		@Override
		public T next() {
			T nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}	
	}
}
