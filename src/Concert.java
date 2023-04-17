import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class Concert {

    /**
     * @param FileName : String representing a file path / name that hold info about crowd or people in reception
     * @return : LinkedList consists of people in crowd or reception
     */
    public static LinkedList getFromFile(String FileName){
        LinkedList persons = new LinkedList();
        try {
            File personsFile = new File(FileName);
            Scanner reader = new Scanner(personsFile);
            while (reader.hasNextLine()){
                String [] person = reader.nextLine().split(",");
                persons.add(person);
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        return persons;
    }

    /**
     * @param file_path : String representing a file path that holds info about the crowd
     * @return : HashClosed with all registered crowd from the file.
     */
    public static HashClosed registerCrowd(String file_path){
        LinkedList persons = getFromFile(file_path);
        HashClosed registers = new HashClosed(persons.getSize());
        Node temp = persons.getHead();
        while (temp!=null){
            registers.insert(temp.getData());
            temp = temp.getNext();
        }
        return registers;
    }

    /**
     * @param file_path : String representing a file path that holds info about people in reception
     * @param registered : HashClosed with all registered crowd
     * @return : Average steps of searching people from reception in HashClosed using the search func in HashClosed
     */
    public static int reception_AverageSteps(String file_path, HashClosed registered){
        LinkedList reception =  getFromFile(file_path);
        if (reception.getSize() == 0) // edge case where no one arrives at the reception
            return 0;
        int sumOfSteps = 0;
        Node temp = reception.getHead();
        while (temp != null){
            sumOfSteps += registered.search(temp.getData())[1];
            temp = temp.getNext();
        }
        return sumOfSteps/reception.getSize();
    }

    /**
     * The function defines which people will be in the show by these parameters, first the registered crowd
     * that arrived to the reception.
     * if there is still places left, the unregistered will be accepted by ticket value and arrival order until
     * there are no more remaining places or every one in the reception was accepted.
     * @param file_path : String representing a file path that holds info about people in reception
     * @param registered : HashClosed with all registered crowd
     * @return : the sorted ids of all the people accepted to the concert using a helper function.
     *
     */

    public static int[] reception(String file_path, HashClosed registered){
        LinkedList reception = getFromFile(file_path);
        int [] crowd;
        Node temp = reception.getHead();
        int numRegistered = registered.getNumOfRegistered();
        if (reception.getSize()<= numRegistered){ // case 1: arrived crowd is smaller than seating places
            crowd = new int[reception.getSize()];
            for (int i=0; i < reception.getSize(); i++){
                crowd[i] = Integer.parseInt(temp.getData()[0]);
                temp = temp.getNext();
            }
        }
        else { // case 1: there are more people in the reception than seats at the concert
            crowd = new int[numRegistered];
            int placesLeft = numRegistered;
            while (temp!= null){ // insert registered crowd
                if (registered.search(temp.getData())[0] == 1){
                    crowd[numRegistered - placesLeft] = Integer.parseInt(temp.getData()[0]);
                    placesLeft--;
                }
                temp = temp.getNext();
            }
            int ticketVal = 4; // defines ticket value
            while (placesLeft > 0){ // insert unregistered crowd by ticket value
                temp = reception.getHead();
                while (temp!=null){
                    if ((registered.search(temp.getData())[0] == 0) && (temp.getTicketVal() == ticketVal)){
                        crowd[numRegistered - placesLeft] = Integer.parseInt(temp.getData()[0]);
                        placesLeft --;
                        if (placesLeft == 0) break; // no more places left
                    }
                    temp = temp.getNext();
                }
                ticketVal = ticketVal - 1; // first search the crowd with the largest ticket value(4 than 3...)
            }
        }
        return bubbleSort(crowd);
    }

    /**
     * @param crowd : an array of ids of people accepted to the concert
     * @return : the list of ids sorted using bubble sort
     */
    private static int[] bubbleSort(int [] crowd){
        int n = crowd.length;
        int swapper;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (crowd[j] > crowd[j + 1]) {
                    swapper = crowd[j];
                    crowd[j] = crowd[j + 1];
                    crowd[j + 1] = swapper;
                }
            }
        }
        return crowd;
    }

    /**
     * @param sortedCrowed : Sorted Array of ids of people at the concert
     * @param registered : HashClosed with all registered crowd, its size defines the number of seats in the concert
     * @param functionNum : 0 or 1, defines which hash function is used to place the crowd in the seats
     * @return : a list of stats as mentioned in the assignment
     */
    public static int[] seatingArrangement(int [] sortedCrowed, HashClosed registered, int functionNum){
        HashOpen seatingHash = new HashOpen(registered.getNumOfRegistered());
        int [] stats = new int[4];
        int stepsCounter = 0;
        for (int i=0; i<sortedCrowed.length; i++){
            if (i == sortedCrowed.length/2) // first N/2
                stats[0] = stepsCounter;
            if (i == (3*sortedCrowed.length)/4) // first 3N/4
                stats[1] = stepsCounter;
            if (i == (sortedCrowed.length - (int)Math.sqrt(sortedCrowed.length))) // first N - Sqrt(N)
                stats[2] = stepsCounter;
            stepsCounter += seatingHash.insert(sortedCrowed[i], functionNum);
        }
        stats[3] = stepsCounter - stats[2]; // last Sqrt(N)
        return stats;
    }
}
