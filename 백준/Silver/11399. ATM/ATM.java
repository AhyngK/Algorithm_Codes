import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());
        int[] times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(times);

        int sum = 0;
        for (int i = 0; i < times.length; i++) {
            if(i==0){
                sum+=times[i];
            }
            else {
                times[i] = times[i]+times[i-1];
                sum+=times[i];
            }
        }
        System.out.println(sum);
    }
}
