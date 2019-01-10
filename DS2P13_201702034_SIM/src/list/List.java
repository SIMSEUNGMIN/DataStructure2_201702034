package list;

import list.Iterator;

public interface List<T> {
	//어떤 것을 리스트로 만들지 모르기 때문에 제너릭 타입으로 한다.
	//반복자를 사용하기 때문에 반복자와 같은 제너릭 타입 변수를 쓴다 (T) (잘 모르겠다)

	//사이즈가 확인
	public int size();
	
	//리스트가 비어있는지 확인
	public boolean isEmpty();
	//리스트가 다 찼는지 확인
	public boolean isFull();
	
	//리스트에 원소를 더함
	public boolean add(T anElement);
	//리스트의 아무 원소를 삭제
	public T removeAny();
	
	//리스트를 전부 초기화
	public void reset();
	
	//리스트의 원소를 확인할 수 있는 반복자
	public Iterator<T> iterator();
}
