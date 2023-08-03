import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Collect input numbers into Array
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length ; i++) {
            nums[i] = sc.nextInt();
        }

        // Deciding Stack
        boolean check = false;
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> answerStack = new Stack<>();
        int arrIndex = 0;
        int start = 1;
        StringBuilder sb = new StringBuilder();

        // to Decide
        // push stack until the first object in array appears
        while (true){
            // EndPoint 1. index reached the end : finished
            if(arrIndex==nums.length){
                check = true;
                break;
            }
            // EndPoint 2. Stack has bigger object than array, there is no way to end
            if(!stack.isEmpty() && stack.peek() > nums[arrIndex]){
                check = false;
                break;
            }

            // Function 1. the top of the Stack is same as first array object
            if(!stack.isEmpty() && stack.peek() == nums[arrIndex]){
                answerStack.add(stack.pop());
                arrIndex+=1;
                sb.append("-").append("\n");
                continue;
            }

            // Default Function. add element to Stack
            stack.push(start);
            sb.append("+").append("\n");
            start+=1;
        }

        if(check){
            System.out.println(sb);
        }
        else {
            System.out.println("NO");
        }
    }
}
