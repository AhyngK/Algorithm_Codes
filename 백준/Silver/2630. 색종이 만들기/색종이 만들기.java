import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int whitePaper = 0;
    static int bluePaper = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] paper = new int[size][size];
        for (int i = 0; i < paper.length; i++) {
            paper[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        recursion(0,0,paper.length-1,paper.length-1,paper);
        System.out.println(whitePaper);
        System.out.println(bluePaper);
    }
    static void recursion(int x1, int y1, int x2, int y2, int[][] paper){
        int size = (x2-x1)/2;

        if(check(x1,y1,x2,y2,paper)){
            return;
        }

        recursion(x1,y1,x1+size,y1+size,paper);
        recursion(x1,y1+size+1,x1+size,y2,paper);
        recursion(x1+size+1,y1,x2,y1+size,paper);
        recursion(x1+size+1,y1+size+1,x2,y2,paper);
    }
    static boolean check(int x1, int y1, int x2, int y2, int[][] paper){
        int first = paper[x1][y1];
        for (int i = x1; i <=x2 ; i++) {
            for (int j = y1; j <=y2 ; j++) {
                if(paper[i][j]!=first){
                    return false;
                }
            }
        }
        if(first==0){
            whitePaper+=1;
        }
        else {
            bluePaper+=1;
        }
        return true;
    }
}
