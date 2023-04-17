public class HashClosed {
    private LinkedList[] hashRegister;
    private int tableSize;
    private int numOfRegistered;

    /**
     constructor - initialize the Hashtable
     */
    public HashClosed(int m){
        numOfRegistered = m;
        if (numOfRegistered < 3 && numOfRegistered > 0) // edge case where table size might init to 0
            tableSize = 1;
        else
            tableSize = m/3;
        hashRegister = new LinkedList[tableSize];
        for (int i=0; i<tableSize; i++) // initialize the linked lists in the hash table
            hashRegister[i] = new LinkedList();
    }

    /**
     * inserts a person to the hash table using modulo hash function
     * @param person : Array of Strings representing a person.
     */
    public void insert(String [] person){
        int personId = Integer.parseInt(person[0]);
        int hashFuncIndex = personId % tableSize;
        hashRegister[hashFuncIndex].add(person);
    }

    /**
     * returns an Array of integers representing the sizes of the lists in the HashTable
     */
    public int[] getNodesSize(){
        int[] nodesSize = new int[tableSize];
        for (int i=0; i < tableSize; i++){
            nodesSize[i] = hashRegister[i].getSize();
        }
        return nodesSize;
    }

    /**
     * searches a person in the hash table and returns the number of steps it took to find / not find him.
     * @param person : Array of Strings representing a person.
     * @return : a Array of two int, the first represents if a person was found, the second is the number of steps
     * it took to find him.
     */
    public int[] search(String [] person){
        int personId = Integer.parseInt(person[0]);
        int hashFuncIndex = personId % tableSize;
        Node temp = hashRegister[hashFuncIndex].getHead();
        int [] steps = {0,1};
        if (temp != null) {
            for (int i = 0; i < hashRegister[hashFuncIndex].getSize(); i++){//search the person(node) in the linked list
                if (Integer.parseInt(temp.getData()[0]) == personId){
                    steps[0] = 1;
                    steps[1] = 1 + i;
                    return steps;
                }
                temp = temp.getNext();
            }
            steps[1] = 1 + hashRegister[hashFuncIndex].getSize();
            return steps;
        }
        return steps;
    }

    /**
     * @return : The size of the Hashtable
     */
    public int getSize(){
        return tableSize;
    }

    /**
     * @return : The number of people in the hash table
     */
    public int getNumOfRegistered(){
        return numOfRegistered;
    }

}
