import java.util.*;

public class BJ2133_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] counts = new int[N+1];
        counts[0] =1;
        counts[1] =1;
        for(int i=2; i<counts.length; i++) {
            if(i%2==0) {
                counts[i] = counts[i-1]*2+counts[i-2];
            }
            else {
                counts[i] = counts[i-1]+counts[i-2];
            }
        }


        if(N%2!=0) {
            System.out.println(0);
        }
        else {
            System.out.println(counts[N]);
        }
    }
}
