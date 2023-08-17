import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int L = Integer.parseInt(input[2]);

        int[] arr = new int[N + 1];
        int count = 0; // 주고받은 횟수
        int index = 1;

        while (true) {
            arr[index]++;
            if (arr[index] == M)
                break;

            if (arr[index] % 2 == 1) { // 현재 위치에 있는 사람이 홀수번 받은 경우
                index = (index + L) % N;
                count++;
            } else {		//짝수번 받은 경우
                index = (index - L + N) % N;
                count++;
            }

            
        }

        System.out.println(count);
    }
}