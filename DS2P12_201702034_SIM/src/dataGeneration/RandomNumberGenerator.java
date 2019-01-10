package dataGeneration;

import java.util.Random;

public class RandomNumberGenerator {
	
	public static final int DEFAULT_MAX_DATA_GENERATOR_SIZE = 1000;
	
	Random _randomGenerator; //난수를 생성하는 변수
	private int _maxSize; //사이즈를 알려주는 변수
	
	//getter/setter
	private int maxSize(){
		return this._maxSize;
	}
	
	private void setMaxSize(int aMaxSize){
		this._maxSize = aMaxSize;
	}
	
	private Random randomGenerator(){
		return this._randomGenerator;
	}
	
	private void setRandomGenerator(Random randomGen){
		this._randomGenerator = randomGen;
	}
	
	//생성자
	public RandomNumberGenerator(){
		this.setRandomGenerator(new Random()); //랜덤 함수를 set한다
	}

	//공개함수
	//주어진 사이즈만큼 무작위 수를 얻을 준비를 한다
	public void prepare(int aMaxSize){
		this.setMaxSize(aMaxSize);
	}
	
	//0부터 aMaxSize-1까지의 무작위 수를 하나 얻는다.
	public int randomNumber(){
		int randomNumber = this.randomGenerator().nextInt(this.maxSize());
		//0부터 maxSize-1까지 무작위 수를 하나 받는다.
		return randomNumber;
	}
}
