package graph;

public class UndirectedAdjacencyMatrixGraph<E extends Edge> implements Graph<E>{
	
	private static final int EDGE_EXIST = 1;
	private static final int EDGE_NONE = 0;
	
	private int _numberOfVertices;
	private int _numberOfEdges;
	private int[][] _adjacency;
	
	//getter/setter
	@Override
	public int numberOfVertices() {
		return this._numberOfVertices;
	}
	
	protected void setNumberOfVertices(int newNumberOfVertices){
		this._numberOfVertices = newNumberOfVertices;
	}

	@Override
	public int numberOfEdges() {
		return this._numberOfEdges;
	}
	
	protected void setNumberOfEdges(int newNumberOfEdges){
		this._numberOfEdges = newNumberOfEdges;
	}
	
	protected int[][] adjacency(){
		return this._adjacency;
	}
	
	protected void setAdjaccency(int[][] newAdjacency){
		this._adjacency = newAdjacency;
	}
	
	protected int adjacencyOfEdge(int tailVertex, int headVertex){
		return this.adjacency()[tailVertex][headVertex];
	}
	
	protected void setAdjacencyOfEdgeAs(int tailVertex, int headVertex, int anAdjacencyOfEdge){
		this.adjacency()[tailVertex][headVertex] = anAdjacencyOfEdge;
	}
	
	private void setAdjacencyOfEdgeAsExist(int tailVertex, int headVertex){
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, UndirectedAdjacencyMatrixGraph.EDGE_EXIST);
	}
	
	protected void setAdjacencyOfEdgeAsNone(int tailVertex, int headVertex){
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, UndirectedAdjacencyMatrixGraph.EDGE_NONE);
	}
	
	//생성자
	protected UndirectedAdjacencyMatrixGraph(){
		
	}
	
	public UndirectedAdjacencyMatrixGraph(int givenNumberOfVertices){
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjaccency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
			for(int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++){
				this.setAdjacencyOfEdgeAsNone(tailVertex, headVertex);
			}
		}
	}
	
	//비공개 함수
	protected boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex){
		return(this.adjacencyOfEdge(tailVertex, headVertex) !=
				UndirectedAdjacencyMatrixGraph.EDGE_NONE);
	}
	
	//공개 함수
	@Override
	public boolean vertexDoesExist(int aVertex) {
		return (aVertex >= 0 && aVertex < this.numberOfVertices());
	}

	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		if(this.edgeIsValid(aTailVertex, aHeadVertex)){
			return (this.adjacencyOfEdgeDoesExist(aTailVertex, aHeadVertex));
		}
		return false;
	}

	@Override
	public boolean edgeDoesExist(E anEdge) {
		if(anEdge != null){
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}

	@Override
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex) {
		return (this.vertexDoesExist(aTailVertex) && 
				this.vertexDoesExist(aHeadVertex));
	}

	@Override
	public boolean edgeIsValid(E anEdge) {
		if(anEdge != null){
			return (this.edgeIsValid(anEdge.tailVertex(), anEdge.headVertex()));
		}
		return false;
	}

	@Override
	public E edge(int aTailVertex, int aHeadVertex) {
		if(this.edgeDoesExist(aTailVertex, aHeadVertex)){
			return (E) new Edge(aTailVertex, aHeadVertex);
		}
		return null;
	}

	@Override
	public boolean addEdge(E anEdge) {
		if(anEdge != null){
			if(this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)){
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
				this.setAdjacencyOfEdgeAsExist(headVertex, tailVertex);
				this.setNumberOfEdges(this.numberOfEdges()+1);
				return true;
			}
		}
		return false;
	}
	
}
