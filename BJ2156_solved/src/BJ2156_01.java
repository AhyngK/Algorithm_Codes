import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ2156_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int glasses = sc.nextInt();
        sc.nextLine();

        int [] wine = new int[glasses];
        for (int i = 0; i < glasses; i++) {
            wine[i] = sc.nextInt();
            sc.nextLine();
        }

        // 각 경우를 이번 포도주를 마시거나 마시지 않는 경우의 수로 본다
        // first는 그 전전 경우를 저장, index 0은 마시지 않는 경우, index 1은 마신 경우로 생각
        // second는 그 전 경우를 저장

        // 이렇게 하니 두번 연속 먹지 않는 경우를 판별할 수 없었다
        ArrayList<int[]> cases = new ArrayList<>();
        int[] first = {0,0};
        int[] second = {0,0};
        cases.add(first);
        cases.add(second);

        for (int i = 0; i < wine.length; i++) {
            if(i==0){
                cases.get(0)[0] = 0;
                cases.get(0)[1] = wine[i];
            }
            else if (i==1) {
                cases.get(1)[0] = first[1];
                cases.get(1)[1] = first[1]+wine[i];
//                System.out.println(Arrays.toString(cases.get(0)));
//                System.out.println(Arrays.toString(cases.get(1)));
            }
            else {
                int[] temp = new int[2];
                temp[0] = cases.get(1)[1];
                temp[1] = (cases.get(0)[0]+wine[i-1]+wine[i]) > (cases.get(0)[1]+wine[i]) ? (cases.get(0)[0]+wine[i-1]+wine[i]) : (cases.get(0)[1]+wine[i]);
                cases.remove(0);
                cases.add(temp);
//                System.out.println(Arrays.toString(cases.get(0)));
//                System.out.println(Arrays.toString(cases.get(1)));
            }

        }
        int temp1 = Math.max(cases.get(0)[1],cases.get(0)[0]);
        int temp2 = Math.max(cases.get(1)[1],cases.get(1)[0]);
        System.out.println(Math.max(temp1,temp2));
    }
}
