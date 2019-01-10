package list;

import list.Iterator;

public interface List<T> {
	//� ���� ����Ʈ�� ������ �𸣱� ������ ���ʸ� Ÿ������ �Ѵ�.
	//�ݺ��ڸ� ����ϱ� ������ �ݺ��ڿ� ���� ���ʸ� Ÿ�� ������ ���� (T) (�� �𸣰ڴ�)

	//����� Ȯ��
	public int size();
	
	//����Ʈ�� ����ִ��� Ȯ��
	public boolean isEmpty();
	//����Ʈ�� �� á���� Ȯ��
	public boolean isFull();
	
	//����Ʈ�� ���Ҹ� ����
	public boolean add(T anElement);
	//����Ʈ�� �ƹ� ���Ҹ� ����
	public T removeAny();
	
	//����Ʈ�� ���� �ʱ�ȭ
	public void reset();
	
	//����Ʈ�� ���Ҹ� Ȯ���� �� �ִ� �ݺ���
	public Iterator<T> iterator();
}
