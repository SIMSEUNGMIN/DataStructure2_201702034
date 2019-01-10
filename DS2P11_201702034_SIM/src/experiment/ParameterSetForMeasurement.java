package experiment;

public class ParameterSetForMeasurement extends ParameterSetForIteration {
	
	//상수
	private static final int DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT = 1;
	
	//비공개변수
	private int _numberOfRepetitionOfSingleSort;
	
	//getter/setter
	public int numberOfRepetitionOfSingleSort(){
		return this._numberOfRepetitionOfSingleSort;
	}
	
	public void setNumberOfRepetitionOfSingleSort(int newNumberOfRepetitionOfSingleSort){
		this._numberOfRepetitionOfSingleSort = newNumberOfRepetitionOfSingleSort;
	}
	
	//생성자
	public ParameterSetForMeasurement(){
		super();
		this.setNumberOfRepetitionOfSingleSort(DEFAULT_NUMBER_OF_REPETITION_OF_SINGLE_SORT);
	}
	
	public ParameterSetForMeasurement(
			int givenStartingSize,
			int givenNumberOfIteration,
			int givenIncrementSize,
			int givenNumberOfRepetitionOfSingleSort){
		super(givenStartingSize, givenNumberOfIteration, givenIncrementSize);
		this.setNumberOfRepetitionOfSingleSort(givenNumberOfRepetitionOfSingleSort);
	}
}
