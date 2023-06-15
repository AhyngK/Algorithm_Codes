import java.util.Scanner;

public class BJ1699_01 {
    public static void main(String[] args) {
        int[] counts = new int[1000001];
        counts[0] =0;
        counts[1] =1;

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int i = 2; i <N+1 ; i++) {
            int smallest = 1000000;
            int pow = 1;
            while (pow*pow<=i){
                int thisCount = counts[i-pow*pow]+1;
                if(smallest>thisCount){
                    smallest = thisCount;
                }
                pow++;
            }
            counts[i]= smallest;
        }

        System.out.println(counts[N]);
    }
}
