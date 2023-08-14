import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] toPrint;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        toPrint = new int[N][N];
        recursion(0,N-1,0,N-1);
        for (int i = 0; i < toPrint.length; i++) {
            for (int j = 0; j < toPrint[0].length; j++) {
                if(toPrint[i][j]==1){
                    sb.append("*");
                }
                else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void recursion(int x1, int x2, int y1, int y2){
        int size = y2-y1+1;
        // Base Case
        if(size<=3){
            toPrint[x1][y1] = 1;
            toPrint[x1][y1+1] = 1;
            toPrint[x1][y1+2] = 1;
            toPrint[x1+1][y1] = 1;
            toPrint[x1+1][y1+1] = 0;
            toPrint[x1+1][y1+2] = 1;
            toPrint[x1+2][y1] = 1;
            toPrint[x1+2][y1+1] = 1;
            toPrint[x1+2][y1+2] = 1;
            return;
        }

        size /=3;
        recursion(x1,x1+size-1,y1,y1+size-1);
        recursion(x1,x1+size-1,y1+size,y1+size*2-1);
        recursion(x1,x1+size-1,y1+size*2,y2);
        recursion(x1+size,x1+size*2-1,y1,y1+size-1);
//        recursion(x1+size,x1+size*2-1,y1+size,y1+size*2-1);
        recursion(x1+size,x1+size*2-1,y1+size*2,y2);
        recursion(x1+size*2,x2,y1,y1+size-1);
        recursion(x1+size*2,x2,y1+size,y1+size*2-1);
        recursion(x1+size*2,x2,y1+size*2,y2);
    }
}
