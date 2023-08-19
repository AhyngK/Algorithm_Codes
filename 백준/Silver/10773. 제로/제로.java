import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int total = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < num; i++) {
            int temp = Integer.parseInt(br.readLine());
            if(temp==0){
                total-=stack.pop();
            }
            else {
                total+=temp;
                stack.add(temp);
            }
        }
        System.out.println(total);
    }
}
