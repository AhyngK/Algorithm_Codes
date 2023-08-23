import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int smallest = Integer.MAX_VALUE;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        BFSSearch(map);
        if(smallest == Integer.MAX_VALUE){
            smallest = -1;
        }
        System.out.println(smallest);
    }
    static void BFSSearch(int[][] map){
        Queue<int[]> que = new LinkedList<>();
        int[][][] visited = new int[map.length][map[0].length][2];

        int currentX = 0;
        int currentY = 0;
        que.add(new int[] {currentX,currentY,0});
        visited[currentX][currentY][0] = 1;

        while (!que.isEmpty()){
            int[] temp = que.poll();
            // End Point
            if(temp[0] == map.length-1 && temp[1] == map[0].length-1){
                break;
            }
            for (int i = 0; i < directions.length; i++) {
                int[] next = new int[3];
                next[0] = temp[0]+directions[i][0];
                next[1] = temp[1]+directions[i][1];
                next[2] = temp[2];
                // 현재 좌표를 아직 방문하지 않은 경우
                if(borderCheck(next,map) && visited[next[0]][next[1]][next[2]]==0){
                    // 현재 좌표가 벽이 아닌 경우
                    if(map[next[0]][next[1]]==0){
                        visited[next[0]][next[1]][next[2]] = visited[temp[0]][temp[1]][temp[2]]+1;
                        que.add(next);
                    }
                    // 벽인데 부술 수 있는 경우
                    else if(map[next[0]][next[1]]==1 && next[2]<1){
                        next[2]+=1;
                        visited[next[0]][next[1]][next[2]] = visited[temp[0]][temp[1]][temp[2]]+1;
                        que.add(next);
                    }
                }
            }
        }

        int end1 = visited[map.length-1][map[0].length-1][0] == 0 ? Integer.MAX_VALUE : visited[map.length-1][map[0].length-1][0];
        int end2 = visited[map.length-1][map[0].length-1][1] == 0 ? Integer.MAX_VALUE : visited[map.length-1][map[0].length-1][1];
        smallest = Math.min(Math.min(end1,end2),smallest);
    }
    static boolean borderCheck(int[] next, int[][] map){
        if(next[0]>=0 && next[0]<map.length && next[1]>=0 && next[1]<map[0].length){
            return true;
        }
        return false;
    }
}
