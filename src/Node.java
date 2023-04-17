
public class Node{
    private String[] person;
    private String Id;
    private String First_Name;
    private String Last_Name;
    private String Ticket_Type;
    private Node next;

    /**
     * initializes the node.
     * @param person : Array of Strings representing a person, each element holds an attribute of the person as seen below.
     */
    public Node(String [] person){
        this.person = person;
        Id = person[0];
        First_Name = person[1];
        Last_Name = person[2];
        Ticket_Type = person[3];
        next = null;
    }

    /**
     * @return : The next Node
     */
    public Node getNext(){
        return next;
    }

    /**
     * @return : The data of the node, meaning an Array of strings of the person the node represents
     */
    public String[] getData() {
        return person;

    }

    /**
     * @param next : The Node we want to set as the next to our node
     */
    public void setNext(Node next){
        this.next = next;
    }

    /**
     * @return : Changes the Ticket_Type to int sorted by importance of the ticket, used in the reception function
     */
    public int getTicketVal(){
        switch (Ticket_Type) {
            case "VIP":
                return 4;
            case "GOLDEN_RING":
                return 3;
            case "INNER_RING":
                return 2;
            default:
                return 1;
        }
    }

}
