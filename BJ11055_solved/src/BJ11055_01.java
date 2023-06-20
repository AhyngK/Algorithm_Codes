import java.util.Arrays;
import java.util.Scanner;

public class BJ11055_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        sc.nextLine();
        int[] array = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] biggest = new int[array.length];
        biggest[0] = array[0];
        for (int i = 1; i < array.length ; i++) {
            int index =-1;
            for (int j = i-1; j>=0 ; j--) {
                if(index==-1 && array[j]<array[i]){
                    index = j;
                }
                else if (index!=-1 &&  array[j]<array[i] && biggest[j]>biggest[index]) {
                    index = j;
                }
            }
            if(index==-1){
                biggest[i] = array[i];
            }
            else {
                biggest[i] = array[i]+biggest[index];
            }
        }
        Arrays.sort(biggest);
        System.out.println(biggest[biggest.length-1]);
    }
}
