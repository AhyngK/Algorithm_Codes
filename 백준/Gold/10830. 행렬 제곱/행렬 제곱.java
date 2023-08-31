import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final long mod = 1000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        long multiply = Long.parseLong(st.nextToken());

        long[][] matrix  = new long[size][size];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }
        StringBuilder sb = new StringBuilder();
        long[][] answer = multiplyFunc(multiply,matrix);
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer.length; j++) {
                sb.append((answer[i][j])%1000).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static long[][] multiplyFunc(long multiply, long[][] matrix){
        if(multiply==1){
            return matrix;
        }

        long[][] multiplied = new long[matrix.length][matrix.length];
        long[][] leftHalf = multiplyFunc(multiply/2,matrix);

        for (int i = 0; i < multiplied.length; i++) {
            for (int j = 0; j < multiplied.length; j++) {
                long sum = 0;
                for (int k = 0; k < multiplied.length; k++) {
                    sum = (leftHalf[i][k]*leftHalf[k][j]+sum)%mod;
                }
                multiplied[i][j] = sum;
            }
        }

        if(multiply%2 != 0){
            long[][] multiplied1 = new long[matrix.length][matrix.length];
            for (int i = 0; i < multiplied1.length; i++) {
                for (int j = 0; j < multiplied1.length; j++) {
                    long sum = 0;
                    for (int k = 0; k < multiplied.length; k++) {
                        sum = (multiplied[i][k]*matrix[k][j]+sum)%mod;
                    }
                    multiplied1[i][j] = sum;
                }
            }
            multiplied = multiplied1;
        }

        return multiplied;
    }
}
