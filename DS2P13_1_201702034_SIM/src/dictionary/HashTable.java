package dictionary;

import java.lang.reflect.Array;

import list.Iterator;
import list.LinkedList;
import list.List;

public class HashTable implements Dictionary {
	//실제로 hash를 구현, 저장하려는 글자단어의 글자를 합산하여 index를 생성한다
	//hash는 인덱스로 구현되어있다 (여러 개의 칸)
	//이 칸은 openHashing으로 같은 칸에 들어가는 원소를 위해 칸을 늘릴 수 있는데 그것이 바로 bucket
	//buckets는 노드로 이루어져있으며 그 안에는 그 hash의 index값에 맞는 값(item)들이 저장된다
	

	//해쉬 테이블 사이즈 지정
	//사이즈만큼 bucket가 존재한다
	private  static final int HASH_TABLE_SIZE = 193;
	
	//비공개 인스턴스 변수
	private List<Node>[] _bucket; //bucket (해쉬 인덱스)
	private int _hashTableSize; //해쉬 사이즈
	private int _numOfItems; //hashTable에 들어온 item의 개수
	private int _numOfNonEmptyBuckets; //비어있지 않은 bucket의 개수
	//index(key)의 값에 따라 안의 bucket에 들어있는 값(item)들을 확인할 수 있는 반복자
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
	
	//생성자
	@SuppressWarnings("unchecked")
	public HashTable() {
		this.sethashTableSize(HASH_TABLE_SIZE); //해쉬 사이즈 초기화
		this.setNumOfItems(0); //들어온 원소의 개수(items)를 초기화
		this.setNumOfNonEmptyBuckets(0); //비어있지 않은 bucket수 초기화
		
		//bucket를 만든다(hashTable을 만든다)
		//bucket은 hashTableSize의 크기만큼 만들어지고 그 하나의 bucket들은 전부 LinkedList로 만들어진다 (결국 LinkedList의 배열)
		this.setBucket((LinkedList<Node>[]) Array.newInstance(LinkedList.class, this.hashTableSize()));
		
		//bucket(LinkedList의 배열)을 하나씩 LinkedList<Node>로 객체 생성을 해준다
		for(int i = 0; i < this.hashTableSize(); i++) {
			this._bucket[i] = new LinkedList<Node>();
		}
	}
	
