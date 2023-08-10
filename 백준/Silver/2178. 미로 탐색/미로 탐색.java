import java.io.*;
import java.util.*;

public class Main {
    // Directions : Up, Right, Down, Left
    static int[] di = {-1,0,1,0};
    static int[] dj = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // Input Size
        int rowSize = Integer.parseInt(st.nextToken());
        int columnSize = Integer.parseInt(st.nextToken());
        // Original Maze Input
        int[][] maze = new int[rowSize][columnSize];
        for (int i = 0; i < maze.length; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        // Boolean Array for visit check
        boolean[][] check = new boolean[rowSize][columnSize];
        for (int i = 0; i < check.length; i++) {
            Arrays.fill(check[i],false);
        }
        System.out.println(BFSSearch(maze,check));
    }
    static int BFSSearch(int[][] maze, boolean[][] check){
        // start[0] : row, start[1] : column, start[2] : check if first element
        int[] start = {0,0,1};
        int[] last = {check.length-1, check[0].length-1};

        // Queue for BFS Search,
        Queue<int[]> que = new LinkedList<>();
        que.add(start);
        check[start[0]][start[1]] = true;
        int count = 0;
        boolean firstCheck = false;
        // Start BFS
        while (!que.isEmpty()){
            int[] temp = que.poll();
            // if polled Element is the First Element, count up and Mark new Element
            if(temp[2] == 1){;
                count+=1;
                firstCheck = true;
            }

            // Ended : if polled Element is the same as Maze's last Element
            if(temp[0]==last[0] && temp[1]==last[1]){
                break;
            }

            // Not Ended : make new Array to offer Que
            // Search Each Direction
            for (int i = 0; i < di.length; i++) {
                int[] next = new int[3];
                next[0] = temp[0]+di[i];
                next[1] = temp[1]+dj[i];
                next[2] = 0;

                if(xCheck(next[0],maze) && yCheck(next[1],maze)
                        && maze[next[0]][next[1]]==1 && !check[next[0]][next[1]]){
                    if(firstCheck){
                        next[2] = 1;
                        firstCheck = false;
                    }
                    que.add(next);
                    check[next[0]][next[1]] = true;
                }
            }
        }
        return count;
    }
    static boolean xCheck (int x, int[][] maze){
        if(x>=0 && x<maze.length){
            return true;
        }
        return false;
    }
    static boolean yCheck(int y, int[][] maze){
        if(y>=0 && y<maze[0].length){
            return true;
        }
        return false;
    }
}