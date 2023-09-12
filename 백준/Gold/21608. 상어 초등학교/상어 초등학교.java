import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static List<List<Integer>> students;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        students = new ArrayList<>();

        for (int i = 0; i < size*size; i++) {
            st = new StringTokenizer(br.readLine());
            students.add(new ArrayList<>());
            while (st.hasMoreTokens()){
                students.get(i).add(Integer.parseInt(st.nextToken())-1);
            }
        }

        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i],-1);
        }

        for (int i = 0; i < students.size(); i++) {
            configureSeat(i);
        }
        System.out.println(happySum());
    }
    static int happySum(){
        int total = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int current = eachStudent(i,j);
                if(current == 1){
                    total+=1;
                }
                else if (current == 2) {
                    total+=10;
                }
                else if (current == 3) {
                    total+=100;
                }
                else if (current == 4) {
                    total+=1000;
                }
            }
        }
        return total;
    }
    static int findIndex(int a){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).get(0) == a){
                return i;
            }
        }
        return 0;
    }
    static int eachStudent(int x, int y){
        int temp = 0;
        for (int i = 0; i < directions.length; i++) {
            int nextX = x+directions[i][0];
            int nextY = y+directions[i][1];
            if(borderCheck(nextX,nextY) && students.get(findIndex(map[x][y])).contains(map[nextX][nextY])){
                temp+=1;
            }
        }
        return temp;
    }
    static void configureSeat(int index){
        PriorityQueue<EachSeat> selection = new PriorityQueue<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]!=-1){
                    continue;
                }
                EachSeat tempSeat = new EachSeat();
                int[] tempCount = nearStudentFunc(index,i,j);
                tempSeat.xCoor = i;
                tempSeat.yCoor = j;
                tempSeat.nearStudent = tempCount[0];
                tempSeat.nearEmpty = tempCount[1];
                selection.add(tempSeat);
            }
        }
        EachSeat finalSeat = selection.poll();
        map[finalSeat.xCoor][finalSeat.yCoor] = students.get(index).get(0);
    }
    static int[] nearStudentFunc(int index, int x, int y){
        int[] count = new int[2];
        for (int i = 0; i < directions.length; i++) {
            int nextX = x+directions[i][0];
            int nextY = y+directions[i][1];
            if(borderCheck(nextX,nextY)){
                if(students.get(index).contains(map[nextX][nextY])){
                    count[0]+=1;
                }
                if(map[nextX][nextY]==-1){
                    count[1]+=1;
                }
            }
        }
        return count;
    }
    static boolean borderCheck (int x, int y){
        if(x>=0 && x< map.length && y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static class EachSeat implements Comparable<EachSeat> {
        int xCoor;
        int yCoor;
        int nearStudent;
        int nearEmpty;

        @Override
        public int compareTo(EachSeat o) {
            if(o.nearStudent == this.nearStudent){
                if(o.nearEmpty == this.nearEmpty){
                    if(o.xCoor == this.xCoor){
                        return this.yCoor - o.yCoor;
                    }
                    return this.xCoor - o.xCoor;
                }
                return o.nearEmpty - this.nearEmpty;
            }
            return o.nearStudent - this.nearStudent;
        }
    }
}
