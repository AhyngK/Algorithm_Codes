import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // pipe rotation 0:right / 1:downRight / 2: down
    static int status = 0;
    static int count = 0;

    public static void main(String[] args) {

        // input
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        sc.nextLine();

        int[][] house = new int[size][size];
        for (int i = 0; i < house.length; i++) {
            house[i] = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        recursion(0,1,house);
        System.out.println(count);
    }
    static void recursion(int i, int j, int[][] house){
        if(i==house.length-1 && j==house[i].length-1){
            count++;
            return;
        }

        int currentStatus = status;
        // direction rignt : can move(right, downright)
        if(status == 0){
            if(rightCheck(i,j,house)){
                status = 0;
                recursion(i,j+1,house);
                status = currentStatus;
            }
            if(downRightCheck(i,j,house)){
                status = 1;
                recursion(i+1,j+1,house);
                status = currentStatus;
            }
        }

        // direction downRight : can move(right, down, downRight)
        if(status==1){
            if(rightCheck(i,j,house)){
                status = 0;
                recursion(i,j+1,house);
                status = currentStatus;
            }
            if(downCheck(i,j,house)){
                status = 2;
                recursion(i+1,j,house);
                status = currentStatus;
            }
            if(downRightCheck(i,j,house)){
                status = 1;
                recursion(i+1,j+1,house);
                status = currentStatus;
            }
        }

        // direction down : can move(down, downright)
        if(status==2){
            if(downCheck(i,j,house)){
                status = 2;
                recursion(i+1,j,house);
                status = currentStatus;
            }
            if(downRightCheck(i,j,house)){
                status = 1;
                recursion(i+1,j+1,house);
                status = currentStatus;
            }
        }
    }
    static boolean rightCheck(int i, int j, int[][] house){
        // 맨 끝이 아니고, 벽이 아닐때
        if((j+1<house[i].length) && house[i][j+1]!=1){
            return true;
        }
        else {
            return false;
        }
    }
    static boolean downRightCheck(int i, int j, int[][] house){
        if(j+1>=house[i].length || i+1>=house.length){
            return false;
        }
        if(house[i][j+1]==1 || house[i+1][j+1]==1 || house[i+1][j]==1){
            return false;
        }
        return true;
    }
    static boolean downCheck(int i, int j, int[][] house){
        if(i+1>=house.length){
            return false;
        }
        if(house[i+1][j]==1){
            return false;
        }
        return true;
    }
}
