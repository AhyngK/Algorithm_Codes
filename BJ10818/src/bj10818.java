import java.util.Arrays;
import java.util.Scanner;

public class bj10818 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String numbers = scanner.nextLine();
        int[] nums = Arrays.stream(numbers.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        System.out.println(nums[0]+" "+nums[nums.length-1]);
    }
}
