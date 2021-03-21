import java.util.NoSuchElementException;

/**
 * This generic queue is designed as circular
 * Implementation only based on array
 * @param <T>
 */
public class ArrayDeque<T> {
    private final int dequeCapacity = 8;
    private T[] queue;
    private int size;
    private int head;
    private int tail;

    /**
     * Constructs an empty queue that can hold pre-determined number of elements
     */
    public ArrayDeque() {
        this.queue = (T[]) new Object[dequeCapacity];
        this.size = 0;
        this.tail = 0;
        this.head = this.queue.length - 1;
    }

    /**
     * Adds T at index tail
     * @param element Element pending for adding
     */
    public void addLast(T element) {
        needsResize();
        queue[tail] = element;
        tail = (tail + 1) % queue.length;
        size++;
    }

    /**
     * Removes T at index tail
     * @return the element that is removed
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            tail = (tail - 1 + queue.length) % queue.length;
            T temp = queue[tail];
            queue[tail] = null;
            size--;
            needsResize();
            return temp;
        }
    }

    /**
     * Adds T at index head
     * @param element Element pending for adding
     */
    public void addFirst(T element) {
        needsResize();
        queue[head] = element;
        head = (head - 1 + queue.length) % queue.length;
        size++;
    }


    /**
     * Removes T at index head
     * @return the element that is removed
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            head = (head + 1) % queue.length;
            T temp = queue[head];
            queue[head] = null;
            size--;
            needsResize();
            return temp;
        }
    }


    /**
     * Creates a new deque array based on the given capacity
     * @param newLength targeted length for the newly resizing queue
     */
    private void resize(int newLength) {
        T[] newQueue = (T[]) new Object[newLength];

        System.arraycopy(queue, (head + 1) % queue.length,
                newQueue, 0, Math.min(size, queue.length - (head + 1) % queue.length));

        if (newLength > queue.length) {
            // needs enlarge here
            System.arraycopy(queue, 0,
                    newQueue, Math.min(size, queue.length - (head + 1) % queue.length),
                            (tail - 1 + queue.length) % queue.length + 1);
        } else if (newLength < queue.length) {
            // needs shrink here
            if (size > queue.length - (head + 1) % queue.length) {
                System.arraycopy(queue, 0, newQueue, queue.length - (head + 1) % queue.length,
                        tail);
            }
        }

        head = newLength - 1;
        tail = size;
        queue = newQueue;
    }

    /**
     *  Checks whether the queue needs resize
     *  Two cases need resizing: 1) wants to add element when the queue is full
     *                           2) when the length of array is 16 or more and
     *                  the usage factor is less than 25%
     */
    private void needsResize() {
        if (isFull()) {
            resize(queue.length * 2);
        } else if (queue.length >= 16 && size < queue.length * 0.25) {
            resize(queue.length / 2);
        }
    }

    /** Returns the item at the given index of the array deque.
     * @param index as specified index
     *  If no such item exists, return null
     *  Needs to flag the first not null item position as the start place.
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int startFlag = (head + 1) % queue.length;
        int i = (startFlag + index) % queue.length;
        return queue[i];
    }

    /**
     *  Returns the number of elements in the queue
     * @return  the number of elements in the queue
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks whether the queue is full or not
     * @return true if the queue is full, false otherwise
     */
    private boolean isFull() {
        return size == queue.length;
    }

    /**
     * Checks whether the queue is empty or not
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prints the elements in the queue in order
     */
    public void printDeque() {
        int count = 1;
        int startFlag = (head + 1) % queue.length;
        while (count <= size) {
            System.out.print(queue[startFlag] + " ");
            startFlag = (startFlag + 1) % queue.length;
            count++;
        }
    }

}