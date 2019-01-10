package app;

import graph.WeightedUndirectedAdjacencyMatrixGraph;
import graph.WeightedEdge;

public class AppController {
	
	//����� �ν��Ͻ� ����
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;
	
	//setter/getter.
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> graph(){
		return this._graph;
	}
	
	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph){
		this._graph = newGraph;
	}
	
	//������.
	public AppController(){
		this.setGraph(null);
	}
	
	//����� �Լ�.
	private int inputNumberOfVertices(){
		int numberOfVertices = AppView.inputNumberOfVertices();
		while(numberOfVertices <= 0){
			AppView.outputLine("[����] vertex ���� 0 ���� Ŀ�� �մϴ�: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}
	
	private int inputNumberofEdge(){
		int numberOfEdges = AppView.inputNumberOfEdges();
		while(numberOfEdges < 0){
			AppView.outputLine("[����] vertex ���� 0 ���� ũ�ų� ���ƾ� �մϴ�: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}
	
	private WeightedEdge inputEdge(){
		do{
			AppView.outputLine("- �Է��� edge�� �� vertex�� cost�����ʷ� �Է��ؾ� �մϴ�:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = AppView.inputCost();
			if(this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)){
				if(tailVertex == headVertex){
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�.");
				}
				else{
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}
			}
			else{
				if(! this.graph().vertexDoesExist(tailVertex)){
					AppView.outputLine("[����] �������� �ʴ� tail Vertex �Դϴ�: " + tailVertex);
				}
				if(! this.graph().vertexDoesExist(headVertex)){
					AppView.outputLine("[����] �������� �ʴ� head Vertex �Դϴ�: " + headVertex);
				}
				if(cost < 0){
					AppView.outputLine("[����] edge�� ����� ����̾�� �մϴ�: " + cost);
				}
			}
		}while(true);
	}
	
	private void inputAndMakeGraph(){
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedUndirectedAdjacencyMatrixGraph(numberOfVertices));
		
		int numberOfEdges = this.inputNumberofEdge();
		AppView.outputLine("");
		AppView.outputLine("> �������� edge�� �־��� �� ��ŭ �Է��մϴ�.");
		
		int edgeCount = 0;
		while(edgeCount < numberOfEdges){
			WeightedEdge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)){
				AppView.outputLine("[����] �Էµ� edge (" + edge.tailVertex() + ", " + edge.headVertex() +
						", (" + edge.weight() + ") �� �׷����� �̹� �����մϴ�.");
			}
			else{
				edgeCount ++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + ", " + edge.headVertex() +
						", (" + edge.weight() + ") �� �׷����� ���ԵǾ����ϴ�.");
			}
		}
	}
	
	private void showGraph(){
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�:");
		for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
			AppView.output("[" + tailVertex + "] ->");
			for(int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++){
				if(this.graph().edgeDoesExist(tailVertex, headVertex)){
					AppView.output(" " + headVertex);
					AppView.output("(" + this.graph().weightOfEdge(tailVertex, headVertex) + ")");
				}
			}
			AppView.outputLine("");
		}
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� Adjacency Matrix�� ������ �����ϴ�:");
		AppView.output("     ");
		for(int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++){
			AppView.output(String.format(" [%1s]", headVertex));
		}
		AppView.outputLine("");
		for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
			AppView.output("[" + tailVertex + "] ->");
			for(int headVertex = 0; headVertex < this.graph().numberOfVertices(); headVertex++){
				int weight = this.graph().weightOfEdge(tailVertex, headVertex);
				AppView.output(String.format("%4d", weight));
			}
			AppView.outputLine("");
		}
	}
	
	//���� �Լ�.
	public void run(){
		AppView.outputLine("<<< �ּҺ�� Ȯ�� Ʈ�� ã�� ���α׷��� �����մϴ�. >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		
		AppView.outputLine("");
		AppView.outputLine("<<< �ּҺ�� Ȯ�� Ʈ�� ã�� ���α׷��� �����մϴ�. >>>");
	}
}
