import java.util.Scanner;

public class Main {
    static boolean check = true;
    static boolean end = false;
    static int firstIndex = 0;
    static int lastIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.nextLine();

        for (int tc = 0; tc < testCase; tc++) {
            check = true;
            end = false;

            // Input 1. Order Input
            String temp = sc.nextLine();
            String [] orders = temp.split("");
            // Input 2. Array Input : split input with , and map it to Int
            int n = sc.nextInt();
            sc.nextLine();
            String temp1 = sc.nextLine();
            String temp2 = temp1.substring(1,temp1.length()-1);
            String[] arr = temp2.split(",");
            int[] nums = new int[n];
            int index = 0;
            for (int i = 0; i < arr.length; i++) {
                if(!arr[i].equals("")){
                    nums[index] = Integer.parseInt(arr[i]);
                    index++;
                }
            }

            //
            firstIndex = 0;
            lastIndex = nums.length;
            for (int i = 0; i < orders.length; i++) {
                if(orders[i].equals("D")){
                    delete(nums);
                }
                else {
                    reverse();
                }
                if(end){
                    break;
                }
            }

            // Print
            StringBuilder sb = new StringBuilder();
            if(end){
                System.out.println("error");
            }
            else if (check) {
                sb.append("[");
                for (int i = firstIndex; i <=lastIndex-1 ; i++) {
                    if(i==lastIndex-1){
                        sb.append(nums[i]);
                    }
                    else {
                        sb.append(nums[i]).append(",");
                    }

                }
                sb.append("]");
                System.out.println(sb);
            }
            else {
                sb.append("[");
                for (int i = lastIndex-1; i >=firstIndex ; i--) {
                    if(i==firstIndex){
                        sb.append(nums[i]);
                    }
                    else {
                        sb.append(nums[i]).append(",");
                    }
                }
                sb.append("]");
                System.out.println(sb);
            }
        }
    }

    static void delete(int[] nums){
        if(check){
            firstIndex+=1;
        }
        else {
            lastIndex-=1;
        }

        if(!indexCheck(nums)){
            end = true;
        }
    }
    static void reverse(){
        check = check ? false : true;
    }
    static boolean indexCheck(int[] nums){
        if(firstIndex<=lastIndex){
            return true;
        }
        return false;
    }
}
