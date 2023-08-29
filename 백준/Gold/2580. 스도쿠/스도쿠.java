import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] sdoku;
    static List<int[]> emptyCoors;
    static boolean endCheck = false;
    static int[][] diagonDir = {{1,1},{1,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sdoku = new int[9][9];
        for (int i = 0; i < sdoku.length; i++) {
            sdoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // Find
        emptyCoors = findCoordinates();
        selection(0);
    }
    static void selection(int index){
        if(endCheck){
            return;
        }
        if(index== emptyCoors.size()){
            for (int i = 0; i < sdoku.length; i++) {
                for (int j = 0; j < sdoku.length; j++) {
                    System.out.print(sdoku[i][j]+" ");
                }
                System.out.println();
            }
            endCheck = true;
            return;
        }

        int x = emptyCoors.get(index)[0];
        int y = emptyCoors.get(index)[1];

        for (int i = 1; i < 10; i++) {
            int current = i;
            sdoku[x][y] = current;
            if(checkBox(x,y,current) && checkHorizon(x,y,current) && checkVertical(x,y,current)){
                selection(index+1);
            }
            sdoku[x][y] = 0;
        }
    }

    static boolean checkBox(int x, int y, int num){
        boolean check = true;
        int[] count = new int[10];
        int startX = x/3*3;
        int startY = y/3*3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                count[sdoku[startX+i][startY+j]] +=1;
            }
        }
        for (int i = 1; i < count.length; i++) {
            if(count[i]>1){
                check = false;
            }
        }
        return check;
    }
    static boolean checkHorizon(int x, int y, int num){
        boolean check = true;
        int[] count = new int[10];
        for (int i = 0; i < sdoku[0].length; i++) {
           count[sdoku[x][i]]+=1;
        }
        for (int i = 1; i < count.length; i++) {
            if(count[i]>1){
                check = false;
            }
        }
        return check;
    }
    static boolean checkVertical(int x, int y, int num){
        boolean check = true;
        int[] count = new int[10];
        for (int i = 0; i < sdoku.length; i++) {
            count[sdoku[i][y]] += 1;
        }
        for (int i = 1; i < count.length; i++) {
            if(count[i]>1){
                check = false;
            }
        }
        return check;
    }
    static List<int[]> findCoordinates(){
        List<int[]> empty = new ArrayList<>();
        for (int i = 0; i < sdoku.length; i++) {
            for (int j = 0; j < sdoku[0].length; j++) {
                if(sdoku[i][j]==0){
                    empty.add(new int[] {i,j});
                }
            }
        }
        return empty;
    }
}
