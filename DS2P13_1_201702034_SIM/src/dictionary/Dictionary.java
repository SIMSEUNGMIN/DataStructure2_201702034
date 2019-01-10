package dictionary;

import list.Iterator;

public interface Dictionary {
	
	//Ű�� �����ϴ� Ű���� Ȯ��
	public boolean KeyDoesExist(Key aKey);
	//Ű�� �ش��ϴ� ������Ʈ�� ��ȯ
	public Object objectForKey(Key aKey);
	
	//Ű�� ������Ʈ�� �߰�
	public boolean addObjectForKey(Object anObject, Key aKey);
	//Ű�� �ִ� ������Ʈ�� ��ü
	public boolean replaceObjectForKey(Object anObject, Key aKey);
	//Ű�� �ִ� ������Ʈ�� ����
	public boolean removeObjectForKey(Object anObject, Key aKey);
	//��� ������Ʈ�� ����
	public void removeAllObjects();
	
	//������Ʈ�� Ȯ���� �� �ִ� �ݺ���
	public Iterator<Object> iterator();
	

}
