package fileHandling;

public enum IOType {
	
	Input,
	Output;
	
	public String ioTypeName(){
		if(this.equals(IOType.Input)){
			return "입력파일";
		}
		else if (this.equals(IOType.Output)){
			return "출력파일";
		}
		else{
			return "파일 타입 오류";
		}
	}

}
