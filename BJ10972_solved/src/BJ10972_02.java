import java.util.Arrays;
import java.util.Scanner;

public class BJ10972_02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int toChange =-1;
        for (int i = nums.length-1; i >0 ; i--) {
            if(nums[i]>nums[i-1]){
                toChange = i-1;
                break;
            }
        }

        if(toChange==-1){
            System.out.println(-1);
        }
        else {
            // 다음에 올 수 찾는 부분에 대해 신경 필요 (바꿀 자리의 수보다 큰 최소값)
            // 이부분 때문에 오류가 났던 것
            int targetChange =-1;
            for (int i = toChange+1; i <nums.length ; i++) {
                if(targetChange==-1 && nums[i]<nums[toChange]){
                    continue;
                }
                else if(targetChange==-1 && nums[i]>nums[toChange]){
                    targetChange =i;
                }
                else if(nums[targetChange]>nums[i] && nums[i]>nums[toChange]){
                    targetChange =i;
                }
            }
            swap(nums,toChange,targetChange);

            int first = toChange+1;
            int last = nums.length-1;
            while (first<last){
                swap(nums,first,last);
                first++;
                last--;
            }

            for(int a: nums){
                System.out.print(a+" ");
            }
        }
    }
    static  void swap(int[]nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
