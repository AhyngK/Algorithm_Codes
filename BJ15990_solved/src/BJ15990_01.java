import java.util.ArrayList;
import java.util.Scanner;

public class BJ15990_01 {
    // N개의 정수 경우의 수 담을 배열
   static long[] nums = new long[100001];
   // N에 대해 +1로 생성된 경우, +2로 생성된 경우, +3을 생성된 경우를 담을 배열
    // N을 계산할 때, N-1, N-2, N-3에 대해 생성 배열을 저장해둘 리스트
    static ArrayList<long[]> storage;
    public static void main(String[] args) {

        storage = new ArrayList<>();
        storage.add(new long[]{0,1,0,0});
        storage.add(new long[]{0,0,1,0});
        storage.add(new long[]{0,1,1,1});

        nums[1] = 1;
        nums[2] = 1;
        nums[3] = 3;

        Scanner sc = new Scanner(System.in);
        int testCaseNum = sc.nextInt();
        sc.nextLine();
        int toContinue = 4;
        for (int i = 0; i <testCaseNum ; i++) {
            int temp = sc.nextInt();
            sc.nextLine();
            // 계산함수 호출
            recursion(toContinue, temp);
            System.out.println(nums[temp]);
            // 이전 마지막으로 계산했던 값에 이어서 계산해야 하므로, 둘 중 큰 값을 저장
            toContinue = Math.max(toContinue, temp + 1);
        }

    }

    static void recursion(int start,int n){
        for (int i = start; i <=n ; i++) {
            long[] thisTemp = new long[4];
            // N을 만드는데 (N+1)+1인 경우, N+1이 +1로 만들어진 경우를 제외한다
            thisTemp[1] = (storage.get(2)[2]+storage.get(2)[3])%1000000009;
            // N을 만드는데 (N+2)+2인 경우, N+2이 +2로 만들어진 경우를 제외한다
            thisTemp[2] = (storage.get(1)[1]+storage.get(1)[3])%1000000009;
            // N을 만드는데 (N+3)+3인 경우, N+3이 +3로 만들어진 경우를 제외한다
            thisTemp[3] = (storage.get(0)[1]+storage.get(0)[2])%1000000009;

            // 총합이 N의 경우의 수
            nums[i] = (thisTemp[1]+thisTemp[2]+thisTemp[3])%1000000009;
            // N-1, N-2, N-3의 +1,+2,+3 배열을 모두 저장하기에 너무 커지기 때문에
            // N-3의 배열을 삭제하고 N의 배열을 추가해줌
            storage.remove(0);
            storage.add(thisTemp);
        }
    }
}