	//공개 함수
	@Override
	public boolean KeyDoesExist(Key aKey) {
		//받은 키가 존재하는지 확인
		//키의 value값은 모든 키가 다르다
		int hashIndex = this.hash(aKey.value());
		List<Node> sysnonymList = this.bucket()[hashIndex];
		//key의 값이 어느 index인지 들어가는지 알려줌
		if(this.bucket()[hashIndex].isEmpty()) {
			//해쉬 인덱스가 아예 비어있으면 false;
			return false;
		}
		else {
			//있으면 반복자를 사용하여 bucket를 탐색한다
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
		//key에 해당하는 값이 있으면 그 key값에 맞는 object를 반환
		int hashIndex = this.hash(aKey.value());
		//bucket의 인덱스에 맞는 list를 꺼내옴
		List<Node> aList = this.bucket()[hashIndex];
		
		if(!aList.isEmpty()) {
			//리스트가 비어있지 않다면 그 리스트의 노드 값을 확인할 수 있는 반복자 생성
			Iterator<Node> iterator = aList.iterator();
			
			while(iterator.hasNext()) {
				//반복자에 값이 있는지 확인하면서 그 값의 value와 받은 key의 value를 비교
				Node aNode = iterator.next();
				if(aNode.key().value().equals(aKey.value())) {
					//만약 같은 게 있는 경우 그 노드의 아이템 개수를 반환
					Item tmp = new Item(aNode.item().count());
					return tmp;
				}
			}
		}
		//맞는게 없으면 null 반환
		return null;
	}

	@Override
	public boolean addObjectForKey(Object anObject, Key aKey) {
		// Object -> Item
		if(! this.KeyDoesExist(aKey)) {
			//키가 중복되는지 확인 
			//키가 중복되지 않으면 (그 키에 해당하는 인덱스에 값이 없다면)
			int hashIndex = hash(aKey.value());
			Node aNode = new Node(aKey, (Item)anObject);
			//노드를 만들어서 그 노드를 해쉬 인덱스에 저장
			this.bucket()[hashIndex].add(aNode);
			return true;
		}
		//실패하면 false
		return false;
	}

	@Override
	public boolean replaceObjectForKey(Object anObject, Key aKey) {
		if(this.KeyDoesExist(aKey)) {
			//키가 존재하면 (이미 키가 있다는 것은 그 값이 해쉬에 있다는 것)
			int hashIndex = hash(aKey.value());
			Node aNode = null;
			Iterator<Node> iterator = this.bucket()[hashIndex].iterator();
			while(iterator.hasNext()) {
				aNode = iterator.next();
				if(aNode.key().value().equals(aKey.value())) {
					//키 값과 같은 아이템을 찾은 경우 (??????)
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
		//해쉬 중에서 현재 사용하고 있는 밀도를 구함
		return ((float)this.numOfNonEmptyBuckets() / (float) this.hashTableSize());
	}
	
	public float averageSynonymListLength() {
		//해쉬에서 같은 버킷 인덱스 안에 생성된 노드의 길이를 계산
		Iterator<Node> iterator = null;
		for(int i = 0; i < HASH_TABLE_SIZE; i++) {
			if(this.bucket()[i] != null) {
				//버킷이 비어있지 않으면 반복자로 길이 확인
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
			//반복자를 호출하는 함수
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
		
		//생성자
		private DictionaryKeyIterator() {
			this.begin();
		}
		
		//공개 함수
		public void begin() {
			//순회를 위한 초기값을 설정, 초기값 설정을 위한 함수
			if(_bucket == null) {
				return;
			}
			this.setCurrentBucketIndex(0);
			nextNonEmptyBucket();
		}
		
		public boolean nextNonEmptyBucket() {
			//비어있지 않은 버킷을 검색
			for(int i = this.currentBucketIndex(); i < _hashTableSize; i++) {
				if(!_bucket[i].isEmpty()) {
					this.setCurrentBucketIndex(++i);
					_numOfNonEmptyBuckets++;
					_linkedListIterator = bucket()[currentBucketIndex()-1].iterator();
					this.setAvailableNode(_linkedListIterator.next());
					return true;
				}
			}
			//더이상 사용 가능한 버킷이 없을 때 설정함
			this.setCurrentBucketIndex(_hashTableSize);
			this.setAvailableNode(null);
			return false;
		}

		@Override
		public boolean hasNext() {
			//사용 가능한 노드가 null이 아니라면 다음 노드가 있다
			return this.availableNode() != null;
		}

		@Override
		public Object next() {
			//현재 사용 가능한 노드가 있을 경우 다음 노드를 연결하기 위한 노드
		
			if(this.currentBucketIndex() == 0
					|| this.availableNode() == null) {
				if(!nextNonEmptyBucket()) {
					return null;
				}
			}
			Object aNode = this.availableNode();
			if(_linkedListIterator.hasNext()) {
				//같은 버킷 안에 노드가 있을 경우 리스트로 연결
				setAvailableNode(_linkedListIterator.next());
			}
			else {
				//같은 버킷 안에 노드가 없을 경우, 새로운 버킷 검색
				nextNonEmptyBucket();
			}
			return aNode;
		}
	}
	
	//비공개 함수
	private int hash(String idString) {
		//해쉬에서 key값이 들어갈 칸을 알려줌
		
		int sum = 0;
		int index = 0;
		char[] id = idString.toCharArray(); //받은 글자를 하나씩 배열로 나눔
		
		while(index < id.length) {
			//글자들을 전부 int형으로 바꾼 다음 값을 전부 더한다
			sum = sum + (int)id[index];
			index++;
		}
		//더한 값을 해쉬테이블 사이즈로 나누면 해쉬의 어느 칸에 들어갈지가 나온다
		return (sum % this.hashTableSize());
	}

}
