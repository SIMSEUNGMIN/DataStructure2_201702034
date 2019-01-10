package app;

import experiment.DataGenerator;
import sort.QuickSortByPivotLeft;
import sort.QuickSortByPivotMedian;
import sort.QuickSortByPivotMid;
import sort.QuickSortByPivotRandom;
import sort.QuickSortWithInsertionSort;
import sort.Sort;

public class AppController {
	
	//������
	private static final int TEST_SIZE = 10000;
	
	private static final QuickSortByPivotLeft<Integer> pivotLeft = new QuickSortByPivotLeft<Integer>(true); 
	private static final QuickSortByPivotMedian<Integer> pivotMedian = new QuickSortByPivotMedian<Integer>(true);
	private static final QuickSortByPivotMid<Integer> pivotMid = new QuickSortByPivotMid<Integer>(true);
	private static final QuickSortByPivotRandom<Integer> privotRandom = new QuickSortByPivotRandom<Integer>(true);
	private static final QuickSortWithInsertionSort<Integer> insertionSort = new QuickSortWithInsertionSort<Integer>(true);
	
	//����� �ν��Ͻ� ����
	private Integer[] _list;
	private String _listTypeName;
	
	//getter/setter
	private Integer[] list(){
		return this._list;
	}
	
	private void setList(Integer[] newList){
		this._list = newList;
	}
	
	private String listTypeName(){
		return this._listTypeName;
	}
	
	private void setListTypeName(String newListTypeName){
		this._listTypeName = newListTypeName;
	}
	
	//����� �Լ�
	private String sortingOrderName(Sort<Integer> aSort){
		if(aSort.nonDecreasingOrder()){
			return "��������";
		}
		else{
			return "��������";
		}
	}
	
	private Integer[] copyList(Integer[] aList){
		Integer[] copiedList = new Integer[aList.length];
		for(int i = 0; i < aList.length; i++){
			copiedList[i] = aList[i];
		}
		return copiedList;
	}
	
	private void outputVaildationMessage(Sort<Integer> aSort, Integer[] aList){
		AppView.output("- [" + this.listTypeName() + "]�� ���� [" + 
				this.sortingOrderName(aSort) + "]�� [" + 
				aSort.getClass().getSimpleName() + "] ����� ");
		if(this.sortedListIsValid(aList, aSort.nonDecreasingOrder())){
			AppView.outputLine("�ùٸ��ϴ�.");
		}
		else{
			AppView.outputLine("�߸��Ǿ����ϴ�.");
		}
	}
	
	private boolean sortedListIsValid(Integer[] aList, boolean nonDecreasing){
		if(nonDecreasing){
			for(int i = 0; i < aList.length-1; i++){
				if(aList[i].compareTo(aList[i+1]) > 0){
					return false;
				}
			}
			return true;
		}
		else{
			for(int i = 0; i < aList.length-1; i++){
				if(aList[i].compareTo(aList[i+1]) < 0){
					return false;
				}
			}
			return true;
		}
	}
	
	private void validateSort(Sort<Integer> aSort){
		Integer[] list = copyList(this.list());
		aSort.sort(list);
		this.outputVaildationMessage(aSort, list);
	}
	
	private void validateSorts(){
		this.validateSort(AppController.pivotLeft);
		this.validateSort(AppController.pivotMid);
		this.validateSort(AppController.pivotMedian);
		this.validateSort(AppController.privotRandom);
		this.validateSort(AppController.insertionSort);
		AppView.outputLine("");
	}
	
	private void validateWithRandomList(){
		this.setListTypeName("����������Ʈ");
		this.setList(DataGenerator.randomList(AppController.TEST_SIZE));
		this.validateSorts();
	}
	
	private void validateWithAscendingList(){
		this.setListTypeName("�������� ����Ʈ");
		this.setList(DataGenerator.ascendingList(AppController.TEST_SIZE));
		this.validateSorts();
	}
	
	private void validateWithDescendingList(){
		this.setListTypeName("�������� ����Ʈ");
		this.setList(DataGenerator.descedingList(AppController.TEST_SIZE));
		this.validateSorts();
	}
	
	private void setSortingOrder(boolean aNonDreasing){
		AppController.pivotLeft.setNonDecreasingOrder(aNonDreasing);
		AppController.pivotMid.setNonDecreasingOrder(aNonDreasing);
		AppController.pivotMedian.setNonDecreasingOrder(aNonDreasing);
		AppController.privotRandom.setNonDecreasingOrder(aNonDreasing);
		AppController.insertionSort.setNonDecreasingOrder(aNonDreasing);
	}
	
	//public method
	public void run(){
		AppView.outputLine("<<< ���� �˰������ �����ϴ� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		
		AppView.outputLine("> �������� ���� ���α׷��� ����: ");
		this.setSortingOrder(true);
		this.validateWithRandomList();
		this.validateWithAscendingList();
		this.validateWithDescendingList();
		
		AppView.outputLine("> �������� ���� ���α׷� ����: ");
		this.setSortingOrder(false);
		this.validateWithRandomList();
		this.validateWithAscendingList();
		this.validateWithDescendingList();
		
		AppView.outputLine("<<< ���� �˰������ �����ϴ� ���α׷��� �����մϴ� >>>");
	}
}
