import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] colorPapers = {5,4,3,2,1};
    static int[] colorPaperNums = {5,5,5,5,5};
    static int totalCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] paper = new int[10][10];
        int[] usedPaper = {0,0,0,0,0};
        for (int i = 0; i < paper.length; i++) {
            paper[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        paperSelect(0,0,0,paper,usedPaper);
        if(totalCount==Integer.MAX_VALUE){
            totalCount=-1;
        }
        System.out.println(totalCount);
    }
    // IDEA
    // 각 칸에서 어떤 색종이로 덮을 것인지 선택하는 것을 한번의 함수로 한다
    // 배열을 순회하며 1(종이가 있음)을 만날 경우, 가능한 색종이 덮는 경우를 모두 시도
    // 0(종이가 없음)일 경우 다음 칸을 호출
    static void paperSelect(int x, int y, int currentCount, int[][] paper, int[] usedPaper){

        // End Point
        if(x == paper.length){
            totalCount = Math.min(currentCount,totalCount);
            return;
        }

        // Stop Point
        if(currentCount > totalCount){
            return;
        }

        // Continue Function
        // 1. Current coordinate don't have paper -> call next Coordinate
        if(paper[x][y]==0){
            int[] nextCoor = nextCoordinates(x,y,paper);
            paperSelect(nextCoor[0],nextCoor[1],currentCount,paper,usedPaper);
        }
        // 2. Current coordinate has paper -> try each colored papers
        else {
            for (int i = 0; i < colorPapers.length; i++) {
                // Check if current paper can be used -> If true, call next coordinate
                if(usedPaper[i]<colorPaperNums[i] && borderCheck(x,y,i,paper) && canCover(x,y,i,paper)){
                    coverWithPaper(x,y,i,0,paper);
                    usedPaper[i]+=1;
                    int[] nextCoor = nextCoordinates(x,y+colorPapers[i]-1,paper);
                    paperSelect(nextCoor[0],nextCoor[1],currentCount+1,paper,usedPaper);
                    usedPaper[i]-=1;
                    coverWithPaper(x,y,i,1,paper);
                }
            }
        }
    }
    static int[] nextCoordinates(int x, int y, int[][] paper){
        int[] next = new int[2];
        next[0] = x;
        next[1] = y+1;
        if(next[1]==paper[0].length){
            next[0] += 1;
            next[1] = 0;
        }
        return next;
    }
    static boolean borderCheck(int x, int y, int paperNum, int[][] paper){
        if(x+colorPapers[paperNum]-1<paper.length && y+colorPapers[paperNum]-1<paper[0].length){
            return true;
        }
        return false;
    }
    static boolean canCover(int x, int y, int paperNum, int[][] paper){
        boolean toReturn = true;
        label : for (int i = x; i < x+colorPapers[paperNum]; i++) {
            for (int j = y; j < y+colorPapers[paperNum]; j++) {
                if(paper[i][j]==0){
                    toReturn = false;
                    break label;
                }
            }
        }
        return toReturn;
    }
    static void coverWithPaper(int x, int y, int paperNum, int coverNum, int[][] paper){
        for (int i = x; i < x+colorPapers[paperNum]; i++) {
            for (int j = y; j < y+colorPapers[paperNum]; j++) {
                paper[i][j] = coverNum;
            }
        }
    }
}
