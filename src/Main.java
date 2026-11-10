void main() {
    MapManager mapManager = new MapManager(10);
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
                IO.println("Map Graph: Cobreros");
                break;
            case 2:
                mapManager.displayAlphabetical();
                break;
            case 3:
                IO.println("BFS Pathfinding: Cordova");
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
