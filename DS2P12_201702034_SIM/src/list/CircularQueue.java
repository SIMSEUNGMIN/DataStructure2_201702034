package list;

public class CircularQueue<T> implements Queue<T> {
	
	private final static int DEFAULT_CIRCULAR_QUEUE_CAPACITY = 1000;
		
	//비공개 인스턴스 변수
	private int _size;
	private int _capacity;
	private T[] _elements;
	private int _rear;
	private int _front;
		
	//비공개 인스턴스 변수 setter/getter
	private int capacity(){
		return this._capacity;
	}
	
	private void setCapacity(int newCapacity){
		this._capacity = newCapacity;
	}
	
	private T[] elements(){
		return this._elements;
	}
	
	private void setElement(T[] newElements){
		this._elements = newElements;
	}
	
	private int rear(){
		return this._rear;
	}
	
	private void setRear(int newRear){
		this._rear = newRear;
	}
		
	private int front(){
		return this._front;
	}
	
	private void setFront(int newFront){
		this._front = newFront;
	}
	
	public int size() {
		return this._size;
	}
	
	private void setSize(int aSize) {
		this._size = aSize;
	}
	
	//생성자
	public CircularQueue() {
		this(DEFAULT_CIRCULAR_QUEUE_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public CircularQueue(int givenCapacity) {
		this.setCapacity(givenCapacity + 1);
		this.setElement((T[]) new Object[this.capacity()]);
		
		this.setFront(0);
		this.setRear(-1);
	}
		
	//공개함수
	@Override
	public void reset() {
		
	}
	
	@Override
	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	@Override
	public boolean isFull() {
		return (this.size() == this.capacity());
	}
	
	@Override
	public boolean add(T element) {
		if(this.isFull()){
			//큐를 사용가능한지 확인
			throw new ArrayIndexOutOfBoundsException();
		}

		if(this.rear() == (this.capacity()-1)) {
			//원형 큐 동작을 위해서 크기를 벗어났을 경우 다시 처음 인덱스로 전환한다
			this.setRear(0); 
		}
		else {
			this.setRear(this.rear() + 1);
		}
		
		//해당 인덱스에 원소 삽입, 사이즈 조정
		this.elements()[this.rear()] = element;
		this.setSize(this.size() + 1);
		
		return true;
	}
	
	@Override
	public T remove() {
		if(this.isEmpty()){
			return null;
		}
		
		//가장 위에 있는 것을 가져온다
		T item = this.peek();
		this.setFront(this.front() + 1);
		if(this.front() == this.capacity()) {
			this.setFront(0);
		}
		
		this.setSize(this.size()-1);
		
		return item;
	}
	
	@Override
	public T peek() {
		if(this.isEmpty()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return this.elements()[this.front()];
	}

	@Override
	public boolean offer(T element) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public T poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T element() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
