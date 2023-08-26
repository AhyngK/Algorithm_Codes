import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.*;

public class Main {
    static int[][] houses;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        houses = new int[size][size];
        for (int i = 0; i < houses.length; i++) {
            houses[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        List<Integer> eachHouse = new ArrayList<>();

        for (int i = 0; i < houses.length; i++) {
            for (int j = 0; j < houses[0].length; j++) {
                if(houses[i][j]>0){
                    eachHouse.add(search(i,j,houses[i][j]));
                }
            }
        }
        Collections.sort(eachHouse);
        System.out.println(eachHouse.size());
        for (int i = 0; i < eachHouse.size(); i++) {
            System.out.println(eachHouse.get(i));
        }
    }
    static int search(int startX, int startY, int startNum){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX,startY});
        houses[startX][startY] = -1;
        int count = 1;

        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+ directions[i][0], temp[1]+directions[i][1]};
                if(borderCheck(next) && houses[next[0]][next[1]]==startNum){
                    count+=1;
                    houses[next[0]][next[1]] = -1;
                    queue.add(next);
                }
            }
        }
        return count;
    }

    static boolean borderCheck(int[] next){
        if(next[0]>=0 && next[0]< houses.length && next[1]>=0 && next[1]<houses[0].length){
            return true;
        }
        return false;
    }
}
