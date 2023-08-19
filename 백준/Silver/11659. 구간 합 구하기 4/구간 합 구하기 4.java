import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nums = Integer.parseInt(st.nextToken());
        int sumNum = Integer.parseInt(st.nextToken());

        int[] sum = new int[nums];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nums; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if(i!=0){
                temp+=sum[i-1];
            }
            sum[i] = temp;
        }

        for (int i = 0; i < sumNum; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken())-2;
            int last = Integer.parseInt(st.nextToken())-1;
            int temp = sum[last];
            if(first>=0){
                temp-=sum[first];
            }
            sb.append(temp).append("\n");
        }
        System.out.println(sb);
    }
}
