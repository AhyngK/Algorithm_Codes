import java.util.Arrays;
import java.util.Scanner;

public class BJ16194_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int toBuy = sc.nextInt();
        sc.nextLine();

        int[] temp = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] prices = new int[temp.length+1];
        prices[0] =0;
        for (int i = 0; i <temp.length ; i++) {
            prices[i+1] = temp[i];
        }

        // 11052번과 동일, 가장 작은 것으로 바꾸기만 하면 됨
        int[] smallestPrices = new int[prices.length];
        smallestPrices[0] =0;
        for (int i = 1; i <smallestPrices.length ; i++) {
            int[] temp1 = new int[i];
            for (int j = 1; j <=i ; j++) {
                temp1[j-1] = prices[j]+smallestPrices[i-j];
            }
            Arrays.sort(temp1);
            smallestPrices[i] = temp1[0];
        }

        System.out.println(smallestPrices[toBuy]);
    }
}
