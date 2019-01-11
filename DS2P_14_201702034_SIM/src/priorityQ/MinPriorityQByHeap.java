package priorityQ;

public class MinPriorityQByHeap<E extends Comparable<E>> {

		//���
		private static final int DEFALUT_CAPACITY = 256;
		private static final int HEAP_ROOT = 1;
		
		//����� �ν��Ͻ� ����
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
		
		//������
		public MinPriorityQByHeap(){
			this(MinPriorityQByHeap.DEFALUT_CAPACITY);
		}
		
		@SuppressWarnings("unchecked")
		public MinPriorityQByHeap(int givenCapacity){
			this.setCapacity(givenCapacity);
			this.setHeap((E[]) new Comparable[givenCapacity+1]);
			this.setSize(0);
		}
		
		//���� �Լ�
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
					//������ �Ŀ� ��� �ϳ��� ���Ұ� �����ִ�.
					//�׷��Ƿ� ������ ��ġ(this.size()+1)�� ���Ҹ� �����,
					//root ��ġ this.heap()[1]�κ��� �Ʒ������� ���ο� ��ġ�� ã�� ��������.
					E lastElement = this.heap()[this.size()+1];
					int parent = MinPriorityQByHeap.HEAP_ROOT;
					while((parent*2) <= this.size()){
						//child�� ����, left, right �߿��� �� ���� key ���� ���� child�� smallerChild�� �Ѵ�.
						int smallerChild = parent * 2;
						if((smallerChild < this.size()) && 
								(this.heap()[smallerChild].compareTo(this.heap()[smallerChild+1]) > 0)){
							smallerChild++; //rightChild�� �����ϰ�, �� ���� �����Ƿ� rightChild�� smallerChild�� �Ѵ�.
						}
						if(lastElement.compareTo(this.heap()[smallerChild]) <= 0){
							break; //lastElement�� ���̻� �Ʒ��� ������ �ʿ䰡 ����. ������ parent��ġ�� �����ϸ� �ȴ�.
						}
						//child���Ҹ� parent ��ġ�� �÷� ������. child�� ��ġ�� ���ο� parent ��ġ�� �ȴ�.
						this.heap()[parent] = this.heap()[smallerChild];
						parent = smallerChild;
					} //end while
					this.heap()[parent] = lastElement;
				}
				return rootElement;
			}
		}		
}
