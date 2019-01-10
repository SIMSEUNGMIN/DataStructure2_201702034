package dataGeneration;

import java.util.Random;

public class RandomNumberGenerator {
	
	public static final int DEFAULT_MAX_DATA_GENERATOR_SIZE = 1000;
	
	Random _randomGenerator; //������ �����ϴ� ����
	private int _maxSize; //����� �˷��ִ� ����
	
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
	
	//������
	public RandomNumberGenerator(){
		this.setRandomGenerator(new Random()); //���� �Լ��� set�Ѵ�
	}

	//�����Լ�
	//�־��� �����ŭ ������ ���� ���� �غ� �Ѵ�
	public void prepare(int aMaxSize){
		this.setMaxSize(aMaxSize);
	}
	
	//0���� aMaxSize-1������ ������ ���� �ϳ� ��´�.
	public int randomNumber(){
		int randomNumber = this.randomGenerator().nextInt(this.maxSize());
		//0���� maxSize-1���� ������ ���� �ϳ� �޴´�.
		return randomNumber;
	}
}
