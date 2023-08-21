import java.util.*;
import java.io.*;

public class Main {
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static int smallest = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());
        int functionCount = Integer.parseInt(st.nextToken());
        
        int[][] array = new int[rows][columns];
        for (int i = 0; i < array.length; i++) {
            array[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<int[]> turns = new ArrayList<>();
        for (int i = 0; i < functionCount; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            turns.add(new int[] {r,c,s});
        }

        int[] visited = new int[turns.size()];
        List<int[]> selected = new ArrayList<>();
        selection(0,turns,selected,visited,array);

        System.out.println(smallest);
    }
    static void selection(int index, List<int[]>turns, List<int[]> selected, int[] visited, int[][] array){
        if(index==visited.length){
            int[][] tempArr = new int[array.length][array[0].length];
            for (int i = 0; i < tempArr.length; i++) {
                tempArr[i] = array[i].clone();
            }
            for (int i = 0; i < selected.size(); i++) {
                int x1 = selected.get(i)[0] - selected.get(i)[2]-1;
                int y1 = selected.get(i)[1] - selected.get(i)[2]-1;
                int x2 = selected.get(i)[0] + selected.get(i)[2]-1;
                int y2 = selected.get(i)[1] + selected.get(i)[2]-1;
                turnArray(x1,y1,x2,y2,tempArr);
            }
            for (int i = 0; i < tempArr.length; i++) {
                smallest = Math.min(smallest,eachLine(i,tempArr));
            }
        }
        for (int i = 0; i < turns.size(); i++) {
            if(visited[i]==0){
                selected.add(turns.get(i));
                visited[i] = 1;
                selection(index+1,turns,selected,visited,array);
                visited[i] = 0;
                selected.remove(selected.size()-1);
            }
        }
    }

    static void turnArray(int x1, int y1, int x2, int y2, int[][] array){
        int startX = x1;
        int startY = y1;
        int endX = x2;
        int endY = y2;
        int currentX = x1;
        int currentY = y1;

        while (true){
            if(startX>=endX){
                break;
            }
            int temp = array[currentX][currentY];
            for (int i = 0; i < directions.length; i++) {
                while (true){
                    int nextX = currentX+directions[i][0];
                    int nextY = currentY+directions[i][1];

                    if(nextX>endX || nextY>endY || nextX<startX || nextY<startY){
                        break;
                    }

                    int temp1 = array[nextX][nextY];
                    array[nextX][nextY] = temp;
                    temp = temp1;
                    currentX = nextX;
                    currentY = nextY;
                }
            }
            currentX+=1;
            currentY+=1;
            startX+=1;
            startY+=1;
            endX-=1;
            endY-=1;
        }
    }
    static int eachLine(int i, int[][] array){
        int sum = 0;
        for (int j = 0; j < array[0].length; j++) {
            sum+=array[i][j];
        }
        return sum;
    }
}
