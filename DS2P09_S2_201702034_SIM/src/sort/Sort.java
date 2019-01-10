package sort;

public abstract class Sort<E extends Comparable<E>> {

	//비공개 인스턴스 변수
	private boolean _nonDecreasingOrder;
	
	//getter/setter
	public boolean nonDecreasingOrder(){
		return this._nonDecreasingOrder;
	}
	
	public void setNonDecreasingOrder(boolean newNonDecreasingOrder){
		this._nonDecreasingOrder = newNonDecreasingOrder;
	}
	
	//protected Methods
	protected void swap(E[] aList, int i, int j){
		E tempElement = aList[i];
		aList[i] = aList[j];
		aList[j] = tempElement;
	}
	
	protected int compare(E anElement, E theOtherElement){
		if(this.nonDecreasingOrder()){
			return anElement.compareTo(theOtherElement);
		}
		else{
			return -anElement.compareTo(theOtherElement);
		}
	}
	
	//생성자
	public Sort(boolean givenSortingOrder){
		this.setNonDecreasingOrder(givenSortingOrder);
	}
	
	public abstract boolean sort(E[] aList);
}
