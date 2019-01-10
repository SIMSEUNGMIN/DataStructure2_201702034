package app;

import experiment.ExperimentManagerForThreeSorts;
import experiment.ListOrder;

public class AppController {
	
	//������ν��Ͻ�����
	private ExperimentManagerForThreeSorts _managerForThreeSorts;

	//getter/setter
	private ExperimentManagerForThreeSorts managerForThreeSorts(){
		return this._managerForThreeSorts;
	}
	
	private void setManagerForThreeSorts(ExperimentManagerForThreeSorts newExperimentManager){
		this._managerForThreeSorts = newExperimentManager;
	}
	
	//������Լ�
	private void showTableTitle(ListOrder anOrder){
		AppView.outputLine("> " + anOrder.orderName() + " �����Ϳ� ���� ����:");
	}
	
	private void showTableHeadForThreeSorts(){
		AppView.outputLine(
				String.format("%8s", "  ") +
				String.format("%26s", "<Insertion Sort>") + 
				String.format("%26s", "  <Quick Sort>  ") + 
				String.format("%26s", "  <Heap Sort>   "));
		
		AppView.outputLine(
				String.format("%7s", "������ ũ��") +
				String.format("%40s", "Measure (Estimate)") + 
				String.format("%40s", "Measure (Estimate)") + 
				String.format("%40s", "Measure (Estimate)"));
	}
	
	private void showTableSontentForThreeSorts(){
		for(int iteration = 0;
				iteration < this.managerForThreeSorts().parameterSetForMeasurement().numberOfIteration();
				iteration++){
			int startingSize = this.managerForThreeSorts().parameterSetForMeasurement().startingSize();
			int incrementSize = this.managerForThreeSorts().parameterSetForMeasurement().incrementSize();
			int sortingSize = startingSize + (incrementSize * iteration);
			AppView.outputLine(
					"[" + String.format("%7d", sortingSize) + "]" +
					String.format("%15d", this.managerForThreeSorts().measurementForInsertionSortAt(iteration)) + 
					" (" + String.format("%8d", this.managerForThreeSorts().estimationForInsertionSortAt(iteration)) + ")" +
					String.format("%15d", this.managerForThreeSorts().measurementForQuickSortAt(iteration)) + 
					" (" + String.format("%8d", this.managerForThreeSorts().estimationForQuickSortAt(iteration)) + ")" +
					String.format("%15d", this.managerForThreeSorts().measurementForHeapSortAt(iteration)) + 
					" (" + String.format("%8d", this.managerForThreeSorts().estimationForHeapSortAt(iteration)) + ")");
		}
	}
	
	private void showResultTableForThreeSorts(ListOrder anOrder){
		this.showTableTitle(anOrder);
		this.showTableHeadForThreeSorts();
		this.showTableSontentForThreeSorts();
		AppView.outputLine("");
	}
	
	private void measureAndshow(ListOrder anOrder){
		this.managerForThreeSorts().performExperiment(anOrder);
		this.showResultTableForThreeSorts(anOrder);
	}
	
	public void run(){
		AppView.outputLine("<<< ���� ���� �� ���α׷��� �����մϴ� >>>");
		AppView.outputLine("");
		{
			AppView.outputLine(">> 3 ���� ������ ���� ��: ����, ��, �Q <<");
			this.setManagerForThreeSorts(new ExperimentManagerForThreeSorts());
			this.managerForThreeSorts().prepareExperiment(null);
			
			
			this.measureAndshow(ListOrder.Ascending);
			this.measureAndshow(ListOrder.Descending);
			this.measureAndshow(ListOrder.Random);
		}
		AppView.outputLine("<<< ���� ���� �� ���α׷��� �����մϴ� >>>");
	}
}