
public class CircularQueue<T> implements Queue<T> {
	
	//비공개 인스턴스 변수
	private T[] _elements;
	private int _capacity;
	private int _front;
	private int _rear;
	
	//비공개 인스턴스 변수 setter/getter
	private T[] elements(){
		return this._elements;
	}
	
	private void setElement(T[] newElements){
		this._elements = newElements;
	}
	
	private int capacity(){
		return this._capacity;
	}
	
	private void setCapacity(int newCapacity){
		this._capacity = newCapacity;
	}
	
	private int front(){
		return this._front;
	}
	
	private void setFront(int newFront){
		this._front = newFront;
	}
	
	private int rear(){
		return this._rear;
	}
	
	private void setRear(int newRear){
		this._rear = newRear;
	}
	
	//비공개 함수
	private int nextRear(){
		return (this.rear()+1) % this.capacity();
	}
	
	private int nextFront(){
		return (this.front()+1) % this.capacity();
	}
	
	//생성자
	public CircularQueue(int maxNumberOfElements){
		this.setCapacity(maxNumberOfElements+1); //환형큐의 full은 elements의 수보다 1 작기 떄문.
		this.setElement((T[]) new Object[this.capacity()]); 
		this.reset();
	}
	
	//Queue의 공개함수
	@Override
	public void reset() {
		this.setFront(0);
		this.setRear(0);
	}
	
	@Override
	public int size() {
		if(this.front() <= this.rear()){
			return (this.rear() - this.front());
		}
		else{
			return(this.capacity() + this.rear() - this.front());
		}
	}
	
	@Override
	public boolean isEmpty() {
		return (this.front() == this.rear());
	}
	
	@Override
	public boolean isFull() {
		return (this.front() == this.nextRear());
	}
	
	@Override
	public boolean add(T anElement) {
		if(this.isFull()){
			return false;
		}
		else{
			this.setRear(this.nextRear());
			this.elements()[this.rear()] = anElement;
			return true;
		}
	}
	
	@Override
	public T remove() {
		if(this.isEmpty()){
			return null;
		}
		else{
			this.setFront(this.nextFront());
			return this.elements()[this.front()];
		}
	}
}
