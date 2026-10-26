void main() {
    //ARRAY TO STORE LOCATIONS
    //QUEUE (FOR BFS...?)
    String[] locationsArr = { "St. La Salle Hall", "Enrique Yuchengo Hall",
            "St. Joseph Hall", "Henry Sy Sr. Hall", "Velasco Hall",
            "St. Miguel Hall", "St. Mutien Marie Hall", "Gokongwei Hall",
            "Enrique Razon Sports Center", "Br. Andrew Gonzalez Hall"};
    int choice=0;
    boolean running=true;

    while (running ) {
        IO.println("Locations:");
        for (int i=0;i<locationsArr.length;i++) {
            IO.println( (i+1) + " " + locationsArr[i]);
        }
        IO.println();
        IO.println("================================================");
        IO.println("1. Choose Location");
        IO.println("2. Queue Thingy");
        IO.println("3. Exit");
        IO.println("================================================");
        IO.println();

        switch (choice = Integer.parseInt(IO.readln("Choice: "))) {
            case 1:
                int locChoice = (Integer.parseInt(IO.readln("Enter a location number: ")))+1;
                IO.println("Chosen Location: " + locationsArr[locChoice]);
                IO.println();
                break;
            case 2:
                IO.println("Idk Queue thingy i guess");
                break;
            case 3:
                IO.println("Exiting program.");
                running=false;
                break;
            default:
                IO.println("Invalid choice.");
        }
    }
}