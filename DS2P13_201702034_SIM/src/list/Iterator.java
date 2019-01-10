package list;

public interface Iterator<T> { 
	//어떤 것의 값을 볼지 모르기 때문에 제너릭 타입으로 한다
	
	//현재 원소가 있는지 확인
	public boolean hasNext();
	//현재 원소를 출력
	public T next();
}
