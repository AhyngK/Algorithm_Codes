import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        long[] prices = new long[n];
        long total = 0;
        for (int i = 0; i < prices.length; i++) {
            st = new StringTokenizer(br.readLine());
            long aCur = Long.parseLong(st.nextToken());
            long bCur = Long.parseLong(st.nextToken());
            total+=aCur;
            prices[i] = bCur-aCur;
        }
        Arrays.sort(prices);
        for (int i = 0; i < b; i++) {
            total+=prices[i];
        }
        System.out.println(total);
    }
}
