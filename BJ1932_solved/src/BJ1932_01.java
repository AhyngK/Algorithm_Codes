import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ1932_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int triangleSize = sc.nextInt();
        sc.nextLine();


        ArrayList<long[]> triangle = new ArrayList<>();
        for (int i = 0; i < triangleSize; i++) {
            long[] temp1 = Arrays.stream(sc.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
            triangle.add(temp1);
        }
        
        ArrayList<long[]> addition = new ArrayList<>();
        addition.add(triangle.get(0));

        for (int i = 1; i <triangle.size() ; i++) {
            long[] temp = new long[i+1];
            for (int j = 0; j < i+1; j++) {
                if(j==0){
                    temp[j] = addition.get(0)[0]+triangle.get(i)[0];
                }
                else if (j==i) {
                    temp[j] = addition.get(0)[j-1]+triangle.get(i)[j];
                }
                else {
                    long temp2 = Math.max(addition.get(0)[j-1],addition.get(0)[j]);
                    temp[j] = temp2+triangle.get(i)[j];
                }
            }
            addition.remove(0);
            addition.add(temp);
        }

        Arrays.sort(addition.get(0));
        System.out.println(addition.get(0)[addition.get(0).length-1]);
    }
}
