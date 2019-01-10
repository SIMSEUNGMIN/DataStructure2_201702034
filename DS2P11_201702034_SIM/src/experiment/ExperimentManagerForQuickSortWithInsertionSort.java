package experiment;

import app.AppView;
import sort.QuickSortByPivotRandom;
import sort.QuickSortWithInsertionSort;

public class ExperimentManagerForQuickSortWithInsertionSort extends ExperimentManager {

	//디버그 메세지
	private static final boolean DEBUG_MODE = false;
	private static void showDebugMessage(String aMessage){
		if(ExperimentManagerForQuickSortWithInsertionSort.DEBUG_MODE){
			AppView.outputDebugMessage(aMessage);
		}
	}
	
	//상수(experiment parameters)
	protected static final int DEFAULT_NUMBER_OF_ITERATION = 10;
	protected static final int DEFAULT_ICREMENT_SIZE = 10000;
	protected static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;
	
	protected static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 10;
	
	//상수(sort objects)
	private static final QuickSortByPivotRandom<Integer> QuickSortByPivotRandom = 
			new QuickSortByPivotRandom<Integer>(true);
	private static final QuickSortWithInsertionSort<Integer> QuickSortWithInsertionSort =
			new QuickSortWithInsertionSort<Integer>(true);
	
	//상수(insertion sort 사이즈)
	private static final int DEFAULT_INSERTION_SORT_STARTING_SIZE = 10;
	private static final int DEFAULT_INSERTION_SORT_INCREMENT_SIZE = 10;
	private static final int DEFAULT_INSERTION_SORT_NUMBER_OF_ITERATION = 9;
	
	//비공개인스턴스변수
	private ParameterSetForIteration _parameterSetForMaxSizeOfInsertionSort;
	
	private long[] _measurementForQuickSortByPivotRandom;
	private long[][] _measurementsForQuickSortWithInsertionSort;
	
	//getter/setter
	public ParameterSetForIteration parameterSetForMaxSizeOfInsertionSort(){
		return this._parameterSetForMaxSizeOfInsertionSort;
	}
	
	public void setParameterSetForMaxSizeOfInsertionSort(ParameterSetForIteration newParameterSet){
		this._parameterSetForMaxSizeOfInsertionSort = newParameterSet;
	}
	
	private long[] measurementForQuickSortByPivotRandom(){
		return this._measurementForQuickSortByPivotRandom;
	}
	
	private void setMeasurementForQuickSortByPivotRandom(long[] newMeasurement){
		this._measurementForQuickSortByPivotRandom = newMeasurement;
	}
	
	private long[][] measurementForQuickSortWithInsertionSort(){
		return this._measurementsForQuickSortWithInsertionSort;
	}
	
	private void setMeasurementsForQuickWithInsertionSort(long[][] newMeasurements){
		this._measurementsForQuickSortWithInsertionSort = newMeasurements;
	}
	
	private void setMeasurementForQuickWithInsertionSort(
			long[] newMeasurement, int measurementIndex){
		this.measurementForQuickSortWithInsertionSort()[measurementIndex] = newMeasurement;
	}
	
	//protected method
	@Override
	protected void performMeasuring(ListOrder anOrder) {
		Integer[] list = this.dataSet().listWithOrder(anOrder);
		
		this.setMeasurementsForQuickWithInsertionSort(
				new long[this.parameterSetForMaxSizeOfInsertionSort().numberOfIteration()][]);
		
		this.setMeasurementForQuickSortByPivotRandom(
				this.experiment().durationOfSort(
						ExperimentManagerForQuickSortWithInsertionSort.QuickSortByPivotRandom,
						list));
		
		ExperimentManagerForQuickSortWithInsertionSort.showDebugMessage(
				"[Debug] end of QuickSortByPivotRandom\n");
		
		for(int iteration = 0;
				iteration < this.parameterSetForMaxSizeOfInsertionSort().numberOfIteration();
				iteration++){
			
			int size = this.parameterSetForMaxSizeOfInsertionSort().startingSize() +
					this.parameterSetForMaxSizeOfInsertionSort().incrementSize() * iteration;
			QuickSortWithInsertionSort.setMaxSizeForInsertionSort(size);
			this.setMeasurementForQuickWithInsertionSort(this.experiment().durationOfSort(ExperimentManagerForQuickSortWithInsertionSort.QuickSortWithInsertionSort,list),
					iteration);
			ExperimentManagerForQuickSortWithInsertionSort.showDebugMessage(
					"[Debug] end of QuickSortWithInsertionSort" + size + "\n");
		}
	}
	
	//생성자
	public ExperimentManagerForQuickSortWithInsertionSort(){
		this.setParameterSetForMeasurement(new ParameterSetForMeasurement(
				DEFAULT_STARTING_SIZE,
				DEFAULT_NUMBER_OF_ITERATION,
				DEFAULT_INCREMENT_SIZE,
				DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT));
		this.setParameterSetForMaxSizeOfInsertionSort(new ParameterSetForIteration(
				DEFAULT_INSERTION_SORT_STARTING_SIZE,
				DEFAULT_INSERTION_SORT_NUMBER_OF_ITERATION,
				DEFAULT_INSERTION_SORT_INCREMENT_SIZE));
	}
	
	//공개함수
	public long measurementForQuickSortByPivotRandomAt(int anIndex){
		return this.measurementForQuickSortByPivotRandom()[anIndex];
	}
	
	public long measurementForQuickSortWithInsertionSortAt(int aMeasurementIndex, int anElementIndex){
		return this.measurementForQuickSortWithInsertionSort()[aMeasurementIndex][anElementIndex];
	}
	
	
	@Override
	public void performExperiment(ListOrder anOrder) {
		this.performMeasuring(anOrder);
		
	}

}
