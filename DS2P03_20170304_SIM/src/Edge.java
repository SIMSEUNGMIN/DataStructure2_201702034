
public class Edge {
	
	private int _tailVertex;
	private int _headVertex;
	
	public Edge(int givenTailVertex, int givenHeadVertex){
		this.setTailVertex(givenTailVertex);
		this.setHeadVertex(givenHeadVertex);
	}
	
	public void setTailVertex(int newTailVertex){
		this._tailVertex = newTailVertex;
	}
	
	public void setHeadVertex(int newHeadVertex){
		this._headVertex = newHeadVertex;
	}
	
	public int tailVertex(){
		return this._tailVertex;
	}
	
	public int headVertex(){
		return this._headVertex;
	}

}
