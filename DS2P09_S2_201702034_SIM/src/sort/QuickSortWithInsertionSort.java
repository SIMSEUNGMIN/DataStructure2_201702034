package sort;

public class QuickSortWithInsertionSort<E extends Comparable<E>>
	extends QuickSortByPivotRandom<E>{
	
	public QuickSortWithInsertionSort(boolean givenSortingOrder){
		super(givenSortingOrder);
	}
	
	private static final int MAX_SIZE_FOR_INSERTION_SORT = 20;
	
	private boolean insertionSort(E[] aList, int left, int right){
		for(int i = (right -1); i >= left; i--){
			E insertedElement = aList[i];
			int j = i + 1;
			while(this.compare(aList[j], insertedElement) < 0){
				aList[j-1] = aList[j];
				j++;
			}
			aList[j-1] = insertedElement;
		}
		return true;
	} 
	
	protected void quickSortRecursively(E[] aList, int left, int right){
		int currentSize = right - left;
		if(currentSize > 0){
			if(currentSize <= QuickSortWithInsertionSort.MAX_SIZE_FOR_INSERTION_SORT){
				this.insertionSort(aList, left, right);
			}
			else{
				int mid = partition(aList, left, right);
				quickSortRecursively(aList, left, mid-1);
				quickSortRecursively(aList, mid+1, right);
			}
		}
	}

}
