import java.util.Arrays;
import java.util.Scanner;

public class BJ1912_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] sums = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if(i==0 || sums[i-1]+array[i]<array[i]){
                sums[i] = array[i];
            }
            else {
                sums[i] = sums[i-1]+array[i];
            }
        }
        Arrays.sort(sums);
        System.out.println(sums[sums.length-1]);
    }
}
