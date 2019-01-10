package list;

public interface Queue<T> {
	
	public void reset();
	
	public int size();
	
	public boolean isEmpty();
	public boolean isFull();
	
	public boolean add(T anElement);
	public boolean offer(T element);
	
	public T remove();
	public T poll();
	
	public T element();
	public T peek();

}
