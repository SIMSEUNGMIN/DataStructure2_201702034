package graph;

public class UndirectedAdjacencyMatrixGraph<E extends Edge>
	extends DirectedAdjacencyMatrixGraph<E>{
	
	public UndirectedAdjacencyMatrixGraph(int givenNumberOfVertices){
		super(givenNumberOfVertices);
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
