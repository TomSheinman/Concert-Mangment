public class LinkedList {
    private Node head;
    private Node tail;
    private int size;

    /**
     * Constructor - initializes the LinkedList
     */
    public LinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * adds a person to the tail of the list as a node
     * @param person : Array of Strings that defines a person
     */
    public void add(String[] person){
        Node temp = new Node(person);
        if (head == null) {
            head = temp;
        }
        else {
            tail.setNext(temp);
        }
        tail = temp;
        size ++;
    }

    /**
     * @return : the size of the LinkedList
     */
    public int getSize(){
        return size;
    }

    /**
     * @return : The head of the LinkedList
     */
    public Node getHead() {
        return head;
    }
}
