import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ1309_01 {
    // 우릐(n)의 수별로 사자가 1~n마리까지 수용 가능하다고 생각
    // 우리의 개수에 따라 사자 마리수를 계산해서 넣음, 그래서 이차원배열로 접근함
    // N개의 우리가 있을때, 사자는 1~N마리까지 수용 가능, 1마리 경우, 2마리 경우.... N마리 경우로 저장
    // 이렇게 하니, 답은 나오지만 N의 범위가 너무 크기 때문에 시간이 너무 오래 걸리고 메모리 초과가 났다

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cageSize = sc.nextInt();

        ArrayList<long[]> cases1 = new ArrayList<>();
        long total = 1;

        for (int i = 1; i < cageSize+1; i++) {
            long[] thisCase = new long[cageSize+1];
            Arrays.fill(thisCase,0);

            for (int j = 0; j <=i; j++) {
                if(j==0){
                    thisCase[j] =0;
                }
                else if(j==1 || i==j){
                    thisCase[j]=2;
                    total = (total+thisCase[j])%9901;
                }
                else {
                    thisCase[j] = (cases1.get(0)[j-1]+cases1.get(1)[j-1]+cases1.get(1)[j])%9901;
                    total = (total+thisCase[j])%9901;
                }
            }

            if(cases1.size()<2){
                cases1.add(thisCase);
            }
            else {
                cases1.remove(0);
                cases1.add(thisCase);
            }
        }
        System.out.println(total);
    }
}
