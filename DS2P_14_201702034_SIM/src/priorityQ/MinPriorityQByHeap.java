package priorityQ;

public class MinPriorityQByHeap<E extends Comparable<E>> {

		//상수
		private static final int DEFALUT_CAPACITY = 256;
		private static final int HEAP_ROOT = 1;
		
		//비공개 인스턴스 변수
		private int _size;
		private int _capacity;
		private E[] _heap;
		
		//getter/setter
		public int size(){
			return this._size;
		}
		
		public void setSize(int newSize){
			this._size = newSize;
		}
		
		public int capacity(){
			return this._capacity;
		}
		
		private void setCapacity(int newCapacity){
			this._capacity = newCapacity;
		}
		
		private E[] heap(){
			return this._heap;
		}
		
		private void setHeap(E[] newHeap){
			this._heap = newHeap;
		}
		
		//생성자
		public MinPriorityQByHeap(){
			this(MinPriorityQByHeap.DEFALUT_CAPACITY);
		}
		
		@SuppressWarnings("unchecked")
		public MinPriorityQByHeap(int givenCapacity){
			this.setCapacity(givenCapacity);
			this.setHeap((E[]) new Comparable[givenCapacity+1]);
			this.setSize(0);
		}
		
		//공개 함수
		public boolean isEmpty(){
			return (this.size() == 0);
		}
		
		public boolean isFull(){
			return (this.size() == this.capacity());
		}
		
		public boolean add(E anElement){
			if(this.isFull()){
				return false;
			}
			else{
				int positionForAdd = this.size()+1;
				this.setSize(positionForAdd);
				while((positionForAdd > 1) &&
						(anElement.compareTo(this.heap()[positionForAdd/2] ) < 0)){
					this.heap()[positionForAdd] = this.heap()[positionForAdd/2];
					positionForAdd = positionForAdd /2;
				}
				this.heap()[positionForAdd] = anElement;
				return true;
			}
		}
		
		public E min(){
			if(this.isEmpty()){
				return null;
			}
			else{
				return this.heap()[MinPriorityQByHeap.HEAP_ROOT];
			}
		}
	
		public E reomoveMin(){
			if(this.isEmpty()){
				return null;
			}
			else{
				E rootElement = this.heap()[MinPriorityQByHeap.HEAP_ROOT];
				this.setSize(this.size()-1);
				if(this.size() > 0){
					//삭제한 후에 적어도 하나의 원소가 남아있다.
					//그러므로 마지막 위치(this.size()+1)의 원소를 떼어내어,
					//root 위치 this.heap()[1]로부터 아래쪽으로 새로운 위치를 찾아 내려간다.
					E lastElement = this.heap()[this.size()+1];
					int parent = MinPriorityQByHeap.HEAP_ROOT;
					while((parent*2) <= this.size()){
						//child가 존재, left, right 중에서 더 작은 key 값을 갖는 child를 smallerChild로 한다.
						int smallerChild = parent * 2;
						if((smallerChild < this.size()) && 
								(this.heap()[smallerChild].compareTo(this.heap()[smallerChild+1]) > 0)){
							smallerChild++; //rightChild가 존재하고, 그 값이 작으므로 rightChild를 smallerChild로 한다.
						}
						if(lastElement.compareTo(this.heap()[smallerChild]) <= 0){
							break; //lastElement는 더이상 아래로 내려갈 필요가 없다. 현재의 parent위치에 삽입하면 된다.
						}
						//child원소를 parent 위치로 올려 보낸다. child의 위치는 새로운 parent 위치가 된다.
						this.heap()[parent] = this.heap()[smallerChild];
						parent = smallerChild;
					} //end while
					this.heap()[parent] = lastElement;
				}
				return rootElement;
			}
		}		
}
