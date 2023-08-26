import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static String[][] picture;
    static int[][] normal;
    static int[][] colorless;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        picture = new String[size][size];
        normal = new int[size][size];
        colorless = new int[size][size];

        for (int i = 0; i < picture.length; i++) {
            picture[i] = br.readLine().split("");
        }

        int normalNum = 0;
        int colorlessNum = 0;
        String tofind2 = "";

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if(normal[i][j] == 0){
                    normalNum+=1;
                    search(i,j,picture[i][j],tofind2,0);
                }
                if(colorless[i][j] == 0){
                    colorlessNum+=1;
                    if(picture[i][j].equals("R")){
                        tofind2 = "G";
                    }
                    if(picture[i][j].equals("G")){
                        tofind2 = "R";
                    }
                    search(i,j,picture[i][j],tofind2,1);
                    tofind2 = "";
                }
            }
        }
        System.out.println(normalNum+" "+colorlessNum);
    }
    static void search(int startX, int startY, String toFind1,String toFind2, int mapNum){
        int[][] tempMap = mapNum==0? normal : colorless;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX,startY});
        tempMap[startX][startY] = 1;

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+ directions[i][0], temp[1]+directions[i][1]};
                if(borderCheck(next) && tempMap[next[0]][next[1]]==0 && (picture[next[0]][next[1]].equals(toFind1) || picture[next[0]][next[1]].equals(toFind2))){
                    tempMap[next[0]][next[1]] = 1;
                    queue.add(next);
                }
            }
        }
    }
    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< picture.length && next[1]>=0 && next[1]<picture[0].length){
            return true;
        }
        return false;
    }
}
