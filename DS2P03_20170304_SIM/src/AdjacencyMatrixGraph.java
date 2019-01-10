
public class AdjacencyMatrixGraph {
	private static final int EDGE_EXIST = 1;
	private static final int EDGE_NONE = 0;
	
	private int _numberOfVertices;
	private int _numberOfEdges;
	private int[][] _adjacency;
	
	public AdjacencyMatrixGraph(int givenNumberOfVertices){
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfedges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
			for(int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++){
				this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_NONE;
			}
		}
	}
	
	private void setNumberOfVertices(int newNumberOfVertices){ //공개 필요 X
		this._numberOfVertices = newNumberOfVertices;
	}
	
	private void setNumberOfedges(int newNumberOfEdges){ //공개될 필요 X
		this._numberOfEdges = newNumberOfEdges;
	}
	
	private void setAdjacency(int[][] newAdjacency){ //공개될 필요 X (생성자에서 vertex를 주기 때문.)
		this._adjacency = newAdjacency;
	}
	
	private int[][] adjacency(){ //adjacecny의 형태는 공개 X
		return this._adjacency;
	}
	
	public int numberOfVertices(){
		return this._numberOfVertices;
	}
	
	public int numberOfEdges(){
		return this._numberOfEdges;
	}
	
	
	public boolean vertexDoesExist(int aVertex){
		return ((0 <= aVertex) && (aVertex <= this.numberOfVertices()));
	}
	
	private boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex){
		return (this.adjacency()[tailVertex][headVertex] != AdjacencyMatrixGraph.EDGE_NONE);
	}
	
	public boolean edgeDoesExist(Edge anEdge){
		if(anEdge != null){
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			
			if(this.vertexDoesExist(tailVertex) && this.vertexDoesExist(headVertex)){
				return (this.adjacencyOfEdgeDoesExist(tailVertex, headVertex));
			}
		}
		return false;
	}
	
	public boolean addEdge(Edge anEdge){
		if(anEdge != null){
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if(this.vertexDoesExist(tailVertex) && (this.vertexDoesExist(tailVertex))){
				if(!this.adjacencyOfEdgeDoesExist(tailVertex, headVertex)){
					this.adjacency()[tailVertex][headVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.adjacency()[headVertex][tailVertex] = AdjacencyMatrixGraph.EDGE_EXIST;
					this.setNumberOfedges(this.numberOfEdges()+1);
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
