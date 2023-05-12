import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class B25556 {
    public static void main(String[] args) {
        // 입력받는 부분, 입력받은 문자열 공백으로 나누고 숫자로 변경
        Scanner scanner = new Scanner(System.in);
        int lengthN = scanner.nextInt();
        scanner.nextLine();
        String A = scanner.nextLine();
        int[] AtoInt = Arrays.stream(A.split(" ")).mapToInt(Integer::parseInt).toArray();

        // Stack 4개를 직접 만들지 않아도 맨 끝에 들어간 숫자만 배열 등으로 모아두고 판별할 수 있음
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> s3 = new Stack<>();
        Stack<Integer> s4 = new Stack<>();

        int index =0;
        boolean check = true;
        while(AtoInt[AtoInt.length-1]!=-1){
            if(s1.isEmpty()||s1.peek()<AtoInt[index]){
                s1.push(AtoInt[index]);
                AtoInt[index] =-1;
                index++;
                continue;
            }
            else if(s2.isEmpty()||s2.peek()<AtoInt[index]){
                s2.push(AtoInt[index]);
                AtoInt[index] =-1;
                index++;
                continue;
            }
            else if(s3.isEmpty()||s3.peek()<AtoInt[index]){
                s3.push(AtoInt[index]);
                AtoInt[index] =-1;
                index++;
                continue;
            }
            else if(s4.isEmpty()||s4.peek()<AtoInt[index]){
                s4.push(AtoInt[index]);
                AtoInt[index] =-1;
                index++;
                continue;
            }
            else {
                check =false;
                break;
            }
        }
        if(check){
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
