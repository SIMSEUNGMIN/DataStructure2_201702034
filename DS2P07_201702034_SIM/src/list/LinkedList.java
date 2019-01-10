package list;

public class LinkedList<T> extends List<T>{
	
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
	
	@Override
	public int size(){
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	//������
	public LinkedList(){
		this.reset();
	}
	
	//�����Լ�
	@Override
	public boolean isEmpty(){
		return (this.size() == 0);
	}
	
	@Override
	public boolean isFull(){
		return false;
	}
	
	@Override
	public boolean add(T anElement){
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement, this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	
	@Override
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
	
	@Override
	public void reset() {
		this.setSize(0);
		this.setHead(null);
	}

	//element Iterator
	@Override
	public Iterator<T> listIterator() {
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
		@SuppressWarnings("unChecked")
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
