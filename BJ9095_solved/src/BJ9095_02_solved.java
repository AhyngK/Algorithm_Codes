import java.util.Arrays;
import java.util.Scanner;

public class BJ9095_02_solved {
    public static void main(String[] args) {

        // N이 11 미만의 수이므로, 미리 계산해둔 뒤 답안만 제출하는 방식으로 진행
        // N에 대해 (N-1)에서 1을 더하기+(N-2)에서 2를 더하기 +(N-3)에서 3을 더하기의 경우로 판단
        // nums[n] = nums[n-1]+nums[n-2]+nums[n-3]
        int[] nums = new int[11];
        Arrays.fill(nums,-1);
        nums[0] = 1;
        for (int i = 1; i <nums.length ; i++) {
            int a =0;
            int b =0;
            int c =0;

            // 1,2,3의 경우에는 0+1,0+2ㅡ0+3의 경우가 될 수 있도록 함
            if(i-1>=0){
                a = nums[i-1];
            }
            if(i-2>=0){
                b = nums[i-2];
            }
            if(i-3>=0){
                c = nums[i-3];
            }

            nums[i] = a+b+c;
        }

        // 만들어둔 답변 배열 출력만 함
        Scanner sc = new Scanner(System.in);
        int testCaseNum = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < testCaseNum; i++) {
            int temp = sc.nextInt();
            sc.nextLine();
            System.out.println(nums[temp]);
        }
    }
}
