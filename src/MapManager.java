import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapManager {
    private final String[] locations;
    private int locationCount;
    private boolean isLoaded = false;

    public MapManager(int maxLocations) {
        locations = new String[maxLocations];
        locationCount = 0;
    }

    /*
    loadMap method - loads the locations.txt file then calls
    addLocation method for each location loaded. Features error
    handling for when the locations file is missing
     */
    public void loadMap(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            locationCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    addLocation(parts[0]);
                    addLocation(parts[1]);
                }
            }
            scanner.close();
            isLoaded = true;
            IO.println("Map file loaded successfully! Total unique "
                    + "locations found: " + locationCount);

        } catch (FileNotFoundException e) {
            IO.println("Error: File was not found.");
            isLoaded = false;
        }
    }

    /*
    addLocation method - checks if the given location is unique then
    adds it to the locations array and increments the location count
    by one.
    */
    private void addLocation(String place) {
        for (int i = 0; i < locationCount; i++) {
            if (locations[i].equals(place)) {
                return;
            }
        }
        if (locationCount < locations.length) {
            locations[locationCount] = place;
            locationCount++;
        }
    }

    /*
    findLocationIndex - performs a linear search through the
    locations array to find and return the index matching the
    given place name, or -1 if the place is not found.
     */
    public int findLocationIndex(String placeName) {
        String formatted = placeName.trim().replaceAll("\\s+", "_");
        for (int i = 0; i < locationCount; i++) {
            if (locations[i].equalsIgnoreCase(formatted)) {
                return i;
            }
        }
        return -1;
    }

    /*
    displayAlphabetical - creates a copy of the locations which are
    then sorted via bubble sort algorithm and outputted in the
    console output alphabetically.
    */
    public void displayAlphabetical() {
        if (!isLoaded) {
            IO.println("Please load the map file first.");
            return;
        }

        String[] sortedCopy = new String[locationCount];
        System.arraycopy(locations, 0, sortedCopy, 0, locationCount);

        for (int i = 0; i < locationCount - 1; i++) {
            for (int j = 0; j < locationCount - i - 1; j++) {
                if (sortedCopy[j].compareToIgnoreCase(sortedCopy[j +
                        1]) > 0) {
                    String temp = sortedCopy[j];
                    sortedCopy[j] = sortedCopy[j + 1];
                    sortedCopy[j + 1] = temp;
                }
            }
        }

        IO.println("======= LOCATIONS IN A-Z ORDER =======");
        for (int i = 0; i < locationCount; i++) {
            IO.println((i + 1) + ". " + sortedCopy[i]);
        }
    }

    /*
    Helper methods - methods to return the values of the fields
    within itself.
    */

    public String[] getLocations() {
        return locations;
    }

    public int getLocationCount() {
        return locationCount;
    }

    public boolean isLoaded() {
        return isLoaded;
    }
}