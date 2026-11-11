void main() {
    MapManager mapManager = new MapManager(10);
    Graph graph = null;
    boolean running = true;

    while (running) {
        IO.println("1. Load the map file");
        IO.println("2. Show all locations in A-to-Z order");
        IO.println("3. Find a path between 2 places (WIP)");
        IO.println("4. Exit");
        IO.println("================================================");

        int choice;
        try {
            choice = Integer.parseInt(IO.readln("Enter your option: "));
        } catch (NumberFormatException e) {
            IO.println("Invalid input.");
            IO.println();
            continue;
        }

        IO.println();

        switch (choice) {
            case 1:
                mapManager.loadMap("Locations.txt");
                if (mapManager.isLoaded()) {
                    graph = new Graph(mapManager.getLocationCount());
                    graph.buildFromFile("Locations.txt", mapManager);
                    graph.printGraph(mapManager);
                }
                break;
            case 2:
                mapManager.displayAlphabetical();
                break;
            case 3:
                if (!mapManager.isLoaded()) {
                    IO.println("Error: Please load the map file first (Option 1).");
                    break;
                }

                String startPlace = IO.readln("Enter starting place name: ").trim();
                String destPlace = IO.readln("Enter destination place name: ").trim();

                // Use Linear Search to find indices
                int startIdx = mapManager.findLocationIndex(startPlace);
                int destIdx = mapManager.findLocationIndex(destPlace);

                // Error handling for wrong place name
                if (startIdx == -1) {
                    IO.println("Error: Starting place '" + startPlace + "' does not exist on the map.");
                    mapManager.printValidLocations();
                    break;
                }
                if (destIdx == -1) {
                    IO.println("Error: Destination place '" + destPlace + "' does not exist on the map.");
                    mapManager.printValidLocations();
                    break;
                }

                // Run BFS pathfinding
                graph.findPathBFS(startIdx, destIdx, mapManager);
                break;
            case 4:
                IO.println("Exiting program. Goodbye!");
                running = false;
                break;
            default:
                IO.println("Invalid option. Please select an option from 1 to 4.");
        }
        IO.println();
    }
}