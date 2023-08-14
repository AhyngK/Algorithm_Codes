import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int columns = Integer.parseInt(st.nextToken());

        int[][] array = new int[rows][columns];
        for (int i = 0; i < array.length; i++) {
            array[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()){
         int current = Integer.parseInt(st.nextToken());
            switch (current){
                case 1: array = upDownReverse(array); break;
                case 2: array = leftRightReverse(array); break;
                case 3: array = rotateRight(array); break;
                case 4: array = rotateLeft(array); break;
                case 5: array = divideClockwise(array); break;
                case 6: array = divideCounterClock(array); break;
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println();
        }
    }
    static int[][] upDownReverse(int[][] array){
        int[][] temp = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            temp[array.length-1-i] = array[i].clone();
        }
        return temp;
    }
    static int[][] leftRightReverse (int[][] array){
        int[][] temp = new int[array.length][array[0].length];
        for (int j = 0; j < array[0].length; j++) {
            for (int i = 0; i < array.length; i++) {
                temp[i][array[0].length-1-j] = array[i][j];
            }
        }
        return temp;
    }
    static int[][] rotateRight(int[][] array){
        int[][] temp = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                temp[j][array.length-1-i] = array[i][j];
            }
        }
        return temp;
    }
    static int[][] rotateLeft(int[][] array){
        int[][] temp = new int[array[0].length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                temp[array[0].length-1-j][i] = array[i][j];
            }
        }
        return temp;
    }
    static int[][] divideClockwise(int[][] array){
        int[][] temp = new int[array.length][array[0].length];
        // left up
        for (int i = 0; i < array.length/2; i++) {
            for (int j = 0; j < array[0].length/2; j++) {
                temp[i][j+array[0].length/2] = array[i][j];
            }
        }
        // right up
        for (int i = 0; i < array.length/2; i++) {
            for (int j = array[0].length/2; j < array[0].length; j++) {
                temp[i+array.length/2][j] = array[i][j];
            }
        }
        // left down
        for (int i = array.length/2; i < array.length; i++) {
            for (int j = 0; j < array[0].length/2; j++) {
                temp[i-array.length/2][j] = array[i][j];
            }
        }
        // right down
        for (int i = array.length/2; i < array.length; i++) {
            for (int j = array[0].length/2; j < array[0].length; j++) {
                temp[i][j-array[0].length/2] = array[i][j];
            }
        }
        return temp;
    }
    static int[][] divideCounterClock(int[][] array){
        int[][] temp = new int[array.length][array[0].length];
        // left up
        for (int i = 0; i < array.length/2; i++) {
            for (int j = 0; j < array[0].length/2; j++) {
                temp[i+ array.length/2][j] = array[i][j];
            }
        }
        // right up
        for (int i = 0; i < array.length/2; i++) {
            for (int j = array[0].length/2; j < array[0].length; j++) {
                temp[i][j-array[0].length/2] = array[i][j];
            }
        }
        // left down
        for (int i = array.length/2; i < array.length; i++) {
            for (int j = 0; j < array[0].length/2; j++) {
                temp[i][j+array[0].length/2] = array[i][j];
            }
        }
        // right down
        for (int i = array.length/2; i < array.length; i++) {
            for (int j = array[0].length/2; j < array[0].length; j++) {
                temp[i- array.length/2][j] = array[i][j];
            }
        }
        return temp;
    }
}
