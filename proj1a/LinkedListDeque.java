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


    /**
     * Adds an type T element at the front of the deque
     * @param data T type element pending for adding
     */
    public void addFirst(T data) {
        Node newNode = new Node(data);
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        size++;
    }

    /**
     * Removes the first Node's data in the front of the LinkedList
     * If the LinkedList is empty, return null
     * @return the data field of the front node, if the list is empty, return null
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            // target will later be dealt with the Garbage Collection
            Node target = sentinel.next;
            T resultData = target.data;
            sentinel.next = target.next;
            target.next.prev = sentinel;
            size--;
            return resultData;
        }
    }


    /**
     * Removes the last Node's data at the end of the LinkedList
     * If the LinkedList is empty, return null
     * @return the data field of the end node, if the list is empty, return null
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            // target will later be dealt with the Garbage Collection
            Node target = sentinel.prev;
            T resultData = target.data;
            sentinel.prev = target.prev;
            target.prev.next = sentinel;
            size--;
            return resultData;
        }
    }


    /**
     * Returns the number of elements in the double LinkedList
     * @return the number of elements in the double LinkedList
     */
    public int size() {
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Returns the data of the node at the given index. If the LinkedList is empty return null
     * @param index THe given index
     * @return node's data at the given index
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            Node flag = sentinel.next;
            int i = 0;
            while (i < index) {
                flag = flag.next;
                i++;
            }

            return flag.data;
        }
    }


    /**
     * Returns the data of the node at the given index. If the LinkedList is empty return null
     * Using recursive method, not iteration
     * @param index The given index
     * @return node's data at the given index
     */
    public T getRecursive(int index) {
        // As a node needs to be passed a parameter for recursion purpose, a helper method is needed
        return getRecursiveHelper(sentinel.next, index);
    }


    /**
     * A private helper method for getRecursive
     * @param node start scanning node
     * @param i the given index
     * @return returns the data of the Node at the given index
     */
    private T getRecursiveHelper(Node node, int i) {
        if (node == sentinel || i >= size) {
            return null;
        } else if (i ==0) {
            return node.data;
        } else {
            return getRecursiveHelper(node.next, i-1);
        }
    }

}
