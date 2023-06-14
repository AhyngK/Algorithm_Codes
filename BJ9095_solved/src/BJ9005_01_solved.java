import java.util.Scanner;

public class BJ9005_01_solved {
    static int count =0;
    public static void main(String[] args) {
        // 입력 받는 부분
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();
        scanner.nextLine();

        // 각 테스트케이스로 주어진 수를 배열에 저장
        int[] testCase = new int[testCount];
        for (int i = 0; i < testCount; i++) {
            testCase[i]= scanner.nextInt();
            scanner.nextLine();
        }

        // 테스트케이스별 답변을 저장할 배열
        // 계산하는 함수를 호출 후 답변 저장
        int[] counts = new int[testCount];
        for (int i = 0; i < testCase.length; i++) {
            int sum =0;
            addition(testCase[i],sum);
            counts[i] =count;
            count=0;
        }

        // 출력
        for (int i = 0; i < counts.length; i++) {
            System.out.println(counts[i]);
        }
    }

    // 1,2,3을 돌아가며 선택하는 것을 재귀함수로 구현
    // 총합이 N을 넘으면 종료
    static void addition (int target, int sum){
        if(sum>=target){
            if(sum==target){
                count++;
            }
            return;
        }
        for (int i = 1; i <=3 ; i++) {
         addition(target,sum+i);
        }
    }
}
