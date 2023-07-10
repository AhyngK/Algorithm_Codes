import java.util.Arrays;
import java.util.Scanner;

public class BJ2156_02_solved {
    static int maxDrink = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        sc.nextLine();

        // 포도주 양들 입력받아서 배열에 넣어둠
        int[] glasses = new int [caseNum];
        for(int i=0; i<caseNum; i++) {
            glasses[i] = sc.nextInt();
            sc.nextLine();
        }

        int[] sums = {0,0,0,0};
        int index =0;

        // 재귀함수 호출
        recursion(index, glasses, sums);
        System.out.println(maxDrink);

    }

    static void recursion(int index, int[] glasses, int[] sums) {
        if(index==glasses.length) {
            Arrays.sort(sums);
            int max = sums[sums.length-1];
            if(max>maxDrink) {
                maxDrink= max;
            }
            return;
        }


        // 이번 포도주를 마실 때
        // 0. oo의 경우 (이전 : xo)
        int array0 = sums[1]+glasses[index];

        // 1. xo의 경우 (이전 : ox, xx)
        int array1 = Math.max(sums[2]+glasses[index], sums[3]+glasses[index]);

        // 이번 포도주를 안 마실 때
        // 2. ox의 경우 (이전 : oo, xo)
        int array2 = Math.max(sums[0], sums[1]);

        // 3. xx의 경우 (이전 : ox)
        int array3 = sums[2];

        sums[0] = array0;
        sums[1] = array1;
        sums[2] = array2;
        sums[3] = array3;

        recursion(index+1, glasses, sums);
    }
}