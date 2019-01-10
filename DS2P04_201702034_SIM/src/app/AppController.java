package app;

import graph.WeightedUndirectedAdjacencyMatrixGraph;
import graph.WeightedEdge;

public class AppController {
	
	//비공개 인스턴스 변수
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> _graph;
	
	//setter/getter.
	private WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> graph(){
		return this._graph;
	}
	
	private void setGraph(WeightedUndirectedAdjacencyMatrixGraph<WeightedEdge> newGraph){
		this._graph = newGraph;
	}
	
	//생성자.
	public AppController(){
		this.setGraph(null);
	}
	
	//비공개 함수.
	private int inputNumberOfVertices(){
		int numberOfVertices = AppView.inputNumberOfVertices();
		while(numberOfVertices <= 0){
			AppView.outputLine("[오류] vertex 수는 0 보다 커야 합니다: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}
	
	private int inputNumberofEdge(){
		int numberOfEdges = AppView.inputNumberOfEdges();
		while(numberOfEdges < 0){
			AppView.outputLine("[오류] vertex 수는 0 보다 크거나 같아야 합니다: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}
	
	private WeightedEdge inputEdge(){
		do{
			AppView.outputLine("- 입력할 edge의 두 vertex와 cost를차례로 입력해야 합니다:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			int cost = AppView.inputCost();
			if(this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)){
				if(tailVertex == headVertex){
					AppView.outputLine("[오류] 두 vertex 번호가 동일합니다.");
				}
				else{
					return (new WeightedEdge(tailVertex, headVertex, cost));
				}
			}
			else{
				if(! this.graph().vertexDoesExist(tailVertex)){
					AppView.outputLine("[오류] 존재하지 않는 tail Vertex 입니다: " + tailVertex);
				}
				if(! this.graph().vertexDoesExist(headVertex)){
					AppView.outputLine("[오류] 존재하지 않는 head Vertex 입니다: " + headVertex);
				}
				if(cost < 0){
					AppView.outputLine("[오류] edge의 비용은 양수이어야 합니다: " + cost);
				}
			}
		}while(true);
	}
	
	private void inputAndMakeGraph(){
		AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new WeightedUndirectedAdjacencyMatrixGraph(numberOfVertices));
		
		int numberOfEdges = this.inputNumberofEdge();
		AppView.outputLine("");
		AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");
		
		int edgeCount = 0;
		while(edgeCount < numberOfEdges){
			WeightedEdge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)){
				AppView.outputLine("[오류] 입력된 edge (" + edge.tailVertex() + ", " + edge.headVertex() +
						", (" + edge.weight() + ") 는 그래프에 이미 존재합니다.");
			}
			else{
				edgeCount ++;
				this.graph().addEdge(edge);
				AppView.outputLine("!새로운 edge (" + edge.tailVertex() + ", " + edge.headVertex() +
						", (" + edge.weight() + ") 가 그래프에 삽입되었습니다.");
			}
		}
	}
	
	private void showGraph(){
		AppView.outputLine("");
		AppView.outputLine("> 입력된 그래프는 다음과 같습니다:");
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
		AppView.outputLine("> 입력된 그래프의 Adjacency Matrix는 다음과 같습니다:");
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
	
	//공개 함수.
	public void run(){
		AppView.outputLine("<<< 최소비용 확장 트리 찾기 프로그램을 시작합니다. >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		
		AppView.outputLine("");
		AppView.outputLine("<<< 최소비용 확장 트리 찾기 프로그램을 종료합니다. >>>");
	}
}
