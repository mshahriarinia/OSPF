package alg;

public class WeightedGraph {

	private int[][] edges; // adjacency matrix
	private Object[] labels;

	public WeightedGraph(int n) {
		edges = new int[n][n];
		labels = new Object[n];
	}

	public int size() {
		return labels.length;
	}

	public void setLabel(int vertex, Object label) {
		labels[vertex] = label;
	}

	public Object getLabel(int vertex) {
		return labels[vertex];
	}

	public void addEdge(int source, int target, int w) {
		edges[source][target] = w;
	}

	public boolean isEdge(int source, int target) {
		return edges[source][target] > 0;
	}

	public void removeEdge(int source, int target) {
		edges[source][target] = 0;
	}

	public int getWeight(int source, int target) {
		return edges[source][target];
	}

	public int[] neighbors(int vertex) {
		int count = 0;
		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0)
				count++;
		}
		final int[] answer = new int[count];
		count = 0;
		for (int i = 0; i < edges[vertex].length; i++) {
			if (edges[vertex][i] > 0)
				answer[count++] = i;
		}
		return answer;
	}

	public void print() {
		for (int j = 0; j < edges.length; j++) {
			System.out.print(labels[j] + ": ");
			for (int i = 0; i < edges[j].length; i++) {
				if (edges[j][i] > 0)
					System.out.print(labels[i] + ":" + edges[j][i] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String args[]) {
		final WeightedGraph t = new WeightedGraph(6);
		t.setLabel(0, "v0");
		t.setLabel(1, "v1");
		t.setLabel(2, "v2");
		t.setLabel(3, "v3");
		t.setLabel(4, "v4");
		t.setLabel(5, "v5");
		t.addEdge(0, 1, 2);
		t.addEdge(0, 4, 1);
		t.addEdge(0, 5, 9);
		t.addEdge(1, 2, 8);
		t.addEdge(1, 3, 15);
		t.addEdge(1, 5, 6);
		t.addEdge(2, 3, 1);
		t.addEdge(4, 3, 3);
		t.addEdge(4, 2, 7);
		t.addEdge(5, 4, 3);
		t.print();

		final int[] pred = Dijkstra.dijkstra(t, 0);
		for (int n = 0; n < 6; n++) {
			Dijkstra.printPath(t, pred, 0, n);
		}
	}

}
