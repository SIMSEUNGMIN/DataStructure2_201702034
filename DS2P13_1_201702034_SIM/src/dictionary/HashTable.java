package dictionary;

import java.lang.reflect.Array;

import list.Iterator;
import list.LinkedList;
import list.List;

public class HashTable implements Dictionary {
	//������ hash�� ����, �����Ϸ��� ���ڴܾ��� ���ڸ� �ջ��Ͽ� index�� �����Ѵ�
	//hash�� �ε����� �����Ǿ��ִ� (���� ���� ĭ)
	//�� ĭ�� openHashing���� ���� ĭ�� ���� ���Ҹ� ���� ĭ�� �ø� �� �ִµ� �װ��� �ٷ� bucket
	//buckets�� ���� �̷���������� �� �ȿ��� �� hash�� index���� �´� ��(item)���� ����ȴ�
	

	//�ؽ� ���̺� ������ ����
	//�����ŭ bucket�� �����Ѵ�
	private  static final int HASH_TABLE_SIZE = 193;
	
	//����� �ν��Ͻ� ����
	private List<Node>[] _bucket; //bucket (�ؽ� �ε���)
	private int _hashTableSize; //�ؽ� ������
	private int _numOfItems; //hashTable�� ���� item�� ����
	private int _numOfNonEmptyBuckets; //������� ���� bucket�� ����
	//index(key)�� ���� ���� ���� bucket�� ����ִ� ��(item)���� Ȯ���� �� �ִ� �ݺ���
	private DictionaryKeyIterator _dictionaryKeyIterator; 
	
	//getter/setter
	public DictionaryKeyIterator dictionaryKeyIterator() {
		return this._dictionaryKeyIterator;
	}
	
	public void setDictionaryKeyIterator(DictionaryKeyIterator aDictionaryKeyIterator) {
		this._dictionaryKeyIterator = aDictionaryKeyIterator;
	}
	
	private List<Node>[] bucket(){
		return this._bucket;
	}
	
	private void setBucket(List<Node>[] aNode) {
		this._bucket = aNode;
	}
	
	private int hashTableSize() {
		return this._hashTableSize;
	}
	
	private void sethashTableSize(int aHashTableSize) {
		this._hashTableSize = aHashTableSize;
	}
	
	private int numOfItems() {
		return this._numOfItems;
	}
	
	private void setNumOfItems(int aNumOfItems) {
		this._numOfItems = aNumOfItems;
	}
	
	private int numOfNonEmptyBuckets() {
		return this._numOfNonEmptyBuckets;
	}
	
	private void setNumOfNonEmptyBuckets(int aNumOfNonEmptyBuckets) {
		this._numOfNonEmptyBuckets = aNumOfNonEmptyBuckets;
	}
	
	//������
	@SuppressWarnings("unchecked")
	public HashTable() {
		this.sethashTableSize(HASH_TABLE_SIZE); //�ؽ� ������ �ʱ�ȭ
		this.setNumOfItems(0); //���� ������ ����(items)�� �ʱ�ȭ
		this.setNumOfNonEmptyBuckets(0); //������� ���� bucket�� �ʱ�ȭ
		
		//bucket�� �����(hashTable�� �����)
		//bucket�� hashTableSize�� ũ�⸸ŭ ��������� �� �ϳ��� bucket���� ���� LinkedList�� ��������� (�ᱹ LinkedList�� �迭)
		this.setBucket((LinkedList<Node>[]) Array.newInstance(LinkedList.class, this.hashTableSize()));
		
		//bucket(LinkedList�� �迭)�� �ϳ��� LinkedList<Node>�� ��ü ������ ���ش�
		for(int i = 0; i < this.hashTableSize(); i++) {
			this._bucket[i] = new LinkedList<Node>();
		}
	}
	
