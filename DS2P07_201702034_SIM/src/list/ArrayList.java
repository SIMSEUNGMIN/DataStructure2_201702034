package list;

public class ArrayList<T> extends List<T> {
	
	//상수
	private static final int DEFAULT_CAPACITY = 10;
	
	//비공개 인스턴스 변수
	private int _capacity;
	private int _size;
	private T[] _element;
	
	//getter/setter
	private int capacity(){
		return this._capacity;
	}
	
	private void setCapacity(int newCapacity){
		this._capacity = newCapacity;
	}
	
	@Override
	public int size() {
		return this._size;
	}
	
	private void setSize(int newSize){
		this._size = newSize;
	}
	
	private T[] elements(){
		return this._element;
	}
	
	private void setElements(T[] newElement){
		this._element = newElement;
	}
	
	//생성자
	public ArrayList(){
		this(ArrayList.DEFAULT_CAPACITY);
	}
	
	public ArrayList(int givenCapacity){
		this.setCapacity(givenCapacity);
		this.setElements((T[]) new Object[this.capacity()]);
		this.setSize(0);
	}

	//공개함수
	@Override
	public boolean isEmpty() {
		return (this.size() == 0);
	}

	@Override
	public boolean isFull() {
		return (this.size() == this.capacity());
	}

	@Override
	public boolean add(T anElement) {
		return this.addToLast(anElement);
	}
	
	public boolean addToLast(T anElement){
		if(!this.isFull()){
			this.elements()[this.size()] = anElement;
			this.setSize(this.size()+1);
		}
		return false;
	}

	@Override
	public T removeAny() {
		return this.removeLast();
	}
	
	public T removeLast(){
		if(!this.isEmpty()){
			this.setSize(this.size()-1);
			T removeElement = this.elements()[this.size()];
			this.elements()[this.size()] = null;
			return removeElement;
		}
		return null;
	}

	@Override
	public void reset() {
		for(int i = 0; i < this.size(); i++){
			this.elements()[i] = null;
		}
		this.setSize(0);
	}
	
	//반복자
	@Override
	public Iterator<T> listIterator() {
		return new IteratorForArrayList();
	}
	
	private  class IteratorForArrayList implements Iterator<T>{
		
		//비공개 인스턴스 변수
		private int _nextPosition;
		
		//getter/setter
		private int nextPosition(){
			return this._nextPosition;
		}
		
		private void setNextPosition(int newPosition){
			this._nextPosition = newPosition;
		}
		
		//생성자
		private IteratorForArrayList(){
			this.setNextPosition(0);
		}
		
		//공개 함수
		@Override
		public boolean hasNext() {
			return (this.nextPosition() < ArrayList.this.size());
		}

		@Override
		public T next() {
			T nextElement = ArrayList.this.elements()[this.nextPosition()];
			this.setNextPosition(this.nextPosition()+1);
			return nextElement;
		}
	}
	
}
