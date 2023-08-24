import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean gram = false;
    static int endtime;
    static int smallestTIme = Integer.MAX_VALUE;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        endtime = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        travel(map);
        if(smallestTIme == Integer.MAX_VALUE){
            System.out.println("Fail");
        }
        else {
            System.out.println(smallestTIme);
        }
    }
    static void travel(int[][] map){
        int[][][] visited = new int[map.length][map[0].length][2];
        Queue<int[]> que = new LinkedList<>();
        int[] first = {0,0,0};
        que.add(first);
        while (!que.isEmpty()){
            int[] temp = que.poll();
            if(temp[0]==map.length-1 && temp[1]==map[0].length-1){
                break;
            }
            if(visited[temp[0]][temp[1]][temp[2]]>endtime){
                continue;
            }

            for (int i = 0; i < directions.length; i++) {
                int[] next = new int[3];
                next[0] = temp[0]+directions[i][0];
                next[1] = temp[1]+directions[i][1];
                next[2] = temp[2];
                if(borderCheck(next,map)){
                    // Found Gram
                    if(map[next[0]][next[1]]==2){
                        next[2] = 1;
                        visited[next[0]][next[1]][1] = visited[temp[0]][temp[1]][0]+1;
                        que.add(next);
                    }
                    // Already Found Gram
                    else if(next[2]==1 && visited[next[0]][next[1]][1] == 0){
                        visited[next[0]][next[1]][1] = visited[temp[0]][temp[1]][1]+1;
                        que.add(next);
                    }
                    // Didnt find Gram
                    else if(next[2]==0 && visited[next[0]][next[1]][0] == 0 && map[next[0]][next[1]]==0){
                        visited[next[0]][next[1]][0] = visited[temp[0]][temp[1]][0]+1;
                        que.add(next);
                    }
                }
            }
        }
        int end1 = visited[map.length-1][map[0].length-1][0] == 0? Integer.MAX_VALUE : visited[map.length-1][map[0].length-1][0];
        int end2 = visited[map.length-1][map[0].length-1][1] == 0? Integer.MAX_VALUE : visited[map.length-1][map[0].length-1][1];

        smallestTIme = Math.min(Math.min(end1,end2),smallestTIme);
    }
    static boolean borderCheck(int[] next, int[][] map){
        if(next[0]>=0 && next[0]<map.length && next[1]>=0 && next[1]<map[0].length){
            return true;
        }
        return false;
    }
}
