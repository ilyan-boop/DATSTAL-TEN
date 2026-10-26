public class ArrayQueue {
    private int[] queueArr;
    private int front;
    private int rear;
    private int capacity;
    private int size;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queueArr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(int item) {
        if (size == capacity) {
            int[] newArr = new int[capacity * 2];
            for (int i = 0; i < size; i++) {
                newArr[i] = queueArr[(front + i) % capacity];
            }
            queueArr = newArr;
            capacity *= 2;
            front = 0;
            rear = size - 1;
        }
        rear = (rear + 1) % capacity;
        queueArr[rear] = item;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            return -1;
        }
        int item = queueArr[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}