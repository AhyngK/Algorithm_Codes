import java.util.Scanner;

public class BJ1309_02 {
    // 두번째 방법
    // 각 우리는 두칸임을 이용, 각 우리에 넣는 사자의 경우를 0마리, 왼쪽1마리, 오른쪽1마리로 구분
    // 0마리의 경우 : 이전 0마리 경우+이전 왼쪽+이전 오른쪽
    // 왼쪽 : 이전 0마리+이전 오른쪽 (연속되게 넣으면 안되므로)
    // 오른쪽 : 이전 0마리+이전 왼쪽
    // 위 식으로 풀어가면 된다
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cageNum = sc.nextInt();

        long total = 3;
        long[] before = {1,1,1};
        for (int i = 2; i <=cageNum ; i++) {
            long[] current = new long[3];
            current[0] = (before[0]+before[1]+before[2])%9901;
            current[1] = (before[0]+before[2])%9901;
            current[2] = (before[0]+before[1])%9901;

            total = (current[0]+current[1]+current[2])%9901;
            before = current;
        }
        System.out.println(total);
    }
}
