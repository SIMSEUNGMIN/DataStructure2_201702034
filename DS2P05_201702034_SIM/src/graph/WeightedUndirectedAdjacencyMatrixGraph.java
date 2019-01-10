package graph;

public class WeightedUndirectedAdjacencyMatrixGraph<WE extends WeightedEdge>
extends UndirectedAdjacencyMatrixGraph<WE>
implements SupplementForWeightedGraph<WE>{
	
	//상수
	private static final int WEIGHTED_EDGE_NONE = -1;

	//비공개 함수
	private void setWeightOfEdge(int aTailVertex, int aHeadVertex, int newWeight){
		this.adjacency()[aTailVertex][aHeadVertex] = newWeight;
	}
	
	private void setWeightOfEdgeAsNone(int aTailVertex, int aHeadVertex){
		this.setWeightOfEdge(aTailVertex, aHeadVertex, WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	
	//생성자
	public WeightedUndirectedAdjacencyMatrixGraph(int givenNumberOfVertices){
		super();
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjaccency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
			for(int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++){
				this.setWeightOfEdgeAsNone(tailVertex, headVertex);
			}
		}
	}
	
	//오버라이드 함수
	@Override
	protected boolean adjacencyOfEdgeDoesExist(int aTailVertex, int aHeadVertex){
		return(this.adjacencyOfEdge(aTailVertex, aHeadVertex) !=
				WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE);
	}
	
	//공개 함수
	@Override
	public int weightOfEdge(WE anEdge) {
		if(anEdge != null){
			return this.weightOfEdge(anEdge.tailVertex(), anEdge.headVertex());
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}

	@Override
	public int weightOfEdge(int aTailVertex, int aHeadVertex) {
		if(this.edgeDoesExist(aTailVertex, aHeadVertex)){
			return this.adjacencyOfEdge(aTailVertex, aHeadVertex);
		}
		return WeightedUndirectedAdjacencyMatrixGraph.WEIGHTED_EDGE_NONE;
	}
	
	//오버라이드  super.addEdge()
	public boolean addEdge(WE anEdge){
		if(anEdge != null){
			if(this.edgeIsValid(anEdge) && !this.edgeDoesExist(anEdge)){
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setWeightOfEdge(tailVertex, headVertex, anEdge.weight());
				this.setWeightOfEdge(headVertex, tailVertex, anEdge.weight());
				this.setNumberOfEdges(this.numberOfEdges()+1);
				return true;
			}
		}
		return false;
	}

}
