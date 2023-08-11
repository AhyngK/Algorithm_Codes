
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int find = sc.nextInt();

        int[][] array = new int[N][N];
        int x = 0;
        int y = 0;

        int current = N*N;
        int xEnd = array.length;
        int yEnd = array[0].length;
        int xStart = -1;
        int yStart = 0;

        int answerX = 0;
        int answerY = 0;

        while (current > 0){
            while (x<xEnd){
                array[x][y] = current;
                if(current==find){
                    answerX = x;
                    answerY = y;
                }
                x+=1;
                current-=1;
            }
            x-=1;
            y+=1;
            xEnd-=1;

            while (y<yEnd){
                array[x][y] = current;
                if(current==find){
                    answerX = x;
                    answerY = y;
                }
                y+=1;
                current-=1;
            }
            x-=1;
            y-=1;
            yEnd-=1;

            while (x>xStart){
                array[x][y] = current;
                if(current==find){
                    answerX = x;
                    answerY = y;
                }
                x-=1;
                current-=1;
            }
            x+=1;
            y-=1;
            xStart+=1;

            while (y>yStart){
                array[x][y] = current;
                if(current==find){
                    answerX = x;
                    answerY = y;
                }
                y-=1;
                current-=1;
            }
            x+=1;
            yStart+=1;
            y+=1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                sb.append(array[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
        System.out.println((answerX+1)+" "+(answerY+1));
    }
}
