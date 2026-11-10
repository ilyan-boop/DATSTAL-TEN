public class GraphNode {
    public int destination;
    public GraphNode next;

    public GraphNode(int destination) {
        this.destination = destination;
        this.next = null;
    }
}