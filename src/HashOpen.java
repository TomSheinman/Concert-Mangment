public class HashOpen {
    private int tableSize;
    private int[] hashSeats;

    /**
     constructor - initialize the Hashtable
     */
    public HashOpen(int m){
        tableSize = m;
        hashSeats = new int[tableSize];
    }

    /**
     helper function for the hash function that returns the id reversed
     */
    private int reveredId(int id) {
        int reverse= 0;
        int digit;
        while (id != 0){
            digit = id % 10;
            reverse = reverse * 10 + digit;
            id /= 10;
        }
        return reverse;
    }

    /**
     * A function that insert an id to the Hashtable based on the received hash func and calculates the amount of steps
     * it takes to insert that id.
     * @param id : Int a person id
     * @param hashFunc : 0 or 1, defines which hash func is used to insert the id.
     * @return : the number of steps it takes to insert the id to the table
     */
    public int insert(int id, int hashFunc) {
        int hashFuncIndex;
        boolean hasRight = true;
        boolean hasLeft = true;
        int steps = 0;
        if (hashFunc == 1)
            hashFuncIndex = id % tableSize;
        else
            hashFuncIndex = reveredId(id) % tableSize;
        if (hashSeats[hashFuncIndex] == 0) {
            hashSeats[hashFuncIndex] = id;
            return steps;
        }
        // calculates the number of steps it takes to insert the id, if id is not found using the hash function
        // it will search as the following h(k)+1, h(k)-1, h(k)+2, h(k)-2, ... unless there is no more
        // indexes to the left / right, in that case it will only go to the opposite direction.
        for (int i = 1; i < tableSize; i++) {
            if (hasRight) {
                if (hashFuncIndex + i >= tableSize)
                    hasRight = false;
                else {
                    steps += 1;
                    if (hashSeats[hashFuncIndex + i] == 0) {
                        hashSeats[hashFuncIndex + i] = id;
                        break;
                    }
                }
            }
            if (hasLeft){
                if (hashFuncIndex - i < 0)
                    hasLeft = false;
                else {
                    steps += 1;
                    if (hashSeats[hashFuncIndex - i] == 0) {
                        hashSeats[hashFuncIndex - i] = id;
                        break;
                    }
                }
            }
        }
        return steps;
    }

    /**
     * @return : The number of ids in the hash table
     */
    public int getNumberElements(){
        int counter = 0;
        for (int i=0; i<tableSize; i++){
            if (hashSeats[i] != 0)
                counter ++;
        }
        return counter;
    }

    /**
     * @return : The size of the hash table
     */
    public int getSize(){
        return tableSize;
    }

}
