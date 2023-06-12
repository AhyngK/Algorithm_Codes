import java.util.Arrays;
import java.util.Scanner;

public class BJ6603_02_solved {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            int[] numsPre = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(numsPre[0]==0){
                break;
            }
            else {
                int[] nums = new int[numsPre.length-1];
                for (int i = 0; i <nums.length ; i++) {
                    nums[i] = numsPre[i+1];
                }
                int numIndex =0;
                int count =0;
                int[] answer = new int[6];
                recursion(nums,numIndex,count,answer);
                System.out.println();
            }
        }


    }
    static void recursion(int[] nums, int numIndex, int count, int[] answer){
        if(count==6){
            for (int a:answer) {
                System.out.print(a+" ");
            }
            System.out.println();
            return;
        }

        if(numIndex==nums.length){
            return;
        }

        // 고르는 경우
        answer[count] = nums[numIndex];
        recursion(nums,numIndex+1,count+1,answer);

        // 고르지 않는 경우
        recursion(nums,numIndex+1,count,answer);
    }
}
