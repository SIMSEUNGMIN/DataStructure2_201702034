package experiment;

public final class Estimation {
	
	//»ý¼ºÀÚ
	private Estimation(){};
	
	public static long[] estimateByLinear(
			long[] measuredTimes, ParameterSetForMeasurement aParameterSet){
		int length = aParameterSet.numberOfIteration();
		long[] estimatedTimes = new long[length];
		double estimatedCoefficient = (double)measuredTimes[length-1] / (double)length;
		for(int i = 1; i <= length; i++){
			estimatedTimes[i-1] = (long)(estimatedCoefficient * (double)(i));
		}
		return estimatedTimes;
	}
	
	public static long[] estimateByQuadratic(
			long[] measuredTimes, ParameterSetForMeasurement aParameterSet){
		int length = aParameterSet.numberOfIteration();
		long[] estimatedTimes = new long[length];
		double estimatedCoefficient = (double)(measuredTimes[length-1]) / (double)(length*length);
		for(int i = 1; i <= length; i++){
			estimatedTimes[i-1] = (long)(estimatedCoefficient * (double)(i*i));
		}
		return estimatedTimes;
	}
	
	public static long[] estimateByNLogN(
			long[] measuredTimes,
			ParameterSetForMeasurement aParameterSet){
		
		int length = aParameterSet.numberOfIteration();
		int incrementSize = aParameterSet.incrementSize();
		int N = aParameterSet.maxDatasize();
		long[] estimatedTimes = new long[length];
		double estimatedCoefficient =
				(double)measuredTimes[length-1] / 
				((double)(N)*Math.log((double)(N)));
		for(int i = 1; i <= length; i++){
			estimatedTimes[i-1] = 
					(long)(estimatedCoefficient * 
					(double)(i*incrementSize) * (Math.log((double)(i*incrementSize))));
		}
		return estimatedTimes;
		
	}
}
