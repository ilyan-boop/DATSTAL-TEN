public class Graph {
    private GraphNode[] adjacencyList;
    private int vertexCount;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        adjacencyList = new GraphNode[vertexCount];
    }

    public void addEdge(int from, int to) {
        if (from < 0 || from >= vertexCount || to < 0 || to >= vertexCount) {
            return;
        }
        if (hasEdge(from, to)) {
            return;
        }
        addDirected(from, to);
        addDirected(to, from);
    }

    private void addDirected(int from, int to) {
        GraphNode node = new GraphNode(to);
        node.next = adjacencyList[from];
        adjacencyList[from] = node;
    }

    public boolean hasEdge(int from, int to) {
        GraphNode current = adjacencyList[from];
        while (current != null) {
            if (current.destination == to) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public GraphNode getNeighbors(int vertex) {
        return adjacencyList[vertex];
    }

    public int getVertexCount() {
        return vertexCount;
    }
}