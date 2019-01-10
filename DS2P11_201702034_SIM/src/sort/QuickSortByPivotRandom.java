package sort;

import java.util.Random;

public class QuickSortByPivotRandom<E extends Comparable<E>>
	extends QuickSort<E>{
	
	//����� �ν��Ͻ� ����
	private Random _random;
	
	//getter/setter
	public Random random(){
		return this._random;
	}
	
	public void setRandom(Random newRandom){
		this._random = newRandom;
	}

	@Override
	protected int pivot(E[] aList, int left, int right) {
		int randomLocationFromLeft = this.random().nextInt(right-left+1);
		int pivot = left + randomLocationFromLeft;
		return pivot;
	}
	
	public QuickSortByPivotRandom(boolean givenSortingOrder){
		super(givenSortingOrder);
		this.setRandom(new Random());
	}

}