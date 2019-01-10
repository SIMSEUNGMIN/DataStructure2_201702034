package dictionary;

import list.Iterator;

public interface Dictionary {
	
	//키가 존재하는 키인지 확인
	public boolean KeyDoesExist(Key aKey);
	//키에 해당하는 오브젝트를 반환
	public Object objectForKey(Key aKey);
	
	//키에 오브젝트를 추가
	public boolean addObjectForKey(Object anObject, Key aKey);
	//키에 있는 오브젝트를 교체
	public boolean replaceObjectForKey(Object anObject, Key aKey);
	//키에 있는 오브젝트를 삭제
	public boolean removeObjectForKey(Object anObject, Key aKey);
	//모든 오브젝트를 삭제
	public void removeAllObjects();
	
	//오브젝트를 확인할 수 있는 반복자
	public Iterator<Object> iterator();
	

}
