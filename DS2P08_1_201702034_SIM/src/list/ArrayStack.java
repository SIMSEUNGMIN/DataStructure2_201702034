package list;

public class ArrayStack<E> implements Stack<E> {
	//상수
	private static final int DEFAULT_CAPACITY = 100;
	
	//비공개인스턴스 변수
	private int _capacity;
	private int _top;
	private E[] _elements;
	
	//getter/setter
	private int capacity(){
		return this._capacity;
	}
	
	private void setCapacity(int newCapacity){
		this._capacity = newCapacity;
	}
	
	private int top(){
		return this._top;
	}
	
	private void setTop(int newTop){
		this._top = newTop;
	}
	
	protected E[] elements(){
		return this._elements;
	}
	
	private void setElements(E[] newElements){
		this._elements = newElements;
	}
	
	//생성자
	public ArrayStack(){
		this(ArrayStack.DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int givenCapacity){
		this.setCapacity(givenCapacity);
		this.setElements((E[]) new Object[this.capacity()]);
		this.setTop(-1);
	}

	@Override
	public void reset() {
		this.setTop(-1);
	}

	@Override
	public int size() {
		return this._top + 1;
	}

	@Override
	public boolean isEmpty() {
		return (this.top() == -1);
	}

	@Override
	public boolean isFull() {
		return (this.size() == this.capacity());
	}

	@Override
	public boolean push(E anElement) {
		if(this.isFull()){
			return false;
		}
		else{
			this.setTop(this.top() + 1);
			this._elements[this.top()] = anElement;
			return true;
		}
	}

	@Override
	public E pop() {
		E topElement = null;
		if(!this.isEmpty()){
			topElement = this._elements[this.top()];
			this._elements[this.top()] = null;
			this.setTop(this.top()-1);
		}
		return topElement;
	}

	@Override
	public E peek() {
		E topElement = null;
		if(!this.isEmpty()){
			topElement = this._elements[this.top()];
		}
		return topElement;
	}

}
