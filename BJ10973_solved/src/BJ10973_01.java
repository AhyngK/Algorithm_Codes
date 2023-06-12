import java.util.Arrays;
import java.util.Scanner;

public class BJ10973_01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int indexToChange =-1;
        for (int i = nums.length-1; i >0 ; i--) {
            if(nums[i-1]>nums[i]){
                indexToChange = i-1;
                break;
            }
        }

        if(indexToChange==-1){
            System.out.println(-1);
        }
        else {
            // 바꿀 위치의 숫자의 이전 숫자 인덱스 찾기
            // 바꿔야 하는 숫자보다 하나 작은 수는 그 앞쪽에 위치할 수 있기 때문에 최댓값으로 찾아야 한다
            // 바꾸는 자리수를 처음에 찾는 부분에서 오류가 잦았다. 주의 필요
            int indexNextChange = -1;
            for (int i = indexToChange+1; i <nums.length ; i++) {
                if(indexNextChange==-1 &&nums[i]<nums[indexToChange] || nums[i]<nums[indexToChange] && nums[i]>nums[indexNextChange]){
                    indexNextChange =i;
                }
            }
            swap(nums,indexToChange,indexNextChange);

            int startIndex = indexToChange+1;
            int lastIndex = nums.length-1;
            while(startIndex<lastIndex){
                swap(nums,startIndex,lastIndex);
                startIndex++;
                lastIndex--;
            }

            for(int a:nums){
                System.out.print(a+" ");
            }
        }
    }
    static void swap(int[] nums, int index, int index1){
        int temp = nums[index];
        nums[index] = nums[index1];
        nums[index1] = temp;
    }
}
