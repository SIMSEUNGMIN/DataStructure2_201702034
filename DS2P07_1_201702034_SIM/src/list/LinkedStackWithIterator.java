package list;

public class LinkedStackWithIterator<E> implements StackWithIterator<E> {
	
	//비공개 인스턴스 변수
	private LinkedNode<E> _top;
	private int _size;
	
	//getter/setter
	private LinkedNode<E> top(){
		return this._top;
	}
	
	private void setTop(LinkedNode<E> newTop){
		this._top = newTop;
	}
	
	@Override
	public int size() {
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	//생성자
	public LinkedStackWithIterator(){
		this.reset();
	}
	
	//공개함수
	@Override
	public void reset() {
		this.setSize(0);
		this.setTop(null);
	}
	
	@Override
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	@Override
	public boolean isFull() {
		return false;
	}
	
	@Override
	public boolean push(E anElement) {
		LinkedNode<E> newTop = new LinkedNode<E>(anElement, this.top());
		this.setTop(newTop);
		this.setSize(this.size() +1);
		return true;
	}
	
	@Override
	public E pop() {
		if(this.isEmpty()){
			return null;
		}
		else{
			E removedElement = this.top().element();
			this.setTop(this.top().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	
	@Override
	public E peek() {
		if(this.isEmpty()){
			return null;
		}
		else{
			return this.top().element();
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return new IteratorForLinkedStack();
	}
	
	private class IteratorForLinkedStack implements Iterator<E>{
		
		//비공개 인스턴스 변수
		private LinkedNode<E> _nextNode;
		
		//getter/setter
		private LinkedNode<E> nextNode(){
			return this._nextNode;
		}
		
		private void setNextNode(LinkedNode<E> newNextNode){
			this._nextNode = newNextNode;
		}
		
		//생성자
		private IteratorForLinkedStack(){
			this.setNextNode(LinkedStackWithIterator.this.top());
		}

		//공개함수
		@Override
		public boolean hasNext() {
			return (this.nextNode() != null);
		}

		@Override
		public E next() {
			E nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}	
	}
	
}
