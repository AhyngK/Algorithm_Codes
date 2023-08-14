import java.util.Scanner;

public class Main {
    static int[][] box;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int size = N/3*5 + (N/3-1);
        box = new int[N][size];
        recursion(0,0,size-1, N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if(box[i][j]==1){
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
    static void recursion (int upCenter, int leftBottom, int rightBottom, int height){
        if(height==3){
            box[upCenter][(rightBottom-leftBottom)/2+leftBottom] = 1;
            box[upCenter+1][(rightBottom-leftBottom)/2-1+leftBottom] = 1;
            box[upCenter+1][(rightBottom-leftBottom)/2+1+leftBottom] = 1;
            for (int i = leftBottom; i <= rightBottom ; i++) {
                box[upCenter+2][i] = 1;
            }
            return;
        }
        recursion(upCenter,(leftBottom+leftBottom+(rightBottom-leftBottom)/2-1)/2+1,(leftBottom+(rightBottom-leftBottom)/2+1+rightBottom)/2-1,height/2);
        recursion(upCenter+height/2,leftBottom,leftBottom+(rightBottom-leftBottom)/2-1, height/2);
        recursion(upCenter+height/2,leftBottom+(rightBottom-leftBottom)/2+1,rightBottom,height/2);
    }
}