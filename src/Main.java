import java.util.Queue;

void main() {
    String[] locations = LocationData.getLocations();

    IO.println("--- Array Example ---");
    IO.println("Total locations: " + locations.length);
    IO.println("First location: " + locations[0]);
    IO.println("Last location: " + locations[locations.length - 1]);
    IO.println();

    IO.println("--- Queue Example ---");
    ArrayQueue queue = new ArrayQueue(5);

    queue.enqueue(0); // St. La Salle Hall
    queue.enqueue(3); // Henry Sy Sr. Hall

    IO.println("Queue size after enqueuing 2 items: " + queue.size());

    IO.println("Dequeued index: " + queue.dequeue());
    IO.println("Dequeued index: " + queue.dequeue());
    IO.println("Is queue empty? " + queue.isEmpty());
}
