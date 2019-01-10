package app;

import equivalenceClasses.EquivalenceClasses;
import graph.AdjacencyGraph;
import graph.UndirectedAdjacencyMatrixGraph;
import graph.Edge;
import list.List;
import list.Iterator;

public class AppController {
	
	//����� �ν��Ͻ� ����
	private AdjacencyGraph<Edge> _graph;
	private EquivalenceClasses<Edge> _equivalenceClasses;
	
	//setter/getter.
	private AdjacencyGraph<Edge> graph(){
		return this._graph;
	}
	
	private void setGraph(AdjacencyGraph<Edge> newGraph){
		this._graph = newGraph;
	}
	
	private EquivalenceClasses<Edge> equivalenceClasses(){
		return this._equivalenceClasses;
	}
	
	private void setEquivalenceClasses(EquivalenceClasses<Edge> newEquivalenceClasses){
		this._equivalenceClasses = newEquivalenceClasses;
	}

	//������.
	public AppController(){
		this.setGraph(null);
		this.setEquivalenceClasses(new EquivalenceClasses<Edge>());
	}
	
	//����� �Լ�.
	private int inputNumberOfVertices(){
		int numberOfVertices = AppView.inputNumberOfVertices();
		while(numberOfVertices <= 0){
			AppView.outputLine("[����] ���� ���� 0 ���� Ŀ�� �մϴ�: " + numberOfVertices);
			numberOfVertices = AppView.inputNumberOfVertices();
		}
		return numberOfVertices;
	}
	
	private int inputNumberofEdge(){
		int numberOfEdges = AppView.inputNumberOfEdges();
		while(numberOfEdges < 0){
			AppView.outputLine("[����] ���� �� ���� 0 ���� ũ�ų� ���ƾ� �մϴ�: " + numberOfEdges);
			numberOfEdges = AppView.inputNumberOfEdges();
		}
		return numberOfEdges;
	}
	
	private Edge inputEdge(){
		do{
			AppView.outputLine("- �Է��� edge�� �� vertex�� �Է��ؾ� �մϴ�:");
			int tailVertex = AppView.inputTailVertex();
			int headVertex = AppView.inputHeadVertex();
			if(this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)){
				if(tailVertex == headVertex){
					AppView.outputLine("[����] �� vertex ��ȣ�� �����մϴ�.");
				}
				else{
					return (new	Edge(tailVertex, headVertex));
				}
			}
			else{
				if(! this.graph().vertexDoesExist(tailVertex)){
					AppView.outputLine("[����] �������� �ʴ� tail Vertex �Դϴ�: " + tailVertex);
				}
				if(! this.graph().vertexDoesExist(headVertex)){
					AppView.outputLine("[����] �������� �ʴ� head Vertex �Դϴ�: " + headVertex);
				}
			}
		}while(true);
	}
	
	private void inputAndMakeGraph(){
		AppView.outputLine("> �Է��� �׷����� vertex ���� edge ���� ���� �Է��ؾ� �մϴ�:");
		int numberOfVertices = this.inputNumberOfVertices();
		this.setGraph(new UndirectedAdjacencyMatrixGraph<Edge>(numberOfVertices));
		
		int numberOfEdges = this.inputNumberofEdge();
		AppView.outputLine("");
		AppView.outputLine("> �������� edge�� �־��� �� ��ŭ �Է��մϴ�.");
		
		int edgeCount = 0;
		while(edgeCount < numberOfEdges){
			Edge edge = this.inputEdge();
			if(this.graph().edgeDoesExist(edge)){
				AppView.outputLine("[����] �Էµ� edge (" + edge.tailVertex() + ", " + edge.headVertex() +
						")) �� �׷����� �̹� �����մϴ�.");
			}
			else{
				edgeCount ++;
				this.graph().addEdge(edge);
				AppView.outputLine("!���ο� edge (" + edge.tailVertex() + ", " + edge.headVertex() +
						")) �� �׷����� ���ԵǾ����ϴ�.");
			}
		}
	}
	
	private void showGraph(){
		AppView.outputLine("");
		AppView.outputLine("> �Էµ� �׷����� ������ �����ϴ�:");
		for(int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
			AppView.output("[" + tailVertex + "] ->");
			Iterator<Edge> neighborIterator =
					this.graph().neighborIteratorOf(tailVertex);
			while(neighborIterator.hasNext()){
				Edge nextEdge = neighborIterator.next();
				AppView.output(" " + nextEdge.headVertex());
			}
			AppView.outputLine("");
		}
	}
	
	private void showEquivalenceClasses(){
		AppView.outputLine("");
		AppView.outputLine("> ã���� ���� Ŭ������ ������ �����ϴ�:");
		Iterator<List<Integer>> equivalenceClassListIterator =
				this.equivalenceClasses().equivalenceClassList().listIterator();
		for(int classOrder = 0; equivalenceClassListIterator.hasNext(); classOrder++){
			AppView.output("[���� Ŭ���� " + String.format("%2d", classOrder) + "] ");
			List<Integer> equivalenceClass = 
					(List<Integer>) equivalenceClassListIterator.next();
			Iterator<Integer> equivalenceClassIterator = equivalenceClass.listIterator();
			AppView.output(String.format(" = {%2d", equivalenceClassIterator.next()));
			while(equivalenceClassIterator.hasNext()){
				AppView.output(String.format(",%2d", equivalenceClassIterator.next()));
			}
			AppView.outputLine(" }");
		}
	}
	
	//���� �Լ�.
	public void run(){
		AppView.outputLine("<<< ���� Ŭ���� ã�� ���α׷��� �����մϴ�. >>>");
		this.inputAndMakeGraph();
		this.showGraph();
		
		if(this.equivalenceClasses().solve(this.graph())){
			this.showEquivalenceClasses();
		}
		else{
			AppView.outputLine("");
			AppView.outputLine("[����] ���� Ŭ������ ���������� ��ġ�� ���߽��ϴ�.");
		}
		
		AppView.outputLine("");
		AppView.outputLine("<<< ���� Ŭ���� ã�� ���α׷��� �����մϴ�. >>>");
	}
}
