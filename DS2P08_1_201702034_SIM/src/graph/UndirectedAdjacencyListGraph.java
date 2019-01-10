package graph;

public class UndirectedAdjacencyListGraph<E extends Edge> 
	extends DirectedAdjacencyListGraph<E> {

	public UndirectedAdjacencyListGraph(int givenNumberOfVertices) {
		super(givenNumberOfVertices);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean addEdge(E anEdge){
		if(this.edgeIsValid(anEdge) && (! this.edgeDoesExist(anEdge))){
			this.neighborListOf(anEdge.tailVertex()).add(anEdge);
			
			@SuppressWarnings("unchecked")
			E reversedEdge = (E) anEdge.reversed();
			this.neighborListOf(reversedEdge.tailVertex()).add(reversedEdge);
			this.setNumberOfEdges(this.numberOfEdges()+1);
			return true;
		}
		return false;
	}
	
	
}
