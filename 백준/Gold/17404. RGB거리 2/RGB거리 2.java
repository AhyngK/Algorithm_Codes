import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] prices = new int[N][3];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // priceSum
        // firstIndex = N
        // SecondIndex : current -> R[0], G[1], B[2]
        // ThirdIndex : first color -> R[0], G[1], B[2]
        int[][][] priceSum = new int[N][3][3];

        // Second Sum
        // Current is Red
        priceSum[1][0][0] = Integer.MAX_VALUE;
        priceSum[1][0][1] = prices[0][1]+prices[1][0];
        priceSum[1][0][2] = prices[0][2]+prices[1][0];
        // Current is Green
        priceSum[1][1][0] = prices[0][0]+prices[1][1];
        priceSum[1][1][1] = Integer.MAX_VALUE;
        priceSum[1][1][2] = prices[0][2]+prices[1][1];
        // Current is Blue
        priceSum[1][2][0] = prices[0][0]+prices[1][2];
        priceSum[1][2][1] = prices[0][1]+prices[1][2];
        priceSum[1][2][2] = Integer.MAX_VALUE;

        for (int i = 2; i < prices.length-1; i++) {
          // Current is Red
          priceSum[i][0][0] = Math.min(priceSum[i-1][1][0],priceSum[i-1][2][0])+prices[i][0];
          priceSum[i][0][1] = Math.min(priceSum[i-1][1][1],priceSum[i-1][2][1])+prices[i][0];
          priceSum[i][0][2] = Math.min(priceSum[i-1][1][2],priceSum[i-1][2][2])+prices[i][0];
          // Current is Green
          priceSum[i][1][0] = Math.min(priceSum[i-1][0][0],priceSum[i-1][2][0])+prices[i][1];
          priceSum[i][1][1] = Math.min(priceSum[i-1][0][1],priceSum[i-1][2][1])+prices[i][1];
          priceSum[i][1][2] = Math.min(priceSum[i-1][0][2],priceSum[i-1][2][2])+prices[i][1];
          // Current is Blue
          priceSum[i][2][0] = Math.min(priceSum[i-1][0][0],priceSum[i-1][1][0])+prices[i][2];
          priceSum[i][2][1] = Math.min(priceSum[i-1][0][1],priceSum[i-1][1][1])+prices[i][2];
          priceSum[i][2][2] = Math.min(priceSum[i-1][0][2],priceSum[i-1][1][2])+prices[i][2];
        }
        int lastIndex = prices.length-2;
        int finalPriceRed = Math.min(Math.min(priceSum[lastIndex][1][1],priceSum[lastIndex][1][2]),Math.min(priceSum[lastIndex][2][1],priceSum[lastIndex][2][2])) + prices[lastIndex+1][0];
        int finalPriceGreen = Math.min(Math.min(priceSum[lastIndex][0][0],priceSum[lastIndex][0][2]),Math.min(priceSum[lastIndex][2][0],priceSum[lastIndex][2][2])) + prices[lastIndex+1][1];
        int finalPriceBlue = Math.min(Math.min(priceSum[lastIndex][0][0],priceSum[lastIndex][0][1]),Math.min(priceSum[lastIndex][1][0],priceSum[lastIndex][1][1])) + prices[lastIndex+1][2];
        System.out.println(Math.min(Math.min(finalPriceRed,finalPriceGreen),finalPriceBlue));
    }
}
