package list;

public class LinkedNode<T> {

	//비공개인스턴스변수
	private T _element;
	private LinkedNode<T> _next;
	
	//비공개인스턴스변수의 getter/setter
	public T element(){
		return this._element;
	}
	
	public void setElement(T newElement){
		this._element = newElement;
	}
	
	public LinkedNode<T> next(){
		return this._next;
	}
	
	public void setNext(LinkedNode<T> newNext){
		this._next = newNext;
	}
	
	//생성자
	public LinkedNode(){
		this.setElement(null);
		this.setNext(null);
	}
	
	public LinkedNode(T givenElement, LinkedNode<T> givenNext){
		this.setElement(givenElement);
		this.setNext(givenNext);
	}
}
