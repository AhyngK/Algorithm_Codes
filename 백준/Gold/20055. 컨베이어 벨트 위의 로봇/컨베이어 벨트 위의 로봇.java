import java.io.*;
import java.util.*;

public class Main {
    static int[] conveyor;
    static int[] robots;
    static List<Integer> currentRobots = new ArrayList<>();
    static int start = 0;
    static int dropOffPoint = 0;
    static int count = 0;
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        maxCount = Integer.parseInt(st.nextToken());
        conveyor = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        robots = new int[conveyor.length];
        dropOffPoint = size-1;

        int process = 0;
        while (true){
            process+=1;
            moveConveyor();
            moveRobots();
            if(count>=maxCount){
                break;
            }
        }
        System.out.println(process);
    }
    static void moveConveyor(){
        dropRobot();
        start = start-1 < 0 ? conveyor.length-1 : start-1;
        dropOffPoint = dropOffPoint-1 < 0 ? conveyor.length-1 : dropOffPoint-1;
        dropRobot();
    }
    static void moveRobots(){
        int size = currentRobots.size();
        for (int i = 0; i <size; i++) {
            if(currentRobots.get(i)+1 == conveyor.length && conveyor[0] > 0 && robots[0] == 0){
                robots[0] = 1;
                robots[currentRobots.get(i)] = 0;
                conveyor[0] -= 1;
                if(conveyor[0] == 0){
                    count++;
                }
                currentRobots.set(i,0);
            }
            else if(currentRobots.get(i)+1 < conveyor.length && conveyor[currentRobots.get(i)+1] > 0 && robots[currentRobots.get(i)+1] == 0){
                robots[currentRobots.get(i)+1] = 1;
                robots[currentRobots.get(i)] = 0;
                conveyor[currentRobots.get(i)+1] -= 1;
                if(conveyor[currentRobots.get(i)+1] == 0){
                    count++;
                }
                currentRobots.set(i,currentRobots.get(i)+1);
            }
        }
        if(conveyor[start] > 0 && robots[start] == 0) {
            currentRobots.add(start);
            robots[start] = 1;
            conveyor[start] -= 1;
            if (conveyor[start] == 0) {
                count++;
            }
        }
    }
    static void dropRobot(){
        if(robots[dropOffPoint] == 1){
            int idx = currentRobots.indexOf(dropOffPoint);
            currentRobots.remove(idx);
        }
        robots[dropOffPoint] = 0;
    }
}