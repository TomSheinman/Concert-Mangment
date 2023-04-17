import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        HashClosed hash = Concert.registerCrowd("input1.txt");
        int[] arrived = Concert.reception("input2.txt", hash);
        int avg = Concert.reception_AverageSteps("input2.txt", hash);
        int[] steps1 = Concert.seatingArrangement(arrived, hash, 1);
        int[] steps2 = Concert.seatingArrangement(arrived, hash, 2);
        int[] nodeSizes = hash.getNodesSize();
        System.out.println(Arrays.toString(nodeSizes));
        System.out.println(Arrays.toString(arrived));
        System.out.println(avg);
        System.out.println(Arrays.toString(steps1));
        System.out.println(Arrays.toString(steps2));

    }
}
