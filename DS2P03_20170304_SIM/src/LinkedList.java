
public class LinkedList<T> {
	
	//����� �ν��Ͻ� ����
	private LinkedNode<T> _head;
	private int _size;
	
	//����� �ν��Ͻ� ���� getter/setter
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
	
	//������
	public LinkedList(){
		this.setSize(0);
		this.setHead(null);
	}
	
	//�����Լ�
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
		
		//����� �ν��Ͻ� ����
		LinkedNode<T> _nextNode;
		
		private LinkedNode<T> nextNode(){
			return this._nextNode;
		}
		
		private void setNextNode(LinkedNode<T> newLinkedNode){
			this._nextNode = newLinkedNode;
		}
		
		//������
		private IteratorForLinkedList(){
			this.setNextNode(LinkedList.this.head());
		} 
		
		//���� �Լ� ����
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
