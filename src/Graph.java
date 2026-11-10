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

    /*
    findPathBFS method - implements the Breadth-First Search (BFS) algorithm
    using a custom queue to find the shortest path with the fewest stops
    between a start and destination vertex, handling missing paths and
    reconstructing the final route.
     */
    public void findPathBFS(int startIdx, int targetIdx, MapManager mapManager) {
        String[] locations = mapManager.getLocations();

        if (startIdx == targetIdx) {
            IO.println("Start and destination are the same: " + locations[startIdx]);
            IO.println("Stops: 0");
            return;
        }

        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            parent[i] = -1;
        }

        ArrayQueue queue = new ArrayQueue(vertexCount);
        queue.enqueue(startIdx);
        visited[startIdx] = true;

        boolean found = false;

        while (!queue.isEmpty()) {
            int current = queue.dequeue();

            if (current == targetIdx) {
                found = true;
                break;
            }

            GraphNode neighborNode = adjacencyList[current];
            while (neighborNode != null) {
                int neighbor = neighborNode.destination;
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = current;
                    queue.enqueue(neighbor);
                }
                neighborNode = neighborNode.next;
            }
        }

        if (!found) {
            IO.println("Error: There is no path between " + locations[startIdx] + " and " + locations[targetIdx] + ".");
            return;
        }

        //Reconstruct path from target back to start
        int[] pathStack = new int[vertexCount];
        int pathLength = 0;
        int curr = targetIdx;
        while (curr != -1) {
            pathStack[pathLength++] = curr;
            curr = parent[curr];
        }

        //Print path and number of stops (edges)
        IO.println("======= PATH FOUND (BFS) =======");
        String pathStr = "";
        for (int i = pathLength - 1; i >= 0; i--) {
            pathStr += locations[pathStack[i]];
            if (i > 0) {
                pathStr += " -> ";
            }
        }

        IO.println("Path: " + pathStr);
        IO.println("Number of stops (edges): " + (pathLength - 1));
    }

    public void printGraph(MapManager mapManager) {
        String[] locations = mapManager.getLocations();
        IO.println("======= MAP GRAPH (ADJACENCY LIST) =======");
        for (int i = 0; i < vertexCount; i++) {
            String line = locations[i] + " -> ";
            GraphNode current = adjacencyList[i];
            while (current != null) {
                line = line + locations[current.destination];
                if (current.next != null) {
                    line = line + ", ";
                }
                current = current.next;
            }
            IO.println(line);
        }
    }
}