import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public void buildFromFile(String filename, MapManager mapManager) {
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    int from = findIndex(parts[0], mapManager);
                    int to = findIndex(parts[1], mapManager);
                    addEdge(from, to);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            IO.println("Error: File was not found.");
        }
    }

    private int findIndex(String place, MapManager mapManager) {
        String[] locations = mapManager.getLocations();
        for (int i = 0; i < mapManager.getLocationCount(); i++) {
            if (locations[i].equals(place)) {
                return i;
            }
        }
        return -1;
    }
}