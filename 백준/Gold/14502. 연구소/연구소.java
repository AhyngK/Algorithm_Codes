import java.io.*;
import java.util.*;

public class Main {
    static int[][] lab;
    static List<int[]> emptySpace;
    static int safeBiggest = Integer.MIN_VALUE;
    static int labsize;
    static int wallNum;

    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());

        lab = new int[rowSize][columnSize];
        labsize = rowSize*columnSize;
        emptySpace = new ArrayList<>();
        for (int i = 0; i < lab.length; i++) {
            lab[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        findEmptySpace();
        List<int[]> currentSelected = new ArrayList<>();
        selectEmptySpace(0,currentSelected);
        System.out.println(safeBiggest);
    }
    static void selectEmptySpace(int index, List<int[]> currentSelected){
        // EndPoint
        if(currentSelected.size()==3){
            spreadProcess(currentSelected);
            return;
        }
        // StopPoint
        if(index==emptySpace.size()){
            return;
        }

        // Continue 1. select current
        currentSelected.add(emptySpace.get(index));
        selectEmptySpace(index+1,currentSelected);
        currentSelected.remove(currentSelected.size()-1);
        // Continue 2. not select current
        selectEmptySpace(index+1,currentSelected);
    }
    static void spreadProcess(List<int[]> currentSelected){
        int count = 0;
        int[][] tempLab = new int[lab.length][lab[0].length];
        for (int i = 0; i < tempLab.length; i++) {
            tempLab[i] = lab[i].clone();
        }

        mapLab(tempLab, currentSelected,1);

        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                if(tempLab[i][j] == 2){
                    count += spreadVirus(i,j,tempLab);
                }
            }
        }

        int currentSafe = labsize-count-wallNum-3;
        safeBiggest = Math.max(safeBiggest,currentSafe);
    }

    static int spreadVirus(int x, int y, int[][] tempLab){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {x,y});
        tempLab[x][y] = 5;
        int count = 1;

        while (!que.isEmpty()){
            int[] temp = que.poll();

            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1]};
                if(borderCheck(next,tempLab) && tempLab[next[0]][next[1]]==0){
                    tempLab[next[0]][next[1]] = 5;
                    count+=1;
                    que.add(next);
                }
            }
        }
        return count;
    }
    static boolean borderCheck(int[] next, int[][] tempLab){
        if(next[0]>=0 && next[0]< tempLab.length && next[1]>=0 && next[1]<tempLab[0].length){
            return true;
        }
        return false;
    }
    static void mapLab(int[][] tempLab, List<int[]> currentSelected, int toMap){
        for (int i = 0; i < currentSelected.size(); i++) {
            tempLab[currentSelected.get(i)[0]][currentSelected.get(i)[1]] = toMap;
        }
    }
    static void findEmptySpace(){
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                if(lab[i][j]==0){
                    emptySpace.add(new int[] {i,j});
                }
                if(lab[i][j]==1){
                    wallNum+=1;
                }
            }
        }
    }
}
