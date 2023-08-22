import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int columnNum = Integer.parseInt(br.readLine());

        int[] columns = new int[1001];
        int smallestIndex = Integer.MAX_VALUE;
        int biggestIndex = Integer.MIN_VALUE;

        int biggestHeight = Integer.MIN_VALUE;
        int heightIndex = 0;

        StringTokenizer st;
        for (int i = 0; i < columnNum; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            columns[a] = b;
            smallestIndex = Math.min(smallestIndex,a);
            biggestIndex = Math.max(biggestIndex,a);
            if(biggestHeight<b){
                biggestHeight = b;
                heightIndex = a;
            }
        }

        int sum = 0;
        Stack <Integer> stack = new Stack<>();
        for (int i = smallestIndex; i <=heightIndex ; i++) {
            if(stack.isEmpty() || stack.peek()<columns[i]){
                stack.add(columns[i]);
            }
            sum+=stack.peek();
        }
        stack = new Stack<>();
        for (int i = biggestIndex; i > heightIndex ; i--) {
            if(stack.isEmpty() || stack.peek()<columns[i]){
                stack.add(columns[i]);
            }
            sum+=stack.peek();
        }

        System.out.println(sum);
    }
}
