import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int[] arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        
        int depart = 0;
        int end = N - 1;

        for (int i = 0; i < arr2.length; i++) {
            test(arr2[i], depart, end);
        }
    }

    static void test(int x, int depart, int end) {
        int mid;
        if (depart > end) {
            System.out.println(0);
            return;
        }
        if (depart <= end) {
            mid = (depart + end) / 2;
            if (x == arr[mid]) {
                System.out.println(1);
                return;
            } else if (x < arr[mid]) {

                test(x, depart, mid - 1);
            } else
            test(x, mid + 1, end);
        }
    }
}