	//���� �Լ�
	@Override
	public boolean KeyDoesExist(Key aKey) {
		//���� Ű�� �����ϴ��� Ȯ��
		//Ű�� value���� ��� Ű�� �ٸ���
		int hashIndex = this.hash(aKey.value());
		List<Node> sysnonymList = this.bucket()[hashIndex];
		//key�� ���� ��� index���� ������ �˷���
		if(this.bucket()[hashIndex].isEmpty()) {
			//�ؽ� �ε����� �ƿ� ��������� false;
			return false;
		}
		else {
			//������ �ݺ��ڸ� ����Ͽ� bucket�� Ž���Ѵ�
			Iterator<Node> iterator = sysnonymList.iterator();
			while(iterator.hasNext()) {
				Node node = iterator.next();
				if(node.key().value().equals(aKey.value())) {
					return true;
				}
			}
			return false;
		}
	}

	@Override
	public Object objectForKey(Key aKey) {
		//key�� �ش��ϴ� ���� ������ �� key���� �´� object�� ��ȯ
		int hashIndex = this.hash(aKey.value());
		//bucket�� �ε����� �´� list�� ������
		List<Node> aList = this.bucket()[hashIndex];
		
		if(!aList.isEmpty()) {
			//����Ʈ�� ������� �ʴٸ� �� ����Ʈ�� ��� ���� Ȯ���� �� �ִ� �ݺ��� ����
			Iterator<Node> iterator = aList.iterator();
			
			while(iterator.hasNext()) {
				//�ݺ��ڿ� ���� �ִ��� Ȯ���ϸ鼭 �� ���� value�� ���� key�� value�� ��
				Node aNode = iterator.next();
				if(aNode.key().value().equals(aKey.value())) {
					//���� ���� �� �ִ� ��� �� ����� ������ ������ ��ȯ
					Item tmp = new Item(aNode.item().count());
					return tmp;
				}
			}
		}
		//�´°� ������ null ��ȯ
		return null;
	}

	@Override
	public boolean addObjectForKey(Object anObject, Key aKey) {
		// Object -> Item
		if(! this.KeyDoesExist(aKey)) {
			//Ű�� �ߺ��Ǵ��� Ȯ�� 
			//Ű�� �ߺ����� ������ (�� Ű�� �ش��ϴ� �ε����� ���� ���ٸ�)
			int hashIndex = hash(aKey.value());
			Node aNode = new Node(aKey, (Item)anObject);
			//��带 ���� �� ��带 �ؽ� �ε����� ����
			this.bucket()[hashIndex].add(aNode);
			return true;
		}
		//�����ϸ� false
		return false;
	}

