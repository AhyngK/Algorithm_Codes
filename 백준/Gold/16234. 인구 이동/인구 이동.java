import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] land;
    static int[][] near;
    static List<List<int[]>> unions;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    static int minDif;
    static int maxDif;
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int landSize = Integer.parseInt(st.nextToken());
        minDif = Integer.parseInt(st.nextToken());
        maxDif = Integer.parseInt(st.nextToken());

        land = new int[landSize][landSize];
        for (int i = 0; i < land.length; i++) {
            land[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(run());
    }
    static int run(){
        int runTime = 0;
        while (true){
            check = true;
            openGates();
            for (int i = 0; i < unions.size(); i++) {
                if(unions.get(i).size()>1){
                    check = false;
                }
            }
            peopleMovement();
            if(check){
                break;
            }
            runTime+=1;
        }
        return runTime;
    }
    static void peopleMovement(){
        int[][] newLand = new int[land.length][land[0].length];
        for (int i = 0; i < unions.size(); i++) {
            int unionsNum = unions.get(i).size();
            int peopleSum = 0;
            for (int j = 0; j < unions.get(i).size(); j++) {
                peopleSum+=land[unions.get(i).get(j)[0]][unions.get(i).get(j)[1]];
            }
            peopleSum /= unionsNum;
            for (int j = 0; j < unions.get(i).size(); j++) {
                newLand[unions.get(i).get(j)[0]][unions.get(i).get(j)[1]] = peopleSum;
            }
        }
        land = newLand;
    }
    static void openGates(){
        unions = new ArrayList<>();
        near = new int[land.length][land[0].length];
        int count = 1;
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if(near[i][j]==0){
                    findNear(i,j,count);
                    count+=1;
                }
            }
        }
    }
    static void findNear(int x, int y, int count){
        int[] current = {x,y};
        near[current[0]][current[1]] = count;

        List<int[]> currentUnion = new ArrayList<>();
        currentUnion.add(current);

        Queue<int[]> que = new LinkedList<>();
        que.add(current);

        while (!que.isEmpty()){
            int[] temp = que.poll();
            for (int i = 0; i < directions.length; i++) {
                int [] next = {temp[0]+directions[i][0], temp[1]+directions[i][1]};
                if(checkBorder(next) && near[next[0]][next[1]]==0 && checkDif(temp,next)){
                    currentUnion.add(next);
                    near[next[0]][next[1]] = count;
                    que.add(next);
                }
            }
        }
        unions.add(currentUnion);
    }
    static boolean checkDif(int[] temp, int[] next){
        int dif = Math.abs(land[temp[0]][temp[1]] - land[next[0]][next[1]]);
        if(minDif<=dif && dif<=maxDif){
            return true;
        }
        return false;
    }
    static boolean checkBorder(int [] next){
        if(next[0]>=0 && next[0]< land.length && next[1]>=0 && next[1]<land[0].length){
            return true;
        }
        return false;
    }
}
