package experiment;

public class ParameterSetForIteration {
	
	//상수
	private static final int DEFAULT_NUMBER_OF_ITERATION = 10;
	private static final int DEFAULT_INCREMENT_SIZE = 1000;
	private static final int DEFAULT_STARTING_SIZE = DEFAULT_INCREMENT_SIZE;
	
	//비공개 인스턴스 변수
	private int _startingSize;
	private int _numberOfIteration;
	private int _incrementSize;
	
	//getter/setter
	public int startingSize(){
		return this._startingSize;
	}
	
	public void setStartingSize(int newStartingSize){
		this._startingSize = newStartingSize;
	}
	
	public int numberOfIteration(){
		return this._numberOfIteration;
	}
	
	public void setNumberofIteration(int newNumberOfIteration){
		this._numberOfIteration = newNumberOfIteration;
	}
	
	public int incrementSize(){
		return this._incrementSize;
	}
	
	public void setIncrementSize(int newIncrementSize){
		this._incrementSize = newIncrementSize;
	}
	
	public int maxDatasize(){
		return(this.startingSize() + (this.incrementSize()*(this.numberOfIteration()-1)));
	}
	
	//생성자
	public ParameterSetForIteration(){
		this.setStartingSize(DEFAULT_STARTING_SIZE);
		this.setNumberofIteration(DEFAULT_NUMBER_OF_ITERATION);
		this.setIncrementSize(DEFAULT_INCREMENT_SIZE);
	}
	
	public ParameterSetForIteration(
			int givenStartingSize,
			int givenNumberOfIteration,
			int givenIncrementSize){
		this.setStartingSize(givenStartingSize);
		this.setNumberofIteration(givenNumberOfIteration);
		this.setIncrementSize(givenIncrementSize);
	}
}
