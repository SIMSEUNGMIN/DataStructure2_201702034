package graph;

public interface SupplementForWeightedGraph<E> {
	
	public int weightOfEdge(E anEdge);
	public int weightOfEdge(int aTailVertex, int aHeadVertex);
}
