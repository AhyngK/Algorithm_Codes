import java.util.Arrays;
import java.util.Scanner;

public class BJ10971_01 {
    static long totalSum = 999999999;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();

        long[][] fee = new long[N][N];
        for (int i = 0; i < fee.length; i++) {
            fee[i] = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }

        int[] route = new int[N];
        for (int i = 0; i < N; i++) {
            route[i] =i;
        }

        travelFee(route,fee);
        while (routes(route)!=-1){
            travelFee(route,fee);
        }
        System.out.println(totalSum);
    }
    static int routes(int[] route){
        int first =-1;
        for (int i =route.length-1 ; i >0 ; i--) {
            if(route[i-1]<route[i]){
                first =i-1;
                break;
            }
        }

        if(first==-1){
            return -1;
        }
        else {
            int last =-1;
            for (int i = first+1; i <route.length ; i++) {
                if(last==-1 && route[i]>route[first]){
                    last =i;
                } else if (last!=-1 && route[i]>route[first] && route[last]>route[i]) {
                    last =i;
                }
            }
            swap(route,first,last);

            first++;
            last = route.length-1;
            while (first<last){
                swap(route,first,last);
                first++;
                last--;
            }
           // System.out.print(Arrays.toString(route)+", ");
            return 0;
        }
    }

    static void travelFee(int[] route, long[][] fee){
        long sum =0;
        for (int i = 0; i <route.length ; i++) {
            int a = route[i];
            int b = i==route.length-1 ? route[0] : route[i+1];
            if(fee[a][b]==0){
                return;
            }
            else {
                sum+=fee[a][b];
            }
        }
       // System.out.println(sum);

        if(totalSum>sum){
            totalSum=sum;
        }
    }
    static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}