	@Override
	public boolean replaceObjectForKey(Object anObject, Key aKey) {
		if(this.KeyDoesExist(aKey)) {
			//Ű�� �����ϸ� (�̹� Ű�� �ִٴ� ���� �� ���� �ؽ��� �ִٴ� ��)
			int hashIndex = hash(aKey.value());
			Node aNode = null;
			Iterator<Node> iterator = this.bucket()[hashIndex].iterator();
			while(iterator.hasNext()) {
				aNode = iterator.next();
				if(aNode.key().value().equals(aKey.value())) {
					//Ű ���� ���� �������� ã�� ��� (??????)
					aNode.item().incrementCount();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean removeObjectForKey(Object anObject, Key aKey) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeAllObjects() {
		// TODO Auto-generated method stub
		
	}
	
	public float loadingFactor() {
		//�ؽ� �߿��� ���� ����ϰ� �ִ� �е��� ����
		return ((float)this.numOfNonEmptyBuckets() / (float) this.hashTableSize());
	}
	
	public float averageSynonymListLength() {
		//�ؽ����� ���� ��Ŷ �ε��� �ȿ� ������ ����� ���̸� ���
		Iterator<Node> iterator = null;
		for(int i = 0; i < HASH_TABLE_SIZE; i++) {
			if(this.bucket()[i] != null) {
				//��Ŷ�� ������� ������ �ݺ��ڷ� ���� Ȯ��
				iterator = this.bucket()[i].iterator();
				while(iterator.hasNext()) {
					Node aNode = iterator.next();
					Item anItem = aNode.item();
					this.setNumOfItems(this.numOfItems() + anItem.count()); //???????
				}
			}
		}
		return ((float) this.numOfItems() / (float) this.numOfNonEmptyBuckets());
	}

	@Override
	public Iterator<Object> iterator() {
			//�ݺ��ڸ� ȣ���ϴ� �Լ�
		return new DictionaryKeyIterator();
	}
	
	public class DictionaryKeyIterator implements Iterator<Object>{
		
		private int _currentBucketIndex;
		private Object _availableNode;
		@SuppressWarnings({ "rawtypes", "unused" })
		private Iterator _linkedListIterator;
		
		//getter/setter
		private int currentBucketIndex() {
			return this._currentBucketIndex;
		}
		
		private void setCurrentBucketIndex(int aCurrentBucketIndex) {
			this._currentBucketIndex = aCurrentBucketIndex;
		}
		
		private Object availableNode() {
			return this._availableNode;
		}
		
		private void setAvailableNode(Object aNode) {
			this._availableNode = aNode;
		}
		
		//������
		private DictionaryKeyIterator() {
			this.begin();
		}
		
		//���� �Լ�
		public void begin() {
			//��ȸ�� ���� �ʱⰪ�� ����, �ʱⰪ ������ ���� �Լ�
			if(_bucket == null) {
				return;
			}
			this.setCurrentBucketIndex(0);
			nextNonEmptyBucket();
		}
		
		public boolean nextNonEmptyBucket() {
			//������� ���� ��Ŷ�� �˻�
			for(int i = this.currentBucketIndex(); i < _hashTableSize; i++) {
				if(!_bucket[i].isEmpty()) {
					this.setCurrentBucketIndex(++i);
					_numOfNonEmptyBuckets++;
					_linkedListIterator = bucket()[currentBucketIndex()-1].iterator();
					this.setAvailableNode(_linkedListIterator.next());
					return true;
				}
			}
			//���̻� ��� ������ ��Ŷ�� ���� �� ������
			this.setCurrentBucketIndex(_hashTableSize);
			this.setAvailableNode(null);
			return false;
		}

		@Override
		public boolean hasNext() {
			//��� ������ ��尡 null�� �ƴ϶�� ���� ��尡 �ִ�
			return this.availableNode() != null;
		}

		@Override
		public Object next() {
			//���� ��� ������ ��尡 ���� ��� ���� ��带 �����ϱ� ���� ���
		
			if(this.currentBucketIndex() == 0
					|| this.availableNode() == null) {
				if(!nextNonEmptyBucket()) {
					return null;
				}
			}
			Object aNode = this.availableNode();
			if(_linkedListIterator.hasNext()) {
				//���� ��Ŷ �ȿ� ��尡 ���� ��� ����Ʈ�� ����
				setAvailableNode(_linkedListIterator.next());
			}
			else {
				//���� ��Ŷ �ȿ� ��尡 ���� ���, ���ο� ��Ŷ �˻�
				nextNonEmptyBucket();
			}
			return aNode;
		}
	}
	
	//����� �Լ�
	private int hash(String idString) {
		//�ؽ����� key���� �� ĭ�� �˷���
		
		int sum = 0;
		int index = 0;
		char[] id = idString.toCharArray(); //���� ���ڸ� �ϳ��� �迭�� ����
		
		while(index < id.length) {
			//���ڵ��� ���� int������ �ٲ� ���� ���� ���� ���Ѵ�
			sum = sum + (int)id[index];
			index++;
		}
		//���� ���� �ؽ����̺� ������� ������ �ؽ��� ��� ĭ�� ������ ���´�
		return (sum % this.hashTableSize());
	}

}
