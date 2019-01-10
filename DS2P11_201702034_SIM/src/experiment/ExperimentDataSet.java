package experiment;

public class ExperimentDataSet {
	
	//비공개인스턴스변수
	private int _maxDataSize;
	
	private Integer[] _randomList;
	private Integer[] _ascendingList;
	private Integer[] _descendingList;
	
	public int maxDataSize(){
		return this._maxDataSize;
	}
	
	public void setMaxDataSize(int newMaxDataSize){
		this._maxDataSize = newMaxDataSize;
	}
	
	public Integer[] randomList(){
		return this._randomList;
	}
	
	public void setRandomList(Integer[] newRandomList){
		this._randomList = newRandomList;
	}
	
	public Integer[] ascendingList(){
		return this._ascendingList;
	}
	
	public void setAscendingList(Integer[] newAscendingList){
		this._ascendingList = newAscendingList;
	}
	
	public Integer[] descendingList(){
		return this._descendingList;
	}
	
	public void setDescendingList(Integer[] newDescendingList){
		this._descendingList = newDescendingList;
	}
	
	//생성자
	public ExperimentDataSet(){
		this.setRandomList(null);
		this.setAscendingList(null);
		this.setDescendingList(null);
	}
	
	public ExperimentDataSet(int givenMaxDataSize){
		if(! this.generate(givenMaxDataSize)){
			this.setRandomList(null);
			this.setAscendingList(null);
			this.setDescendingList(null);
		}
	}
	
	//공개함수
	public boolean generate(int aMaxDataSize){
		if(aMaxDataSize <= 0){
			return false;
		}
		else{
			this.setMaxDataSize(aMaxDataSize);
			this.setRandomList(DataGenerator.randomList(this.maxDataSize()));
			this.setAscendingList(DataGenerator.ascendingList(this.maxDataSize()));
			this.setDescendingList(DataGenerator.descedingList(this.maxDataSize()));
			return true;
		}
	}
	
	public Integer[] listWithOrder(ListOrder anOrder){
		if(anOrder.equals(ListOrder.Random)){
			return this.randomList();
		}
		else if(anOrder.equals(ListOrder.Ascending)){
			return this.ascendingList();
		}
		else if(anOrder.equals(ListOrder.Descending)){
			return this.descendingList();
		}
		else{
			return null;
		}
	}

}
