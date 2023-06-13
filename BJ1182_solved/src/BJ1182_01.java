import java.util.Arrays;
import java.util.Scanner;

public class BJ1182_01 {
   static int count =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long[] NS = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long S = NS[1];
        long[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToLong(Long::parseLong).toArray();

        int index =0;
        long sum = -9999999;
        int included =0;
        int includeBefore =-1;
        recursion(index,nums,S,sum,included, includeBefore);

        System.out.println(count);
    }

    static  void recursion(int index, long[] nums, long S, long sum, int included, int includeBefore){
        if(included>0 && included!=includeBefore){
            if(sum==S){
                count++;
            }
        }
        if(index==nums.length){
            return;
        }

        // 선택하는 경우
        if(sum == -9999999){
            sum = nums[index];
        }
        else {
            sum+=nums[index];
        }
        recursion(index+1,nums,S, sum, included+1, included);
        sum-=nums[index];

        // 선택하지 않는 경우
        recursion(index+1,nums,S, sum, included, included);
    }
}
