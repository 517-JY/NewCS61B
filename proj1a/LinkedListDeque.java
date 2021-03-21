/**
 * A generic double linkedList designed on circular sentinel topology
 */

public class LinkedListDeque<T> {
    /**
     * First item (if exists) is sentinel.next
     * Last item (if exists).next is the sentinel Node.
     */
    public class Node {
        /** Gets the previous Node in the deque. */
        private Node prev;

        /** Type T item that needs to be stored in the deque. */
        private T data;

        /** Gets the next Node in the deque. */
        private Node next;

        /**
         * Constructs a doubly linked list node that holds a data element but does not point to any other nodes
         */
        Node(T element) {
            data = element;
            prev = null;
            next = null;
        }


        /**
         * Sets the element field of this node
         * @param element T Type element pending for setting
         */
        public void setElement(T element) {
            data = element;
        }


        /**
         * Sets the next pointer of this node
         * @param next Next node waits for link
         */
        public void setNext(Node next) {
            this.next = next;
            if (next != null) {
                next.prev = this;
            }
        }


        /**
         * Sets the previous pointer of this node
         * @param prev Next node waits for link
         */
        public void setPrev(Node prev) {
            this.prev = prev;
            if (prev != null) {
                prev.next = this;
            }
        }


        /**
         * Returns the pointer to the next node or null if one does not exist
         * @return the pointer to the next node or null if one does not exist
         */
        public Node getNext() {
            return next;
        }


        /**
         * Returns the pointer to the previous node or null if one does not exist
         * @return the pointer to the previous node or null if one does not exist
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Returns the element stored in this node
         * @return the element stored in the current node
         */
        public T getElement() {
            return data;
        }


        /**
         * Returns whether the current node has node after it
         * @return true if it has, false otherwise
         */
        public boolean hasNext() {
            return next != null;
        }


        /**
         * Returns whether the current node has node before it
         * @return true if it has, false otherwise
         */
        public boolean hasPrev() {
            return prev != null;
        }


        /**
         * Returns the String representation of this node's element
         * @return a String representation of this node's element
         */
        @Override
        public String toString() {
            return "Node item is: " + data;
        }

    }

    /**
     * A special Node that is used for easier implementation of the circular double LinkedList
     * For an empty double LinkedList, sentinel.next = sentinel, sentinel.prev = sentinel
     * The data stored in the sentinel node is unimportant
     * In the Double LinkedList, the first item (if exists) would be sentinel.next
     *                           the last item (if exists).next would be the sentinel node
     */
    private Node sentinel;

    // A private helper variable that keeps track of the size of the double LinkedList
    private int size;

    /**
     * Constructs an empty double LinkedList
     */
    public LinkedListDeque() {
        T sentinelData = (T) new Object();
        sentinel = new Node(sentinelData);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds an type T element at the end of the deque
     * @param data T type element pending for adding
     */
    public void addLast(T data) {
        Node newNode = new Node(data);
        sentinel.prev.next = newNode;
        newNode.prev = sentinel.prev;
        sentinel.prev = newNode;
        newNode.next = sentinel;
        size++;
    }

}
